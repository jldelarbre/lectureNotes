package lectureNotes.lesson3.rule2;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;

public class DesignFlaw1 {
    
    // Engine control unit
    interface ECU {
        double getCarbonDioxydeEmitted();
    }
    interface Engine {
        ECU getEcu();
    }
    
    static class PollutionTestBench {

        // Pass the wrong dependency
        int measurePollutionLevel(Engine engine) {
            int pollutionLevel = 0;
            
            // Do not really need the engine but only the embedded control unit
            // Lying API only the ECU is important to measure pollution level
            ECU ecu = engine.getEcu();
            double carbonDioxydeEmitted = ecu.getCarbonDioxydeEmitted();
            // Use carbonDioxydeEmitted...
            return pollutionLevel;
        }
    }
    
    // Production code
    public static void main(String[] args) {
        Engine engine = new Engine() {
            @Override
            public ECU getEcu() {
                return new ECU() {
                    @Override
                    public double getCarbonDioxydeEmitted() {
                        return 0;
                    }
                };
            }
        };
        
        PollutionTestBench pollutionTestBench = new PollutionTestBench();
        pollutionTestBench.measurePollutionLevel(engine);
    }
    
    public static class PollutionTestBenchTest {

        @Test
        public void test() {
            // Setup
            // -----
            
            // Hard to setup the test: need to mock useless engine
            // Introduce coupling with engine:
            // If tomorrow you change your architecture by moving the ECU from the inside of the engine
            // but you put it next to it, just like a component of the car at the same level.
            ECU fakeEcu = mock(ECU.class);
            Engine fakeEngine = mock(Engine.class);
            when(fakeEngine.getEcu()).thenReturn(fakeEcu);
            
            PollutionTestBench pollutionTestBench = new PollutionTestBench();
            
            // Exercise SUT (software under test)
            // ----------------------------------
            
            int actualPollutionLevel = pollutionTestBench.measurePollutionLevel(fakeEngine);
            
            // Verify
            // ------
            
            int expectedPollutionLevel = 42;
            Assert.assertEquals("...", expectedPollutionLevel, actualPollutionLevel);
        }
    }
}

package lectureNotes.lesson3.rule2;

import static org.mockito.Mockito.mock;

import org.junit.Assert;
import org.junit.Test;

public class FixedDesignFlaw1 {
    
    // Engine control unit
    interface ECU {
        double getCarbonDioxydeEmitted();
    }
    interface Engine {
        ECU getEcu();
    }
    
    static class PollutionTestBench {

        int measurePollutionLevel(ECU ecu) {
            int pollutionLevel = 0;
            
            // Directly use the necessary ecu
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
        ECU ecu = engine.getEcu();
        pollutionTestBench.measurePollutionLevel(ecu);
    }
    
    public static class PollutionTestBenchTest {

        @Test
        public void test() {
            // Setup
            // -----
            
            // Only fake necessary object, no coupling with application architecture
            // which leads to brittle tests
            // ECU could be part of the engine or the car directly it does not matter
            ECU fakeEcu = mock(ECU.class);
            
            PollutionTestBench pollutionTestBench = new PollutionTestBench();
            
            // Exercise SUT (software under test)
            // ----------------------------------
            
            int actualPollutionLevel = pollutionTestBench.measurePollutionLevel(fakeEcu);
            
            // Verify
            // ------
            
            int expectedPollutionLevel = 42;
            Assert.assertEquals("...", expectedPollutionLevel, actualPollutionLevel);
        }
    }
}

package lectureNotes.lesson3.rule2;

import static org.mockito.Mockito.mock;

import org.junit.Assert;
import org.junit.Test;

public class FixedDesignFlaw2 {
    
    interface OverTemperatureMonitor {
        boolean isOverTemperature();
    }
    // Engine control unit
    interface ECU {
        OverTemperatureMonitor getOverTemperatureMonitor();
    }
    interface Engine {
        ECU getEcu();
    }
    interface Car {
        Engine getEngine();
    }
    
    static class EngineTestStand {

        void monitorEngineOverTemperature(OverTemperatureMonitor overTemperatureMonitor) {
            while (true) {
                if (overTemperatureMonitor.isOverTemperature()) {
                    System.out.println("ALERT");
                }
            }
        }
    }
    
    public static class PollutionTestBenchTest {

        @Test
        public void test() {
            // Setup
            // -----
            
            // Test setup is very simple and test not fragile
            OverTemperatureMonitor fakeOverTemperatureMonitor = mock(OverTemperatureMonitor.class);
            
            EngineTestStand engineTestStand = new EngineTestStand();
            
            // Exercise SUT (software under test)
            // ----------------------------------
            
            engineTestStand.monitorEngineOverTemperature(fakeOverTemperatureMonitor);
            
            // Verify
            // ------
            
            boolean isAlertPrint = false; //...
            Assert.assertTrue("...", isAlertPrint);
        }
    }
}

package lectureNotes.lesson3.rule2;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;

public class DesignFlaw2 {
    
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

        // Chain of getter
        void monitorEngineOverTemperature(Car car) {
            OverTemperatureMonitor overTemperatureMonitor = car.getEngine().getEcu().getOverTemperatureMonitor();
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
            
            // Hard to setup the test: need to mock lot of useless elements
            // Introduce coupling with many unrelated classes
            OverTemperatureMonitor fakeOverTemperatureMonitor = mock(OverTemperatureMonitor.class);

            ECU fakeEcu = mock(ECU.class);
            when(fakeEcu.getOverTemperatureMonitor()).thenReturn(fakeOverTemperatureMonitor);
            
            Engine fakeEngine = mock(Engine.class);
            when(fakeEngine.getEcu()).thenReturn(fakeEcu);

            Car fakeCar = mock(Car.class);
            when(fakeCar.getEngine()).thenReturn(fakeEngine);
            
            EngineTestStand engineTestStand = new EngineTestStand();
            
            // Exercise SUT (software under test)
            // ----------------------------------
            
            engineTestStand.monitorEngineOverTemperature(fakeCar);
            
            // Verify
            // ------
            
            boolean isAlertPrint = false; //...
            Assert.assertTrue("...", isAlertPrint);
        }
    }
}

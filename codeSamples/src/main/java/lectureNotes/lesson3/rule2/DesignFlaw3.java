package lectureNotes.lesson3.rule2;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;

public class DesignFlaw3 {
    
    // Context object which is just a bunch of many unrelated things
    interface CarContext {
        double getCoolantTemerature();
        String getGearBoxPosition();
        double getEgrPosition();
        double getRemainingFuelTankCapacity();
        double getEngineRpm();
        double getGazolineInjectedPerCycle();
        Boolean isLeftFlasherOn();
        Boolean isRightFlasherOn();
        Boolean isWarningOn();
        Boolean isHeadlightOn();
        String getAirConditionerState();
        // ...
    }
    
    static class ConsumptionEstimator {

        double computeConsumption(CarContext carContext) {
            return carContext.getEngineRpm() * carContext.getGazolineInjectedPerCycle();
        }
    }
    
    public static class ConsumptionEstimatorTest {

        @Test
        public void test() {
            // Setup
            // -----
            
            // Hard to setup the test: Many unrelated things in CarContext for our purpose,
            // introduce coupling with all of them
            // Difficult to estimate what is really needed for the test
            CarContext fakeCarContext = mock(CarContext.class);

            // Uselessly mocked method
            when(fakeCarContext.getRemainingFuelTankCapacity()).thenReturn(34.0);
            
            when(fakeCarContext.getEngineRpm()).thenReturn(2500.0);
            // Forgotten mocked method:
            // when(fakeCarContext.getGazolineInjectedPerCycle()).thenReturn(32.0);
            
            ConsumptionEstimator consumptionEstimator = new ConsumptionEstimator();
            
            // Exercise SUT (software under test)
            // ----------------------------------
            
            double actualConsumption = consumptionEstimator.computeConsumption(fakeCarContext);
            
            // Verify
            // ------
            
            double expectedConsumption = 5.0;
            Assert.assertEquals("...", expectedConsumption, actualConsumption, 0.1);
        }
    }
}

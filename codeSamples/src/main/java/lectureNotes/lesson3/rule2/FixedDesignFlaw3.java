package lectureNotes.lesson3.rule2;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;

public class FixedDesignFlaw3 {
    
    // Use ISP (interface segregation principle)
    // To define many focused abstractions
    interface SomeCU {
        String getGearBoxPosition();
        double getRemainingFuelTankCapacity();
    }
    interface EngineCU {
        double getCoolantTemerature();
        double getEgrPosition();
        double getEngineRpm();
        double getGazolineInjectedPerCycle();
    }
    interface LightCU {
        Boolean isLeftFlasherOn();
        Boolean isRightFlasherOn();
        Boolean isWarningOn();
        Boolean isHeadlightOn();
    }
    interface AirConditionerCU {
        String getAirConditionerState();
    }
    
    // It's possible to have all the abstraction implemented in a same class if needed
    static class MainEcu implements SomeCU, EngineCU, LightCU, AirConditionerCU {
        @Override public String getAirConditionerState() { return "..."; }
        @Override public Boolean isLeftFlasherOn() { return true; }
        @Override public Boolean isRightFlasherOn() { return null; }
        @Override public Boolean isWarningOn() { return null; }
        @Override public Boolean isHeadlightOn() { return null; }
        @Override public double getCoolantTemerature() { return 0; }
        @Override public double getEgrPosition() { return 0; }
        @Override public double getEngineRpm() { return 0; }
        @Override public double getGazolineInjectedPerCycle() { return 0; }
        @Override public String getGearBoxPosition() { return "..."; }
        @Override public double getRemainingFuelTankCapacity() { return 0; }
    }
    
    static class ConsumptionEstimator {

        // 1st solution: Use ISP to pass a suitable abstraction
        double computeConsumption(EngineCU engineCU) {
            return engineCU.getEngineRpm() * engineCU.getGazolineInjectedPerCycle();
        }
        
        // 2nd solution: Pass only what is needed (better)
        double computeConsumption(double engineRpm, double gazolineInjectedPerCycle) {
            return engineRpm * gazolineInjectedPerCycle;
        }
    }
    
    public static class ConsumptionEstimatorTest {

        @Test
        public void test() {
            // Setup
            // -----
            
            // Easy to setup test: only few elements to cope with
            EngineCU fakeEngineCU = mock(EngineCU.class);

            when(fakeEngineCU.getEngineRpm()).thenReturn(2500.0);
            when(fakeEngineCU.getGazolineInjectedPerCycle()).thenReturn(32.0);
            
            ConsumptionEstimator consumptionEstimator = new ConsumptionEstimator();
            
            // Exercise SUT (software under test)
            // ----------------------------------
            
            double actualConsumption = consumptionEstimator.computeConsumption(fakeEngineCU);
            double actualConsumption2 = consumptionEstimator.computeConsumption(2500.0, 32.0);
            
            // Verify
            // ------
            
            double expectedConsumption = 5.0;
            Assert.assertEquals("...", expectedConsumption, actualConsumption, 0.1);
            Assert.assertEquals("...", expectedConsumption, actualConsumption2, 0.1);
        }
    }
}

package lectureNotes.lesson3.testStrategy;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class TestDesignFlaw1 {

    static class ECU {
        
        private final PressureLossThroughFuelFilterCalculator pressureLossThroughFuelFilterCalculator;
        
        // Some ECU parameters
        double pressureBeforeFilter = 5.0; // Updated at ECU startup
        double pressureAfterFilter = 4.9; // Updated at ECU startup
        double nominalFuelPumpThroughput = 10.3; // Updated at ECU startup
        double currentFuelPumpThroughput = 10.2; // Updated at ECU startup
        
        public static ECU build() {
            PressureLossThroughFuelFilterCalculator pressureLossThroughFuelFilterCalculator =
                new PressureLossThroughFuelFilterCalculator();
            return new ECU(pressureLossThroughFuelFilterCalculator);
        }
        
        public ECU(PressureLossThroughFuelFilterCalculator pressureLossThroughFuelFilterCalculator) {
            this.pressureLossThroughFuelFilterCalculator = pressureLossThroughFuelFilterCalculator;
        }

        double getFuelFilterDirtinessPercent() {
            double fuelFilterDirtiness = 0.0;
            // ...
            double fuelPressureDifference =
                pressureLossThroughFuelFilterCalculator.getFuelPressureDifference(pressureBeforeFilter, pressureAfterFilter);
            // Compute fuel filter dirtiness using fuelPressureDifference
            // ...
            return fuelFilterDirtiness;
        }
        
        double computeFuelQuantityToInject(int throttlePosition) {
            double fuelQuantityToInject = 0;
            // Compute fuel quantity to inject
            // ...
            return fuelQuantityToInject;
        }
    }
    
    // Utilitary class only used by ECU for getFuelFilterDirtinessPercent
    // It is an implementation detail
    static class PressureLossThroughFuelFilterCalculator {
        
        double getFuelPressureDifference(double pressureBeforeFilter, double pressureAfterFilter) {
            // Compute in some specific way pressure difference
            return pressureBeforeFilter - pressureAfterFilter;
        }
    }
    
    static class ECUTest {
        @Test
        void computeFuelQuantityToInjectTest() {
            // Setup
            // -----
            
            int throttlePosition = 5;
            
            ECU ecu = new ECU(null);
            
            // Exercise SUT
            // ------------
            
            double actualComputedFuelQuantityToInject = ecu.computeFuelQuantityToInject(throttlePosition);
            
            // Verify
            // ------
            
            double expectedComputedFuelQuantityToInject = 12;
            assertEquals("...", expectedComputedFuelQuantityToInject, actualComputedFuelQuantityToInject, 0.1);
        }
        
        @Test
        void getFuelFilterDirtinessPercentTest() {
            // Setup
            // -----
            
            PressureLossThroughFuelFilterCalculator pressureLossThroughFuelFilterCalculatorMock =
                mock(PressureLossThroughFuelFilterCalculator.class);
            doReturn(0.1).when(pressureLossThroughFuelFilterCalculatorMock).getFuelPressureDifference(5.0, 4.9);
            
            ECU ecu = new ECU(pressureLossThroughFuelFilterCalculatorMock);
            
            // Exercise SUT
            // ------------
            
            double actualFuelFilterDirtinessPercent = ecu.getFuelFilterDirtinessPercent();
            
            // Verify
            // ------
            
            double expectedFuelFilterDirtinessPercent = 6;
            assertEquals("...", expectedFuelFilterDirtinessPercent, actualFuelFilterDirtinessPercent, 0.1);
        }
    }
    
    // With the "one to one correspondence" between tests and classes we have a high coupling
    // even on implementation detail
    static class PressureLossThroughFuelFilterCalculatorTest {
        @Test
        void getTotalFilteredFuelAmountTest() {
            // Setup
            // -----
            
            PressureLossThroughFuelFilterCalculator pressureLossThroughFuelFilterCalculator =
                new PressureLossThroughFuelFilterCalculator();
            
            // Exercise SUT
            // ------------
            
            double pressureBeforeFilter = 5.0;
            double pressureAfterFilter = 4.9;
            double actualFuelPressureDifference =
                pressureLossThroughFuelFilterCalculator.getFuelPressureDifference(pressureBeforeFilter, pressureAfterFilter);
            
            // Verify
            // ------
            double expectedFuelPressureDifference = 0.1;
            assertEquals("...", expectedFuelPressureDifference, actualFuelPressureDifference, 0.01);
        }
    }
}

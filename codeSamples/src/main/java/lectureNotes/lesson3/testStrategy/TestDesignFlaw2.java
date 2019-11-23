package lectureNotes.lesson3.testStrategy;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class TestDesignFlaw2 {
    
    // Same as TestDesignStrategy1 but production code has evolved
    // ECU.getFuelFilterDirtinessPercent no longer use the private utilitary class
    // PressureLossThroughFuelFilterCalculator but ThroughputLossThroughFuelFilterCalculator
    // Dirtiness now is evaluated using fuel pump throughput loss

    static class ECU {
        
        private final ThroughputLossThroughFuelFilterCalculator throughputLossThroughFuelFilterCalculator;
        
        // Some ECU parameters
        double pressureBeforeFilter = 5.0; // Updated at ECU startup
        double pressureAfterFilter = 4.9; // Updated at ECU startup
        double nominalFuelPumpThroughput = 10.3; // Updated at ECU startup
        double currentFuelPumpThroughput = 10.2; // Updated at ECU startup
        
        public static ECU build() {
            ThroughputLossThroughFuelFilterCalculator throughputLossThroughFuelFilterCalculator =
                new ThroughputLossThroughFuelFilterCalculator();
            return new ECU(throughputLossThroughFuelFilterCalculator);
        }
        
        public ECU(ThroughputLossThroughFuelFilterCalculator throughputLossThroughFuelFilterCalculator) {
            this.throughputLossThroughFuelFilterCalculator = throughputLossThroughFuelFilterCalculator;
        }

        double getFuelFilterDirtinessPercent() {
            double fuelFilterDirtiness = 0.0;
            // ...
            double fuelThroughputDifference =
                throughputLossThroughFuelFilterCalculator.getFuelThroughputDifference(pressureBeforeFilter, pressureAfterFilter);
            // Compute fuel filter dirtiness using fuelThroughputDifference
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
    // It is still an implementation detail: business intelligence has changed
    // We prefer to use throughput instead of pressure
    static class ThroughputLossThroughFuelFilterCalculator {
        
        double getFuelThroughputDifference(double throughputBeforeFilter, double throughputAfterFilter) {
            // Compute in some specific way throughput difference
            return throughputBeforeFilter - throughputAfterFilter;
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
            
            // Here we are forced to update this test setup in order to create new mock
            
            ThroughputLossThroughFuelFilterCalculator throughputLossThroughFuelFilterCalculatorMock =
                mock(ThroughputLossThroughFuelFilterCalculator.class);
            doReturn(0.1).when(throughputLossThroughFuelFilterCalculatorMock).getFuelThroughputDifference(10.3, 10.2);
            
            ECU ecu = new ECU(throughputLossThroughFuelFilterCalculatorMock);
            
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
    // We are forced to erase the previous test "PressureLossThroughFuelFilterCalculatorTest"
    // And rewrite this new one
    static class ThroughputLossThroughFuelFilterCalculatorTest {
        @Test
        void getTotalFilteredFuelAmountTest() {
            // Setup
            // -----
            
            ThroughputLossThroughFuelFilterCalculator throughputLossThroughFuelFilterCalculator =
                new ThroughputLossThroughFuelFilterCalculator();
            
            // Exercise SUT
            // ------------
            
            double throughputBeforeFilter = 10.3;
            double throughputAfterFilter = 10.2;
            double actualFuelThroughputDifference =
                throughputLossThroughFuelFilterCalculator.getFuelThroughputDifference(throughputBeforeFilter, throughputAfterFilter);
            
            // Verify
            // ------
            double expectedFuelThroughputDifference = 0.1;
            assertEquals("...", expectedFuelThroughputDifference, actualFuelThroughputDifference, 0.01);
        }
    }
}

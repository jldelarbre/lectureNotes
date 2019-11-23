package lectureNotes.lesson3.testStrategy;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class FixedTestDesignFlaw4 {
    
    // Here client and service are based on a defined API
    
    // Client
    static class PolutionController {
        
        private final IECU ecu;
        private final IEcuForMaintenance ecuForMaintenance;
        
        public PolutionController(IECU ecu, IEcuForMaintenance ecuForMaintenance) {
            this.ecu = ecu;
            this.ecuForMaintenance = ecuForMaintenance;
        }
        
        double measurePollutionLevel(int throttlePosition) {
            double pollutionLevel = 0.0;
            // ...
            double injectedFuelQuantity = ecu.computeFuelQuantityToInject(throttlePosition);
            double fuelFilterDirtinessPercent = ecuForMaintenance.getFuelFilterDirtinessPercent();
            // Compute pollution level using injectedFuelQuantity and fuelFilterDirtinessPercent
            // ...
            return pollutionLevel;
        }
    }
    
    // Services API
    static interface IEcuForMaintenance {
        double getFuelFilterDirtinessPercent();
    }
    
    static interface IECU {
        double computeFuelQuantityToInject(int throttlePosition);
    }
    
    // Service implementation: first implementation, often at the beginning of an implementation
    // it is hard to split different responsibilities and it is easier to have things together.
    // But interface segregation principle says that the API has to be clean from the beginning
    static class EcuAllInOne implements IECU, IEcuForMaintenance {
        
        @Override
        public double getFuelFilterDirtinessPercent() {
            double fuelFilterDirtiness = 0.0;
            // ... Compute
            return fuelFilterDirtiness;
        }
        
        @Override
        public double computeFuelQuantityToInject(int throttlePosition) {
            double fuelQuantityToInject = 0;
            // Compute fuel quantity to inject
            // ...
            return fuelQuantityToInject;
        }
    }

    // Service implementation: second implementation when code has evolved and different
    // responsibilities have been split at implementation level
    static class EcuForMaintenance implements IEcuForMaintenance {
        
        @Override
        public double getFuelFilterDirtinessPercent() {
            double fuelFilterDirtiness = 0.0;
            // ... Compute
            return fuelFilterDirtiness;
        }
    }
        
    static class ECU implements IECU {
        
        @Override
        public double computeFuelQuantityToInject(int throttlePosition) {
            double fuelQuantityToInject = 0;
            // Compute fuel quantity to inject
            // ...
            return fuelQuantityToInject;
        }
    }
    
    static class PolutionControllerTest {
        
        // Implementation of this test remains the same for both implementation
        @Test
        void measurePollutionLevelTest() {
            // Setup
            // -----
            
            int throttlePosition = 5;
            
            IECU ecuMock = mock(IECU.class);
            IEcuForMaintenance ecuForMaintenanceMock = mock(IEcuForMaintenance.class);

            doReturn(8.0).when(ecuMock).computeFuelQuantityToInject(throttlePosition);
            doReturn(3.5).when(ecuForMaintenanceMock).getFuelFilterDirtinessPercent();
            
            PolutionController polutionController = new PolutionController(ecuMock, ecuForMaintenanceMock);
            
            // Exercise SUT
            // ------------
            
            double actualPollutionLevel = polutionController.measurePollutionLevel(throttlePosition);
            
            // Verify
            // ------
            
            double expectedPollutionLevel = 7;
            assertEquals("...", expectedPollutionLevel, actualPollutionLevel, 0.1);
        }
    }
}

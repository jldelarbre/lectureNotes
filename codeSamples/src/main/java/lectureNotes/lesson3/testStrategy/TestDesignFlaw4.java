package lectureNotes.lesson3.testStrategy;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class TestDesignFlaw4 {
    
    // Same as TestDesignFlaw3 but the service ECU is refactored into 2 parts
    // It leads to refactor the tests also
    
    // Client
    static class PolutionController {
        
        private final ECU ecu;
        private final EcuForMaintenance ecuForMaintenance;
        
        public PolutionController(ECU ecu, EcuForMaintenance ecuForMaintenance) {
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

    // Services
    static class EcuForMaintenance {
        
        double getFuelFilterDirtinessPercent() {
            double fuelFilterDirtiness = 0.0;
            // ... Compute
            return fuelFilterDirtiness;
        }
    }
        
    static class ECU {
        
        double computeFuelQuantityToInject(int throttlePosition) {
            double fuelQuantityToInject = 0;
            // Compute fuel quantity to inject
            // ...
            return fuelQuantityToInject;
        }
    }
    
    static class PolutionControllerTest {
        @Test
        void measurePollutionLevelTest() {
            // Setup
            // -----
            
            int throttlePosition = 5;
            
            ECU ecuMock = mock(ECU.class);
            EcuForMaintenance ecuForMaintenanceMock = mock(EcuForMaintenance.class);

            // Refactoring of ECU service involve changes in tests. Here in mock setting
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

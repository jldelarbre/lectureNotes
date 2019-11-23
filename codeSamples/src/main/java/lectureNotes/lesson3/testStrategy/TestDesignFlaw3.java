package lectureNotes.lesson3.testStrategy;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class TestDesignFlaw3 {
    
    // A sample of a client and a service coupled together without API
    // Since tests are also another client for the service they are coupled too
    
    // Client
    static class PolutionController {
        
        private final ECU ecu;
        
        public PolutionController(ECU ecu) {
            this.ecu = ecu;
        }
        
        double measurePollutionLevel(int throttlePosition) {
            double pollutionLevel = 0.0;
            // ...
            double injectedFuelQuantity = ecu.computeFuelQuantityToInject(throttlePosition);
            double fuelFilterDirtinessPercent = ecu.getFuelFilterDirtinessPercent();
            // Compute pollution level using injectedFuelQuantity and fuelFilterDirtinessPercent
            // ...
            return pollutionLevel;
        }
    }

    // Service
    static class ECU {
        
        double getFuelFilterDirtinessPercent() {
            double fuelFilterDirtiness = 0.0;
            // ... Compute
            return fuelFilterDirtiness;
        }
        
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
            doReturn(3.5).when(ecuMock).getFuelFilterDirtinessPercent();
            doReturn(8.0).when(ecuMock).computeFuelQuantityToInject(throttlePosition);
            
            PolutionController polutionController = new PolutionController(ecuMock);
            
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

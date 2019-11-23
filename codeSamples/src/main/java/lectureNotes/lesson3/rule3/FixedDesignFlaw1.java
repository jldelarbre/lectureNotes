package lectureNotes.lesson3.rule3;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class FixedDesignFlaw1 {
    
    // Engine control unit
    static class ECU {
        public static final String ACTIVATE_FAN_UNIT = "Activate Fan unit";
        public static final String ACTIVATE_EGR = "Activate EGR";

        private final BSI bsi;
        
        public ECU(BSI bsi) {
            this.bsi = bsi;
        }

        boolean dispatchEngineParam(String engineParam) {
            boolean everyCommandProcessed = true;
            // ...
            if (engineParam.equals("Engine too hot")) {
                String answer = bsi.sendMessage("CoolingSystemId", ACTIVATE_FAN_UNIT);
                // process answer
                everyCommandProcessed &= answer.equals(BSI.NO_ACTION_PERFORMED);
            }
            if (engineParam.equals("EGR filter full")) {
                String answer = bsi.sendMessage("EGRSystemId", ACTIVATE_EGR);
                // process answer
                everyCommandProcessed &= answer.equals(BSI.NO_ACTION_PERFORMED);
            }
            // ...
            return everyCommandProcessed;
        }
    }
    
    // It is still valid to want an unique instance for BSI, using singleton for this is not a good
    // option.
    // The wiring part has to manage that:
    // Factories or dependency injection framework
    static class BSI {
        public static final String NO_ACTION_PERFORMED = "No action performed";

        // With a constructor with visibility package, we almost only allow the BSI factory
        // to instantiate BSI class
        BSI() {
        }
        
        String sendMessage(String ecuId, String message) {
            // ...
            if (message.equals(ECU.ACTIVATE_FAN_UNIT)) {
                // Prevent over heating of engine since generate heat (may be an unrealistic
                // automotive behavior: it's just a sample)
                inhibitEGRMessage();
                return "Fan activated";
                // ...
            } else if (message.equals(ECU.ACTIVATE_EGR) && !isInhibitedEGRMessage()) {
                return "EGR Activated";
            }
            // ...
            return NO_ACTION_PERFORMED;
        }

        private void inhibitEGRMessage() { // ...
        }
        private boolean isInhibitedEGRMessage() { // ...
            return true; // or false
        }
    }
    
    // BSI factory manages to create only BSI instance
    public static class BSIFactory {
        private static BSI bsi;
        
        public BSI build() {
            if (bsi == null) {
                bsi = new BSI();
            }
            return bsi;
        }
    }
    
    public static class ECUTest {

        @Test
        public void testActivateFanUnit() {
            // Setup
            // -----
            
            BSI fakableBsi = mock(BSI.class);
            
            ECU ecu = new ECU(fakableBsi);
            
            // Exercise SUT (software under test)
            // ----------------------------------
            
            // A side effect is produced by this tests in BSI: EGR message are inhibited
            // But it does not matter since tests are run in isolation and current instance of BSI
            // will be garbage collected
            boolean actualEveryCommandProcessed = ecu.dispatchEngineParam("Engine too hot");
            
            // Verify
            // ------
            
            boolean expectedEveryCommandProcessed = true;
            assertEquals("...", expectedEveryCommandProcessed, actualEveryCommandProcessed);
        }
        
        @Test
        public void testActivateEGR() {
            // Setup
            // -----
            
            BSI fakableBsi = mock(BSI.class);
            
            ECU ecu = new ECU(fakableBsi);
            
            // Exercise SUT (software under test)
            // ----------------------------------
            
            // This test does not depend on the execution of the previous one, we have a fresh
            // instance of BSI. Tests are run in isolation
            boolean actualEveryCommandProcessed = ecu.dispatchEngineParam("EGR filter full");
            
            // Verify
            // ------
            
            // Test succeeds
            boolean expectedEveryCommandProcessed = true;
            assertEquals("...", expectedEveryCommandProcessed, actualEveryCommandProcessed);
        }
    }
}

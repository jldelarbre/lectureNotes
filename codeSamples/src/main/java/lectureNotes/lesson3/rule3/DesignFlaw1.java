package lectureNotes.lesson3.rule3;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;

public class DesignFlaw1 {
    
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
    
    // Built-in Systems Interface: heart of CAN bus in a car
    // Singleton is static global state
    static final class BSI {
        public static final String NO_ACTION_PERFORMED = "No action performed";
        
        private static BSI instance;
        
        private BSI() {}
        
        public static BSI getInstance() {
            // Not thread safe but does not matter here
            if (instance == null) {
                instance = new BSI();
            }
            return instance;
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
        
        // Bad practice: avoid to create special method for test or make method public only for tests
        void resetForTests() {
            // ...
        }
    }
    
    public static class ECUTest {

        @Test
        public void testActivateFanUnit() {
            // Setup
            // -----
            
            // BSI is a singleton global state. It's impossible to run tests in isolation
            // since singleton instance survives between tests. So singleton state is shared
            // between test.
            // We are forced to add unsuitable method to reset it.
            BSI notFakableBsi = BSI.getInstance();
            
            ECU ecu = new ECU(notFakableBsi);
            
            // Exercise SUT (software under test)
            // ----------------------------------
            
            // A side effect is produced by this tests in BSI: EGR message are inhibited
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
            
            BSI notFakableBsi = BSI.getInstance();
            
            ECU ecu = new ECU(notFakableBsi);
            
            // Exercise SUT (software under test)
            // ----------------------------------
            
            // This test depends on the execution of the previous one, BSI is not in initial state.
            // Test does not run in isolation
            boolean actualEveryCommandProcessed = ecu.dispatchEngineParam("EGR filter full");
            
            // Verify
            // ------
            
            // Test fails since EGR messages are inhibited unless BSI is reset
            boolean expectedEveryCommandProcessed = true;
            assertEquals("...", expectedEveryCommandProcessed, actualEveryCommandProcessed);
        }
        
        @After
        public void tearDown() {
            BSI.getInstance().resetForTests();
        }
    }
}

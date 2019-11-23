package lectureNotes.lesson3.rule1;

import org.junit.Assert;
import org.junit.Test;

public class DesignFlaw2 {
    
    static interface Engine {}
    static interface Brake {}
    
    public static double ignitionTiming(String engineParameters) {
        // Compute ignition timing
        return 3.14;
    }

    static class Car {
        Engine engine;
        Brake brake;

        public Car(Engine engine, Brake brake) {
            this.engine = engine;
            this.brake = brake;
        }

        int runWLTP() {
            int score = 0;
            // Direct call of static method
            double ignitionTiming = ignitionTiming("engineParameters");
            // Use break and engine and ignition timing...
            return score;
        }
    }
    
    public static class CarTest {

        @Test
        public void test() {
            // Setup
            // -----
            
            Engine fakeEngine = new Engine() {/* Test code: stub, mock... */};
            Brake fakeBrake = new Brake() {/* Test code: stub, mock... */};
            
            // Can test the code of car itself with fake dependencies
            Car testCar = new Car(fakeEngine, fakeBrake);
            
            // Exercise SUT (software under test)
            // ----------------------------------
            
            // The test does not run in isolation since failure could be caused by "ignitionTiming" method
            // Impossible to test the car against various ignition timing laws
            int actualScore = testCar.runWLTP();
            
            // Verify
            // ------
            
            int expectedScore = 42;
            Assert.assertEquals("...", expectedScore, actualScore);
        }
    }
}

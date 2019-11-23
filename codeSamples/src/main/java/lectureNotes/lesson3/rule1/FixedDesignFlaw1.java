package lectureNotes.lesson3.rule1;

import org.junit.Assert;
import org.junit.Test;

public class FixedDesignFlaw1 {
    
    static interface Engine {}
    static interface Brake {}

    static class Car {
        Engine engine;
        Brake brake;

        public Car(Engine engine, Brake brake) {
            this.engine = engine;
            this.brake = brake;
        }

        int runWLTP() {
            int score = 0;
            // Use break and engine...
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
            
            int actualScore = testCar.runWLTP(); // Test is the first case of code reuse !!!
            
            // Verify
            // ------
            
            int expectedScore = 42;
            Assert.assertEquals("...", expectedScore, actualScore);
        }
    }
}

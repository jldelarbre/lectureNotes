package lectureNotes.lesson3.rule1;

import org.junit.Assert;
import org.junit.Test;

public class FixedDesignFlaw3 {
    
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
        // Tests are independent and could be run in parallel

        @Test
        public void test() {
            // Setup
            // -----
            
            Engine fakeEngine = new Engine() {/* Test code: stub, mock... */};
            Brake fakeBrake = new Brake() {/* Test code: stub, mock... */};
            
            Car testCar = new Car(fakeEngine, fakeBrake);
            
            // Exercise SUT (software under test)
            // ----------------------------------
            
            int actualScore = testCar.runWLTP();
            
            // Verify
            // ------
            
            int expectedScore = 42;
            Assert.assertEquals("...", expectedScore, actualScore);
        }
        
        @Test
        public void test2() {
            // Setup
            // -----
            
            Engine fakeEngine2 = new Engine() {/* Other test code: stub, mock... */};
            Brake fakeBrake2 = new Brake() {/* Other test code: stub, mock... */};
            
            Car testCar = new Car(fakeEngine2, fakeBrake2);
            
            // Exercise SUT (software under test)
            // ----------------------------------
            
            int actualScore = testCar.runWLTP();
            
            // Verify
            // ------
            
            int expectedScore = 37;
            Assert.assertEquals("...", expectedScore, actualScore);
        }
    }
}

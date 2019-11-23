package lectureNotes.lesson3.rule1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class DesignFlaw3 {
    
    static interface Engine {}
    static interface Brake {}

    // Usage of static field
    static class Car {
        private static int score = 0;
        Engine engine;
        Brake brake;

        public Car(Engine engine, Brake brake) {
            this.engine = engine;
            this.brake = brake;
        }

        int runWLTP() {
            // Use break and engine...
            return score;
        }
    }
    
    public static class CarTest {
        // Due to static "score" field the tests are no longer independent
        // Need tear down between the two run test instances to clear "score" field
        
        // You do not have to forget tear down between tests
        // You cannot run parallel tests
        // You need to know implementation details to code the tests, API is not enough
        @After
        public void tearDown() {
            Car.score = 0;
        }

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

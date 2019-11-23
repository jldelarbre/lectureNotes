package lectureNotes.lesson3.rule1;

import org.junit.Test;

public class DesignFlaw1 {
    
    static interface Engine {}
    static interface Brake {}

    // Using "new" in constructor
    static class Car {
        Engine engine = new Engine() {/* Production code */};
        Brake brake;
        
        public Car() {
            this.brake = new Brake() {/* Production code */};
        }
        
        int runWLTP() {
            int score = 0;
            // Use break and engine....
            
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
            
            // Force to use real dependencies for tests, impossible to use faked one
            Car testCar = new Car();
        }
    }
}

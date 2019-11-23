package lectureNotes.lesson3.rule1;

import java.util.function.Function;

import org.junit.Assert;
import org.junit.Test;

public class FixedDesignFlaw2 {
    
    static interface Engine {}
    static interface Brake {}
    
    public static double ignitionTiming(String engineParameters) {
        // Compute ignition timing
        return 3.14;
    }
    
    static class EngineLaws {
        public double ignitionTiming(String engineParameters) {
            // Compute ignition timing
            return 3.14;
        }
    }

    static class Car {
        Engine engine;
        Brake brake;
        // First Injection Method
        EngineLaws engineLaws;
        // Second Injection Method
        Function<String, Double> ignitionTiming;

        public Car(Engine engine,
                   Brake brake,
                   EngineLaws engineLaws,
                   Function<String, Double> ignitionTiming) {
            this.engine = engine;
            this.brake = brake;
        }

        int runWLTP() {
            int score = 0;
            double ignitionTimingValue1 = engineLaws.ignitionTiming("engineParameters");
            // or
            double ignitionTimingValue2 = ignitionTiming.apply("engineParameters");
            
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
            EngineLaws engineLaws = new EngineLaws() {/* Test code: stub, mock... */};
            Function<String, Double> ignitionTiming = (engineParam) -> 2.0;
            
            // Can test the code of car itself with fake dependencies
            Car testCar = new Car(fakeEngine, fakeBrake, engineLaws, ignitionTiming);
            
            // Exercise SUT (software under test)
            // ----------------------------------
            
            // Could use predefined values for ignition timing
            // Could test car robustness against abnormal ignition timing values
            int actualScore = testCar.runWLTP();
            
            // Verify
            // ------
            
            int expectedScore = 42;
            Assert.assertEquals("...", expectedScore, actualScore);
        }
    }
}

package lectureNotes.lesson3.rule1;

import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class DesignFlaw4 {
    
    static interface Engine {
        double getPower();
    }
    static interface Brake {
        String[] brakeTypes = {"type1", "type2", "type3"};
        String defaultBrakeType = "defaultBrakeType";
        double getCompatiblePower();
    }
    
    static class Car {
        Engine engine;
        Brake brake;

        // Logic in constructor
        public Car(Engine engine, Map<String, Brake> brakeShelf) {
            // Constructor with logic
            this.engine = engine;
            for (String brakeType : Brake.brakeTypes) {
                Brake tmpBrake = brakeShelf.get(brakeType);
                if (Math.abs((engine.getPower() - tmpBrake.getCompatiblePower())) < 10.0) {
                    this.brake = tmpBrake;
                    break;
                }
            }
            if (this.brake == null) {
                this.brake = brakeShelf.get(Brake.defaultBrakeType);
            }
        }

        int runWLTP() {
            int score = 0;
            // Use break and engine...
            return score;
        }
    }
    
    public static class CarTest {

        @Test
        // Hard to write test
        public void test() {
            // Setup
            // -----
           
            // It's possible to inject everything but
            // If I want to test the car with default brake type, I need to build
            // useless brake of type 1, 2, 3 and to populate a map
            // Moreover it is very sensitive to code changes => fragile tests
            Engine fakeEngine = mock(Engine.class);
            Brake fakeBrakeType1 = mock(Brake.class);
            Brake fakeBrakeType2 = mock(Brake.class);
            Brake fakeBrakeType3 = mock(Brake.class);
            Brake fakeBrakeDefault = mock(Brake.class);
            
            Map<String, Brake> brakeShelf = new HashMap<>();
            brakeShelf.put(Brake.brakeTypes[0], fakeBrakeType1);
            brakeShelf.put(Brake.brakeTypes[1], fakeBrakeType2);
            brakeShelf.put(Brake.brakeTypes[2], fakeBrakeType3);
            brakeShelf.put(Brake.defaultBrakeType, fakeBrakeDefault);
            
            Car testCar = new Car(fakeEngine, brakeShelf);
            
            // Exercise SUT (software under test)
            // ----------------------------------
            
            int actualScore = testCar.runWLTP();
            
            // Verify
            // ------
            
            int expectedScore = 42;
            Assert.assertEquals("...", expectedScore, actualScore);
        }
    }
}

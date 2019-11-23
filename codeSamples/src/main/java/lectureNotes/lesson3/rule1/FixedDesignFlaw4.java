package lectureNotes.lesson3.rule1;

import static org.mockito.Mockito.mock;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class FixedDesignFlaw4 {
    
    static interface Engine {
        double getPower();
    }
    static interface Brake {
        String[] brakeTypes = {"type1", "type2", "type3"};
        String defaultBrakeType = "defaultBrakeType";
        double getCompatiblePower();
    }
    
    static class CarFactory {
       Car build(Engine engine, Map<String, Brake> brakeShelf) {
           Brake brake = null;
           for (String brakeType : Brake.brakeTypes) {
               Brake tmpBrake = brakeShelf.get(brakeType);
               if (Math.abs((engine.getPower() - tmpBrake.getCompatiblePower())) < 10.0) {
                   brake = tmpBrake;
                   break;
               }
           }
           if (brake == null) {
               brake = brakeShelf.get(Brake.defaultBrakeType);
           }
           
           return new Car(engine, brake);
       }
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
            // Use break and engine...
            return score;
        }
    }
    
    public static class CarTest {

        @Test
        public void test() {
            // Setup
            // -----
           
            // Tests are easy to write since we only need to build useful mocks
            // Responsibility for wiring is put on the factory
            Engine fakeEngine = mock(Engine.class);
            Brake fakeBrakeDefault = mock(Brake.class);
            
            Car testCar = new Car(fakeEngine, fakeBrakeDefault);
            
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

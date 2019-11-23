package lectureNotes.lesson3;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import lectureNotes.lesson3.TestAsDocumentation.ECU.InjectorCommand;

public class TestAsDocumentation {
    
    interface GlowPlug {
        void startHeat();
    }
    
    // This component has many methods and dependencies.
    // It is hard to understand its purpose and usage
    interface ECU {
        class InjectorCommand {
            double timing;
            double duration;
            double fuelQuantityPerCycle;
        }
        
        void setDeepColdMode();
        
        void setNormalMode();
        
        InjectorCommand computeUpdatedEngineCommand(int throttlePosition);
        
        interface Factory {
            ECU build(GlowPlug glowPlug);
        }
    }

    // The implementation does not really matter here and more over
    // we want to understand ECU without having to read the implementation
    // and having to bothered with irrelevant details
    static class EcuImpl implements ECU {
        private static final int IDLE_POSITION = 10;
        private final GlowPlug glowPlug;
        
        // false = normal mode
        private boolean isDeepColdMode = false;

        EcuImpl(GlowPlug glowPlug) {
            this.glowPlug = glowPlug;
        }
        
        public void setDeepColdMode() {
            isDeepColdMode = true;
        }
        
        public void setNormalMode() {
            isDeepColdMode = false;
        }
        
        public InjectorCommand computeUpdatedEngineCommand(int throttlePosition) {
            InjectorCommand injectorCommand = new InjectorCommand();
            double timing = 0;
            double duration = 0;
            double fuelQuantityPerCycle = 0;
            
            if (isDeepColdMode) {
                if (throttlePosition < IDLE_POSITION) {
                    glowPlug.startHeat();
                }
                // Compute injector command for deep cold
                // ...
                injectorCommand.timing = timing;
                injectorCommand.duration = duration;
                injectorCommand.fuelQuantityPerCycle = fuelQuantityPerCycle;
            } else {
                // Compute injector command for normal weather
                // ...
                injectorCommand.timing = timing;
                injectorCommand.duration = duration;
                injectorCommand.fuelQuantityPerCycle = fuelQuantityPerCycle;
            }
            injectorCommand.timing = timing;
            
            return injectorCommand;
        }
        
        static class Factory implements ECU.Factory {
            @Override
            public ECU build(GlowPlug glowPlug) {
                return new EcuImpl(glowPlug);
            }
        }
    }
    
    // Tests not only prevent for regression but also show usage and purpose
    // of a component: this is test as documentation
    // Each test focused on a simple use case
    static class ECUTest {
        
        @Test
        void testDeepColdCommandWithGlowPlugActivated() {
            // Given - When - then: with BDD style (feature available with mockito)
            
            // Setup
            // -----
            GlowPlug glowPlugMock = mock(GlowPlug.class);
            
            int throttlePosition = 9;
            
            ECU ecu = new EcuImpl.Factory().build(glowPlugMock);
            
            // Exercise SUT
            // ------------
            ecu.setDeepColdMode();
            InjectorCommand actualInjectorCommand = ecu.computeUpdatedEngineCommand(throttlePosition);
            
            // Verify
            // ------
            
            double expectedTiming = 0; //someValue
            double expectedDuration = 0; //someValue
            double expectedFuelQuantityPerCycle = 0; //someValue
            
            assertEquals("...", expectedTiming, actualInjectorCommand.timing, 0.1);
            assertEquals("...", expectedDuration, actualInjectorCommand.duration, 0.1);
            assertEquals("...", expectedFuelQuantityPerCycle, actualInjectorCommand.fuelQuantityPerCycle, 0.1);
            
            // Verify what is part of the contract of your element under test
            verify(glowPlugMock).startHeat();
        }
        
        @Test
        void testDeepColdCommandWithGlowPlugNotActivated() {
            // Given - When - then: with BDD style (feature available with mockito)
            
            // Setup
            // -----
            GlowPlug glowPlugMock = mock(GlowPlug.class);
            
            int throttlePosition = 10;
            
            ECU ecu = new EcuImpl.Factory().build(glowPlugMock);
            
            // Exercise SUT
            // ------------
            ecu.setDeepColdMode();
            InjectorCommand actualInjectorCommand = ecu.computeUpdatedEngineCommand(throttlePosition);
            
            // Verify
            // ------
            
            double expectedTiming = 0; //someValue
            double expectedDuration = 0; //someValue
            double expectedFuelQuantityPerCycle = 0; //someValue
            
            assertEquals("...", expectedTiming, actualInjectorCommand.timing, 0.1);
            assertEquals("...", expectedDuration, actualInjectorCommand.duration, 0.1);
            assertEquals("...", expectedFuelQuantityPerCycle, actualInjectorCommand.fuelQuantityPerCycle, 0.1);
            
            verify(glowPlugMock, never()).startHeat();
        }
        
        @Test
        void testNormalWeatherCommand() {
            // Given - When - then: with BDD style (feature available with mockito)
            
            // Setup
            // -----
            int throttlePosition = 7;
            
            ECU ecu = new EcuImpl.Factory().build(null);
            
            // Exercise SUT
            // ------------
            ecu.setNormalMode();
            InjectorCommand actualInjectorCommand = ecu.computeUpdatedEngineCommand(throttlePosition);
            
            // Verify
            // ------
            
            double expectedTiming = 0; //someValue
            double expectedDuration = 0; //someValue
            double expectedFuelQuantityPerCycle = 0; //someValue
            
            assertEquals("...", expectedTiming, actualInjectorCommand.timing, 0.1);
            assertEquals("...", expectedDuration, actualInjectorCommand.duration, 0.1);
            assertEquals("...", expectedFuelQuantityPerCycle, actualInjectorCommand.fuelQuantityPerCycle, 0.1);
        }
    }
}

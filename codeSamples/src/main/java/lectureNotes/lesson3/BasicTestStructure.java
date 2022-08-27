package lectureNotes.lesson3;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class BasicTestStructure {

    interface Dependency1 {
        String someMethod(String param);
    }
    // ...
    interface DependencyN {
        String otherMethod(String param);
    }
    
    static class ComponentToTest {
        private final Dependency1 dependency1;
        // ...
        private final DependencyN dependencyN;
        
        public ComponentToTest(Dependency1 dependency1, DependencyN dependencyN) {
            this.dependency1 = dependency1;
            this.dependencyN = dependencyN;
        }
        
        // Method that produces a result using a dependency which return a value
        String firstMethod(String input) {
            // ...
            String localVar = "computedValue_" + input;
            // ...
            String someResult = dependency1.someMethod(localVar);
            // ...
            String result = someResult + "_finalComputedValue";
            return result;
        }
        
        // Method that produces a result by side effect by calling a collaborator
        void secondMethod(String input) {
            // ...
            String localVar = "otherComputedValue_" + input;
            // ...
            String theResult = dependencyN.otherMethod(localVar);
            // ...
        }
    }
    
    static class ComponentToTestTest {
        
        @Test
        // Chicago (Detroit) style (state based) - TDD
        void firstMethodTest() {
            // Setup (or given)
            // ----------------
            String input = "testValue";
            
            // Fake dependency1 - Use mockito to easily create a stub but generally unused in Detroit style
            // Mockito interaction checking not used here in Chicago style
            Dependency1 fakeDependency1 = mock(Dependency1.class);
            doReturn("someResult").when(fakeDependency1).someMethod("computedValue_" + input);
            
            // Unused dependency2 set to null
            ComponentToTest componentToTest = new ComponentToTest(fakeDependency1, null);
            
            // Exercise SUT (when)
            // -------------------
            String actualValue = componentToTest.firstMethod(input);
            
            // Verify (then)
            // -------------
            String expectedValue = "someResult" + "_finalComputedValue";
            assertEquals("...", expectedValue, actualValue);
        }
        
        @Test
        // London style (interaction style) - TDD
        void secondMethodTest() {
            // Setup
            // -----
            String input = "testValue";
            
            // Fake dependency2
            DependencyN fakeDependencyN = mock(DependencyN.class);
            String dependencyNInput = "otherComputedValue_" + input;
            doReturn("theResult").when(fakeDependencyN).otherMethod(dependencyNInput);
            
            // Unused dependency1 set to null
            ComponentToTest componentToTest = new ComponentToTest(null, fakeDependencyN);
            
            // Exercise SUT
            // ------------
            componentToTest.secondMethod(input);
            
            // Verify
            // ------
            verify(fakeDependencyN).otherMethod(dependencyNInput);
        }
    }
}

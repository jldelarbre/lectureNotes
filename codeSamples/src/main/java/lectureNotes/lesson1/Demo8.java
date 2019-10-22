package lectureNotes.lesson1;

public class Demo8 {

    // Problem encountered with static instances
    static final class Foo {
        private final int a;
        private final int b;
        
        // Not a good idea (private static field)
        private static int iAmAGlobalState;
        
        public Foo(int a, int b) {
            super();
            this.a = a;
            this.b = b;
        }
        
        // I have the power to let anybody change the behavior of doSomething method on any
        // Foo instance everyWhere, any time no one could stop me
        public static void setTheGlobalState(int val) {
            iAmAGlobalState = val;
        }
        
        public int doSomething(int param) {
            return a + b + param + iAmAGlobalState;
        }
        
        // public static method
        // Often not a good, guess why... we will see it later
        public static int doSomethingElse(int param) {
            return param;
        }
    }
}

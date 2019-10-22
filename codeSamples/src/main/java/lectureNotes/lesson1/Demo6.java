package lectureNotes.lesson1;

import java.util.Date;

public class Demo6 {

    // Samples of factories (and factory methods) definition
    static final class Foo {
        // Data
        private final int a;
        private final int b;
        // NB: Date is obsolete see LocalDateTime since Java 8
        private final Date date;
        
        // Dependency / (coworker). Only known through its interface
        private final IBar bar;
        
        public Foo(int a, int b, IBar bar) {
            // 'new' operator shall never be used in the constructor to set dependencies
            // This is the factory responsibility to wire the application (build or fetch
            // the dependencies)
            super();
            this.a = a;
            this.b = b;
            
            // 'new' could be used to initialize data object
            date = new Date();
            
            this.bar = bar;
        }
        
        // Use factory methods in simple cases, it builds Foo and set its dependencies
        
        // This is a factory method. The 'bar' dependency is pass to the factory method
        public static Foo build(int a, int b, IBar bar) {
            return new Foo(a, b, bar);
        }
        
        // This is another factory method. The 'bar' dependency is built by the factory method
        public static Foo build(int a, int b) {
            IBar bar = new Bar();
            return new Foo(a, b, bar);
        }
        
        public int doSomething(int param) {
            return a + b + param + (int) date.getTime() + bar.barOperation(param).length();
        }
    }
    
    // Use a dedicated factory for more complex cases
    // it also builds Foo and set its dependencies
    // Another responsibility of factories is not shown here.
    // We only set the dependencies for the built object,
    // but the factory also knows which concrete type to choose for it.
    // Since we only have one concrete type 'Foo', this responsibility is not
    // apparent here.
    // In next lesson, factories will choose among many implementation the one to use
    static final class FooFactory {
        
        // Dependencies could be stored in advance for future construction
        private final IBar bar;
        
        public FooFactory(IBar bar) {
            super();
            this.bar = bar;
        }

        public Foo build(int a, int b) {
            return new Foo(a, b, this.bar);
        }
    }
    
    interface IBar {
        String barOperation(int val);
    }
    
    static class Bar implements IBar {

        @Override
        public String barOperation(int val) {
            return String.valueOf(val);
        }
    }
}

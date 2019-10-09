package lectureNotes.lesson1;

public class Demo5 {

    // An immutable class
    static final class Foo {
        private final int a;
        private final int b;
        
        // Since the fields are 'final' we are force to define state at construction time
        public Foo(int a, int b) {
            super();
            this.a = a;
            this.b = b;
        }
        
        // Sample of 'immutable' setter
        // Return a copy of the current instance but with a parameter changed
        public Foo setA(int a) {
            return new Foo(a, this.b);
            
            // The 'mutable' setter would be like this
            // this.a = a;
            // return this
            // or return void
        }
        
        public int doSomething(int param) {
            return a + b + param;
        }
    }
}

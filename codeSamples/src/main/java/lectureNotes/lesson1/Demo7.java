package lectureNotes.lesson1;

public class Demo7 {

    static final class Foo {
        private final int a;
        private final int b;
        
        public Foo(int a, int b) {
            super();
            this.a = a;
            this.b = b;
        }
        
        public int doSomething(int param) {
            return a + b + param;
        }
    }
    
    // We could add factory methods to the builder but for the sake of simplicity we discard them here
    static final class FooBuilder {
        private final int a;
        private final int b;
        
        public FooBuilder() {
            // Define some default values
            this.a = 32;
            this.b = 42;
        }
        
        public FooBuilder(int a, int b) {
            this.a = a;
            this.b = b;
        }
        
        public FooBuilder setA(int a) {
            return new FooBuilder(a, this.b);
        }
        
        public FooBuilder setB(int b) {
            return new FooBuilder(this.a, b);
        }
        
        public Foo build() {
            return new Foo(this.a, this.b);
        }
    }
    
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        // Builder usage samples
        
        FooBuilder builder1 = new FooBuilder();
        Foo foo1 = builder1.build();
        
        FooBuilder builder1b = builder1.setB(-15);
        Foo foo1b = builder1b.build();
        
        FooBuilder builder2 = new FooBuilder(27, 56);
        Foo foo2 = builder2.build();
        
        FooBuilder builder2a = builder2.setA(156);
        Foo foo2a = builder2a.build();
        
        Foo foo = builder2
                .setA(69)
                .setB(75)
                .build();
    }
}

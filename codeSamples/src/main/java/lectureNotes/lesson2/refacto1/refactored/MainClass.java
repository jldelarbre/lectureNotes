package lectureNotes.lesson2.refacto1.refactored;

import java.util.Optional;

public class MainClass {

    public static void main(String[] args) {
        Foo foo = new Foo(13, 83, Optional.empty());
        
        someProcessingWithFoo(foo);
    }
    
    static void someProcessingWithFoo(Foo foo) {
        // Some useless processing:
        foo.doWork();
        System.out.println(2*foo.getParam1() + foo.getParam2());
    }
}

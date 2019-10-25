package lectureNotes.lesson2.refacto1;

public class MainClass {

    public static void main(String[] args) {
        Foo foo = new Foo();
        foo.setParam1(13);
        foo.setParam2(83);
        
        // Foo setters are never called after object construction
        someProcessingWithFoo(foo);
    }
    
    static void someProcessingWithFoo(Foo foo) {
        foo.doWork();
        System.out.println(foo.getParam1() + foo.getParam2());
    }
}

package lectureNotes.specialIssues.si1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class WildcardBadUsage {

    static class SomeClass {
        List<Double> doubleList = new ArrayList<>();
        List<Number> numberList = new ArrayList<>();
        
        // Cumbersome and no profit for function caller
        List<? extends Number> get1() {return doubleList;}
        
        // If you want to protect your internal data use immutable collection
        // Bounded wildcard is not immutable
        List<Double> get2() {return Collections.unmodifiableList(doubleList);}
        
        // Useless
        List<? super Number> get3() {return numberList;}
    }
    
    static class OtherClass {
        Consumer<Double> doubleWorker = d -> System.out.println(d);
        
        Consumer<? super Double> get1() { return doubleWorker;}
        Consumer<Double> get2() { return doubleWorker;}
    }
    
    static class ThirdClass<T> {
        // Impossible to use wildcard and to pass an exact T and get an exact T
        // Use exact type
        T doSomething(T t) { return null; }
    }
    
    public static void main(String[] args) {
        SomeClass someClass = new SomeClass();
        
        // Cumbersome
        List<? extends Number> get1_1 = someClass.get1();
        // Simpler to pass the true type (no drawback)
        List<Double> get1_2 = someClass.get2();
        
        // Bad idea to use super to restrict things to add in your collection
        // Prefer to keep your data private, add and filter yourself the data to add in your collection
        List<? super Number> get1_3a = someClass.get3();
        get1_3a.add(3);
        get1_3a.add(3.2);
        List<? super Double> get1_3b = someClass.get3();
        get1_3b.add(3.2);
        
        // Collection should always be used as producer (extends)
        // Exception: util methods of JDK: Collections class
        // public static <T> boolean addAll(Collection<? super T> c, T... elements)
        
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        
        // Useless to use super with return value
        Double d = 3.2;
        Number n = d;
        OtherClass otherClass = new OtherClass();
        
        Consumer<? super Double> get2_1 = otherClass.get1();
        get2_1.accept(d);
        // get2_1.accept(n); // Error
        Consumer<Double> get2_2 = otherClass.get2();
        get2_2.accept(d);
    }
}

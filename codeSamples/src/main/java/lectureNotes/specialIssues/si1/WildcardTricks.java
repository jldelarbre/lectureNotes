package lectureNotes.specialIssues.si1;

import java.util.Collection;
import java.util.List;

// Explain diff between List, List<?>, List<Object> demonstrate it with code
public class WildcardTricks {

    // See effective Java (Bloch)
    // Two possible declarations for the swap method

    public static <E> void swap1(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }
    
    
    // There is only one list, but the compiler could not know that an element extract from list
    // is of the same type than an element to insert in the same list
    public static void swap2(List<?> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
        
        // Same code as above but in many readable steps
        Object x_i_old = list.get(i);
        Object x_j_old = list.set(j, x_i_old);
        list.set(i, x_j_old);
    }
    
    // Nevertheless '?' has many advantages (see Bloch). It is possible to write it thanks to
    // a helper generic method
    public static void swap3(List<?> list, int i, int j) {
        swap1(list, i, j);
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    // Complex "JDK" sample: Collections
    // public static <T extends Comparable<? super T>> T max(Collection<? extends T> coll)
    //
    // This is what you really have in JDK (extends Object avoid to break compatibility with
    // pre Java 5 code):
    //
    // public static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll)
}

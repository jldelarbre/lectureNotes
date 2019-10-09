package lectureNotes.lesson1;

import java.util.HashMap;
import java.util.Map;

public class Demo2 {

    // No hashCode nor equals redefined
    static class Key {
        int a;
    }
    
    public static void main(String[] args) {
        Key k = new Key();
        k.a = 5;
        String val = "value";
        
        Map<Key, String> map = new HashMap<>();
        map.put(k, val);
        
        String valOut = map.get(k);
        System.out.println(valOut);
        // Console output
        // # value
        
        
        // Elsewhere in code, create another key to retrieve "value"
        // The key seems 'equals' to the previous one but not for the jvm
        // since neither equals nor hashCode have been redefined
        Key k2 = new Key();
        k2.a = 5;
        
        String valOut2 = map.get(k2);
        System.out.println(valOut2);
        // Console output
        // # null
        
        // 'k' and 'k2' are not 'equals', hence 'k2' could not been retrieved with 'k2'
        System.out.println(k.equals(k2));
        // Console output
        // # false
    }
}

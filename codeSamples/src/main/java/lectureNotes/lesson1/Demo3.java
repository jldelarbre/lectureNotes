package lectureNotes.lesson1;

import java.util.HashMap;
import java.util.Map;

public class Demo3 {

    // Great hashCode and equals are redefined together
    // All will be OK ! Sure ?
    static class Key {
        int a;

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + a;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Key other = (Key) obj;
            if (a != other.a)
                return false;
            return true;
        }
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
        
        
        // Elsewhere in code, create another key (truly valid) to retrieve 'value'
        Key k2 = new Key();
        k2.a = 5;
        
        // But it is possible to alter the original key
        k.a = 42;
        
        String valOut2 = map.get(k2);
        System.out.println(valOut2);
        // Console output
        // # null
        
        // 'k' and 'k2' are no longer 'equals', hence 'k2' could not been retrieved with 'k2'
        System.out.println(k.equals(k2));
        // Console output
        // # false
    }
}

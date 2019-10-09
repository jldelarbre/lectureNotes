package lectureNotes.lesson1;

import java.util.HashMap;
import java.util.Map;

public class Demo1 {

    // Missing hashCode
    static class Key {
        int a;

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
        
        
        // Elsewhere in code, create another valid key to retrieve "value"
        Key k2 = new Key();
        k2.a = 5;
        
        String valOut2 = map.get(k2);
        System.out.println(valOut2);
        // Console output
        // # null
        
        // Since hashCode has not been defined along with equals
        // the valid key 'k2' does not work
    }
}

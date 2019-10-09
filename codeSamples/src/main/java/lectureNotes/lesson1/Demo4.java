package lectureNotes.lesson1;

import java.util.HashMap;
import java.util.Map;

public class Demo4 {

    // hashCode and equals redefined together and 'Key' is immutable
    static final class KeyB {
        private final int a;
        
        public KeyB(int a) {
            super();
            this.a = a;
        }

        // Do not implement equals or hashCode yourself ask your IDE to do it for you
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
            KeyB other = (KeyB) obj;
            if (a != other.a)
                return false;
            return true;
        }
    }
    
    public static void main(String[] args) {
        KeyB k = new KeyB(5);
        String val = "value";
        
        Map<KeyB, String> map = new HashMap<>();
        map.put(k, val);
        
        String valOut = map.get(k);
        System.out.println(valOut);
        // Console output
        // # value
        
        
        // Elsewhere in code, create another key (truly valid) to retrieve 'value'
        KeyB k2 = new KeyB(5);
        
        String valOut2 = map.get(k2);
        System.out.println(valOut2);
        // Console output
        // # value
        
        // Everything works fine and reliably
    }
}

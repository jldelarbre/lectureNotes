package lectureNotes.lesson4.ocp;

import java.util.Collections;
import java.util.List;

public class OCP3 {
    
    static interface Waste extends Comparable<Waste> {
        void recycle();
        
        double recycledValue();
        
        @Override
        default int compareTo(Waste o) {
            return (int) (this.recycledValue() - o.recycledValue());
        }
    }

    static class OrganicWaste implements Waste {
        @Override
        public void recycle() {
            // Anaerobic digestion
            // ...
        }
        
        @Override
        public double recycledValue() {
            // First check test with 0.0, the behavior of "RecyclingCenter"
            // should be the same as before then only change to expected value
            // To see the behavior modification
            // return 0.0;
            return 8.0;
        }
    }
    
    static class PlasticWaste implements Waste {
        @Override
        public void recycle() {
            // Produce microbead
            // ...
        }
        
        @Override
        public double recycledValue() {
            // First check test with 0.0, the behavior of "RecyclingCenter"
            // should be the same as before then only change to expected value
            // To see the behavior modification
            return 3.0;
        }
    }
    
    static class ElectronicWaste implements Waste {
        @Override
        public void recycle() {
            // Send to south east Asia
            // ...
        }
        
        @Override
        public double recycledValue() {
            // First check test with 0.0, the behavior of "RecyclingCenter"
            // should be the same as before then only change to expected value
            // To see the behavior modification
            return 0.5;
        }
    }
    
    // Recycling center is closed for any new kind of waste but not for any feature
    // you can imagine.
    // If you want to introduce a hierarchy between wastes, you need to modify
    // your code. You have to choose your closure strategically
    static class RecyclingCenter {
        static final double BREAK_EVEN_POINT = 1.0;
        
        void recycleWaste(List<? extends Waste> wastes) {
            
            // Must valuable first
            Collections.sort(wastes, Collections.reverseOrder());
            
            for (Waste waste : wastes) {
                if (waste.recycledValue() < BREAK_EVEN_POINT) {
                    // Throw in river
                } else {
                    waste.recycle();
                }
            }
        }
    }
}

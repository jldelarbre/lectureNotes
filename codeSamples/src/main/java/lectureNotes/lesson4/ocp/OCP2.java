package lectureNotes.lesson4.ocp;

import java.util.List;

public class OCP2 {
    
    // Create an abstraction for any kind of waste
    // For any new kind of waste, it is only necessary to create an implementation for it.
    static interface Waste {
        void recycle();
    }

    static class OrganicWaste implements Waste {
        @Override
        public void recycle() {
            // Anaerobic digestion
            // ...
        }
    }
    
    static class PlasticWaste implements Waste {
        @Override
        public void recycle() {
            // Produce microbead
            // ...
        }
    }
    
    static class ElectronicWaste implements Waste {
        @Override
        public void recycle() {
            // Send to south east Asia
            // ...
        }
    }
    
    // To make the recycling center able to recycle new kind of waste it is no longer
    // necessary to modify it.
    // It is just necessary to extends waste with a new implementation.
    // RecyclingCenter is closed for modification but open for extension
    static class RecyclingCenter {
        void recycleWaste(List<? extends Waste> wastes) {
            for (Waste waste : wastes) {
                waste.recycle();
            }
        }
    }
}

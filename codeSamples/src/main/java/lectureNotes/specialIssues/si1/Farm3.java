package lectureNotes.specialIssues.si1;

import java.util.ArrayList;
import java.util.List;

public class Farm3 {

    static class Animal {}
    static class Goat extends Animal {}
    
    static class LivestockTruck {
        
        List<Animal> freeLocations = new ArrayList<>();
        
        void loadAnimals(List<? extends Animal> animals) {
            freeLocations.addAll(animals);
        }
    }

    static class Farmer {
        
        List<Goat> herd = new ArrayList<>();
        
        void sellHerd(LivestockTruck livestockTruck) {
            // ...
            livestockTruck.loadAnimals(herd);
            herd.clear();
            // ...
        }
    }
}

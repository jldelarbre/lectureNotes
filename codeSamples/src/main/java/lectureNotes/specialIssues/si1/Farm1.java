package lectureNotes.specialIssues.si1;

import java.util.ArrayList;
import java.util.List;

public class Farm1 {

    static class Animal {}
    static class Goat extends Animal {}
    
    static class Veterinarian {
        
        void vaccinate(List<Animal> herd) {
            for (@SuppressWarnings("unused") Animal a : herd) {
                // Vaccinate it
            }
        }
    }
    
    static class LivestockTruck {
        
        List<Animal> freeLocations = new ArrayList<>();
        
        List<Animal> getFreeLocations() {
            // ...
            return freeLocations;
        }
    }

    static class Farmer {
        
        List<Animal> herd = new ArrayList<>();
        
        void takeCareOfHerd(Veterinarian veterinarian) {
            // ...
            veterinarian.vaccinate(herd);
            // ...
        }
        
        void sellHerd(LivestockTruck livestockTruck) {
            // ...
            loadGoats(livestockTruck.getFreeLocations());
            // ...
        }
        
        void loadGoats(List<Animal> freeLocations) {
            for (Animal animal : herd) {
                freeLocations.add(animal);
            }
            herd.clear();
        }
    }
}

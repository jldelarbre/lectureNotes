package lectureNotes.specialIssues.si1;

import java.util.ArrayList;
import java.util.List;

public class Farm2 {

    static class Animal {}
    static class Goat extends Animal {}
    
    static class Veterinarian {
        
        void vaccinate(List<? extends Animal> herd) {
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
    
    static class GoatLivestockTruck {
        
        List<Goat> freeLocations = new ArrayList<>();
        
        List<Goat> getFreeLocations() {
            // ...
            return freeLocations;
        }
    }

    static class Farmer {
        
        List<Goat> herd = new ArrayList<>();
        
        void takeCareOfHerd(Veterinarian veterinarian) {
            // ...
            veterinarian.vaccinate(herd);
            // ...
        }
        
        void sellHerd(LivestockTruck livestockTruck) {
            // ...
            List<Animal> freeLocations = livestockTruck.getFreeLocations();
            loadGoats(freeLocations);
            // ...
        }
        
        void sellHerdToGoatBuyer(GoatLivestockTruck goatLivestockTruck) {
            // ...
            List<Goat> freeLocations = goatLivestockTruck.getFreeLocations();
            loadGoats(freeLocations);
            // ...
        }
        
        void loadGoats(List<? super Goat> freeLocations) {
            for (Goat goat : herd) {
                freeLocations.add(goat);
            }
            herd.clear();
        }
    }
}

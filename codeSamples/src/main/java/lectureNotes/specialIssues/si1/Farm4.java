package lectureNotes.specialIssues.si1;

import java.util.ArrayList;
import java.util.List;

public class Farm4 {

    static class Animal {}
    static class Goat extends Animal {}
    
    static class OrganicFarmingLabel {}
    
    static class OrganicFarmingCertifier<T extends Animal> {
        
        OrganicFarmingLabel certify(T t) {
            // ...
            return new OrganicFarmingLabel();
        }
    }

    static class Farmer {
        
        List<Goat> herd = new ArrayList<>();
        
        void doDayOfWork() {
            // ...
            OrganicFarmingCertifier<Goat> organicFarmingCertifierGoat = new OrganicFarmingCertifier<>();
            doMarketingWork(organicFarmingCertifierGoat);
            
            // Or alternatively
            OrganicFarmingCertifier<Animal> organicFarmingCertifierAnimal = new OrganicFarmingCertifier<>();
            doMarketingWork(organicFarmingCertifierAnimal);
            
            //...
        }
        
        void doMarketingWork(OrganicFarmingCertifier<? super Goat> organicFarmingCertifier) {
            List<OrganicFarmingLabel> organicFarmingLabels = new ArrayList<>();
            
            for (Goat goat : herd) {
                OrganicFarmingLabel organicFarmingLabel = organicFarmingCertifier.certify(goat);
                organicFarmingLabels.add(organicFarmingLabel);
            }
        }
    }
}

package lectureNotes.specialIssues.si1;

import java.util.ArrayList;
import java.util.List;

public class Farm5a {

    static class Animal {
        String name;
        public Animal(String name) { this.name = name; }
        
        interface Factory<A extends Animal> {
            A build();
        }
    }
    static class Goat extends Animal {
        Goat(){super("Biquette");}
        Goat(Goat g){super(g.name);}
        
        void eatRope() {}
    }
    static class CertifiedGoat extends Goat {
        OrganicFarmingLabel organicFarmingLabel = new OrganicFarmingLabel();
        
        CertifiedGoat(){}
        CertifiedGoat(Goat g){super(g);}
        CertifiedGoat(CertifiedGoat g){super(g);}
    }
    
    static class OrganicFarmingLabel {}
    
    static class OrganicFarmingCertifier<T extends Animal> {
        <A extends T> A certify(T t, Animal.Factory<A> factory) {
            // Check that "t" is certifiable ...
            // Then build output if certif ok
            return factory.build();
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
        
        @SuppressWarnings("unused")
        void doMarketingWork(OrganicFarmingCertifier<? super Goat> organicFarmingCertifier) {
            
            // ...
            for (Goat goat : herd) {
                Goat certifiedGoat = organicFarmingCertifier.certify(goat, Goat::new);
                CertifiedGoat reallyCertifiedGoat = organicFarmingCertifier.certify(goat, CertifiedGoat::new);
            }
            // ...
        }
    }
}

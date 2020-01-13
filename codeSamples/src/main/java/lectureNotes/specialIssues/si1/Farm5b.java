package lectureNotes.specialIssues.si1;

import java.util.ArrayList;
import java.util.List;

public class Farm5b {

    static class Animal {
        String name;
        public Animal(String name) { this.name = name; }

        interface Factory<A extends Animal> {
            <SpecificA extends A> SpecificA build(A a, Class<SpecificA> clazz);
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
        
        <SpecificT extends T> SpecificT certify(T t, Animal.Factory<? super T> factory, Class<SpecificT> clazz) {
            // Check that "t" is certifiable ...
            // Then build output using input "t" if certif ok
            return factory.build(t, clazz);
        }
    }
    
    static class GoatFactory implements Animal.Factory<Goat> {
        @Override
        public <SpecificA extends Goat> SpecificA build(Goat g, Class<SpecificA> clazz) {
            try {
                return clazz.newInstance(); // Or use a constructor with parameter with goat as input
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }
    
    static class AnimalFactory implements Animal.Factory<Animal> {
        @Override
        public <SpecificA extends Animal> SpecificA build(Animal a, Class<SpecificA> clazz) {
            try {
                return clazz.newInstance(); // Or use a constructor with parameter with animal as input
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }

    static class Farmer {
        
        List<Goat> herd = new ArrayList<>();
        
        @SuppressWarnings("unused")
        void doDayOfWork() {
            Goat goat = herd.get(0);
            // ...
            OrganicFarmingCertifier<Goat> organicFarmingCertifierGoat = new OrganicFarmingCertifier<>();
            doMarketingWork(organicFarmingCertifierGoat);
            
            // Animal.Factory f = (Goat g, Class<Goat> clazz) -> new Goat(); // Does no work
            GoatFactory gf = new GoatFactory();
            Goat certifyGoat = organicFarmingCertifierGoat.certify(goat, gf, Goat.class);
            CertifiedGoat reallyCertifyGoat = organicFarmingCertifierGoat.certify(goat, gf, CertifiedGoat.class);
            
            // Or alternatively
            OrganicFarmingCertifier<Animal> organicFarmingCertifierAnimal = new OrganicFarmingCertifier<>();
            doMarketingWork(organicFarmingCertifierAnimal);
            
            AnimalFactory af = new AnimalFactory();
            Goat certifyGoat2 = organicFarmingCertifierAnimal.certify(goat, af, Goat.class);
            CertifiedGoat reallyCertifyGoat2 = organicFarmingCertifierAnimal.certify(goat, af, CertifiedGoat.class);
            //...
        }
        
        @SuppressWarnings("unused")
        void doMarketingWork(OrganicFarmingCertifier<? super Goat> organicFarmingCertifier) {
            // ...
            for (Goat goat : herd) {
                AnimalFactory af = new AnimalFactory();
                Goat certifiedGoat = organicFarmingCertifier.certify(goat, af, Goat.class);
                CertifiedGoat reallyCertifiedGoat = organicFarmingCertifier.certify(goat, af, CertifiedGoat.class);
                
                // Do not work
                // GoatFactory gf = new GoatFactory();
                // Goat certifiedGoat2 = organicFarmingCertifier.certify(goat, gf, Goat.class);
                // CertifiedGoat reallyCertifiedGoat2 = organicFarmingCertifier.certify(goat, gf, CertifiedGoat.class);
            }
            // ...
        }
    }
}

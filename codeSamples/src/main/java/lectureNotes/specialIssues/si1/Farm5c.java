package lectureNotes.specialIssues.si1;

import java.util.ArrayList;
import java.util.List;

public class Farm5c {

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
        
        void doDayOfWork() {
            // ...
            
            GoatFactory gf = new GoatFactory();
            OrganicFarmingCertifier<Goat> organicFarmingCertifierGoat = new OrganicFarmingCertifier<>();
            doMarketingWork(organicFarmingCertifierGoat, gf, Goat.class, herd);
            doMarketingWork(organicFarmingCertifierGoat, gf, CertifiedGoat.class, herd);
            
            // Or alternatively
            AnimalFactory af = new AnimalFactory();
            OrganicFarmingCertifier<Animal> organicFarmingCertifierAnimal = new OrganicFarmingCertifier<>();
            doMarketingWork(organicFarmingCertifierAnimal, af, Goat.class, herd);
            doMarketingWork(organicFarmingCertifierAnimal, af, CertifiedGoat.class, herd);
            
            //...
        }
        
        @SuppressWarnings("unused")
        <T extends Animal, SpecificT extends T>
        void doMarketingWork(OrganicFarmingCertifier<T> organicFarmingCertifier,
                             Animal.Factory<? super T> factory,
                             Class<SpecificT> clazz,
                             List<? extends T> pHerd) {
            for (T goat : pHerd) {
                final SpecificT certifyAnimal = organicFarmingCertifier.certify(goat, factory, clazz);
            }
        }
    }
}

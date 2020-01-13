package lectureNotes.specialIssues.si1;

import java.util.ArrayList;
import java.util.List;

public class Farm5d {

    static class Animal {
        String name;
        public Animal(String name) { this.name = name; }

        interface Factory<A extends Animal, SpecificA extends A> {
            SpecificA build(A a);
        }
    }
    static class Goat extends Animal {
        Goat(){super("Biquette");}
        Goat(Goat g){super(g.name);}
        Goat(Animal a){super(a.name);}
        
        void eatRope() {}
    }
    static class CertifiedGoat extends Goat {
        OrganicFarmingLabel organicFarmingLabel = new OrganicFarmingLabel();
        
        CertifiedGoat(){}
        CertifiedGoat(Goat g){super(g);}
        CertifiedGoat(CertifiedGoat g){super(g);}
        CertifiedGoat(Animal a){super(a);}
    }
    
    static class OrganicFarmingLabel {}
    
    static class OrganicFarmingCertifier<T extends Animal> {
        
        <SpecificT extends T> SpecificT certify(T t, Animal.Factory<? super T, SpecificT> factory) {
            // Check that "t" is certifiable ...
            // Then build output using input "t" if certif ok
            return factory.build(t);
        }
    }
    
    static class GoatFactory implements Animal.Factory<Goat, Goat> {
        @Override
        public Goat build(Goat g) {
            try {
                return new Goat(g);
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }
    
    static class CertifiedGoatFactory implements Animal.Factory<Goat, CertifiedGoat> {
        @Override
        public CertifiedGoat build(Goat g) {
            try {
                return new CertifiedGoat(g);
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }
    
    static class AnimalToGoatFactory implements Animal.Factory<Animal, Goat> {
        @Override
        public Goat build(Animal a) {
            try {
                Goat goat = new Goat();
                goat.name = a.name;
                return goat;
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }
    
    static class AnimalToCertifiedGoatFactory implements Animal.Factory<Animal, CertifiedGoat> {
        @Override
        public CertifiedGoat build(Animal a) {
            try {
                CertifiedGoat goat = new CertifiedGoat();
                goat.name = a.name;
                return goat;
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
            CertifiedGoatFactory cgf = new CertifiedGoatFactory();
            OrganicFarmingCertifier<Goat> organicFarmingCertifierGoat = new OrganicFarmingCertifier<>();
            doMarketingWork(organicFarmingCertifierGoat, Goat::new, herd);
            doMarketingWork(organicFarmingCertifierGoat, CertifiedGoat::new, herd);
            doMarketingWork(organicFarmingCertifierGoat, gf, herd);
            doMarketingWork(organicFarmingCertifierGoat, cgf, herd);
            
            // Or alternatively
            AnimalToGoatFactory af = new AnimalToGoatFactory();
            AnimalToCertifiedGoatFactory caf = new AnimalToCertifiedGoatFactory();
            OrganicFarmingCertifier<Animal> organicFarmingCertifierAnimal = new OrganicFarmingCertifier<>();
            doMarketingWork(organicFarmingCertifierAnimal, Goat::new, herd);
            doMarketingWork(organicFarmingCertifierAnimal, CertifiedGoat::new, herd);
            doMarketingWork(organicFarmingCertifierAnimal, af, herd);
            doMarketingWork(organicFarmingCertifierAnimal, caf, herd);
            
            //...
        }
        
        @SuppressWarnings("unused")
        <T extends Animal, SpecificT extends T>
        void doMarketingWork(OrganicFarmingCertifier<T> organicFarmingCertifier,
                             Animal.Factory<? super T, SpecificT> factory,
                             List<? extends T> herd) {
            for (T goat : herd) {
                final SpecificT certifyAnimal = organicFarmingCertifier.certify(goat, factory);
            }
        }
    }
}

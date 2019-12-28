package lectureNotes.specialIssues.si1;

import java.util.ArrayList;
import java.util.List;

public class Farm5 {

    static class Animal {
        interface Factory<A extends Animal> {
            A build();
        }
        interface Factory2<A extends Animal> {
            <SpecificA extends A> SpecificA build(A a, Class<SpecificA> clazz);
        }
    }
    static class Goat extends Animal {Goat(){} Goat(Goat g){/*copy*/}}
    static class CertifiedGoat extends Goat {CertifiedGoat(){} CertifiedGoat(Goat g){} CertifiedGoat(CertifiedGoat g){}}
    
    static class OrganicFarmingLabel {}
    
    static class OrganicFarmingCertifier<T extends Animal> {
        
        OrganicFarmingLabel certify(T t) {
            // ...
            return new OrganicFarmingLabel();
        }
        
        <A extends T> A certify(T t, Animal.Factory<A> factory) {
            return factory.build();
        }
        
        <SpecificT extends T> SpecificT certify2(T t, Animal.Factory2<? super T> factory, Class<SpecificT> clazz) {
            return factory.build(t, clazz);
        }
    }
    
    static class GoatFactory implements Animal.Factory2<Goat> {
        @Override
        public <SpecificA extends Goat> SpecificA build(Goat a, Class<SpecificA> clazz) {
            try {
                return clazz.newInstance();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }
    
    static class AnimalFactory implements Animal.Factory2<Animal> {
        @Override
        public <SpecificA extends Animal> SpecificA build(Animal a, Class<SpecificA> clazz) {
            try {
                return clazz.newInstance();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }

    static class Farmer {
        
        List<Goat> herd = new ArrayList<>();
        
        void doDayOfWork() {
            Goat goat = herd.get(0);
            GoatFactory gf = new GoatFactory();
            AnimalFactory af = new AnimalFactory();
            // ...
            OrganicFarmingCertifier<Goat> organicFarmingCertifierGoat = new OrganicFarmingCertifier<>();
            doMarketingWork(organicFarmingCertifierGoat);
            
            // Animal.Factory f = (Goat g, Class<Goat> clazz) -> new Goat(); // Impossible
            
            Goat certifyGoat = organicFarmingCertifierGoat.certify2(goat, gf, Goat.class);
            CertifiedGoat reallyCertifyGoat = organicFarmingCertifierGoat.certify2(goat, gf, CertifiedGoat.class);
            
            // Or alternatively
            OrganicFarmingCertifier<Animal> organicFarmingCertifierAnimal = new OrganicFarmingCertifier<>();
            doMarketingWork(organicFarmingCertifierAnimal);
            
            Goat certifyGoat2 = organicFarmingCertifierAnimal.certify2(goat, af, Goat.class);
            CertifiedGoat reallyCertifyGoat2 = organicFarmingCertifierAnimal.certify2(goat, af, CertifiedGoat.class);
            //...
        }
        
        void doMarketingWork(OrganicFarmingCertifier<? super Goat> organicFarmingCertifier) {
            List<OrganicFarmingLabel> organicFarmingLabels = new ArrayList<>();
            
            for (Goat goat : herd) {
                OrganicFarmingLabel organicFarmingLabel = organicFarmingCertifier.certify(goat);
                organicFarmingLabels.add(organicFarmingLabel);
                
                Goat certifiedGoat = organicFarmingCertifier.certify(goat, () -> new Goat());
                CertifiedGoat reallyCertifiedGoat = organicFarmingCertifier.certify(goat, () -> new CertifiedGoat());
                
                AnimalFactory af = new AnimalFactory();
                Goat certifiedGoat2 = organicFarmingCertifier.certify2(goat, af, Goat.class);
                CertifiedGoat reallyCertifiedGoat2 = organicFarmingCertifier.certify2(goat, af, CertifiedGoat.class);
                
                GoatFactory gf = new GoatFactory();
                Goat certifiedGoat3 = organicFarmingCertifier.certify2(goat, gf, Goat.class);
                CertifiedGoat reallyCertifiedGoat3 = organicFarmingCertifier.certify2(goat, gf, CertifiedGoat.class);
            }
        }
    }
}

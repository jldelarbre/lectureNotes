package lectureNotes.lesson4.isp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ISP2 {
    
    interface Waste {}
    interface ChimicWaste extends Waste {}
    interface PlasticWaste extends Waste {}
    interface UnrecyclableWaste extends Waste {}

    interface PublicAuthoritiesRecyclingRateReporter {
        
        String generateOrganicWasteReport();
        String generateChimicWasteReport();
        String generatePlasticWasteReport();
    }
    
    interface PollutionAnalysisLaboratory {
        double measureToxicityLevel(ChimicWaste chimicWaste);
    }
    
    interface EnvironmentInformationCenter extends PublicAuthoritiesRecyclingRateReporter,
                                                   PollutionAnalysisLaboratory {
    }
    
    // RecyclingCenter no longer inherit from PublicAuthoritiesRecyclingRateReporter
    // Its responsibilities are splited into many interfaces for each clients
    interface RecyclingCenter {
        // Assimilate garbage truck as a list for the sake of simplicity
        Map<Class<? extends Waste>, List<? extends Waste>> sortWastes(List<? extends Waste> garbageTruck);
        
        void incinerate(List<? extends UnrecyclableWaste> unrecyclableWastes);
    }
    
    interface MicrobeadProducer {
        // return mass (kg) of microbead generated
        double generateMicrobead(List<? extends PlasticWaste> plasticWastes);
    }
    
    // The recycling center implementation class still has the internal resources to implement
    // its various role, but it is done through many specific interface dedicated for each kind of
    // client
    static class RecyclingCenterImpl implements RecyclingCenter,
                                                EnvironmentInformationCenter,
                                                MicrobeadProducer {
        
        String recyclingCenterData = ""; //...

        @Override public String generateOrganicWasteReport() { return "the report ..."; }
        @Override public String generateChimicWasteReport() { return "the report ..."; }
        @Override public String generatePlasticWasteReport() { return "the report ..."; }

        @Override
        public Map<Class<? extends Waste>, List<? extends Waste>> sortWastes(List<? extends Waste> garbageTruck) {
            Map<Class<? extends Waste>, List<? extends Waste>> output = new HashMap<>();
            
            // Sort waste and generate statistics data about waste...
            
            return output;
        }

        @Override
        public void incinerate(List<? extends UnrecyclableWaste> unrecyclableWastes) {
            // Incinerate waste and generate statistics data
        }

        @Override
        public double generateMicrobead(List<? extends PlasticWaste> plasticWastes) {
            double microbeadMass = 0.0;
            // Recycles plasticWastes and generate statistics data
            return microbeadMass;
        }

        @Override
        public double measureToxicityLevel(ChimicWaste chimicWaste) {
            double toxicity = 0.;
            // Analyse chimicWaste toxicity
            return toxicity;
        }
    }
    
    // Municipal services is client of 2 dedicated services
    // It has no need to know if they are implemented by the same class
    static class MunicipalServices {
        
        @SuppressWarnings("unused")
        void administerCityWaste(EnvironmentInformationCenter environmentInformationCenter) {
            
            // ... Get reports about waste
            
            String organicWasteReport = environmentInformationCenter.generateOrganicWasteReport();
            String chimicWasteReport = environmentInformationCenter.generateChimicWasteReport();
            String plasticWasteReport = environmentInformationCenter.generatePlasticWasteReport();
            
            // ... Analyse waste toxicity for the population
            
            ChimicWaste chimicWaste = null; // ...
            environmentInformationCenter.measureToxicityLevel(chimicWaste);
            // ...
        }
        
        void manageCityWaste(RecyclingCenter recyclingCenter) {
            
            // Garbage truck collects garbage...
            List<? extends Waste> garbageTruck = new ArrayList<>();
            // Then waste are sorted in recycling center
            Map<Class<? extends Waste>, List<? extends Waste>> sortedWastes = recyclingCenter.sortWastes(garbageTruck);
            
            
            // Then unrecyclable waste are incinerated...
            
            @SuppressWarnings("unchecked")
            List<? extends UnrecyclableWaste> unrecyclableWastes =
                (List<? extends UnrecyclableWaste>) sortedWastes.get(UnrecyclableWaste.class);
            recyclingCenter.incinerate(unrecyclableWastes);
            
            // ...
        }
    }
    
    static class BottleFactory {
        
        @SuppressWarnings("unused")
        void produceBottles(MicrobeadProducer microbeadProducer, List<? extends PlasticWaste> plasticWastes) {
            
            // ... Delegate production of raw material to recycling center
            
            double microbeadMass = microbeadProducer.generateMicrobead(plasticWastes);
            
            // ... Produce bottles ...
        }
    }
}

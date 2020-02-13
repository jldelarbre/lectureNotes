package lectureNotes.lesson4.isp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ISP1 {
    
    interface Waste {}
    interface ChimicWaste extends Waste {}
    interface PlasticWaste extends Waste {}
    interface UnrecyclableWaste extends Waste {}

    // Interface used to generate report about recycled wastes
    interface PublicAuthoritiesRecyclingRateReporter {
        
        String generateOrganicWasteReport();
        
        String generateChimicWasteReport();
        
        String generatePlasticWasteReport();
        
        // many more ...
    }
    
    // Making RecyclingCenter extending PublicAuthoritiesRecyclingRateReporter is interface pollution
    // This 2 distinct responsibilities: clients for one of them are not necessary client for the
    // other one.
    // Even RecyclingCenter interface has many unrelated methods put together. As we will see
    // methods are not useful for the same client
    interface RecyclingCenter extends PublicAuthoritiesRecyclingRateReporter {
        
        // Assimilate garbage truck as a list for the sake of simplicity
        Map<Class<? extends Waste>, List<? extends Waste>> sortWastes(List<? extends Waste> garbageTruck);
        
        void incinerate(List<? extends UnrecyclableWaste> unrecyclableWastes);
        
        // return mass (kg) of microbead generated
        double generateMicrobead(List<? extends PlasticWaste> plasticWastes);
        
        double measureToxicityLevel(ChimicWaste chimicWaste);
        
        // many more ...
    }
    
    // The recycling center implementation class produces data after each recycling operation
    // So it naturally owns them to generate reports
    // It has also all the internal mechanisms to implements every RecyclingCenter interface
    static class RecyclingCenterImpl implements RecyclingCenter {
        
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
    
    // Municipal services is a client of the recycling center. But it needs it for 2
    // separate purposes:
    // Recycling waste and get statistics and information about them
    static class MunicipalServices {
        
        @SuppressWarnings("unused")
        void administerCityWaste(RecyclingCenter recyclingCenter) {
            
            // ... Get reports about waste
            
            String organicWasteReport = recyclingCenter.generateOrganicWasteReport();
            String chimicWasteReport = recyclingCenter.generateChimicWasteReport();
            String plasticWasteReport = recyclingCenter.generatePlasticWasteReport();
            
            // ... Analyse waste toxicity for the population
            
            ChimicWaste chimicWaste = null; // ...
            recyclingCenter.measureToxicityLevel(chimicWaste);
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
    
    // Bottle factory is another client for recycling center in order to get raw material for
    // bottle production
    static class BottleFactory {
        
        @SuppressWarnings("unused")
        void produceBottles(RecyclingCenter recyclingCenter, List<? extends PlasticWaste> plasticWastes) {
            
            // ... Delegate production of raw material to recycling center
            
            double microbeadMass = recyclingCenter.generateMicrobead(plasticWastes);
            
            // ... Produce bottles ...
        }
    }
}

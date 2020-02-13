package lectureNotes.lesson4.isp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ISP1b {
    
    interface Waste {}
    interface ChimicWaste extends Waste {}
    interface PlasticWaste extends Waste {}
    interface UnrecyclableWaste extends Waste {}

    interface PublicAuthoritiesRecyclingRateReporter {
        
        String generateOrganicWasteReport();
        
        String generateChimicWasteReport();
        
        String generatePlasticWasteReport();
        
        // many more ...
    }
    
    interface RecyclingCenter extends PublicAuthoritiesRecyclingRateReporter {
        
        // Assimilate garbage truck as a list for the sake of simplicity
        Map<Class<? extends Waste>, List<? extends Waste>> sortWastes(List<? extends Waste> garbageTruck);
        
        void incinerate(List<? extends UnrecyclableWaste> unrecyclableWastes);
        
        // return mass (kg) of microbead generated
        double generateMicrobead(List<? extends PlasticWaste> plasticWastes);
        
        double measureToxicityLevel(ChimicWaste chimicWaste);
        
        // many more ...
    }
    
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
    
    // Assume Bottle factory needs high quality microbead provided by a special implementation
    // of RecyclingCenter
    static class BottleFactory {
        
        @SuppressWarnings("unused")
        void produceBottles(RecyclingCenter recyclingCenter, List<? extends PlasticWaste> plasticWastes) {
            
            // ... Delegate production of raw material to recycling center
            
            double microbeadMass = recyclingCenter.generateMicrobead(plasticWastes);
            
            // ... Produce bottles ...
        }
    }
    
    // Create a new implementation of RecyclingCenter for the needs of BottleFactory.
    // Only "generateMicrobead" method is needed but we have to implement every other method
    // interface.
    // If interface was segregated, we do not have to write all those implementations
    static class RecyclingCenterHighQualityMicrobeadImpl implements RecyclingCenter {

        @Override public String generateOrganicWasteReport() { return "Force to generate report"; }
        @Override public String generateChimicWasteReport() { return "Force to generate report"; }
        @Override public String generatePlasticWasteReport() { return "Force to generate report"; }

        @Override
        public Map<Class<? extends Waste>, List<? extends Waste>> sortWastes(List<? extends Waste> garbageTruck) {
            Map<Class<? extends Waste>, List<? extends Waste>> output = new HashMap<>();
            
            // Need an unnecessary implementation
            
            return output;
        }

        @Override
        public void incinerate(List<? extends UnrecyclableWaste> unrecyclableWastes) {
            // Need an unnecessary implementation
        }

        @Override
        public double generateMicrobead(List<? extends PlasticWaste> plasticWastes) {
            double microbeadMass = 0.0;
            // Only method for which we really need an implementation
            //
            // Produce high quality microbead
            return microbeadMass;
        }

        @Override
        public double measureToxicityLevel(ChimicWaste chimicWaste) {
            double toxicity = 0.;
            // Need an unnecessary implementation
            return toxicity;
        }
    }
}

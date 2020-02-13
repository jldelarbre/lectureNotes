package lectureNotes.lesson4.isp;

public interface ISP3b {
    
    interface Waste {}
    interface ChimicWaste extends Waste {}

    interface PublicAuthoritiesRecyclingRateReporter {
        
        String generateOrganicWasteReport();
        String generateChimicWasteReport();
        String generatePlasticWasteReport();
    }
    
    interface PollutionAnalysisLaboratory {
        double measureToxicityLevel(ChimicWaste chimicWaste);
    }
    
    // Since recycling center is known through dedicated interfaces, refactoring it
    // in many implementation classes is really easy.
    // Recycling center implementation and its clients are loosely coupled
    static class RecyclingCenterImpl implements PublicAuthoritiesRecyclingRateReporter {
        
        String recyclingCenterData = ""; //...

        @Override public String generateOrganicWasteReport() { return "the report ..."; }
        @Override public String generateChimicWasteReport() { return "the report ..."; }
        @Override public String generatePlasticWasteReport() { return "the report ..."; }
    }

    static class PollutionAnalysisLaboratoryImpl implements PollutionAnalysisLaboratory {
        @Override
        public double measureToxicityLevel(ChimicWaste chimicWaste) {
            double toxicity = 0.;
            // Analyse chimicWaste toxicity
            return toxicity;
        }
    }
    
    // It is better to define administerCityWaste method in polyadic form
    // If a class implements many interfaces, it is better to know it through each interface
    static class MunicipalServices {
        
        @SuppressWarnings("unused")
        void administerCityWaste(PublicAuthoritiesRecyclingRateReporter publicAuthoritiesRecyclingRateReporter,
                                 PollutionAnalysisLaboratory pollutionAnalysisLaboratory) {
            
            // ... Get reports about waste
            
            String organicWasteReport = publicAuthoritiesRecyclingRateReporter.generateOrganicWasteReport();
            String chimicWasteReport = publicAuthoritiesRecyclingRateReporter.generateChimicWasteReport();
            String plasticWasteReport = publicAuthoritiesRecyclingRateReporter.generatePlasticWasteReport();
            
            // ... Analyse waste toxicity for the population
            
            ChimicWaste chimicWaste = null; // ...
            pollutionAnalysisLaboratory.measureToxicityLevel(chimicWaste);
            // ...
        }
    }
}

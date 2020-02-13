package lectureNotes.lesson4.isp;

public interface ISP3 {
    
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
    
    interface EnvironmentInformationCenter extends PublicAuthoritiesRecyclingRateReporter,
                                                   PollutionAnalysisLaboratory {
    }
    
    // We have removed some elements from recycling center just to focus on EnvironmentInformationCenter
    // methods
    static class RecyclingCenterImpl implements EnvironmentInformationCenter {
        
        String recyclingCenterData = ""; //...

        @Override public String generateOrganicWasteReport() { return "the report ..."; }
        @Override public String generateChimicWasteReport() { return "the report ..."; }
        @Override public String generatePlasticWasteReport() { return "the report ..."; }

        @Override
        public double measureToxicityLevel(ChimicWaste chimicWaste) {
            double toxicity = 0.;
            // Analyse chimicWaste toxicity
            return toxicity;
        }
    }
    
    // It is better to define administerCityWaste method in polyadic form.
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

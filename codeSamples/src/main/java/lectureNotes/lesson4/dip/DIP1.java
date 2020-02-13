package lectureNotes.lesson4.dip;

import java.util.ArrayList;
import java.util.List;

public class DIP1 {

    interface Waste {}
    interface RecoveredWaste {}
    
    // RecyclingCenter is a high level module. It has the knowledge for recycling and
    // defines the application policy
    //
    // It depends on lower level modules for sub tasks which are specific for individuals.
    // It is not reusable in other contexts: industrial, ...
    //
    // We can apply the same kind of transformation than with OCP, but we will more focused
    // on the structural implication of this transformation
    static class RecyclingCenter {
        
        private CityWasteCollector cityWasteCollector;
        private DomesticWasteRecoverer domesticWasteRecoverer;
        private IndividualRecoveredWasteVendor individualRecoveredWasteVendor;
        
        public void wasteProcessing() {
            List<Waste> collectedWastes = cityWasteCollector.collectWaste();
            List<RecoveredWaste> recoveredWastes = domesticWasteRecoverer.recoverWastes(collectedWastes);
            individualRecoveredWasteVendor.sell(recoveredWastes);
        }
    }
    
    // Lower level modules...
    
    static class CityWasteCollector {
        
        public List<Waste> collectWaste() {
            List<Waste> collectedWastes = new ArrayList<>();
            // Collect waste in the city
            return collectedWastes;
        }
    }
    
    static class DomesticWasteRecoverer {
        
        public List<RecoveredWaste> recoverWastes(List<Waste> wastes) {
            List<RecoveredWaste> recoveredWastes = new ArrayList<>();
            // Recover waste
            return recoveredWastes;
        }
    }
    
    static class IndividualRecoveredWasteVendor {
        
        public void sell(List<RecoveredWaste> recoveredWastes) {
            // Recovered wastes selling
        }
    }
}

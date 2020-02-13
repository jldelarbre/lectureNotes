package lectureNotes.lesson4.dip;

import java.util.ArrayList;
import java.util.List;

public class DIP2 {

    interface Waste {}
    interface RecoveredWaste {}
    
    // RecyclingCenter is a high level module. It is now a reusable component since it depends
    // on abstraction
    static class RecyclingCenter {
        
        private IWasteCollector wasteCollector;
        private IWasteRecoverer wasteRecoverer;
        private IRecoveredWasteVendor recoveredWasteVendor;
        
        void wasteProcessing() {
            List<Waste> collectedWastes = wasteCollector.collectWaste();
            List<RecoveredWaste> recoveredWastes = wasteRecoverer.recoverWastes(collectedWastes);
            recoveredWasteVendor.sell(recoveredWastes);
        }
    }
    
    // Definition of abstractions:
    // High level modules depends on them and use them.
    // Low level modules have to implements them
    
    interface IWasteCollector {
        List<Waste> collectWaste();
    }
    
    interface IWasteRecoverer {
        List<RecoveredWaste> recoverWastes(List<Waste> wastes);
    }
    
    interface IRecoveredWasteVendor {
        void sell(List<RecoveredWaste> recoveredWastes);
    }
    
    // Factories allows to build different kind of RecyclingCenter by injecting
    // different kind of sub modules (dependency injection)
    
    public static RecyclingCenter buildForIndividuals() {
        RecyclingCenter recyclingCenter = new RecyclingCenter();
        recyclingCenter.wasteCollector = new CityWasteCollector();
        recyclingCenter.wasteRecoverer = new DomesticWasteRecoverer();
        recyclingCenter.recoveredWasteVendor = new IndividualRecoveredWasteVendor();
        return recyclingCenter;
    }
    
    public static RecyclingCenter buildForIndustrials() {
        RecyclingCenter recyclingCenter = new RecyclingCenter();
        recyclingCenter.wasteCollector = new IndustrialWasteCollector();
        recyclingCenter.wasteRecoverer = new IndustrialWasteRecoverer();
        recyclingCenter.recoveredWasteVendor = new IndustrialRecoveredWasteVendor();
        return recyclingCenter;
    }
    
    // Lower level modules for individuals ...
    
    static class CityWasteCollector implements IWasteCollector {
        
        @Override
        public List<Waste> collectWaste() {
            List<Waste> collectedWastes = new ArrayList<>();
            // Collect waste in the city
            return collectedWastes;
        }
    }
    
    static class DomesticWasteRecoverer implements IWasteRecoverer {
        
        @Override
        public List<RecoveredWaste> recoverWastes(List<Waste> wastes) {
            List<RecoveredWaste> recoveredWastes = new ArrayList<>();
            // Recover waste
            return recoveredWastes;
        }
    }
    
    static class IndividualRecoveredWasteVendor implements IRecoveredWasteVendor {
        
        @Override
        public void sell(List<RecoveredWaste> recoveredWastes) {
            // Recovered wastes selling
        }
    }
    
    // Lower level modules for industry ...
    
    static class IndustrialWasteCollector implements IWasteCollector {
        
        @Override
        public List<Waste> collectWaste() {
            List<Waste> collectedWastes = new ArrayList<>();
            // Collect waste in industry
            return collectedWastes;
        }
    }
    
    static class IndustrialWasteRecoverer implements IWasteRecoverer {
        
        @Override
        public List<RecoveredWaste> recoverWastes(List<Waste> wastes) {
            List<RecoveredWaste> recoveredWastes = new ArrayList<>();
            // Recover waste
            return recoveredWastes;
        }
    }
    
    static class IndustrialRecoveredWasteVendor implements IRecoveredWasteVendor {
        
        @Override
        public void sell(List<RecoveredWaste> recoveredWastes) {
            // Recovered wastes selling
        }
    }
}

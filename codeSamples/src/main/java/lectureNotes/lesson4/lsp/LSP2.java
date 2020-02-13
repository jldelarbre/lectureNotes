package lectureNotes.lesson4.lsp;

import java.util.HashSet;
import java.util.Set;

public class LSP2 {

    interface SheetWaste {
        /**
         * @return Initial sheet mass (g)
         */
        double mass();
        
        /**
         * @return Mass of extractible Cellulosic Fiber from sheet waste to recycle. Recyclable mass
         *         is above 80% of initial mass. This condition defines a postcondition.
         *         It could be stronger in subclass (ie > 90%%)
         */
        double extractibleCellulosicFiberMass();
    }
    
    // First implementation that fulfills "SheetWaste" postconditions
    static class PaperSheet implements SheetWaste {
        @Override public double mass() { return 5; }
        @Override public double extractibleCellulosicFiberMass() { return 4; }
    }
    
    // Second implementation that fulfills "SheetWaste" postconditions
    static class CardboardSheet implements SheetWaste {
        @Override public double mass() { return 10; }
        @Override public double extractibleCellulosicFiberMass() { return 9; }
    }
    
    // Third implementation that DOES NOT fulfill "SheetWaste" postconditions
    static class PlasticSheet implements SheetWaste {
        @Override public double mass() { return 7; }
        @Override public double extractibleCellulosicFiberMass() { return 0; }
    }
    
    static class RecyclingCenter {
        final double MINIMAL_MASS_TO_PUT_IN_FILTER = 10_000;
        CellulosicFiberFilter cellulosicFiberFilter = new CellulosicFiberFilter();
        
        void recycleSheet(Set<? extends SheetWaste> sheets) {
            
            Set<SheetWaste> sheetsToFilter = extractSheetForFiltering(sheets);
            cellulosicFiberFilter.filterSheet(sheetsToFilter);
        }

        // Extract from input a certain amount of sheet to feed the "CellulosicFiberFilter"
        // Since "PlasticSheet" does not fulfill "SheetWaste" invariant not enough cellulosic
        // fiber could be enter into the filter
        private Set<SheetWaste> extractSheetForFiltering(Set<? extends SheetWaste> sheets) {
            Set<SheetWaste> sheetsToFilter = new HashSet<>();
            double totalMass = 0.0;
            for (SheetWaste sheet : sheets) {
                totalMass += sheet.mass();
                sheetsToFilter.add(sheet);
                if (totalMass > MINIMAL_MASS_TO_PUT_IN_FILTER) {
                    break;
                }
            }
            return sheetsToFilter;
        }
    }
    
    static class CellulosicFiberFilter {
        /**
         * @param sheetsToFilter For correct operation the filter needs a MINIMAL cellulosic fiber mass
         *                       If not enough cellulosic fiber is put inside the filter it will not
         *                       work well.
         *                       This is a precondition. It could be weakened in subclasses (ie = 0)
         */
        void filterSheet(Set<SheetWaste> sheetsToFilter) {
            // ...
        }
    }
}

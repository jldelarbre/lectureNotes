package lectureNotes.lesson4.ocp;

import java.util.List;

public class OCP1 {
    
    static class Waste {
    }

    // A first kind of specific waste
    static class OrganicWaste extends Waste {
        void recycleOrganicWaste() {
            // Anaerobic digestion
            // ...
        }
    }
    
    // A second kind of specific waste
    static class PlasticWaste extends Waste {
        void recyclePlasticWaste() {
            // Produce microbead
            // ...
        }
    }
    
    // A third kind of specific waste
    static class ElectronicWaste extends Waste {
        void recycleElectronicWaste() {
            // Send to south east Asia
            // ...
        }
    }
    
    static class RecyclingCenter {
        void recycleWaste(List<? extends Waste> wastes) {
            // The "recycleWaste" method of "RecyclingCenter" has to be changed each time
            // a new kind a waste is created
            for (Waste waste : wastes) {
                if (waste instanceof OrganicWaste) {
                    OrganicWaste organicWaste = (OrganicWaste) waste;
                    organicWaste.recycleOrganicWaste();
                } else if (waste instanceof PlasticWaste) {
                    PlasticWaste plasticWaste = (PlasticWaste) waste;
                    plasticWaste.recyclePlasticWaste();
                } else if (waste instanceof ElectronicWaste) {
                    ElectronicWaste electronicWaste = (ElectronicWaste) waste;
                    electronicWaste.recycleElectronicWaste();
                }
            }
        }
    }
}

package lectureNotes.lesson5.command;

import java.util.Map;

public class Command1 {

    // Some railway equipment that need maintenance
    // --------------------------------------------
    
    interface RailwayEquipment {}
    interface Locomotive extends RailwayEquipment {}
    interface PassengerCar extends RailwayEquipment {}
    interface SignalingSystem extends RailwayEquipment {}
    interface Station extends RailwayEquipment {}
    interface Platform extends RailwayEquipment {}
    // ...
    
    interface MaintainableEquipment {
        String getMaintenanceData();
    }
    
    // Use SRP, separate interface that concerns railway equipment from maintainable equipment
    static class LocomotiveImp implements Locomotive, MaintainableEquipment {
        @Override
        public String getMaintenanceData() {
            return "locoData";
        }
        // ...
    }
    
    static class PlatformImp implements Platform, MaintainableEquipment {
        @Override
        public String getMaintenanceData() {
            return "platformData";
        }
        // ...
    }
    
    // Component responsible to operate maintenance operation,
    // maintenance process apply command pattern: only one method
    // ----------------------------------------------------------
    
    interface MaintenanceProcess {
        void execute();
    }
    
    static class LocomotiveMaintenanceProcess implements MaintenanceProcess {
        Locomotive locomotive;
        @Override
        public void execute() {
            // ...
        }
    }
    
    // Other maintenance process implementation ...
    
    static class PlatformMaintenanceProcess implements MaintenanceProcess {
        Platform platform;
        @Override
        public void execute() {
            // ...
        }
    }
    
    // Maintenance scheduler is a client for the command object.
    // It is extensible (OCP) since it do not depends on a specific maintenance
    // process implementation (DIP)
    // ------------------------------------------------------------------------
    
    static class MaintenanceScheduler {
        Map<MaintainableEquipment, MaintenanceProcess> equipmentMaintenanceMap;
        
        @SuppressWarnings("unused")
        void scheduleMaintenance() {
            
            for (MaintainableEquipment maintainableEquipment : equipmentMaintenanceMap.keySet()) {
                String maintenanceData = maintainableEquipment.getMaintenanceData();
                // Take decision to launch maintenance according to
                // - maintenance data
                // - calendar
                // - budget
                // ...
                
                MaintenanceProcess maintenanceProcess = equipmentMaintenanceMap.get(maintainableEquipment);
                // The command object is very simple to use and handle
                maintenanceProcess.execute();
            }
        }
    }
}

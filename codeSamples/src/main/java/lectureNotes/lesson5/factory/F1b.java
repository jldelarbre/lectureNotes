package lectureNotes.lesson5.factory;

public class F1b {

    /////////////////
    // API package //
    /////////////////
    
    interface RailNetwork {
        void transportPeople();
    }
    
    interface RailwayMaintenanceService {
        void repairRailway();
    }
    
    ////////////////////////////
    // Implementation package //
    ////////////////////////////
    
    static class RailNetworkImp implements RailNetwork {

        private final RailwayMaintenanceService railwayMaintenanceService;
        
        public RailNetworkImp(RailwayMaintenanceService railwayMaintenanceService) {
            this.railwayMaintenanceService = railwayMaintenanceService;
        }

        @Override
        public void transportPeople() {
            // ...
            railwayMaintenanceService.repairRailway();
            // ...
        }
    }
    
    static class RailwayMaintenanceServiceImp implements RailwayMaintenanceService {
        
        RailwayMaintenanceServiceImp() {}
        
        // Optional static factory method, not necessary if "RailwayMaintenanceService" only built
        // by the other "buildRailNetwork" static factory
        static RailwayMaintenanceService build() {
            return new RailwayMaintenanceServiceImp();
        }
        
        @Override
        public void repairRailway() { /* ... */ }
    }
    
    static class RailwayMaintenanceServiceImpBis implements RailwayMaintenanceService {
        
        RailwayMaintenanceServiceImpBis() {}
        
        @Override
        public void repairRailway() { /* ... */ }
    }
    
    // FACTORIES
    // ---------
    // A static factory method is STATIC
    //
    // Linking against static things is one of the strongest coupling you can do.
    // Factories and application main can use them since they aim to wire components
    // and hence couple them together (but loosely)
    
    // Factory injects collaborators
    // It selects the appropriate instance for the object under construction which only know
    // abstraction ("RailwayMaintenanceServiceImp" or "RailwayMaintenanceServiceImpBis")
    public static RailNetwork buildRailNetwork() {
        RailwayMaintenanceService railwayMaintenanceService = RailwayMaintenanceServiceImp.build();
        return new RailNetworkImp(railwayMaintenanceService);
    }
    
    // Factory can call directly constructor of sub element (collaborator) to construct
    // if they are part of the same package of main object under construction
    // Hence, there is no absolute need for a factory for "RailwayMaintenanceService"
    public static RailNetwork buildRailNetworkBis() {
        RailwayMaintenanceService railwayMaintenanceService = new RailwayMaintenanceServiceImpBis();
        return new RailNetworkImp(railwayMaintenanceService);
    }
    
    //////////////////////
    // Application main //
    //////////////////////
    
    public static void main(String[] args) {
        
        RailNetwork railway = buildRailNetwork();
        railway.transportPeople();
    }
}

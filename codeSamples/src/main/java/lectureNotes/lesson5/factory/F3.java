package lectureNotes.lesson5.factory;

public class F3 {

	////////////////////////////////
	// Business layer API package //
	////////////////////////////////
    
    interface RailNetwork {
        void transportPeople();
    }
    
    interface RailwayMaintenanceService {
        void repairRailway();
    }

    ///////////////////////////////////////////
    // Business layer implementation package //
    ///////////////////////////////////////////
    
    static class RailNetworkImp implements RailNetwork {

    	// A dependency to inject
        private final RailwayMaintenanceService railwayMaintenanceService;
        
        // Constructor does not do work: only initializing fields
        // Set dependencies
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
        
        @Override
        public void repairRailway() { /* ... */ }
    }
    
    // Optional static factory method, not necessary if "RailwayMaintenanceService" only built
    // by "buildRailNetwork" factory method
    static RailwayMaintenanceService buildRailwayMaintenanceService() {
    	return new RailwayMaintenanceServiceImp();
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
    // abstraction
    public static RailNetwork buildRailNetwork() {
        RailwayMaintenanceService railwayMaintenanceService = buildRailwayMaintenanceService();
        return new RailNetworkImp(railwayMaintenanceService);
    }
    
    // Factory can call directly constructor of sub element (collaborator) to build object.
    // It's OK since RailNetwork and RailwayMaintenanceService are part of the same package.
    // Hence, there is no absolute need for a factory for "RailwayMaintenanceService"
    public static RailNetwork buildRailNetworkBis() {
        RailwayMaintenanceService railwayMaintenanceService = new RailwayMaintenanceServiceImp();
        return new RailNetworkImp(railwayMaintenanceService);
    }
    
    //////////////////////
    // Application main //
    //////////////////////
    
    public static void main(String[] args) {
        
    	// We are agnostic of RailNetwork dependencies
    	// The usage of a factory allows to ignore changes of implementation details
    	// like collaborators list but the application remains testable
        RailNetwork railway = buildRailNetwork();
        railway.transportPeople();
    }
}

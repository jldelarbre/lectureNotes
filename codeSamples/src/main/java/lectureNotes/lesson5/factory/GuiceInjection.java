package lectureNotes.lesson5.factory;

import javax.inject.Inject;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceInjection {

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
        
        @Inject
        RailNetworkImp(RailwayMaintenanceService railwayMaintenanceService) {
            this.railwayMaintenanceService = railwayMaintenanceService;
        }

        @Override
        public void transportPeople() {
            // ...
            railwayMaintenanceService.repairRailway();
            // ...
            System.out.println("I transport people");
        }
    }
    
    static class RailwayMaintenanceServiceImp implements RailwayMaintenanceService {
        
        RailwayMaintenanceServiceImp() {}
        
        @Override
        public void repairRailway() { System.out.println("I repair railway"); }
    }
    
    ///////////
    // Guice //
    ///////////
    
    static class RailwayModule extends AbstractModule {
        
        @Override
        protected void configure() {
            bind(RailwayMaintenanceService.class).to(RailwayMaintenanceServiceImp.class);
        }
    }
    
    //////////////////////
    // Application main //
    //////////////////////
    
    public static void main(String[] args) {
        
        Injector injector = Guice.createInjector(new RailwayModule());
        RailNetwork railway = injector.getInstance(RailNetworkImp.class);
        railway.transportPeople();
    }
}

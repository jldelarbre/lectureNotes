package lectureNotes.lesson5.factory;

public class F5 {
    // compagny transprot people dans business layer
    ////////////////////////////////
    // Business layer API package //
    ////////////////////////////////
    
    interface RailNetwork {
        void transportPeople();
    }
    
    interface RailwayCompagny {
        void peopleNeedTransportCallback();
    }

    ///////////////////////////////////////////
    // Business layer implementation package //
    ///////////////////////////////////////////
    
    static class RailNetworkImp implements RailNetwork {

        public RailNetworkImp() {}

        @Override
        public void transportPeople() { /* ... */ }
    }
    
    static class RailwayCompagnyImpl implements RailwayCompagny {
        
        private final RailNetwork railNetwork;
        
        private RailwayCompagnyImpl(RailNetwork railNetwork) {
            this.railNetwork = railNetwork;
        }
        
        @Override
        public void peopleNeedTransportCallback() {
            // ...
            
            // Business layer uses RailNetwork abstraction but do not
            // implement it (received by injection)
            railNetwork.transportPeople();
            // ...
        }
    }
    
    public static RailNetwork buildRailNetwork() {
        return new RailNetworkImp();
    }
    
    //////////////////////
    // Application main //
    //////////////////////
    
    public static void main(String[] args) {
        
        // Part of the application responsible of application wiring
        // It can statically link to "buildRailNetwork" factory method
        // No business intelligence takes place here
        
        RailNetwork railway = buildRailNetwork();
        RailwayCompagny railwayCompagny = new RailwayCompagnyImpl(railway);
        
        // Link people need transport event to "peopleNeedTransportCallback"
    }
}

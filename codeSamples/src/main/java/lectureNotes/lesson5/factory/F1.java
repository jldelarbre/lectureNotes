package lectureNotes.lesson5.factory;

public class F1 {

    ////////////////////////////////
    // Business layer API package //
    ////////////////////////////////
    
    interface RailNetwork {
        void transportPeople();
    }

    ///////////////////////////////////////////
    // Business layer implementation package //
    ///////////////////////////////////////////
    
    static class RailNetworkImp implements RailNetwork {

        private RailNetworkImp() {}
        
        @Override
        public void transportPeople() { /* ... */ }
    }
    
    // THE FACTORY METHOD
    public static RailNetwork buildRailNetwork() {
        return new RailNetworkImp();
    }
    
    /////////////////
    // Application //
    /////////////////
    
    public static void main(String[] args) {
        // Why adding this layer of factory method instead of a direct call to the constructor ?
        RailNetwork railway = buildRailNetwork();
        railway.transportPeople();
    }
}

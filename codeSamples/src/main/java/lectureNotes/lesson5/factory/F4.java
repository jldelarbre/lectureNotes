package lectureNotes.lesson5.factory;

public class F4 {

    ////////////////////////////////
    // Business layer API package //
    ////////////////////////////////
    
    interface RailNetwork {
        void transportPeople();
    }

    ///////////////////////////////////////////
    // Business layer implementation package //
    ///////////////////////////////////////////
    
    static class ElectricRailNetworkImp implements RailNetwork {

        public ElectricRailNetworkImp() {}

        @Override
        public void transportPeople() { /* ... */ }
    }
    
    static class DieselRailNetworkImp implements RailNetwork {
        
        public DieselRailNetworkImp() {}
        
        @Override
        public void transportPeople() { /* ... */ }
    }
    
    // Classic factory: Select implementation to return
    // Not just a method but a whole class here. It is
    // not relevant in this case but may be useful in
    // more complex case
    // ------------------------------------------------
    static class RailNetworkFactory {
        
        public RailNetwork build(String type) {
            switch (type) {
            case "ELECTRIC":
                return new ElectricRailNetworkImp();
            case "DIESEL":
                return new DieselRailNetworkImp();
            }
            throw new RuntimeException("Unknown type");
        }
    }
    
    //////////////////////
    // Application main //
    //////////////////////
    
    public static void main(String[] args) {
        RailNetworkFactory railNetworkFactory = new RailNetworkFactory();
        
        RailNetwork railway = railNetworkFactory.build("ELECTRIC");
        railway.transportPeople();
    }
}

package lectureNotes.lesson5.factory;

public class F2 {

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
        
        private static final double DEFAULT_CAPACITY = 100.0;
        
        @SuppressWarnings("unused")
		private final double capacity;

        // Constructor does not do work: only initializing fields
        // Constructors shall never be public: private or package
        private RailNetworkImp(double capacity) {
            this.capacity = capacity;
        }
        
        @Override
        public void transportPeople() { /* ... */ }
    }

    ///////////////////////////////////////////
    // Business layer implementation package //
    //              PUBLIC PART              //
    ///////////////////////////////////////////
    
    // Simplest kind of factory: static factory method
    //
    // - Allow to restrict constructor access
    // - Initialize data
    // - Do not return a concrete type but an interface (allow to change later with another
    //   implementation)
    public static RailNetwork buildRailNetwork() {
    	return new RailNetworkImp(RailNetworkImp.DEFAULT_CAPACITY);
    }
    
    // Allow many way of building instances (with same signature) impossible to do with
    // constructors
    //
    public static RailNetwork buildRailNetworkWithCapacity(double capacity) {
    	return new RailNetworkImp(capacity);
    }
    public static RailNetwork buildResilientRailNetworkWithCapacity(double resilientCapacity) {
    	return new RailNetworkImp(2.0 * resilientCapacity);
    }
    
    /////////////////
    // Application //
    /////////////////
    
    public static void main(String[] args) {
        
        // A static factory method is STATIC
        //
        // Linking against static things is one of the strongest coupling you can do.
        // Factory and application main can do such since they aim to wire components
        // and hence couple them together
        RailNetwork railway = buildRailNetwork();
        railway.transportPeople();
    }
}

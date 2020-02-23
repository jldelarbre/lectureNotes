package lectureNotes.lesson5.factory;

public class F1 {

    interface RailNetwork {
        void transportPeople();

        /**
         * @return capacity in millions people per year
         */
        double getCapacity();
        
        /**
         * @return peak capacity in thousands people (holiday period)
         */
        double getPeakCapacity();
    }
    
    static class RailNetworkImp implements RailNetwork {
        
        private static final double DEFAULT_CAPACITY = 100.0;
        private static final double DEFAULT_PEAK_CAPACITY = 5.0;
        
        private final double capacity;
        private final double peakCapacity;

        // Constructor does not do work: only initializing fields
        // Constructors shall never be public: private or package
        private RailNetworkImp(double capacity, double peakCapacity) {
            this.capacity = capacity;
            this.peakCapacity = peakCapacity;
        }

        // Simplest kind of factory: static factory method
        //
        // - Allow to restrict constructor access
        // - Initialize data
        // - Do not return a concrete type but an interface (allow to change later with another
        //   implementation)
        public static RailNetwork build() {
            return new RailNetworkImp(DEFAULT_CAPACITY, DEFAULT_PEAK_CAPACITY);
        }
        
        // Allow many way of building instances (with same signature) impossible to do with
        // constructors
        //
        // RailNetworkImp(double capacity) {...}
        // RailNetworkImp(double peakCapacity) {...}
        //
        public static RailNetwork buildWithCapacity(double capacity) {
            return new RailNetworkImp(capacity, DEFAULT_PEAK_CAPACITY);
        }
        
        public static RailNetwork buildWithPeakCapacity(double peakCapacity) {
            return new RailNetworkImp(peakCapacity, DEFAULT_PEAK_CAPACITY);
        }
        
        @Override
        public void transportPeople() {
            // ...
        }
        
        @Override
        public double getCapacity() {
            return capacity;
        }
        
        @Override
        public double getPeakCapacity() {
            return peakCapacity;
        }
    }
    
    public static void main(String[] args) {
        
        // A static factory method is STATIC
        //
        // Linking against static things is one of the strongest coupling you can do.
        // Factories and application main can use them since they aim to wire components
        // and hence couple them together (but loosely)
        RailNetwork railway = RailNetworkImp.build();
        railway.transportPeople();
    }
}

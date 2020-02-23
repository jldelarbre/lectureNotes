package lectureNotes.lesson5.factory;

import lectureNotes.lesson5.factory.F4.RailwayLine.Factory;

public class F4 {

    /////////////////
    // API package //
    /////////////////
    
    interface RailNetwork {
        void addRailway(RailwayLine railwayLine);
    }
    
    interface RailwayLine {
        // ...
        
        interface Factory {
            RailwayLine build(String departureCity, String destinationCity);
        }
    }
    
    // No more or less than the classic "abstract factory" pattern
    // 
    // A factory of factory
    // Sometimes a factory could be complex object that needs a factory to be also
    // constructed.
    interface RailwayLineFactoryFactory {
        RailwayLine.Factory build(String kindOfLine);
    }
    
    interface MinistryOfTransport {
        void manageTransportationInCountry();
    }
        
    ////////////////////////////
    // Implementation package //
    ////////////////////////////

    static class MinistryOfTransportImp implements MinistryOfTransport {
        
        private RailNetwork railNetwork;
        private RailwayLineFactoryFactory railwayLineFactoryFactory;
        
        @Override
        public void manageTransportationInCountry() {
            String departureCity1 = "...";
            String destinationCity1 = "...";
            // ...
            String kindOfLine1 = "HighSpeedLine";
            
            // A client may need to build its own factory and even factory of factory since
            // parameters for build could not be known at its construction time.
            // So injection is not possible.
            // Information for build is only available at runtime.
            Factory highSpeedLineFactory = railwayLineFactoryFactory.build(kindOfLine1);
            
            RailwayLine railwayLine1 = highSpeedLineFactory.build(departureCity1, destinationCity1);
            
            railNetwork.addRailway(railwayLine1);
            
            ////////////////////////////////////////////////////////////////////////////////////////
            
            String departureCity2 = "...";
            String destinationCity2 = "...";
            // ...
            String kindOfLine2 = "normalLine";
            
            Factory normalLineFactory = railwayLineFactoryFactory.build(kindOfLine2);
            
            RailwayLine railwayLine2 = normalLineFactory.build(departureCity2, destinationCity2);
            
            railNetwork.addRailway(railwayLine2);
        }
    }
    
    static class HighSpeedRailwayLine implements RailwayLine.Factory {

        @Override
        public RailwayLine build(String departureCity, String destinationCity) {
            RailwayLine railwayLine = null;
            // Build the line
            return railwayLine;
        }
    }
    
    
    static class NormalRailwayLine implements RailwayLine.Factory {
        
        @Override
        public RailwayLine build(String departureCity, String destinationCity) {
            RailwayLine railwayLine = null;
            // Build the line
            return railwayLine;
        }
    }
    
    static class RailwayLineFactoryFactoryImp implements RailwayLineFactoryFactory {

        @Override
        public Factory build(String kindOfLine) {
            RailwayLine.Factory railwayLineFactory = null;
            
            switch (kindOfLine) {
            case "HighSpeedLine":
                // Build line
                break;
            case "normalLine":
                // Build line
                break;
            }
            
            return railwayLineFactory;
        }
    }
}

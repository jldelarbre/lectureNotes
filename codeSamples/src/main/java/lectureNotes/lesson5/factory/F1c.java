package lectureNotes.lesson5.factory;

import java.util.HashMap;
import java.util.Map;

public class F1c {

    /////////////////
    // API package //
    /////////////////
    
    interface Railway {
        double getSpeedLimit(double mileageFromTerminus);
    }
    
    // Simple (immutable) data structure class may be part of an API
    static final class SpeedLimitSign {
        private final double speedLimit;
        
        SpeedLimitSign(double speedLimit) {
            this.speedLimit = speedLimit;
        }
        
        private static final Map<Double, SpeedLimitSign> builtSpeedLimitSign = new HashMap<>();
        
        // The factory allows to managed the instance to be returned:
        // Really useful with immutable elements to always get the same instance
        // with identical elements
        //
        // Also allow to build singleton without using static reference to it
        public static SpeedLimitSign build(double speedLimit) {
            SpeedLimitSign returnedValue;
            if (!builtSpeedLimitSign.containsKey(speedLimit)) {
                returnedValue = new SpeedLimitSign(speedLimit);
                builtSpeedLimitSign.put(speedLimit, returnedValue);
            } else {
                returnedValue = builtSpeedLimitSign.get(speedLimit);
            }
            
            return returnedValue;
        }
        
        double getSpeedLimit() {
            return speedLimit;
        }
    }
    
    ////////////////////////////
    // Implementation package //
    ////////////////////////////
    
    static class RailwayImp implements Railway {

        private final Map<Double, SpeedLimitSign> speedLimitAtPositionOnTrack;
        
        public RailwayImp(Map<Double, SpeedLimitSign> speedLimitAtPositionOnTrack) {
            this.speedLimitAtPositionOnTrack = speedLimitAtPositionOnTrack;
        }

        @Override
        public double getSpeedLimit(double mileageFromTerminus) {
            Double previousSignPositionOnTrack = speedLimitAtPositionOnTrack.keySet().stream()
                .sorted()
                .filter(positionOnTrack -> mileageFromTerminus > positionOnTrack)
                .findFirst()
                .get();
            
            SpeedLimitSign speedLimitSign = speedLimitAtPositionOnTrack.get(previousSignPositionOnTrack);
            return speedLimitSign.getSpeedLimit();
        }
    }
    
    // Factories can use other factories
    public static Railway buildRailway() {
        Map<Double, SpeedLimitSign> speedLimitAtPositionOnTrack = new HashMap<>();
        
        speedLimitAtPositionOnTrack.put(0., SpeedLimitSign.build(50));
        speedLimitAtPositionOnTrack.put(10., SpeedLimitSign.build(150));
        speedLimitAtPositionOnTrack.put(150., SpeedLimitSign.build(200));
        speedLimitAtPositionOnTrack.put(300., SpeedLimitSign.build(50));
        
        return new RailwayImp(speedLimitAtPositionOnTrack);
    }
    
    ////////////
    // Client //
    ////////////

    static class Train {
        
        @SuppressWarnings("unused")
        void runOnRailway(Railway railway) {
            // Start journey
            double mileageTraveled = 0.;
            // ...
            // mileage increases...
            // ...
            double speedLimit = railway.getSpeedLimit(mileageTraveled);
            // Adjust speed ...
        }
    }
    
    ///////////
    // Tests //
    ///////////
    
    // Factory for test purpose allows to always get a fresh instance on every tests.
    // SpeedLimitSign constructor has package visibility so you can build many factories
    // for different needs
    public static SpeedLimitSign buildForTest(double speedLimit) {
        return new SpeedLimitSign(speedLimit);
    }
}

package lectureNotes.lesson5.factory;

import java.util.HashMap;
import java.util.Map;

public class F3 {

    /////////////////
    // API package //
    /////////////////
    
    interface Railway {
        double getSpeedLimit(double mileageFromTerminus);
    }
    
    interface SpeedLimitEstimator {
        /**
         * Estimate for this portion of the railway what is the maximum velocity reachable
         */
        double estimateSpeedLimit(double mileageFromTerminus);
    }
    
    ////////////////////////////
    // Implementation package //
    ////////////////////////////
    
    static final class SpeedLimitSign {
        private final double speedLimit;
        
        SpeedLimitSign(double speedLimit) {
            this.speedLimit = speedLimit;
        }
        
        double getSpeedLimit() {
            return speedLimit;
        }
    }
    
    static class SpeedLimitEstimatorImp implements SpeedLimitEstimator {
        
        public static SpeedLimitEstimator build() {
            return new SpeedLimitEstimatorImp();
        }
        
        @SuppressWarnings("unused")
        @Override
        public double estimateSpeedLimit(double mileageFromTerminus) {
            double calculatedSpeedLimit = 0.;
            // Compute speed at given position
            return 0;
        }
    }
    
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
    
    static class RailwayFactory1 {
        
        // A factory is still normal code: it could have dependencies (known as abstraction
        // to be injected).
        // Factories may need to be tested
        private final SpeedLimitEstimator speedLimitEstimator;
        
        RailwayFactory1(SpeedLimitEstimator speedLimitEstimator) {
            this.speedLimitEstimator = speedLimitEstimator;
        }

        public Railway build() {
            Map<Double, SpeedLimitSign> speedLimitAtPositionOnTrack = new HashMap<>();
        
            processSection(speedLimitAtPositionOnTrack, 0.);
            processSection(speedLimitAtPositionOnTrack, 10.);
            processSection(speedLimitAtPositionOnTrack, 150.);
            processSection(speedLimitAtPositionOnTrack, 300.);
            
            return new RailwayImp(speedLimitAtPositionOnTrack);
        }

        private void processSection(Map<Double, SpeedLimitSign> speedLimitAtPositionOnTrack, double position) {
            double estimatedSpeedLimit = speedLimitEstimator.estimateSpeedLimit(position);
            speedLimitAtPositionOnTrack.put(position, new SpeedLimitSign(estimatedSpeedLimit));
        }
    }
    
    static class RailwayFactory2 {
        
        public Railway build() {
            Map<Double, SpeedLimitSign> speedLimitAtPositionOnTrack = new HashMap<>();
            
            processSection(speedLimitAtPositionOnTrack, 0.);
            processSection(speedLimitAtPositionOnTrack, 10.);
            processSection(speedLimitAtPositionOnTrack, 150.);
            processSection(speedLimitAtPositionOnTrack, 300.);
            
            return new RailwayImp(speedLimitAtPositionOnTrack);
        }
        
        private void processSection(Map<Double, SpeedLimitSign> speedLimitAtPositionOnTrack, double position) {
            // A factory could depend on other factories for building necessary elements, here
            // "SpeedLimitEstimatorImp.build"
            SpeedLimitEstimator speedLimitEstimator = SpeedLimitEstimatorImp.build();
            
            // Calling "SpeedLimitEstimatorImp" constructor for RailwayFactory is wrong:
            // SpeedLimitEstimator speedLimitEstimator = new SpeedLimitEstimatorImp();
            //
            // Only factories are allowed to call constructors and only to build elements,
            // not to construct resources they need
            
            double estimatedSpeedLimit = speedLimitEstimator.estimateSpeedLimit(position);
            speedLimitAtPositionOnTrack.put(position, new SpeedLimitSign(estimatedSpeedLimit));
        }
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
}

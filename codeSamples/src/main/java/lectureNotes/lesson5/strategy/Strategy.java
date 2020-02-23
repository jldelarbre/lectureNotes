package lectureNotes.lesson5.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Strategy {

    interface Train {}
    interface Platform{}
    
    // Strategy: Abstraction for algorithms of train versus platform allocation
    // ------------------------------------------------------------------------
    
    interface TrainToPlatformAllocator {
        Map<Train, Platform> computeBestAllocation(List<Train> trains);
    }
    
    static class TrainToPlatformAllocatorOptimizeTravelerMovements implements TrainToPlatformAllocator {
        @Override
        public Map<Train, Platform> computeBestAllocation(List<Train> trains) {
            Map<Train, Platform> optimizationResults = new HashMap<>();
            
            // Optimize traveler movement: Let the travelers go to the nearest platform when
            // they have to change train
            
            return optimizationResults;
        }
    }
    
    static class TrainToPlatformAllocatorOptimizePlatformUsage implements TrainToPlatformAllocator {
        @Override
        public Map<Train, Platform> computeBestAllocation(List<Train> trains) {
            Map<Train, Platform> optimizationResults = new HashMap<>();
            
            // Optimize platform usage: Reduce cost maintenance of platforms, let as much as possible
            // some platform unused
            
            return optimizationResults;
        }
    }
    
    // Client of TrainToPlatformAllocator, the whole station traffic regulator
    // -----------------------------------------------------------------------
    
    static class StationTrafficRegulator {
        TrainToPlatformAllocator trainToPlatformAllocator;
        
        // Strategies could be changed at runtime
        public void setTrainToPlatformAllocator(TrainToPlatformAllocator trainToPlatformAllocator) {
            this.trainToPlatformAllocator = trainToPlatformAllocator;
        }
        
        public void regulateTraffic() {
            // Regulate Buses
            // ...
            
            // Regulate subway
            // ...
            
            // Regulate trains
            // ...
            List<Train> trains = new ArrayList<>();
            // Get train of the day
            trainToPlatformAllocator.computeBestAllocation(trains);
            // ...
        }
    }
}

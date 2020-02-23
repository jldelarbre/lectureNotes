package lectureNotes.lesson5.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemplateMethod {

    interface Train {}
    interface Platform{}
    
    // Abstract class for station traffic regulation:
    // regulateTraffic is a template method that used abstract methods implemented in
    // sub classes implements "train to platform algorithm"
    //
    // More coupling is induced by "template method" than "strategy" even if they serve
    // the same purpose
    
    static abstract class StationTrafficRegulator {
        
        public void regulateTraffic() {
            // Regulate Buses
            // ...
            
            // Regulate subway
            // ...
            
            // Regulate trains
            // ...
            List<Train> trains = new ArrayList<>();
            // Get train of the day
            computeBestAllocation(trains);
            // ...
        }
        
        protected abstract Map<Train, Platform> computeBestAllocation(List<Train> trains);
    }
    
    static class StationTrafficRegulatorOptimizeTravelerMovements extends StationTrafficRegulator {
        @Override
        public Map<Train, Platform> computeBestAllocation(List<Train> trains) {
            Map<Train, Platform> optimizationResults = new HashMap<>();
            
            // Optimize traveler movement: Let the travelers go to the nearest platform when
            // they have to change train
            
            return optimizationResults;
        }
    }
    
    static class StationTrafficRegulatorOptimizePlatformUsage extends StationTrafficRegulator {
        @Override
        public Map<Train, Platform> computeBestAllocation(List<Train> trains) {
            Map<Train, Platform> optimizationResults = new HashMap<>();
            
            // Optimize platform usage: Reduce cost maintenance of platforms, let as much as possible
            // some platform unused
            
            return optimizationResults;
        }
    }
}

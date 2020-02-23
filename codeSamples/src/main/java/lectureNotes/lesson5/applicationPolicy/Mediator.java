package lectureNotes.lesson5.applicationPolicy;

import java.util.Observable;
import java.util.Observer;

public class Mediator {

    // Responsible to display information to travelers in public space among stations of the network
    // It is independent (and even agnostic) from NetworkTrafficRegulator
    static class TravelerInformationSystem {
        void displayInterruptionForUnattendedBaggage() {
            // ...
        }
        
        void displayInterruptionForStrike() {
            // ...
        }
        
        void displayFreeTransportationForPollutionPeak() {
            // ...
        }
        
        // ...
    }
    
    // Responsible to regulate traffic; it could enter in many states depending on events like:
    // - Unattended baggage
    // - Strike
    // - Pollution peak
    // It is independent (and even agnostic) from TravelerInformationSystem
    static class NetworkTrafficRegulator extends Observable {
        
        void regulateTraffic() {
            // ...
            
            // Detect unattended baggage
            notifyObservers("UnattendedBaggage");
            
            // ...
            
            // Strike warning
            notifyObservers("Strike");
            
            // ...
            
            // Detect pollution peak
            notifyObservers("PollutionPeak");
        }
    }
    
    // The mediator impose a policy on the application: TravelerInformationSystem displays info to
    // travelers according traffic regulator state.
    // Here we are using observer and observable but it just a mean to make it work (nothing to do
    // with mediator)
    static class TravelerInfoToTrafficRegulationMediator implements Observer {

        private TravelerInformationSystem travelerInformationSystem;
        
        @Override
        public void update(Observable o, Object arg) {
            switch ((String) arg) {
            case "UnattendedBaggage":
                travelerInformationSystem.displayInterruptionForUnattendedBaggage();
                break;
            case "Strike":
                travelerInformationSystem.displayInterruptionForStrike();
                break;
            case "PollutionPeak":
                travelerInformationSystem.displayFreeTransportationForPollutionPeak();
                break;
            }
        }
        
    }
}

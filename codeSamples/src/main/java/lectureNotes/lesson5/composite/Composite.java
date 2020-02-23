package lectureNotes.lesson5.composite;

import java.util.List;

public class Composite {

    // Component on which we need to set air conditioning
    static class RailVehicle {
        void setAirCondionning(double temperature) {/* ... */}
    }
    
    // Composite component on which we could set air conditioning in the same manner as
    // a single element. We do not need to manage cardinality
    static class CompositeRailVehicle extends RailVehicle {
        
        private List<RailVehicle> children;
        
        void add(RailVehicle railVehicle) {/* ... */}
        
        @Override
        void setAirCondionning(double temperature) {
            for (RailVehicle child : children) {
                child.setAirCondionning(temperature);
            }
        }
    }
    
    // Only one element rail vehicle
    static class Railcar extends RailVehicle {}
    
    // Rail vehicle component
    static class PassengerCar extends RailVehicle {}
    
    // PassengerConfortSystem is able to manage air conditioning for any rail vehicle composed
    // of sub elements or not
    static class PassengerConfortSystem {
        
        void regulatePassengerConfort(RailVehicle railVehicle) {
            // ...
            
            railVehicle.setAirCondionning(20.);
            
            // ...
        }
    }
    
    public static void main(String[] args) {
        PassengerConfortSystem passengerConfortSystem = new PassengerConfortSystem();
        
        CompositeRailVehicle trainWithManyElement = new CompositeRailVehicle();
        trainWithManyElement.add(new PassengerCar());
        trainWithManyElement.add(new PassengerCar());
        trainWithManyElement.add(new PassengerCar());
        trainWithManyElement.add(new PassengerCar());
        
        passengerConfortSystem.regulatePassengerConfort(trainWithManyElement);
        
        
        // Train with only one element
        Railcar railcar = new Railcar();
        
        passengerConfortSystem.regulatePassengerConfort(railcar);
    }
}

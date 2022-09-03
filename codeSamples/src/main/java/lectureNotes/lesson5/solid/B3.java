package lectureNotes.lesson5.solid;

public class B3 {
    
    interface Train {
        void runOnTrack();
    }
    
    // Use SRP, the engine is not managed by Train but it is the responsibility of a dedicated class
    
    static final class ElectricTravelingTrainImpl implements Train {
        ElectricEngine electricEngine;
        ElectricTravelingTrainImpl(ElectricEngine electricEngine) {this.electricEngine = electricEngine;}
        
        @Override
        public void runOnTrack() { /* Use electric engine optimizing speed and comfort ... */ }
    }
    
    static final class ElectricFreightTrainImpl implements Train {
        ElectricEngine electricEngine;
        ElectricFreightTrainImpl(ElectricEngine electricEngine) {this.electricEngine = electricEngine;}
        
        @Override
        public void runOnTrack() { /* Use electric engine optimizing cost: low speed for heavy load ... */ }
    }
    
    static final class ElectricPostalTrainImpl implements Train {
        ElectricEngine electricEngine;
        ElectricPostalTrainImpl(ElectricEngine electricEngine) {this.electricEngine = electricEngine;}
        
        @Override
        public void runOnTrack() { /* Use electric engine optimizing cost: low speed ... */ }
    }
    
    static final class DieselTravelingTrainImpl implements Train {
        DieselEngine dieselEngine;
        DieselTravelingTrainImpl(DieselEngine dieselEngine) {this.dieselEngine = dieselEngine;}
        
        @Override
        public void runOnTrack() { /* Use diesel engine optimizing speed and comfort ... */ }
    }
    
    static final class DieselFreightTrainImpl implements Train {
        DieselEngine dieselEngine;
        DieselFreightTrainImpl(DieselEngine dieselEngine) {this.dieselEngine = dieselEngine;}
        
        @Override
        public void runOnTrack() { /* Use diesel engine optimizing cost: low speed for heavy load ... */ }
    }
    
    static final class DieselPostalTrainImpl implements Train {
        DieselEngine dieselEngine;
        DieselPostalTrainImpl(DieselEngine dieselEngine) {this.dieselEngine = dieselEngine;}
        
        @Override
        public void runOnTrack() { /* Use diesel engine optimizing cost: low speed ... */ }
    }

    static class DieselEngine {
        public void setThrottle(double percent) { /* ... */ }
    }
    
    static class ElectricEngine {
        public void setVoltage(double percent) { /* ... */ }
    }
    
    // FACTORIES //
    
    static Train buildElectricTravelingTrain() { return new ElectricTravelingTrainImpl( new ElectricEngine() ); }
    static Train buildElectricFreightTrain()   { return new ElectricFreightTrainImpl(   new ElectricEngine() ); }
    static Train buildElectricPostalTrain()    { return new ElectricPostalTrainImpl(    new ElectricEngine() ); }
    static Train buildDieselTravelingTrain() { return new DieselTravelingTrainImpl( new DieselEngine() ); }
    static Train buildDieselFreightTrain()   { return new DieselFreightTrainImpl(   new DieselEngine() ); }
    static Train buildDieselPostalTrain()    { return new DieselPostalTrainImpl(    new DieselEngine() ); }
    
    //////////////
    // USE CASE //
    //////////////
    
    static class Driver {
        public void drive(Train train) {
            train.runOnTrack();
        }
    }
    
    public static void main(String[] args) {
        Driver driver = new Driver();
        
        Train electricTravelingTrain = buildElectricTravelingTrain();
        driver.drive(electricTravelingTrain);
        
        Train electricFreightTrain = buildElectricFreightTrain();
        driver.drive(electricFreightTrain);
        
        Train electricPostalTrain = buildElectricPostalTrain();
        driver.drive(electricPostalTrain);
        
        Train dieselTravelingTrain = buildDieselTravelingTrain();
        driver.drive(dieselTravelingTrain);
        
        Train dieselFreightTrain = buildDieselFreightTrain();
        driver.drive(dieselFreightTrain);
        
        Train dieselPostalTrain = buildDieselPostalTrain();
        driver.drive(dieselPostalTrain);
    }
}

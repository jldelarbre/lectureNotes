package lectureNotes.lesson5.solid;

public class B4 {
    
    interface Train {
        void runOnTrack();
    }
    
    // Use OCP and LSP, define an abstraction for the engines
    
    static final class ElectricTravelingTrainImpl implements Train {
        Engine engine;
        ElectricTravelingTrainImpl(Engine engine) {this.engine = engine;}
        
        @Override
        public void runOnTrack() { /* Use electric engine optimizing speed and comfort ... */ }
    }
    
    static final class ElectricFreightTrainImpl implements Train {
        Engine engine;
        ElectricFreightTrainImpl(Engine engine) {this.engine = engine;}
        
        @Override
        public void runOnTrack() { /* Use electric engine optimizing cost: low speed for heavy load ... */ }
    }
    
    static final class ElectricPostalTrainImpl implements Train {
        Engine engine;
        ElectricPostalTrainImpl(Engine engine) {this.engine = engine;}
        
        @Override
        public void runOnTrack() { /* Use electric engine optimizing cost: low speed ... */ }
    }
    
    static final class DieselTravelingTrainImpl implements Train {
        Engine engine;
        DieselTravelingTrainImpl(Engine engine) {this.engine = engine;}
        
        @Override
        public void runOnTrack() { /* Use diesel engine optimizing speed and comfort ... */ }
    }
    
    static final class DieselFreightTrainImpl implements Train {
        Engine engine;
        DieselFreightTrainImpl(Engine engine) {this.engine = engine;}
        
        @Override
        public void runOnTrack() { /* Use diesel engine optimizing cost: low speed for heavy load ... */ }
    }
    
    static final class DieselPostalTrainImpl implements Train {
        Engine engine;
        DieselPostalTrainImpl(Engine engine) {this.engine = engine;}
        
        @Override
        public void runOnTrack() { /* Use diesel engine optimizing cost: low speed ... */ }
    }

    interface Engine {
        void setPower(double percent);
    }
    
    interface DieselEngine extends Engine {}
    interface ElectricEngine extends Engine {}
    
    static class DieselEngineImpl implements DieselEngine {
        @Override
        public void setPower(double percent) { /* ... */ }
    }
    
    static class ElectricEngineImpl implements ElectricEngine {
        @Override
        public void setPower(double percent) { /* ... */ }
    }
    
    // FACTORIES //
    
    static Train buildElectricTravelingTrain() { return new ElectricTravelingTrainImpl( new ElectricEngineImpl() ); }
    static Train buildElectricFreightTrain()   { return new ElectricFreightTrainImpl(   new ElectricEngineImpl() ); }
    static Train buildElectricPostalTrain()    { return new ElectricPostalTrainImpl(    new ElectricEngineImpl() ); }
    static Train buildDieselTravelingTrain() { return new DieselTravelingTrainImpl( new DieselEngineImpl() ); }
    static Train buildDieselFreightTrain()   { return new DieselFreightTrainImpl(   new DieselEngineImpl() ); }
    static Train buildDieselPostalTrain()    { return new DieselPostalTrainImpl(    new DieselEngineImpl() ); }
    
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

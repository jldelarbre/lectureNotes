package lectureNotes.lesson5.solid;

public class B6 {
    
    // Code easily extensible to satisfy new requirements 

    /////////////////
    // ABSTRACTION //
    /////////////////
    
    interface Train {
        void runOnTrack();
    }
    
    interface TravelingTrain extends Train {}
    interface FreightTrain extends Train {}
    interface PostalTrain extends Train {}
    
    interface COVIDTrain extends Train {}
    
    static final class TravelingTrainImpl implements TravelingTrain {
        Engine engine;
        TravelingTrainImpl(Engine engine) {this.engine = engine;}
        
        @Override
        public void runOnTrack() { /* Use engine optimizing speed and comfort ... */ }
    }
    
    static final class FreightTrainImpl implements FreightTrain {
        Engine engine;
        FreightTrainImpl(Engine engine) {this.engine = engine;}
        
        @Override
        public void runOnTrack() { /* Use engine optimizing cost: low speed for heavy load ... */ }
    }
    
    static final class PostalTrainImpl implements PostalTrain {
        Engine engine;
        PostalTrainImpl(Engine engine) {this.engine = engine;}
        
        @Override
        public void runOnTrack() { /* Use engine optimizing cost: low speed ... */ }
    }
    
    static final class COVIDTrainImpl implements COVIDTrain {
        Engine engine;
        COVIDTrainImpl(Engine engine) {this.engine = engine;}
        
        @Override
        public void runOnTrack() { /* Use engine optimizing cost: low speed ... */ }
    }
    
    ////////////////////
    // IMPLEMENTATION //
    ////////////////////
    
    interface Engine {
        void setPower(double percent);
    }
    
    interface DieselEngine extends Engine {}
    interface ElectricEngine extends Engine {}
    
    interface HydrogenEngine extends Engine {}
    
    static class DieselEngineImpl implements DieselEngine {
        @Override
        public void setPower(double percent) { /* ... */ }
    }
    
    static class ElectricEngineImpl implements ElectricEngine {
        @Override
        public void setPower(double percent) { /* ... */ }
    }
    
    static class HydrogenEngineImpl implements HydrogenEngine {
        @Override
        public void setPower(double percent) { /* ... */ }
    }
    
    // FACTORIES //
    
    static Train buildElectricTravelingTrain() { return new TravelingTrainImpl( new ElectricEngineImpl()); }
    static Train buildElectricFreightTrain()   { return new FreightTrainImpl(   new ElectricEngineImpl()); }
    static Train buildElectricPostalTrain()    { return new PostalTrainImpl(    new ElectricEngineImpl()); }
    static Train buildDieselTravelingTrain() { return new TravelingTrainImpl( new DieselEngineImpl()); }
    static Train buildDieselFreightTrain()   { return new FreightTrainImpl(   new DieselEngineImpl()); }
    static Train buildDieselPostalTrain()    { return new PostalTrainImpl(    new DieselEngineImpl()); }
    
    static Train buildElectricCOVIDTrain() { return new COVIDTrainImpl( new ElectricEngineImpl()); }
    static Train buildHydrogenFreightTrain()   { return new FreightTrainImpl(   new HydrogenEngineImpl()); }
    
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
        
        Train electricTravelingTrain = buildElectricCOVIDTrain();
        driver.drive(electricTravelingTrain);
        
        Train electricFreightTrain = buildHydrogenFreightTrain();
        driver.drive(electricFreightTrain);
    }
}

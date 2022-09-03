package lectureNotes.lesson5.solid;

public class B5 {
    
    // Refactor code in order to factorize it
    //
    // Train is the main business ABSTRACTION in code, it is refined by:
    //     - TravelingTrain
    //     - FreightTrain
    //     - PostalTrain
    //
    // Engine is part of the Train IMPLEMENTATION
    //
    // This pattern
    // decouples an abstraction from its implementation so that the two can vary independently

    /////////////////
    // ABSTRACTION //
    /////////////////
    
    interface Train {
        void runOnTrack();
    }
    
    interface TravelingTrain extends Train {}
    interface FreightTrain extends Train {}
    interface PostalTrain extends Train {}
    
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
    
    ////////////////////
    // IMPLEMENTATION //
    ////////////////////
    
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
    
    static Train buildElectricTravelingTrain() { return new TravelingTrainImpl( new ElectricEngineImpl()); }
    static Train buildElectricFreightTrain()   { return new FreightTrainImpl(   new ElectricEngineImpl()); }
    static Train buildElectricPostalTrain()    { return new PostalTrainImpl(    new ElectricEngineImpl()); }
    static Train buildDieselTravelingTrain() { return new TravelingTrainImpl( new DieselEngineImpl()); }
    static Train buildDieselFreightTrain()   { return new FreightTrainImpl(   new DieselEngineImpl()); }
    static Train buildDieselPostalTrain()    { return new PostalTrainImpl(    new DieselEngineImpl()); }
    
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

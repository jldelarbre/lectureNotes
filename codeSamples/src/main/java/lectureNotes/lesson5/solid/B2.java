package lectureNotes.lesson5.solid;

public class B2 {
	
	// Use DIP, so that Driver and Train implementations all depend on Train abstraction
	// Introduce Train abstraction
	
	interface Train {
		void runOnTrack();
	}
	
	static final class ElectricTravelingTrainImpl implements Train {
		@Override
		public void runOnTrack() { /* Optimize speed and comfort with electric power ... */ }
	}
	
	static final class ElectricFreightTrainImpl implements Train {
		@Override
		public void runOnTrack() { /* Optimize cost: low speed for heavy load with electric power ... */ }
	}
	
	static final class ElectricPostalTrainImpl implements Train {
		@Override
		public void runOnTrack() { /* Optimize cost: low speed with electric power ... */ }
	}
	
	static final class DieselTravelingTrainImpl implements Train {
		@Override
		public void runOnTrack() { /* Optimize speed and comfort with diesel power ... */ }
	}
	
	static final class DieselFreightTrainImpl implements Train {
		@Override
		public void runOnTrack() { /* Optimize cost: low speed for heavy load with diesel power ... */ }
	}
	
	static final class DieselPostalTrainImpl implements Train {
		@Override
		public void runOnTrack() { /* Optimize cost: low speed with diesel power ... */ }
	}
	
	// FACTORIES //
	
	static Train buildElectricTravelingTrain() { return new ElectricTravelingTrainImpl(); }
	static Train buildElectricFreightTrain()   { return new ElectricFreightTrainImpl(); }
	static Train buildElectricPostalTrain()    { return new ElectricPostalTrainImpl(); }
	static Train buildDieselTravelingTrain() { return new DieselTravelingTrainImpl(); }
	static Train buildDieselFreightTrain()   { return new DieselFreightTrainImpl(); }
	static Train buildDieselPostalTrain()    { return new DieselPostalTrainImpl(); }
	
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

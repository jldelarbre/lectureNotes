package lectureNotes.lesson5.solid;

public class B1 {
	
	// Refactor an application to drive trains
	
	// A train could have many purpose:
	//     - Traveling
	//     - Freight
	//     - Postal
	
	// A train could be powered either by:
	//     - Electricity
	//     - Diesel
	
	// All the combinations lead to a huge number of implementation 
	
	static final class ElectricTravelingTrain {
		public void runOnTrack() { /* Optimize speed and comfort with electric power ... */ }
	}
	
	static final class ElectricFreightTrain {
		public void runOnTrack() { /* Optimize cost: low speed for heavy load with electric power ... */ }
	}
	
	static final class ElectricPostalTrain {
		public void runOnTrack() { /* Optimize cost: low speed with electric power ... */ }
	}
	
	static final class DieselTravelingTrain {
		public void runOnTrack() { /* Optimize speed and comfort with diesel power ... */ }
	}
	
	static final class DieselFreightTrain {
		public void runOnTrack() { /* Optimize cost: low speed for heavy load with diesel power ... */ }
	}
	
	static final class DieselPostalTrain {
		public void runOnTrack() { /* Optimize cost: low speed with diesel power ... */ }
	}
	
	//////////////
	// USE CASE //
	//////////////
	
	static class Driver {
		public void drive(ElectricTravelingTrain train) {
			train.runOnTrack();
		}
		public void drive(ElectricFreightTrain train) {
			train.runOnTrack();
		}
		public void drive(ElectricPostalTrain train) {
			train.runOnTrack();
		}
		public void drive(DieselTravelingTrain train) {
			train.runOnTrack();
		}
		public void drive(DieselFreightTrain train) {
			train.runOnTrack();
		}
		public void drive(DieselPostalTrain train) {
			train.runOnTrack();
		}
	}
	
	public static void main(String[] args) {
		Driver driver = new Driver();
		
		ElectricTravelingTrain electricTravelingTrain = new ElectricTravelingTrain();
		driver.drive(electricTravelingTrain);
		
		ElectricFreightTrain electricFreightTrain = new ElectricFreightTrain();
		driver.drive(electricFreightTrain);
		
		ElectricPostalTrain electricPostalTrain = new ElectricPostalTrain();
		driver.drive(electricPostalTrain);
		
		DieselTravelingTrain dieselTravelingTrain = new DieselTravelingTrain();
		driver.drive(dieselTravelingTrain);
		
		DieselFreightTrain dieselFreightTrain = new DieselFreightTrain();
		driver.drive(dieselFreightTrain);
		
		DieselPostalTrain dieselPostalTrain = new DieselPostalTrain();
		driver.drive(dieselPostalTrain);
	}
}

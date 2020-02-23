package lectureNotes.lesson5.factory;

public class F2 {

    // A complex object with a lot of fields
    static class RailwayUsageReport {
        
        private final int numberOfMovement;
        private final int timeInDaysSinceLastMaintenance;
        private final int numberOfIncident;
        // ...
        
        RailwayUsageReport(int numberOfMovement, int timeInDaysSinceLastMaintenance, int numberOfIncident) {
            this.numberOfMovement = numberOfMovement;
            this.timeInDaysSinceLastMaintenance = timeInDaysSinceLastMaintenance;
            this.numberOfIncident = numberOfIncident;
        }

        public int getNumberOfMovement() {
            return numberOfMovement;
        }
        
        public int getTimeInDaysSinceLastMaintenance() {
            return timeInDaysSinceLastMaintenance;
        }
        
        public int getNumberOfIncident() {
            return numberOfIncident;
        }
    }
    
    // A builder to build the complex object
    static class RailwayUsageReportBuilder {
        
        private int numberOfMovement;
        private int timeInDaysSinceLastMaintenance;
        private int numberOfIncident;
        
        // Mandatory parameters to set should preferably be set at builder construction time.
        // For too numerous parameters, setters allow a more expressive code
        public RailwayUsageReportBuilder(int numberOfMovement) {
            this.numberOfMovement = numberOfMovement;
        }
        
        RailwayUsageReport build() {
            return new RailwayUsageReport(numberOfMovement, timeInDaysSinceLastMaintenance, numberOfIncident);
        }

        // Optional parameters to set are set by setters
        // For too numerous parameters, setters allow a more expressive code.
        // Setters that return "this" allow to cascade them
        public RailwayUsageReportBuilder setTimeInDaysSinceLastMaintenance(int timeInDaysSinceLastMaintenance) {
            this.timeInDaysSinceLastMaintenance = timeInDaysSinceLastMaintenance;
            return this;
        }

        public RailwayUsageReportBuilder setNumberOfIncident(int numberOfIncident) {
            this.numberOfIncident = numberOfIncident;
            return this;
        }
    }
    
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        // It should be cleaner to also define a static factory for the builder itself
        RailwayUsageReport railwayUsageReport = new RailwayUsageReportBuilder(500)
            .setTimeInDaysSinceLastMaintenance(20)
            .setNumberOfIncident(1)
            .build();
    }
}

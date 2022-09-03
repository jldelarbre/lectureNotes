package lectureNotes.lesson7.architecture.v2.persistence;

import lectureNotes.lesson7.architecture.v2.appCore.SatelliteSaver;

public class SatelliteSaverMongoDB implements SatelliteSaver {
    
    /*
     * Changing persistence layer shall not change appCore
     */

    @Override
    public void saveSatellite() {
        // Save satellite using Mongo DB
        
    }

}

package lectureNotes.lesson7.architecture.v1.persistence;

import lectureNotes.lesson7.architecture.v1.appCore.SatelliteSaver;

public class SatelliteSaverXML implements SatelliteSaver {
    
    /*
     * Persistence layer depends on appCore (and not the other way around)
     * It acts as a plugin for appCore.
     */

    @Override
    public void saveSatellite() {
        // Save satellite in XML format
        
    }

}

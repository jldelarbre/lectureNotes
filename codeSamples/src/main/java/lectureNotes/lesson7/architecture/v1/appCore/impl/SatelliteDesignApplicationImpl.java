package lectureNotes.lesson7.architecture.v1.appCore.impl;

import lectureNotes.lesson7.architecture.v1.appCore.ErrorAlerter;
import lectureNotes.lesson7.architecture.v1.appCore.SatelliteDesignApplication;
import lectureNotes.lesson7.architecture.v1.appCore.SatelliteSaver;

public class SatelliteDesignApplicationImpl implements SatelliteDesignApplication {
    
    SatelliteSaver satelliteSaver;
    
    ErrorAlerter errorAlerter;

    @Override
    public void designSatellite() {
        
        // Design satellite...
        
        // if design error
        errorAlerter.warnOfDesignError();
        
        /*
         * appCore does not depends on GUI to warn user, since it defines the
         * API to warn user and GUI implements it as a plugin
         */
    }

    @Override
    public void saveWork() {
        satelliteSaver.saveSatellite();
    }
}

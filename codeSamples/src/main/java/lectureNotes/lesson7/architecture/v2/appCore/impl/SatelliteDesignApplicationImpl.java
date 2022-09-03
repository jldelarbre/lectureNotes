package lectureNotes.lesson7.architecture.v2.appCore.impl;

import lectureNotes.lesson7.architecture.v2.appCore.ErrorAlerter;
import lectureNotes.lesson7.architecture.v2.appCore.SatelliteDesignApplication;
import lectureNotes.lesson7.architecture.v2.appCore.SatelliteSaver;

public class SatelliteDesignApplicationImpl implements SatelliteDesignApplication {
    
    SatelliteSaver satelliteSaver;
    
    ErrorAlerter errorAlerter;

    @Override
    public void designSatellite() {
        
        // Design satellite...
        
        // if design error
        errorAlerter.warnOfDesignError();
    }

    @Override
    public void saveWork() {
        satelliteSaver.saveSatellite();
    }
}

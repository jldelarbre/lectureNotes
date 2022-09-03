package lectureNotes.lesson7.architecture.v2.gui;

import lectureNotes.lesson7.architecture.v2.appCore.ErrorAlerter;
import lectureNotes.lesson7.architecture.v2.appCore.SatelliteDesignApplication;

public class WebGui implements ErrorAlerter {
    
    // Display Satellite design application using Internet browser
    
    /*
     * Changing GUI shall not impact appCore
     */

    SatelliteDesignApplication satelliteDesignApplication;
    
    @Override
    public void warnOfDesignError() {
        // Warn user
    }

    public void userDesignActionCallback() {
        satelliteDesignApplication.designSatellite();
    }
    
    public void userSaveActionCallback() {
        satelliteDesignApplication.saveWork();
    }
}

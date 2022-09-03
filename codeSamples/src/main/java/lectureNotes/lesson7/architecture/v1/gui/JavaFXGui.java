package lectureNotes.lesson7.architecture.v1.gui;

import lectureNotes.lesson7.architecture.v1.appCore.ErrorAlerter;
import lectureNotes.lesson7.architecture.v1.appCore.SatelliteDesignApplication;

public class JavaFXGui implements ErrorAlerter {
    
    // Display Satellite design application using Java FX
    
    /*
     * Gui does depend on appCore (not the other way around). It calls its exposed service.
     * It also acts as a plugin for appCore through ErrorAlerter.
     * 
     * This is appCore who defines and leads the type of interactions with utility services (gui, persistence...)
     * AppCore shall impose API to GUI and not the reverse.
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

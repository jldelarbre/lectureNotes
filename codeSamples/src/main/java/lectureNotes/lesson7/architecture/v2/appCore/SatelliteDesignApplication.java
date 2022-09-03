package lectureNotes.lesson7.architecture.v2.appCore;

public interface SatelliteDesignApplication {
    
    /*
     * When we want to change GUI or persistence of our satellite application,
     * the application core must no be impacted and remain unchanged.
     */

    void designSatellite();
    
    void saveWork();
    
}

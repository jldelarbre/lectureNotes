package lectureNotes.lesson7.architecture.v1.appCore;

public interface SatelliteDesignApplication {
    
    /*
     * appCore contains all the business knowledge and value of
     * satellite design application. It has no dependencies on
     * peripheral components: GUI, persistence
     */

    void designSatellite();
    
    void saveWork();
    
}

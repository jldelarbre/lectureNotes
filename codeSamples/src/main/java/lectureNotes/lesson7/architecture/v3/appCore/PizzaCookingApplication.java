package lectureNotes.lesson7.architecture.v3.appCore;

public interface PizzaCookingApplication {
    
    /*
     * If the business of the application change: satellite to pizza
     * The whole application will change. Starting from appCore
     * which holds the business knowledge, every components will be
     * impacted: GUI, persistence...
     */

    void cookPizza();
    
    void saveRecipe();
    
}

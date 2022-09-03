package lectureNotes.lesson7.architecture.v3.gui;

import lectureNotes.lesson7.architecture.v3.appCore.OvercookingAlerter;
import lectureNotes.lesson7.architecture.v3.appCore.PizzaCookingApplication;

public class PizzaGui implements OvercookingAlerter {
    
    // Display pizza application using using Internet browser

    PizzaCookingApplication pizzaCookingApplication;
    
    @Override
    public void warnOfOvercook() {
        // Warn cooker
    }

    public void userCookCallback() {
        pizzaCookingApplication.cookPizza();
    }
    
    public void userSaveRecipeCallback() {
        pizzaCookingApplication.saveRecipe();
    }
}

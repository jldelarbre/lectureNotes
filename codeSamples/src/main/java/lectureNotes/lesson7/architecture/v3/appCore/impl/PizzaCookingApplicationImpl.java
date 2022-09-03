package lectureNotes.lesson7.architecture.v3.appCore.impl;

import lectureNotes.lesson7.architecture.v3.appCore.OvercookingAlerter;
import lectureNotes.lesson7.architecture.v3.appCore.PizzaCookingApplication;
import lectureNotes.lesson7.architecture.v3.appCore.RecipeSaver;

public class PizzaCookingApplicationImpl implements PizzaCookingApplication {
    
    OvercookingAlerter overcookingAlerter;
    
    RecipeSaver recipeSaver;

    @Override
    public void cookPizza() {
        
        // Cook pizza...
        
        // if pizza overcooked
        overcookingAlerter.warnOfOvercook();
    }

    @Override
    public void saveRecipe() {
        recipeSaver.saveRecipe();
    }
}

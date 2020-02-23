package lectureNotes.lesson5.optional;

import java.util.List;

public class Null {

    // Our purpose is to manage unload of freight train
    interface Goods {}
    
    interface FreightTrain {
        List<Goods> getGoods();
    }
    
    // Some operations are valid for any goods extracted from freight train
    interface Balance {
        double weigh(Goods goods);
    }
    
    interface Invoicer {
        void invoice(Goods goods);
    }
    
    // But others are valid for some of them
    // For instance Sanitary control exist only for food, medicine...
    interface SanitaryControler {
        void control(Goods goods);
    }
    
    // Implementation for elements where sanitary controls are required
    static class SanitaryControlerImp implements SanitaryControler {
        @Override
        public void control(Goods goods) {
            // ...
        }
    }
    
    
    // Implementation for elements where sanitary controls do not even exist
    static class SanitaryControlerNullImp implements SanitaryControler {
        @Override
        public void control(Goods goods) {
            // Do nothing
        }
    }
    
    public static SanitaryControler build(Goods goods) {
        SanitaryControler sanitaryControler = null;
        
        // Goods that need sanitary control
        sanitaryControler = new SanitaryControlerImp();
        
        // Goods that DO NOT need sanitary control
        sanitaryControler = new SanitaryControlerNullImp();
        
        return sanitaryControler;
    }
    
    // Implementation of freight train unload does not depend on exception cases for
    // sanitary controls (when goods are food or medicine...)
    // It is not its responsibility to manage it, it does even know this
    static class FreightTrainUnloader {
        
        Balance balance;
        Invoicer invoicer;
        SanitaryControler sanitaryControler;
        
        void unload(FreightTrain freightTrain) {
            for (Goods goods : freightTrain.getGoods()) {
                balance.weigh(goods);
                invoicer.invoice(goods);
                
                SanitaryControler sanitaryControler = build(goods);
                sanitaryControler.control(goods);
            }
        }
    }
}

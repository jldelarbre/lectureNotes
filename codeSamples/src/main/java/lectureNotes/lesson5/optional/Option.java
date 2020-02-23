package lectureNotes.lesson5.optional;

import java.util.List;
import java.util.Optional;

public class Option {

    // Our purpose is to manage unload of freight train
    interface Goods {}
    
    // Some operations are valid for any goods extracted from freight train
    interface FreightTrain {
        List<Goods> getGoods();
    }
    
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
    
    public static Optional<SanitaryControler> build(Goods goods) {
        Optional<SanitaryControler> sanitaryControler = null;
        
        // Goods that need sanitary control
        sanitaryControler = Optional.of(new SanitaryControlerImp());
        
        // Goods that DO NOT need sanitary control
        sanitaryControler = Optional.empty();
        
        return sanitaryControler;
    }
    
    // Implementation of freight train unload depends on exception cases for
    // sanitary controls (when goods are food or medicine...)
    // It has to explicitly managed it
    static class FreightTrainUnloader {
        
        Balance balance;
        Invoicer invoicer;
        SanitaryControler sanitaryControler;
        
        void unload(FreightTrain freightTrain) {
            for (Goods goods : freightTrain.getGoods()) {
                balance.weigh(goods);
                invoicer.invoice(goods);
                
                Optional<SanitaryControler> sanitaryControler = build(goods);
                if (sanitaryControler.isPresent()) {
                    sanitaryControler.get().control(goods);
                }
            }
        }
    }
}

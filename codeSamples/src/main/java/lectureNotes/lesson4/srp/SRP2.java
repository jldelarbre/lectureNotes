package lectureNotes.lesson4.srp;

import java.util.HashMap;
import java.util.Map;

public class SRP2 {
    
    enum KindOfWork {
        //...
    }

    static class Employee {
        public void calculatePay(Map<KindOfWork, Integer> hourPerKindOfWork) {
            // Calculate Pay
            // ...
            saveEmployeeWorkHours(hourPerKindOfWork);
        }
        
        private void saveEmployeeWorkHours(Map<KindOfWork, Integer> hourPerKindOfWork) {
            // Save to Mongo DB instead of CSV file, as demanded by purchase service
        }
    }
    
    static class SalesService {
        public void salesServiceWork(Employee employee) {
            // Ask employee for work
            Map<KindOfWork, Integer> hourPerKindOfWork = new HashMap<>();
            // Complete hours of work to do
            // hourPerKindOfWork.add(...);
            
            // Pay employee
            employee.calculatePay(hourPerKindOfWork);
        }
    }
    
    // Purchase service uses Mongo DB, so developers change save function in order to use mongo DB
    // forgetting CSV are still in use in sales service
    // Calculating pay and data saving are not independent (orthogonal). They are tied together
    // so modifying "save" leads to a different "calculatePay" method.
    static class PurchaseService {
        public void purchaseServiceWork(Employee employee) {
            // Ask employee for work in PURCHASE SERVICE WAY
            Map<KindOfWork, Integer> hourPerKindOfWork = new HashMap<>();
            // Complete hours of PURCHASE work to do
            // hourPerKindOfWork.add(...);
            
            // Pay employee
            employee.calculatePay(hourPerKindOfWork);
        }
    }
}

package lectureNotes.lesson4.srp;

import java.util.HashMap;
import java.util.Map;

public class SRP3 {
    
    enum KindOfWork {
        //...
    }

    // Now "calculatePay" and "save" are orthogonal BUT
    // Employee has many responsibilities: "calculatePay" and "save"
    // If you want to save in binary format you need to modify "Employee" again
    static class Employee {
        public void calculatePay(Map<KindOfWork, Integer> hourPerKindOfWork) {
            // Calculate Pay
            // ...
        }
        
        public void saveEmployeeWorkHoursInCSV(Map<KindOfWork, Integer> hourPerKindOfWork) {
            // Save to CSV
        }
        
        public void saveEmployeeWorkHoursToMongo(Map<KindOfWork, Integer> hourPerKindOfWork) {
            // Save to Mongo
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
            
            // Save
            employee.saveEmployeeWorkHoursInCSV(hourPerKindOfWork);
        }
    }
    
    static class PurchaseService {
        public void purchaseServiceWork(Employee employee) {
            // Ask employee for work in PURCHASE SERVICE WAY
            Map<KindOfWork, Integer> hourPerKindOfWork = new HashMap<>();
            // Complete hours of PURCHASE work to do
            // hourPerKindOfWork.add(...);
            
            // Pay employee
            employee.calculatePay(hourPerKindOfWork);
            
            // Save
            employee.saveEmployeeWorkHoursToMongo(hourPerKindOfWork);
        }
    }
}

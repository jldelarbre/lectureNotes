package lectureNotes.lesson4.srp;

import java.util.HashMap;
import java.util.Map;

public class SRP1 {
    
    enum KindOfWork {
        //...
    }

    // A class that has many responsibilities and so many reason to change
    // This is also not an orthogonal system, the 2 responsibilities may interfere
    // to each other
    static class Employee {
        public void calculatePay(Map<KindOfWork, Integer> hourPerKindOfWork) {
            // Calculate Pay
            // ...
            saveEmployeeWorkHours(hourPerKindOfWork);
        }
        
        private void saveEmployeeWorkHours(Map<KindOfWork, Integer> hourPerKindOfWork) {
            // Save to CSV file
        }
    }
    
    // Sales service asks employee to do work.
    // Employee knows how to compute its pay
    // And save pay data in a csv file
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
}

package lectureNotes.lesson4.srp;

import java.util.HashMap;
import java.util.Map;

public class SRP4 {
    
    enum KindOfWork {
        //...
    }

    // "calculatePay" and "save" are two axes of changes so it has to be embody by 2 separate
    // components
    
    static class Employee {
        public void calculatePay(Map<KindOfWork, Integer> hourPerKindOfWork) {
            // Calculate Pay
            // ...
        }
    }
    
    static interface EmployeeDataSaver {
        void saveEmployeeWorkHours(Map<KindOfWork, Integer> hourPerKindOfWork);
    }
    
    static class EmployeeDataSaverCSV implements EmployeeDataSaver {
        @Override
        public void saveEmployeeWorkHours(Map<KindOfWork, Integer> hourPerKindOfWork) {
            // ...
        }
    }
    
    static class EmployeeDataSaverMongo implements EmployeeDataSaver {
        @Override
        public void saveEmployeeWorkHours(Map<KindOfWork, Integer> hourPerKindOfWork) {
            // ...
        }
    }
    
    static class SalesService {
        // Inject the desired data saver
        EmployeeDataSaver employeeDataSaver = new EmployeeDataSaverCSV();
        
        public void salesServiceWork(Employee employee) {
            // Ask employee for work
            Map<KindOfWork, Integer> hourPerKindOfWork = new HashMap<>();
            // Complete hours of work to do
            // hourPerKindOfWork.add(...);
            
            // Pay employee
            employee.calculatePay(hourPerKindOfWork);
            
            // Save
            employeeDataSaver.saveEmployeeWorkHours(hourPerKindOfWork);
        }
    }
    
    static class PurchaseService {
        // Inject the desired data saver
        EmployeeDataSaver employeeDataSaver = new EmployeeDataSaverMongo();
        
        public void purchaseServiceWork(Employee employee) {
            // Ask employee for work in PURCHASE SERVICE WAY
            Map<KindOfWork, Integer> hourPerKindOfWork = new HashMap<>();
            // Complete hours of PURCHASE work to do
            // hourPerKindOfWork.add(...);
            
            // Pay employee
            employee.calculatePay(hourPerKindOfWork);
            
            // Save
            employeeDataSaver.saveEmployeeWorkHours(hourPerKindOfWork);
        }
    }
}

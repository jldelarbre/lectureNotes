package lectureNotes.introcicd;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

//Inspired by: https://www.martinfowler.com/articles/languageWorkbench.html
/*
01234567890123456789012345678901234567890123456789

BUY    MARTIN       ORDER42 CHEQUE  500.30
RETURN DUPOND       ORDER37 WARRANTY    REPAIR
BUY    SMITH        ORDER56 BC     1320.55
RETURN DURAND       ORDER33 WARRANTY    REPLACE
RETURN RICHARD      ORDER75 UNSATISFIED REFUND
BUY    MALVY        ORDER84 BC       42.20
RETURN DUBOIS       ORDER15 UNSATISFIED EXCHANGE
*/

public class Dsl {
	
	interface Operation {}
	
	class BuyOperation implements Operation {
		String customerName;
		String orderId;
		String paymentMean;
		double amount;
	}
	
	class ReturnOperation implements Operation {
		String customerName;
		String orderId;
		String cause;
		String action;
	}
	
	class ReportProcessor {
		Map<String, OperationProcessor> operationProcessors = new HashMap<>();
		
		List<Operation> process(FileInputStream fis) {
			List<Operation> records = new ArrayList<>();
			
			Scanner sc = new Scanner(fis);
			
			while (sc.hasNextLine()) {
				records.add(processLine(sc.nextLine()));
			}
			sc.close();
			
			return records;
		}
		
		Operation processLine(String line) {
			String operationType = getOperationType(line);
			OperationProcessor operationProcessor = operationProcessors.get(operationType);
			return operationProcessor.extractOperation(operationType);
		}
		
		String getOperationType(String line) {
			String operationType = "";
			// ...
			return operationType;
		}
	}
	
	class OperationProcessor {
		Supplier<Operation> newOperation;
		List<FieldExtractor> fieldExtractors = new ArrayList<>();
		
		OperationProcessor(Supplier<Operation> newOperation) {/* ... */}
		
		Operation extractOperation(String lineOperation) {
			Operation operation = newOperation.get();
			for (FieldExtractor fieldExtrator : fieldExtractors) {
				fieldExtrator.extractField(lineOperation, operation);
			}
			return operation;
		}
		
		void addFieldExtractor(int startPosition, int endPosition, String propertyName) {
			this.fieldExtractors.add(new FieldExtractor(startPosition, endPosition, propertyName));
		}
	}
	
	class FieldExtractor {
		int startPosition;
		int endPosition;
		String propertyName;
		
		FieldExtractor(int startPosition, int endPosition, String propertyName) {/* ... */}
		
		void extractField(String line, Operation targetOperation) {
			// ...
		}
	}
	
	void configureReportProcessor(ReportProcessor reportProcessor) {
		reportProcessor.operationProcessors.put("BUY", configureBuyOperationProcessor());
		reportProcessor.operationProcessors.put("RETURN", configureReturnOperationProcessor());
	}
	
	OperationProcessor configureBuyOperationProcessor() {
		OperationProcessor buyOperationProcessor = new OperationProcessor(BuyOperation::new);
		buyOperationProcessor.addFieldExtractor(7, 18, "customerName");
		buyOperationProcessor.addFieldExtractor(20, 26, "orderId");
		buyOperationProcessor.addFieldExtractor(28, 33, "paymentMean");
		buyOperationProcessor.addFieldExtractor(35, 41, "amount");
		return buyOperationProcessor;
	}
	
	OperationProcessor configureReturnOperationProcessor() {
		OperationProcessor returnOperationProcessor = new OperationProcessor(ReturnOperation::new);
		returnOperationProcessor.addFieldExtractor(7, 18, "customerName");
		returnOperationProcessor.addFieldExtractor(20, 26, "orderId");
		returnOperationProcessor.addFieldExtractor(28, 38, "cause");
		returnOperationProcessor.addFieldExtractor(40, 47, "action");
		return returnOperationProcessor;
	}
}

package swing.calculator.modelo;

import java.util.ArrayList;
import java.util.List;

public class Memory {

	private enum CommandType {
			RESET, SIGNAL, NUMBER, DIV, MULT, SUB, SUM, EQUAL, COMMA;
	}
	
	private static final Memory instance = new Memory();
	
	private final List<MemoryObserver> observers =
			new ArrayList<>();
	
	private CommandType lastOperation = null;
	private boolean toReplace = false;
	private String currentText = "";
	private String bufferText = "";
	
	public Memory() {
		
	}
	
	public static Memory getInstance() {
		return instance;
	}
	
	public void addObserver(MemoryObserver observer) {
		observers.add(observer);
	}

	public String getCurrentText() {
		return currentText.isEmpty() ? "0" : currentText;
	}
	
	public void processCommand(String text) {
		
		CommandType commandType = detectCommandType(text);
		
		if(commandType == null) {
			return;
		} else if(commandType == CommandType.RESET) {
			currentText = "";
			bufferText = "";
			toReplace = false;
			lastOperation = null;
		} else if(commandType == CommandType.SIGNAL
				&& currentText.contains("-")) {
			currentText = currentText.substring(1);
		} else if(commandType == CommandType.SIGNAL
				&& !currentText.contains("-")) {
			currentText = "-" + currentText;
		} else if(commandType == CommandType.NUMBER
				|| commandType == CommandType.COMMA) {
			currentText = toReplace ? text : currentText + text;
			toReplace = false;
		} else {
			toReplace = true;
			currentText = getOperationResult();
			bufferText = currentText;
			lastOperation = commandType;
		}
		
		observers.forEach(o -> o.changedValue(getCurrentText()));
	}

	private String getOperationResult() {
		if(lastOperation == null 
				|| lastOperation == CommandType.EQUAL) {
			return currentText;
		}
		
		double bufferNumber = 
				Double.parseDouble(bufferText.replace(",", "."));
		double currentNumber = 
				Double.parseDouble(currentText.replace(",", "."));
		
		double result = 0;
		
		if(lastOperation == CommandType.SUM) {
			result = bufferNumber + currentNumber;
		} else if(lastOperation == CommandType.SUB) {
			result = bufferNumber - currentNumber;
		} else if(lastOperation == CommandType.MULT) {
			result = bufferNumber * currentNumber;
		} else if(lastOperation == CommandType.DIV) {
			result = bufferNumber / currentNumber;
		}
		
		String text = Double.toString(result).replace(".", ",");
		boolean roundNumber = text.endsWith(",0");
		return roundNumber ? text.replace(",0", "") : text;
	}

	private CommandType detectCommandType(String text) {
		if(currentText.isEmpty() && text == "0") {
			return null;
		}
		
		try {
			Integer.parseInt(text);
			return CommandType.NUMBER;
		} catch (NumberFormatException e) {
			if("AC".equals(text)) {
				return CommandType.RESET;
			} else if("/".equals(text)) {
				return CommandType.DIV;
			} else if("*".equals(text)) {
				return CommandType.MULT;
			} else if("-".equals(text)) {
				return CommandType.SUB;
			} else if("+".equals(text)) {
				return CommandType.SUM;
			} else if("=".equals(text)) {
				return CommandType.EQUAL;
			} else if("±".equals(text)) {
				return CommandType.SIGNAL;
			} else if(",".equals(text)
					&& !currentText.contains(",")) {
				return CommandType.COMMA;
			}
		}
		return null;
	}
}

package es.keensoft.bean;

import java.util.List;

public class Input {
	
	private List<String> firstLine;
	private List<InputLine> inputLines;
	
	public List<String> getFirstLine() {
		return firstLine;
	}
	public void setFirstLine(List<String> firstLine) {
		this.firstLine = firstLine;
	}
	public List<InputLine> getInputLines() {
		return inputLines;
	}
	public void setInputLines(List<InputLine> inputLines) {
		this.inputLines = inputLines;
	}
	
	@Override
	public String toString() {
		return "Input [firstLine=" + firstLine + ", inputLines=" + inputLines + "]";
	}
	
}

package es.keensoft.bean;

import java.util.List;

public class Output {

	private List<String> firstLine;
	private List<OutputLine> outputLines;

	public List<String> getFirstLine() {
		return firstLine;
	}

	public void setFirstLine(List<String> firstLine) {
		this.firstLine = firstLine;
	}

	public List<OutputLine> getOutputLines() {
		return outputLines;
	}

	public void setOutputLines(List<OutputLine> outputLines) {
		this.outputLines = outputLines;
	}

	@Override
	public String toString() {
		return "Output [firstLine=" + firstLine + ", outputLines=" + outputLines + "]";
	}

}

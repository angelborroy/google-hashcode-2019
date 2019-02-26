package es.keensoft.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.keensoft.bean.Input;
import es.keensoft.bean.InputLine;
import es.keensoft.bean.Output;
import es.keensoft.bean.OutputLine;

public class Translator {

	public static Input getInput(File file) throws Exception {
		Input input = new Input();
		boolean firstLine = true;
		List<InputLine> lines = new ArrayList<InputLine>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			for (String line; (line = br.readLine()) != null;) {
				List<String> numbers = Arrays.asList(line.split(" "));
				if (firstLine) {
					input.setFirstLine(numbers);
					firstLine = false;
				} else {
					InputLine inputLine = new InputLine();
					inputLine.setNumbers(numbers);
					lines.add(inputLine);
				}
			}
		}
		input.setInputLines(lines);
		return input;
	}

	public static void writeOutput(Output output, File outFile) throws Exception {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)))) {
			writer.write("" + output.getFirstLine());
			if (output.getOutputLines() != null) {
				for (OutputLine o : output.getOutputLines()) {
					writer.write(System.lineSeparator() + o);
				}
			}
		}
	}

}

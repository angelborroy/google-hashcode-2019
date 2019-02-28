package es.keensoft.main;

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
import es.keensoft.bean.Output;
import es.keensoft.bean.Photo;

public class Translator {

	public static Input getInput(File file) throws Exception {
		Input input = new Input();
		boolean firstLine = true;
		List<Photo> photos = new ArrayList<Photo>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			for (String line; (line = br.readLine()) != null;) {
				List<String> numbers = Arrays.asList(line.split(" "));
				if (firstLine) {
					input.setPhotosCount(Integer.parseInt(numbers.get(0)));
					firstLine = false;
				} else {
					Photo photo = new Photo();
					photo.setVertical(numbers.get(0).equals("V"));
					photo.setTagsCount(Integer.parseInt(numbers.get(1)));
					photo.setTags(numbers.subList(2, numbers.size()));
					photos.add(photo);
				}
			}
		}
		input.setPhotos(photos);
		return input;
	}

	public static void writeOutput(Output output, File outFile) throws Exception {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)))) {
			writer.write("" + output.getSlidesCount() + "\n");
			if (output.getSlidesLines() != null) {
				for (List<Integer> line : output.getSlidesLines()) {
					String lineString = line.toString().replaceAll(",", " ");
				    writer.write("" + 
				        lineString.substring(1, lineString.length() - 1) + 
				        "\n");
				}
			}
		}
	}

}
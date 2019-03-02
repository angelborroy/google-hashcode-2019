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
import java.util.HashSet;
import java.util.List;

import es.keensoft.bean.Input;
import es.keensoft.bean.Output;
import es.keensoft.bean.Photo;
import es.keensoft.engine.SimpleEngine;

public class Translator {
	
	static Integer CURRENT_MAX_TAGS_COUNT = 0;

	public static Input getInput(File file) throws Exception {
		Input input = new Input();
		boolean firstLine = true;
		List<Photo> photos = new ArrayList<Photo>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			int lineCount = 0;
			for (String line; (line = br.readLine()) != null;) {
				List<String> numbers = Arrays.asList(line.split(" "));
				if (firstLine) {
					input.setPhotosCount(Integer.parseInt(numbers.get(0)));
					firstLine = false;
					lineCount = 0;
				} else {
					Photo photo = new Photo();
					photo.setId(lineCount);
					photo.setVertical(numbers.get(0).equals("V"));
					photo.setTagsCount(Integer.parseInt(numbers.get(1)));
					HashSet<String> tags = new HashSet<String>();
					tags.addAll(numbers.subList(2, numbers.size()));
					photo.setTags(tags);
					photos.add(photo);
					if (photo.getTagsCount() > CURRENT_MAX_TAGS_COUNT) {
						CURRENT_MAX_TAGS_COUNT = photo.getTagsCount();
					}
					lineCount++;
				}
			}
			SimpleEngine.STOP_PAIRING_H_VALUE = (CURRENT_MAX_TAGS_COUNT / 2);
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
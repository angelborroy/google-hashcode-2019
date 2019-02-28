package es.keensoft.main;

import java.io.File;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import es.keensoft.bean.Input;
import es.keensoft.bean.Output;
import es.keensoft.bean.SimpleEngine;

/**
 * Main class to be launched from command line
 * 
 * java -jar target/hashcode-2019-1.0.0.jar
 * --fileIn=src/main/resources/in/a_example.in
 * --fileOut=src/main/resources/in/a_example.out
 * 
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {

		PropertySource<?> ps = new SimpleCommandLinePropertySource(args);
		File inFile = new File(ps.getProperty("fileIn").toString());
		Input in = Translator.getInput(inFile);

		List<List<Integer>> slidesLines = SimpleEngine.scorePhotos(in.getPhotos());
		Output output = new Output();
		output.setSlidesCount(slidesLines.size());
		output.setSlidesLines(slidesLines);
		File outFile = new File(ps.getProperty("fileOut").toString());
		Translator.writeOutput(output, outFile);

	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}

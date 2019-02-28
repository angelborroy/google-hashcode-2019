package es.keensoft.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import es.keensoft.bean.Input;
import es.keensoft.bean.Output;

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

		System.out.println(in);
		
		Output output = new Output();
		output.setSlidesCount(2);
		
		List<List<Integer>> lines = new ArrayList<List<Integer>>();
		List<Integer> line = new ArrayList<Integer>();
		line.add(1);
		line.add(2);
		lines.add(line);
		output.setSlidesLines(lines);
		
		System.out.println(output);

		File outFile = new File(ps.getProperty("fileOut").toString());
		Translator.writeOutput(output, outFile);

	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}

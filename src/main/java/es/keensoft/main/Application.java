package es.keensoft.main;

import java.io.File;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import es.keensoft.bean.Input;
import es.keensoft.bean.Output;
import es.keensoft.main.engine.BasicStepEngine;
import es.keensoft.util.Translator;

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

		Output output = BasicStepEngine.start(in);

		File outFile = new File(ps.getProperty("fileOut").toString());
		Translator.writeOutput(output, outFile);

	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}

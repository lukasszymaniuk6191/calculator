package com.storware.calculator;

import com.storware.calculator.components.Calculator;
import com.storware.calculator.config.Config;
import com.storware.calculator.dto.Screen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class CalculatorApplication {

	private static Calculator calculator;
	private static Config config;

	@Autowired
	public CalculatorApplication(Calculator calculator, Config conf){
		this.calculator = calculator;
		config = conf;
	}

	public static void main(String[] args) {

		SpringApplication.run(CalculatorApplication.class, args);

		boolean toManyParamterers = false;

		if(args.length==0){
			System.out.println("Path readed from application.properties file");
		}else if(args.length==1){
			System.out.println("Path readed from terminal");
			config.setFilePath(args[0]);
		}else {
			System.out.println("Too many parameters have been provided\n");
			toManyParamterers = true;
		}

		if(!toManyParamterers) {

			Screen screen = calculator.calculate(config.getFilePath());

			System.out.println("INPUT FROM FILE");
			for (String line : screen.getInputFromFile()) {
				System.out.println(line);
			}

			System.out.println("OUTPUT");
			System.out.println(screen.getOutput());

			System.out.println("EXPLANATION");
			System.out.println(screen.getExplanation() + " = " + screen.getOutput());

		}
	}
}


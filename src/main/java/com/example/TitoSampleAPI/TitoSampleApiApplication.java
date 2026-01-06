package com.example.TitoSampleAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
// extends SpringBootServletInitializer 

@SpringBootApplication
public class TitoSampleApiApplication  {

	// @Override
	// protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	// 	return application.sources(TitoSampleApiApplication.class);
	// }

	public static void main(String[] args) {
		SpringApplication.run(TitoSampleApiApplication.class, args);
	}

}

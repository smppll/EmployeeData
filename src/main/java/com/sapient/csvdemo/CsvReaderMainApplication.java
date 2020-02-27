package com.sapient.csvdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CsvReaderMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvReaderMainApplication.class, args);
	}

}

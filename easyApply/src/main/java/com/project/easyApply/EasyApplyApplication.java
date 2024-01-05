package com.project.easyApply;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.awt.Desktop;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
@SpringBootApplication
public class EasyApplyApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyApplyApplication.class, args);
	}


}

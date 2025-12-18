package com.alvinodev.desafiogutendex;

import com.alvinodev.desafiogutendex.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafiogutendexApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DesafiogutendexApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal();
        principal.muestraMenu();
    }
}

//package com.alvinodev.screenmatchpro;
//
//import com.alvinodev.screenmatchpro.principal.Principal;
//import com.alvinodev.screenmatchpro.repository.SerieRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class ScreenmatchproApplicationConsole implements CommandLineRunner {
//
//    @Autowired
//    private SerieRepository repository;
//	public static void main(String[] args) {
//        SpringApplication.run(ScreenmatchproApplicationConsole.class, args);
//	}
//    @Override
//    public void run(String... args) throws Exception {
//        Principal principal = new Principal(repository);
//        principal.muestraElMenu();
//    }
//}
package com.unimag.medicaloffice;

import org.springframework.boot.SpringApplication;

public class TestMedicalofficeApplication {

	public static void main(String[] args) {
		SpringApplication.from(MedicalofficeApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

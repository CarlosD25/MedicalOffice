package com.unimag.medicaloffice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class MedicalofficeApplicationTests {

	@Test
	void contextLoads() {
	}

}

package com.teamdfx.addressregistry;

import com.teamdfx.addressregistry.config.ModelMapperConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

// @SpringBootApplication, bu sınıfın bir Spring Boot uygulaması olduğunu belirtir ve otomatik yapılandırmaları etkinleştirir.
@Import({ModelMapperConfig.class})
@SpringBootApplication
public class AddressRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressRegistryApplication.class, args);
	}

}

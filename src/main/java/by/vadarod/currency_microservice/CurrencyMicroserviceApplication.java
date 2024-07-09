package by.vadarod.currency_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

/**
 * @author Aliaksandr_Hryharovich
 * @date 08/07/2024
 */

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class CurrencyMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyMicroserviceApplication.class, args);
	}

}

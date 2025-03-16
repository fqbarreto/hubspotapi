package fqbarreto.hubspotapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HubspotapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HubspotapiApplication.class, args);
	}

}

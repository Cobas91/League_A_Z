package cobas.coding.lol_a_z_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication @EnableScheduling public class LolAZBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LolAZBackendApplication.class, args);
	}

}

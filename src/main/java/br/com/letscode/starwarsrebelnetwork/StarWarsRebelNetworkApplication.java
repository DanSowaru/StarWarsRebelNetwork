package br.com.letscode.starwarsrebelnetwork;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "StarWars API", version = "2.0", description = "Rebels Information"))
public class StarWarsRebelNetworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarWarsRebelNetworkApplication.class, args);
    }

}

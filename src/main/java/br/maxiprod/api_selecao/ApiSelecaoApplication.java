package br.maxiprod.api_selecao;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(servers = { @Server(url = "/", description = "Uma API de controle de gastos") })
@SpringBootApplication
public class ApiSelecaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiSelecaoApplication.class, args);
    }

}

package br.com.curso.udemy.productapi.config;

import br.com.curso.udemy.productapi.aplication.core.usecase.GetAllProductsUseCase;
import br.com.curso.udemy.productapi.aplication.ports.in.GetAllProductsUseCasePort;
import br.com.curso.udemy.productapi.aplication.ports.out.GetAllProductsAdapterPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    @Bean
    public GetAllProductsUseCasePort getAllProductsUseCasePort(GetAllProductsAdapterPort getAllProductsAdapterPort) {
        return new GetAllProductsUseCase(getAllProductsAdapterPort);
    }
}

package br.com.curso.udemy.productapi.config;

import br.com.curso.udemy.productapi.aplication.core.usecase.GetAllProductsUseCase;
import br.com.curso.udemy.productapi.aplication.core.usecase.category.CrudCategoryUseCase;
import br.com.curso.udemy.productapi.aplication.ports.in.category.CrudCategoryUseCasePort;
import br.com.curso.udemy.productapi.aplication.ports.in.product.GetAllProductsUseCasePort;
import br.com.curso.udemy.productapi.aplication.ports.out.GetAllProductsAdapterPort;
import br.com.curso.udemy.productapi.aplication.ports.out.category.CrudCategoryAdapterPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    @Bean
    public GetAllProductsUseCasePort getAllProductsUseCasePort(GetAllProductsAdapterPort getAllProductsAdapterPort) {
        return new GetAllProductsUseCase(getAllProductsAdapterPort);
    }

    @Bean
    public CrudCategoryUseCasePort crudCategoryUseCasePort(CrudCategoryAdapterPort crudCategoryAdapterPort) {
        return  new CrudCategoryUseCase(crudCategoryAdapterPort);
    }
}

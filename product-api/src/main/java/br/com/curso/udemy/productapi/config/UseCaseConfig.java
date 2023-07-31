package br.com.curso.udemy.productapi.config;

import br.com.curso.udemy.productapi.aplication.core.logging.Logger;
import br.com.curso.udemy.productapi.aplication.core.usecase.category.CrudCategoryUseCase;
import br.com.curso.udemy.productapi.aplication.core.usecase.product.CrudProductUseCase;
import br.com.curso.udemy.productapi.aplication.core.usecase.product.GetAllProductsUseCase;
import br.com.curso.udemy.productapi.aplication.core.usecase.product.UpdateProductStockUseCase;
import br.com.curso.udemy.productapi.aplication.core.usecase.supplier.CrudSupplierUseCase;
import br.com.curso.udemy.productapi.aplication.ports.in.category.CrudCategoryUseCasePort;
import br.com.curso.udemy.productapi.aplication.ports.in.product.CrudProductUseCasePort;
import br.com.curso.udemy.productapi.aplication.ports.in.product.GetAllProductsUseCasePort;
import br.com.curso.udemy.productapi.aplication.ports.in.product.UpdateProductStockUseCasePort;
import br.com.curso.udemy.productapi.aplication.ports.in.supplier.CrudSupplierUseCasePort;
import br.com.curso.udemy.productapi.aplication.ports.out.category.CrudCategoryAdapterPort;
import br.com.curso.udemy.productapi.aplication.ports.out.product.CrudProductAdapterPort;
import br.com.curso.udemy.productapi.aplication.ports.out.product.GetAllProductsAdapterPort;
import br.com.curso.udemy.productapi.aplication.ports.out.product.UpdateProductAdapterPort;
import br.com.curso.udemy.productapi.aplication.ports.out.supplier.CrudSupplierAdapterPort;
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
        return new CrudCategoryUseCase(crudCategoryAdapterPort);
    }

    @Bean
    public CrudSupplierUseCasePort crudSupplierUseCasePort(CrudSupplierAdapterPort crudSupplierAdapterPort) {
        return new CrudSupplierUseCase(crudSupplierAdapterPort);
    }

    @Bean
    public CrudProductUseCasePort crudProductUseCasePort(CrudProductAdapterPort crudProductAdapterPort) {
        return new CrudProductUseCase(crudProductAdapterPort);
    }

    @Bean
    UpdateProductStockUseCasePort updateProductStockUseCasePort(
            CrudProductAdapterPort crudProductAdapterPort,
            UpdateProductAdapterPort updateProductAdapterPort,
            Logger logger) {
        return new UpdateProductStockUseCase(crudProductAdapterPort, updateProductAdapterPort, logger);
    }
}

package br.com.curso.udemy.productapi.aplication.core.usecase.product;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Product;
import br.com.curso.udemy.productapi.aplication.ports.in.product.GetAllProductsUseCasePort;
import br.com.curso.udemy.productapi.aplication.ports.out.product.GetAllProductsAdapterPort;

import java.util.List;

public class GetAllProductsUseCase implements GetAllProductsUseCasePort {

    private final GetAllProductsAdapterPort getAllProductsAdapterPort;

    public GetAllProductsUseCase(GetAllProductsAdapterPort getAllProductsAdapterPort) {
        this.getAllProductsAdapterPort = getAllProductsAdapterPort;
    }

    @Override
    public List<Product> execute() {
        return getAllProductsAdapterPort.getAllProducts();
    }
}

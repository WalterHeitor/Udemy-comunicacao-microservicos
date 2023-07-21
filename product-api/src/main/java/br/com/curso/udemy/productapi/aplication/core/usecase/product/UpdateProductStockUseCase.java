package br.com.curso.udemy.productapi.aplication.core.usecase.product;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Product;
import br.com.curso.udemy.productapi.aplication.ports.in.product.UpdateProductStockUseCasePort;
import br.com.curso.udemy.productapi.aplication.ports.out.product.UpdateProductStockAdapterPort;

public class UpdateProductStockUseCase implements UpdateProductStockUseCasePort {

    private final UpdateProductStockAdapterPort updateProductStockAdapterPort;

    public UpdateProductStockUseCase(UpdateProductStockAdapterPort updateProductStockAdapterPort) {
        this.updateProductStockAdapterPort = updateProductStockAdapterPort;
    }

    @Override
    public void updateProductStock(Product product) {

    }
}

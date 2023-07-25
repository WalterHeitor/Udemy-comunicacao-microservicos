package br.com.curso.udemy.productapi.aplication.core.usecase.product;

import br.com.curso.udemy.productapi.adapters.in.rabbit.dto.ProductStockDTO;
import br.com.curso.udemy.productapi.aplication.core.domain.model.Product;
import br.com.curso.udemy.productapi.aplication.ports.in.product.UpdateProductStockUseCasePort;
import br.com.curso.udemy.productapi.aplication.ports.out.product.CrudProductAdapterPort;

public class UpdateProductStockUseCase implements UpdateProductStockUseCasePort {

    private final CrudProductAdapterPort crudProductAdapterPort;

    public UpdateProductStockUseCase(CrudProductAdapterPort crudProductAdapterPort) {
        this.crudProductAdapterPort = crudProductAdapterPort;
    }


    @Override
    public void updateProductStock(ProductStockDTO productStock) {

        Product productById = crudProductAdapterPort.findById(productStock.getProducts().get(0).getProductId());

    }
}

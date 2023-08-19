package br.com.curso.udemy.productapi.aplication.core.usecase.product;

import br.com.curso.udemy.productapi.adapters.in.controller.dto.request.ProductQuantityDTO;
import br.com.curso.udemy.productapi.adapters.in.controller.dto.request.ProductStockRequest;
import br.com.curso.udemy.productapi.adapters.in.controller.dto.response.SuccessResponse;
import br.com.curso.udemy.productapi.adapters.out.client.SalesClient;
import br.com.curso.udemy.productapi.adapters.out.persistence.exceptions.ValidationException;
import br.com.curso.udemy.productapi.aplication.core.logging.Logger;
import br.com.curso.udemy.productapi.aplication.ports.in.product.CheckProductsStockUseCasePort;
import br.com.curso.udemy.productapi.aplication.ports.in.product.GetProductsSalesUseCasePort;
import br.com.curso.udemy.productapi.aplication.ports.out.product.CrudProductAdapterPort;
import br.com.curso.udemy.productapi.aplication.ports.out.product.UpdateProductAdapterPort;


public class CheckProductsStockUseCase implements CheckProductsStockUseCasePort {

    private final CrudProductAdapterPort crudProductAdapterPort;
    private final Logger logger;

    public CheckProductsStockUseCase(CrudProductAdapterPort crudProductAdapterPort, UpdateProductAdapterPort updateProductAdapterPort, SalesClient salesClient, Logger logger) {
        this.crudProductAdapterPort = crudProductAdapterPort;
        this.logger = logger;
    }

    @Override
    public SuccessResponse checkProductsStock(ProductStockRequest productStockRequest) {

        productStockRequest.productQuantity()
                .forEach(this::validateStock);
        return SuccessResponse.create("Thestock is ok!");
    }
    private void validateStock(ProductQuantityDTO productQuantityDTO) {

        var product = crudProductAdapterPort.findById(productQuantityDTO.productId());
        if (productQuantityDTO.quantity() > product.getQuantityAvailable()) {
            throw new ValidationException(String.format(
                    "The product %s is out of stock.", product.getProductId()));
        }
    }

}

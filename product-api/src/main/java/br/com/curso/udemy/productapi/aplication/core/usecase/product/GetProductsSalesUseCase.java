package br.com.curso.udemy.productapi.aplication.core.usecase.product;

import br.com.curso.udemy.productapi.adapters.in.controller.dto.response.ProductSalesResponse;
import br.com.curso.udemy.productapi.adapters.out.client.SalesClient;
import br.com.curso.udemy.productapi.adapters.out.persistence.exceptions.ValidationException;
import br.com.curso.udemy.productapi.aplication.core.logging.Logger;
import br.com.curso.udemy.productapi.aplication.ports.in.product.GetProductsSalesUseCasePort;
import br.com.curso.udemy.productapi.aplication.ports.out.product.CrudProductAdapterPort;
import br.com.curso.udemy.productapi.aplication.ports.out.product.UpdateProductAdapterPort;

import static org.springframework.util.ObjectUtils.isEmpty;


public class GetProductsSalesUseCase implements GetProductsSalesUseCasePort {

    private final CrudProductAdapterPort crudProductAdapterPort;
    private final SalesClient salesClient;
    private final Logger logger;

    public GetProductsSalesUseCase(CrudProductAdapterPort crudProductAdapterPort, UpdateProductAdapterPort updateProductAdapterPort, SalesClient salesClient, Logger logger) {
        this.crudProductAdapterPort = crudProductAdapterPort;
        this.salesClient = salesClient;
        this.logger = logger;
    }


    @Override
    public ProductSalesResponse executeFinById(Long productId) {
        var product = crudProductAdapterPort.findById(productId);
        try {
            var sales =
                    salesClient.findByproductsId(product.getProductId())
                            .orElseThrow(() -> new ValidationException("The sales was not found by this product."));
            return ProductSalesResponse.of(product, sales.getSalesIds());
        } catch (Exception e) {
            throw new ValidationException(
                    "There was an error trying to get product's sales.");
        }
    }
}

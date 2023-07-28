package br.com.curso.udemy.productapi.aplication.core.usecase.product;

import br.com.curso.udemy.productapi.adapters.in.rabbit.dto.ProductStockDTO;
import br.com.curso.udemy.productapi.adapters.out.persistence.exceptions.ValidationException;
import br.com.curso.udemy.productapi.aplication.core.domain.model.Product;
import br.com.curso.udemy.productapi.aplication.core.logging.Logger;
import br.com.curso.udemy.productapi.aplication.ports.in.product.UpdateProductStockUseCasePort;
import br.com.curso.udemy.productapi.aplication.ports.out.product.CrudProductAdapterPort;
import org.springframework.util.ObjectUtils;

import static org.springframework.util.ObjectUtils.isEmpty;


public class UpdateProductStockUseCase implements UpdateProductStockUseCasePort {

    private final CrudProductAdapterPort crudProductAdapterPort;
    private final Logger logger;

    public UpdateProductStockUseCase(CrudProductAdapterPort crudProductAdapterPort, Logger logger) {
        this.crudProductAdapterPort = crudProductAdapterPort;
        this.logger = logger;
    }


    @Override
    public void updateProductStock(ProductStockDTO productStock) {

        try {
            validateStockUpdateData(productStock);
            Product productById = crudProductAdapterPort.findById(productStock.getProducts().get(0).getProductId());

        } catch (Exception e) {
            logger.error(e.getMessage()
                    .formatted("Error while trying to update stock for message with error: %s"), e);
        }
    }

    private static void validateStockUpdateData(ProductStockDTO productStock) {
        if (isEmpty(productStock) || isEmpty(productStock.getSalesId())) {
            throw new ValidationException("The product data and  the sales ID must be informed");
        }
        if (isEmpty(productStock.getProducts())) {
            throw new ValidationException("The sales  products must be informed");
        }
        productStock.getProducts().forEach(salesProduct -> {
            if (ObjectUtils.isEmpty(salesProduct.getQuantity())) {
                throw  new ValidationException("The productId and quantity must be informed.");
            }
        });
    }
}

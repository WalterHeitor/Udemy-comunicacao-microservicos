package br.com.curso.udemy.productapi.aplication.core.usecase.product;

import br.com.curso.udemy.productapi.adapters.in.rabbit.dto.ProductStockDTO;
import br.com.curso.udemy.productapi.adapters.in.rabbit.sales.SalesConfirmationDTO;
import br.com.curso.udemy.productapi.adapters.in.rabbit.sales.SalesStatus;
import br.com.curso.udemy.productapi.adapters.out.persistence.exceptions.ValidationException;
import br.com.curso.udemy.productapi.aplication.core.domain.model.Product;
import br.com.curso.udemy.productapi.aplication.core.logging.Logger;
import br.com.curso.udemy.productapi.aplication.ports.in.product.UpdateProductStockUseCasePort;
import br.com.curso.udemy.productapi.aplication.ports.out.product.CrudProductAdapterPort;
import br.com.curso.udemy.productapi.aplication.ports.out.product.UpdateProductAdapterPort;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;

import static org.springframework.util.ObjectUtils.isEmpty;


public class UpdateProductStockUseCase implements UpdateProductStockUseCasePort {

    private final CrudProductAdapterPort crudProductAdapterPort;
    private final UpdateProductAdapterPort updateProductAdapterPort;
    private final Logger logger;

    public UpdateProductStockUseCase(CrudProductAdapterPort crudProductAdapterPort, UpdateProductAdapterPort updateProductAdapterPort, Logger logger) {
        this.crudProductAdapterPort = crudProductAdapterPort;
        this.updateProductAdapterPort = updateProductAdapterPort;
        this.logger = logger;
    }


    @Override
    public void updateProductStock(ProductStockDTO productStock) {

        try {
            validateStockUpdateData(productStock);
            var productsForUpdate = new ArrayList<Product>();
            productStock
                    .getProducts()
                    .forEach(salesProduct -> {
                        Product productById =
                                crudProductAdapterPort.findById(salesProduct.getProductId());
                        validateQuantityProductStock(
                                salesProduct.getQuantity() > productById.getQuantityAvailable(),
                                String.format("Product %s with quantity is out of stock.",
                                        productById.getProductId()));
                        productById.updateStock(salesProduct.getQuantity());
                        productsForUpdate.add(productById);
                    });
            if (!productsForUpdate.isEmpty()) {
                crudProductAdapterPort.updateAll(productsForUpdate);
                var approvedMenssage =
                        new SalesConfirmationDTO(productStock.getSalesId(), SalesStatus.APPROVED.name());
                updateProductAdapterPort.sendSalesConfirmationMessage(approvedMenssage);
            }

        } catch (Exception e) {
            logger.error(e.getMessage()
                    .formatted("Error while trying to update stock for message with error: %s"), e);
            var rejectedMessage = new SalesConfirmationDTO(productStock.getSalesId(), SalesStatus.REJECTED.name());
            updateProductAdapterPort.sendSalesConfirmationMessage(rejectedMessage);
        }
    }

    private static void validateQuantityProductStock(boolean salesProduct, String message) {
        if (salesProduct) {
            throw new ValidationException(message);
        }
    }

    private static void validateStockUpdateData(ProductStockDTO productStock) {
        validateQuantityProductStock(isEmpty(productStock) || isEmpty(productStock.getSalesId()),
                "The product data and  the sales ID must be informed");

        validateQuantityProductStock(isEmpty(productStock.getProducts()),
                "The sales  products must be informed");

        productStock.getProducts().forEach(salesProduct ->
                validateQuantityProductStock(ObjectUtils.isEmpty(salesProduct.getQuantity()),
                        "The productId and quantity must be informed.")
        );
    }
}

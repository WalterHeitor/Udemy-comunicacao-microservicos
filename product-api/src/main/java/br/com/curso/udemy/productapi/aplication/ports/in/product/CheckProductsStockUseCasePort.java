package br.com.curso.udemy.productapi.aplication.ports.in.product;

import br.com.curso.udemy.productapi.adapters.in.controller.dto.request.ProductStockRequest;
import br.com.curso.udemy.productapi.adapters.in.controller.dto.response.SuccessResponse;

public interface CheckProductsStockUseCasePort {

    SuccessResponse checkProductsStock(ProductStockRequest productStockRequest);
}

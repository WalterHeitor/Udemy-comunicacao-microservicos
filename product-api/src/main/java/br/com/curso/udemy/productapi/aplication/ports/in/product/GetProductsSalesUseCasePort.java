package br.com.curso.udemy.productapi.aplication.ports.in.product;

import br.com.curso.udemy.productapi.adapters.in.controller.dto.response.ProductSalesResponse;

public interface GetProductsSalesUseCasePort {
    ProductSalesResponse executeFinById(Long productId);
}

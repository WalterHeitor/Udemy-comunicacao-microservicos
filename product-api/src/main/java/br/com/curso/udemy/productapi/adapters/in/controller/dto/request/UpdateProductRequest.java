package br.com.curso.udemy.productapi.adapters.in.controller.dto.request;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Product;

public record UpdateProductRequest(
        Long productId,
        String name,
        Integer quantityAvailable,
        UpdateSupplierRequest updateSupplierRequest,
        UpdateCategoryRequest updateCategoryRequest
) {
    public Product toDomain() {
        return new Product(
                productId,
                name,
                quantityAvailable,
                updateSupplierRequest.toDomain(),
                updateCategoryRequest.toDomain()
        );
    }
}

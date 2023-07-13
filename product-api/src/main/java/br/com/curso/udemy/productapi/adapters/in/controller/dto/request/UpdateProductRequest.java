package br.com.curso.udemy.productapi.adapters.in.controller.dto.request;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UpdateProductRequest(
        @JsonProperty("product_id")
        @Positive
        Long productId,

        @NotBlank
        @Size(max = 2000)
        String name,

        @Positive
        @NotBlank
        @JsonProperty("update_supplier_request")
        UpdateSupplierRequest updateSupplierRequest,

        @Positive
        @NotBlank
        @JsonProperty("quantity_available")
        Integer quantityAvailable,

        @Positive
        @NotBlank
        @JsonProperty("update_category_request")
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

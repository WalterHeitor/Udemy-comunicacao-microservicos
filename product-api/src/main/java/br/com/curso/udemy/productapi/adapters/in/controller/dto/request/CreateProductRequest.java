package br.com.curso.udemy.productapi.adapters.in.controller.dto.request;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Category;
import br.com.curso.udemy.productapi.aplication.core.domain.model.Product;
import br.com.curso.udemy.productapi.aplication.core.domain.model.Supplier;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateProductRequest(

        @NotBlank
        @Size(max = 2000)
        String name,
        @NotBlank
        @JsonProperty("quantity_available")
        Integer quantityAvailable,
        @JsonProperty("supplier_id")
        Long supplierId,
        @JsonProperty("category_id")
        Long categoryId

) {


    public Product toDomain() {

        return new Product(
                null,
                this.name,
                this.quantityAvailable,
                new Supplier(this.supplierId, ""),
                new Category(this.categoryId, ""));
    }
}

package br.com.curso.udemy.productapi.adapters.in.controller.dto.request;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Category;
import br.com.curso.udemy.productapi.aplication.core.domain.model.Product;
import br.com.curso.udemy.productapi.aplication.core.domain.model.Supplier;

public record CreateProductRequest(

        String name,
        Integer quantityAvailable,
        Long supplierId,
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

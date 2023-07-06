package br.com.curso.udemy.productapi.adapters.in.controller.dto.request;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Category;

public record UpdateCategoryRequest(
        Long categoryId,
        String description
) {
    public Category toDomain() {
        return new Category(this.categoryId, this.description);
    }
}

package br.com.curso.udemy.productapi.adapters.in.controller.dto.request;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Category;
import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequest(
        @NotBlank
        String description
) {
    public Category toDomain() {
        return new Category(null, description);
    }
}

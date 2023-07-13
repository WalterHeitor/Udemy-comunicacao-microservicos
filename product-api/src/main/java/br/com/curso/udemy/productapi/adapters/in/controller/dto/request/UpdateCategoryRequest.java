package br.com.curso.udemy.productapi.adapters.in.controller.dto.request;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UpdateCategoryRequest(
        @Positive
        @JsonProperty("category_id")
        Long categoryId,
        @NotBlank
        @Size(max = 2000)
        String description
) {
    public Category toDomain() {
        return new Category(this.categoryId, this.description);
    }
}

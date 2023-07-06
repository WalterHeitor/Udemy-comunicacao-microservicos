package br.com.curso.udemy.productapi.adapters.in.controller.dto.response;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private Long categoryId;
    private String description;

    public static CategoryResponse fromToDomain(Category category) {
        return CategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .description(category.getDescription())
                .build();
    }
}

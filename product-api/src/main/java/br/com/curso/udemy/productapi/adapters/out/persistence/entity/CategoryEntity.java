package br.com.curso.udemy.productapi.adapters.out.persistence.entity;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    @Column(name = "description", nullable = false)
    private String description;

    public static CategoryEntity fromCategory(Category category) {

        return CategoryEntity.builder()
                .categoryId(category.getCategoryId())
                .description(category.getDescription())
                .build();
    }

    public Category toDomain() {
        return new Category(this.getCategoryId(), this.getDescription());
    }
}

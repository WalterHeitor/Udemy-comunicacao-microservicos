package br.com.curso.udemy.productapi.aplication.core.domain.model;

import br.com.curso.udemy.productapi.adapters.out.persistence.entity.CategoryEntity;

import java.util.Objects;

public class Category {
    private Long categoryId;
    private String description;

    public Category() {
    }

    public Category(Long categoryId, String description) {
        this.categoryId = categoryId;
        this.description = description;
    }

    public static Category fromEntity(CategoryEntity categoryEntity) {

        return new Category(
                categoryEntity.getCategoryId(),
                categoryEntity.getDescription());
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return categoryId.equals(category.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId);
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", description='" + description + '\'' +
                '}';
    }
}

package br.com.curso.udemy.productapi.adapters.out.persistence;

import br.com.curso.udemy.productapi.adapters.out.persistence.entity.CategoryEntity;
import br.com.curso.udemy.productapi.adapters.out.persistence.repository.CategoryRepository;
import br.com.curso.udemy.productapi.aplication.core.domain.model.Category;
import br.com.curso.udemy.productapi.aplication.ports.out.category.CrudCategoryAdapterPort;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class CrudCategoryAdapter implements CrudCategoryAdapterPort {

    private final CategoryRepository categoryRepository;

    public CrudCategoryAdapter(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(Category category) {

        CategoryEntity categoryEntity = CategoryEntity.fromCategory(category);
        CategoryEntity savedEntity = categoryRepository.save(categoryEntity);
        return savedEntity.toDomain();
    }

    @Override
    public Category update(Category category) {

        return categoryRepository.save(CategoryEntity
                        .fromCategory(category))
                .toDomain();
    }

    @Override
    public Category findById(Long categoryId) {
        try {
            Optional<CategoryEntity> optionalCategoryEntity = findByIdCategoryEntity(categoryId);
            throwNoSuchElementExceptionIfCategoryNotExists(categoryId, optionalCategoryEntity);
            return Category.fromEntity(optionalCategoryEntity.get());
        } catch (Exception e) {
            throw new RuntimeException("Create exception: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long categoryId) {
        Optional<CategoryEntity> optionalCategoryEntity = findByIdCategoryEntity(categoryId);
        throwNoSuchElementExceptionIfCategoryNotExists(categoryId, optionalCategoryEntity);
        this.categoryRepository.delete(optionalCategoryEntity.get());
    }

    private static void throwNoSuchElementExceptionIfCategoryNotExists(Long categoryId, Optional<CategoryEntity> optionalCategoryEntity) {
        if (optionalCategoryEntity.isEmpty()) {
            throw new NoSuchElementException(
                    String.format("Category no longer exists for category id = %d", categoryId));
        }
    }

    private Optional<CategoryEntity> findByIdCategoryEntity(Long categoryId) {
        return this.categoryRepository.findById(categoryId);
    }

}

package br.com.curso.udemy.productapi.adapters.out.persistence.repository;

import br.com.curso.udemy.productapi.adapters.out.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    List<CategoryEntity> findByDescriptionIgnoreCaseContaining(String description);
}

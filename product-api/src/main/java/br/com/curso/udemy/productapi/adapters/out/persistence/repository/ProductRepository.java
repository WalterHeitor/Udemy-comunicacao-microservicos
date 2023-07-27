package br.com.curso.udemy.productapi.adapters.out.persistence.repository;

import br.com.curso.udemy.productapi.adapters.out.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>,
        JpaSpecificationExecutor<ProductEntity> {

    boolean existsByProductId(Long productId);

    boolean existsByCategoryCategoryId(Long categoryId);
}

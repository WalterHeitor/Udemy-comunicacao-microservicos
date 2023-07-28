package br.com.curso.udemy.productapi.adapters.out.persistence.specification;

import br.com.curso.udemy.productapi.adapters.out.persistence.entity.ProductEntity;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationProduct {

    public static Specification<ProductEntity> likeName(String name) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%" + name + "%"));
    }
}

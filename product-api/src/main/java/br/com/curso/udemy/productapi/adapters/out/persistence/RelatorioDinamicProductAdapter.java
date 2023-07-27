package br.com.curso.udemy.productapi.adapters.out.persistence;

import br.com.curso.udemy.productapi.adapters.out.persistence.entity.ProductEntity;
import br.com.curso.udemy.productapi.adapters.out.persistence.repository.ProductRepository;
import br.com.curso.udemy.productapi.adapters.out.persistence.specification.SpecificationProduct;
import br.com.curso.udemy.productapi.aplication.core.domain.model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RelatorioDinamicProductAdapter  {

    private final ProductRepository productRepository;

    public RelatorioDinamicProductAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> getLikeName(String name) {
        List<ProductEntity> productEntities =
                productRepository.findAll(Specification.where(SpecificationProduct.name(name)));
        return Product.fromEntityList(productEntities);
    }
}

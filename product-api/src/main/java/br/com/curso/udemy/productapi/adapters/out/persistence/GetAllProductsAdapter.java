package br.com.curso.udemy.productapi.adapters.out.persistence;

import br.com.curso.udemy.productapi.adapters.out.persistence.entity.ProductEntity;
import br.com.curso.udemy.productapi.adapters.out.persistence.repository.ProductRepository;
import br.com.curso.udemy.productapi.aplication.core.domain.Product;
import br.com.curso.udemy.productapi.aplication.ports.out.GetAllProductsAdapterPort;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class GetAllProductsAdapter  implements GetAllProductsAdapterPort {

    private final ProductRepository productRepository;

    public GetAllProductsAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return Product.fromEntity(productEntities);
    }
}

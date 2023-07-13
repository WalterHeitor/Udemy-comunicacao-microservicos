package br.com.curso.udemy.productapi.adapters.out.persistence;

import br.com.curso.udemy.productapi.adapters.out.persistence.entity.ProductEntity;
import br.com.curso.udemy.productapi.adapters.out.persistence.repository.ProductRepository;
import br.com.curso.udemy.productapi.aplication.core.domain.model.Category;
import br.com.curso.udemy.productapi.aplication.core.domain.model.Product;
import br.com.curso.udemy.productapi.aplication.core.domain.model.Supplier;
import br.com.curso.udemy.productapi.aplication.ports.out.category.CrudCategoryAdapterPort;
import br.com.curso.udemy.productapi.aplication.ports.out.product.CrudProductAdapterPort;
import br.com.curso.udemy.productapi.aplication.ports.out.supplier.CrudSupplierAdapterPort;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class CrudProductAdapter implements CrudProductAdapterPort {

    private final ProductRepository productRepository;
    private final CrudCategoryAdapterPort crudCategoryAdapterPort;
    private final CrudSupplierAdapterPort crudSupplierAdapterPort;

    public CrudProductAdapter(ProductRepository productRepository, CrudCategoryAdapterPort crudCategoryAdapterPort, CrudSupplierAdapterPort crudSupplierAdapterPort) {
        this.productRepository = productRepository;
        this.crudCategoryAdapterPort = crudCategoryAdapterPort;
        this.crudSupplierAdapterPort = crudSupplierAdapterPort;
    }

    @Override
    public Product create(Product product) {

        getCategory(product);
        getSupplier(product);

        ProductEntity productEntity = ProductEntity.fromProduct(product);
        ProductEntity savedEntity = productRepository.save(productEntity);

        return savedEntity.toDomain();
    }

    private void getSupplier(Product product) {
        Supplier supplier =
                crudSupplierAdapterPort.findById(product.getSupplier().getSupplierId());
        product.setSupplier(supplier);
    }

    private void getCategory(Product product) {
        Category category =
                crudCategoryAdapterPort.findById(product.getCategory().getCategoryId());
        product.setCategory(category);
    }

    @Override
    public Product update(Product product) {

        return productRepository.save(ProductEntity
                        .fromProduct(product))
                .toDomain();
    }

    @Override
    public Product findById(Long productId) {
        try {
            Optional<ProductEntity> optionalProductEntity = findByIdProductEntity(productId);
            throwNoSuchElementExceptionIfProductNotExists(productId, optionalProductEntity);
            return Product.fromEntity(optionalProductEntity.get());
        } catch (Exception e) {
            throw new RuntimeException("Create exception: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long productId) {
        Optional<ProductEntity> optionalProductEntity = findByIdProductEntity(productId);
        throwNoSuchElementExceptionIfProductNotExists(productId, optionalProductEntity);
        this.productRepository.delete(optionalProductEntity.get());
    }

    private static void throwNoSuchElementExceptionIfProductNotExists(Long productId, Optional<ProductEntity> optionalProductEntity) {
        if (optionalProductEntity.isEmpty()) {
            throw new NoSuchElementException(
                    String.format("Product no longer exists for product id = %d", productId));
        }
    }

    private Optional<ProductEntity> findByIdProductEntity(Long productId) {
        return this.productRepository.findById(productId);
    }

}

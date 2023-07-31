package br.com.curso.udemy.productapi.aplication.core.domain.model;

import br.com.curso.udemy.productapi.adapters.out.persistence.entity.ProductEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Product {
    private Long productId;
    private String name;
    private Integer quantityAvailable;
    private Supplier supplier;
    private Category category;
    private LocalDateTime createAt;

    public Product(Long productId, String name, Integer quantityAvailable, Supplier supplier, Category category) {
        this.productId = productId;
        this.name = name;
        this.quantityAvailable = quantityAvailable;
        this.supplier = supplier;
        this.category = category;
    }

    public Product(Long productId, String name, Integer quantityAvailable,
                   Supplier supplier, Category category, LocalDateTime createAt) {
        this.productId = productId;
        this.name = name;
        this.quantityAvailable = quantityAvailable;
        this.supplier = supplier;
        this.category = category;
        this.createAt = createAt;
    }

    public static Product fromEntity(ProductEntity productEntity) {
        return new Product(
                productEntity.getProductId(),
                productEntity.getName(),
                productEntity.getQuantityAvailable(),
                new Supplier(
                        productEntity.getSupplier().getSupplierId(),
                        productEntity.getSupplier().getName()),
                new Category(
                        productEntity.getCategory().getCategoryId(),
                        productEntity.getCategory().getDescription()),
                productEntity.getCreateAt()
        );
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId.equals(product.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    public static List<Product> fromEntityList(List<ProductEntity> productEntities) {
        return productEntities.stream().map(productEntity -> new Product(
                productEntity.getProductId(),
                productEntity.getName(),
                productEntity.getQuantityAvailable(),
                new Supplier(
                        productEntity.getSupplier().getSupplierId(),
                        productEntity.getSupplier().getName()
                ),
                new Category(
                        productEntity.getCategory().getCategoryId(),
                        productEntity.getCategory().getDescription()
                ),
                productEntity.getCreateAt()
        )).collect(Collectors.toList());
    }

    public void updateStock(Integer quantity) {
        quantityAvailable = quantityAvailable - quantity;
    }
}

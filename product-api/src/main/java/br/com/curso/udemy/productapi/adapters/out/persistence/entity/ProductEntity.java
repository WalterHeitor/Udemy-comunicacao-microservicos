package br.com.curso.udemy.productapi.adapters.out.persistence.entity;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "quantity_available", nullable = false)
    private Integer quantityAvailable;
    @ManyToOne
    @JoinColumn(name = "fk_supplier")
    private SupplierEntity supplier;
    @ManyToOne
    @JoinColumn(name = "fk_category")
    private CategoryEntity category;
    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createAt;

    public ProductEntity(
            Long productId, String name, Integer quantityAvailable, SupplierEntity supplier, CategoryEntity category) {
        this.productId = productId;
        this.name = name;
        this.quantityAvailable = quantityAvailable;
        this.supplier = supplier;
        this.category = category;
    }

    @PrePersist
    public void prePersist() {
        this.createAt = LocalDateTime.now();
    }


    public static ProductEntity fromProduct(Product product) {
        return new ProductEntity(
                product.getProductId(),
                product.getName(),
                product.getQuantityAvailable(),
                SupplierEntity.builder()
                        .supplierId(product.getSupplier().getSupplierId())
                        .name(product.getSupplier().getName())
                        .build(),
                CategoryEntity.builder()
                        .categoryId(product.getCategory().getCategoryId())
                        .description(product.getCategory().getDescription())
                        .build()
        );
    }

    public Product toDomain() {
        return new Product(
                this.productId,
                this.name,
                this.quantityAvailable,
                this.supplier.toDomain(),
                this.category.toDomain()
        );
    }
}

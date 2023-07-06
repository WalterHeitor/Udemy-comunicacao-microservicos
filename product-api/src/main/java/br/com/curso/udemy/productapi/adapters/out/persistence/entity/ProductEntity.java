package br.com.curso.udemy.productapi.adapters.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}

package br.com.curso.udemy.productapi.aplication.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "quantity_available", nullable = false)
    private Integer quantityAvailable;
    @ManyToOne
    @JoinColumn(name = "fk_supplier")
    private Supplier supplier;
    @ManyToOne
    @JoinColumn(name = "fk_category")
    private Category category;
}

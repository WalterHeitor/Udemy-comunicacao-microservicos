package br.com.curso.udemy.productapi.adapters.out.persistence.entity;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Supplier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "supplier")
public class SupplierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;
    @Column(name = "name", nullable = false)
    private String name;

    public static SupplierEntity fromSupplier(Supplier supplier) {
        return SupplierEntity.builder()
                .supplierId(supplier.getSupplierId())
                .name(supplier.getName())
                .build();
    }

    public Supplier toDomain() {
        return new Supplier(this.getSupplierId(), this.getName());
    }
}


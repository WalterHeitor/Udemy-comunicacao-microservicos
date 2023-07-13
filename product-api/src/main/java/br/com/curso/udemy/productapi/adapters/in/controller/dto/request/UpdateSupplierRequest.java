package br.com.curso.udemy.productapi.adapters.in.controller.dto.request;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Supplier;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UpdateSupplierRequest (
        @Positive
        Long supplierId,
        @NotBlank
        @Size(max = 2000)
        String name
){
    public Supplier toDomain() {
        return new Supplier(this.supplierId, this.name);
    }
}

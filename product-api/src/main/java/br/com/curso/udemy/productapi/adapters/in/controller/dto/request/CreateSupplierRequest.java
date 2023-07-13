package br.com.curso.udemy.productapi.adapters.in.controller.dto.request;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Supplier;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateSupplierRequest(

        @NotBlank
        @Size(max = 2000)
        String name) {
    public Supplier toDomain() {
        return new Supplier(null, this.name);
    }
}

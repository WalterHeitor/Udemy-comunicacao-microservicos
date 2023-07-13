package br.com.curso.udemy.productapi.adapters.in.controller.dto.response;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Supplier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierResponse {
    private Long supplierId;
    private String name;

    public static SupplierResponse fromToDomain(Supplier executeCreate) {
        return SupplierResponse.builder()
                .supplierId(executeCreate.getSupplierId())
                .name(executeCreate.getName())
                .build();
    }
}

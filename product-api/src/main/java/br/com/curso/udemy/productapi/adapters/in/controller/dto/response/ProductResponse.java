package br.com.curso.udemy.productapi.adapters.in.controller.dto.response;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    @JsonProperty("product_id")
    private Long productId;
    private String name;
    @JsonProperty("quantity_available")
    private Integer quantityAvailable;
    private SupplierResponse supplier;
    private CategoryResponse category;

    public static List<ProductResponse> fromDomain(List<Product> products) {
        return products.stream().map(product -> ProductResponse.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .quantityAvailable(product.getQuantityAvailable())
                .category(CategoryResponse.builder()
                        .categoryId(product.getCategory().getCategoryId())
                        .description(product.getCategory().getDescription())
                        .build())
                .supplier(SupplierResponse.builder()
                        .supplierId(product.getSupplier().getSupplierId())
                        .name(product.getSupplier().getName())
                        .build())
                .build()).collect(Collectors.toList());
    }
}
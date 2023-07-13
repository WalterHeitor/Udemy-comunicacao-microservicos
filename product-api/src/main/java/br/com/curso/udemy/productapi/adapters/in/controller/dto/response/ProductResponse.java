package br.com.curso.udemy.productapi.adapters.in.controller.dto.response;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    @JsonProperty("create_at")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createAt;

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
                .createAt(product.getCreateAt())
                .build()).collect(Collectors.toList());
    }

    public static ProductResponse fromToDomain(Product executeCreate) {
        return ProductResponse
                .builder()
                .productId(executeCreate.getProductId())
                .name(executeCreate.getName())
                .quantityAvailable(executeCreate.getQuantityAvailable())
                .category(CategoryResponse.builder()
                        .categoryId(executeCreate.getCategory().getCategoryId())
                        .description(executeCreate.getCategory().getDescription())
                        .build())
                .supplier(SupplierResponse.builder()
                        .supplierId(executeCreate.getSupplier().getSupplierId())
                        .name(executeCreate.getSupplier().getName())
                        .build())
                .createAt(executeCreate.getCreateAt())
                .build();
    }
}

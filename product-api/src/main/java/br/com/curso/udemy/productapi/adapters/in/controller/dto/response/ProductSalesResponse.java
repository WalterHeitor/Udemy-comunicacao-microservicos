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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSalesResponse {

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
    private List<String> sales;

    public static ProductSalesResponse of(Product product, List<String> sales) {
        return ProductSalesResponse
                .builder()
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
                .sales(sales)
                .build();
    }

}



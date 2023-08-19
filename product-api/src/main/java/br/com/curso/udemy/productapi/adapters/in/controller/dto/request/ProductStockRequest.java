package br.com.curso.udemy.productapi.adapters.in.controller.dto.request;

import java.util.List;

public record ProductStockRequest(
        List<ProductQuantityDTO> productQuantity
) {
}

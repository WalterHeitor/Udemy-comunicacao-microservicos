package br.com.curso.udemy.productapi.adapters.in.controller.dto.request;

public record ProductQuantityDTO(
        Long productId,
        Integer quantity
) {
}

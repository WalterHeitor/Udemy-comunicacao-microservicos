package br.com.curso.udemy.productapi.adapters.in.rabbit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductQuantityDTO {

    private Long productId;
    private Integer quantity;
}

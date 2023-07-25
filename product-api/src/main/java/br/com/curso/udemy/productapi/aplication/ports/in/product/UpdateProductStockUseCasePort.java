package br.com.curso.udemy.productapi.aplication.ports.in.product;

import br.com.curso.udemy.productapi.adapters.in.rabbit.dto.ProductStockDTO;

public interface UpdateProductStockUseCasePort {

    void updateProductStock(ProductStockDTO product);
}

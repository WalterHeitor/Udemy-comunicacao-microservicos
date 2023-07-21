package br.com.curso.udemy.productapi.aplication.ports.in.product;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Product;

public interface UpdateProductStockUseCasePort {

    void updateProductStock(Product product);
}

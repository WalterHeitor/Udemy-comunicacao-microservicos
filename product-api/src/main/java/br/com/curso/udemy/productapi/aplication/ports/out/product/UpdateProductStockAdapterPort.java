package br.com.curso.udemy.productapi.aplication.ports.out.product;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Product;

public interface UpdateProductStockAdapterPort {

    void updateProductStock(Product product);
}

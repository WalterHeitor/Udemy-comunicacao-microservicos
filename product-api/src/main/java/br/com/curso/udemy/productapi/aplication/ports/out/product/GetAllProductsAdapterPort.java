package br.com.curso.udemy.productapi.aplication.ports.out.product;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Product;

import java.util.List;

public interface GetAllProductsAdapterPort {
    List<Product> getAllProducts();
}

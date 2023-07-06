package br.com.curso.udemy.productapi.aplication.ports.out;

import br.com.curso.udemy.productapi.aplication.core.domain.Product;

import java.util.List;

public interface GetAllProductsAdapterPort {
    List<Product> getAllProducts();
}

package br.com.curso.udemy.productapi.aplication.ports.in.product;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Product;

import java.util.List;

public interface GetAllProductsUseCasePort {
    List<Product> execute();
}

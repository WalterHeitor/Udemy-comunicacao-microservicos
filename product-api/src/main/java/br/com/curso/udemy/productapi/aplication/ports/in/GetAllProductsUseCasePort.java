package br.com.curso.udemy.productapi.aplication.ports.in;

import br.com.curso.udemy.productapi.aplication.core.domain.Product;

import java.util.List;

public interface GetAllProductsUseCasePort {
    List<Product> execute();
}

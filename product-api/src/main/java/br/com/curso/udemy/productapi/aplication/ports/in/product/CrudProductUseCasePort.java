package br.com.curso.udemy.productapi.aplication.ports.in.product;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Product;

public interface CrudProductUseCasePort {
    Product executeCreate (Product product);
    Product executeUpdate (Product product);
    Product executeFindById (Long productId);
    void executeDelete (Long productId);
}

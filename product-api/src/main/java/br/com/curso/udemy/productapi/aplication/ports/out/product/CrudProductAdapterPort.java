package br.com.curso.udemy.productapi.aplication.ports.out.product;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Product;

public interface CrudProductAdapterPort {

    Product create(Product product);

    Product update(Product product);

    Product findById(Long productId);

    void delete(Long productId);

}

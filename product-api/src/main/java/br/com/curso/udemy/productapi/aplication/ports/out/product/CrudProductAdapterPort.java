package br.com.curso.udemy.productapi.aplication.ports.out.product;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Product;

import java.util.List;

public interface CrudProductAdapterPort {

    Product create(Product product);

    Product update(Product product);

    Product findById(Long productId);

    void delete(Long productId);

    List<Product> updateAll(List<Product> products);

}

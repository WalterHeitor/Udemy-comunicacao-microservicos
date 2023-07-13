package br.com.curso.udemy.productapi.aplication.core.usecase.product;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Product;
import br.com.curso.udemy.productapi.aplication.ports.in.product.CrudProductUseCasePort;
import br.com.curso.udemy.productapi.aplication.ports.out.product.CrudProductAdapterPort;

public class CrudProductUseCase implements CrudProductUseCasePort {

    private final CrudProductAdapterPort crudProductAdapterPort;

    public CrudProductUseCase(CrudProductAdapterPort crudProductAdapterPort) {
        this.crudProductAdapterPort = crudProductAdapterPort;
    }

    @Override
    public Product executeCreate(Product product) {

        return crudProductAdapterPort.create(product);
    }

    @Override
    public Product executeUpdate(Product product) {

        return crudProductAdapterPort.update(product);
    }

    @Override
    public Product executeFindById(Long productId) {

        return crudProductAdapterPort.findById(productId);
    }

    @Override
    public void executeDelete(Long productId) {

        this.crudProductAdapterPort.delete(productId);
    }
}

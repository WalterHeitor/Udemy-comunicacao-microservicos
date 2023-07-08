package br.com.curso.udemy.productapi.aplication.core.usecase.category;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Category;
import br.com.curso.udemy.productapi.aplication.ports.in.category.CrudCategoryUseCasePort;
import br.com.curso.udemy.productapi.aplication.ports.out.category.CrudCategoryAdapterPort;

public class CrudCategoryUseCase implements CrudCategoryUseCasePort {

    private final CrudCategoryAdapterPort crudCategoryAdapterPort;

    public CrudCategoryUseCase(CrudCategoryAdapterPort crudCategoryAdapterPort) {
        this.crudCategoryAdapterPort = crudCategoryAdapterPort;
    }

    @Override
    public Category executeCreate(Category category) {

        return crudCategoryAdapterPort.create(category);
    }

    @Override
    public Category executeUpdate(Category category) {

        return crudCategoryAdapterPort.update(category);
    }

    @Override
    public Category executeFindById(Long categoryId) {

        return crudCategoryAdapterPort.findById(categoryId);
    }

    @Override
    public void executeDelete(Long categoryId) {

        this.crudCategoryAdapterPort.delete(categoryId);
    }
}

package br.com.curso.udemy.productapi.aplication.ports.in.category;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Category;

public interface CrudCategoryUseCasePort {
    Category executeCreate (Category category);
    Category executeUpdate (Category category);
    Category executeFindById (Long categoryId);
    void executeDelete (Long categoryId);
}

package br.com.curso.udemy.productapi.aplication.ports.out.category;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Category;

public interface CrudCategoryAdapterPort {

    Category create (Category category);
    Category update (Category category);
    Category findById (Long categoryId);
    void delete (Long categoryId);

}

package br.com.curso.udemy.productapi.aplication.ports.out.supplier;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Supplier;

public interface CrudSupplierAdapterPort {

    Supplier create(Supplier supplier);

    Supplier update(Supplier supplier);

    Supplier findById(Long supplierId);

    void delete(Long supplierId);

}

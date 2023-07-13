package br.com.curso.udemy.productapi.aplication.ports.in.supplier;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Supplier;

public interface CrudSupplierUseCasePort {

    Supplier executeCreate(Supplier toDomain);

    Supplier executeUpdate(Supplier category);

    Supplier executeFindById(Long supplierId);

    void executeDelete(Long supplierId);
}

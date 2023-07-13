package br.com.curso.udemy.productapi.aplication.core.usecase.supplier;

import br.com.curso.udemy.productapi.aplication.core.domain.model.Supplier;
import br.com.curso.udemy.productapi.aplication.ports.in.supplier.CrudSupplierUseCasePort;
import br.com.curso.udemy.productapi.aplication.ports.out.supplier.CrudSupplierAdapterPort;

public class CrudSupplierUseCase implements CrudSupplierUseCasePort {

    private final CrudSupplierAdapterPort crudSupplierAdapterPort;

    public CrudSupplierUseCase(CrudSupplierAdapterPort crudSupplierAdapterPort) {
        this.crudSupplierAdapterPort = crudSupplierAdapterPort;
    }

    @Override
    public Supplier executeCreate(Supplier supplier) {

        return crudSupplierAdapterPort.create(supplier);
    }

    @Override
    public Supplier executeUpdate(Supplier supplier) {

        return crudSupplierAdapterPort.update(supplier);
    }

    @Override
    public Supplier executeFindById(Long supplierId) {

        return crudSupplierAdapterPort.findById(supplierId);
    }

    @Override
    public void executeDelete(Long supplierId) {

        this.crudSupplierAdapterPort.delete(supplierId);
    }
}

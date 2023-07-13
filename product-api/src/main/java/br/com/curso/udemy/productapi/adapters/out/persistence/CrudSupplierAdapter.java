package br.com.curso.udemy.productapi.adapters.out.persistence;

import br.com.curso.udemy.productapi.adapters.out.persistence.entity.SupplierEntity;
import br.com.curso.udemy.productapi.adapters.out.persistence.repository.SupplierRepository;
import br.com.curso.udemy.productapi.aplication.core.domain.model.Supplier;
import br.com.curso.udemy.productapi.aplication.ports.out.supplier.CrudSupplierAdapterPort;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class CrudSupplierAdapter implements CrudSupplierAdapterPort {

    private final SupplierRepository supplierRepository;

    public CrudSupplierAdapter(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Supplier create(Supplier supplier) {

        SupplierEntity supplierEntity = SupplierEntity.fromSupplier(supplier);
        SupplierEntity savedEntity = supplierRepository.save(supplierEntity);
        return savedEntity.toDomain();
    }

    @Override
    public Supplier update(Supplier supplier) {

        return supplierRepository.save(SupplierEntity
                        .fromSupplier(supplier))
                .toDomain();
    }

    @Override
    public Supplier findById(Long supplierId) {
        try {
            Optional<SupplierEntity> optionalSupplierEntity = findByIdSupplierEntity(supplierId);
            throwNoSuchElementExceptionIfSupplierNotExists(supplierId, optionalSupplierEntity);
            return Supplier.fromEntity(optionalSupplierEntity.get());
        } catch (Exception e) {
            throw new RuntimeException("Create exception: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long SupplierId) {
        Optional<SupplierEntity> optionalSupplierEntity = findByIdSupplierEntity(SupplierId);
        throwNoSuchElementExceptionIfSupplierNotExists(SupplierId, optionalSupplierEntity);
        this.supplierRepository.delete(optionalSupplierEntity.get());
    }

    private static void throwNoSuchElementExceptionIfSupplierNotExists(Long SupplierId, Optional<SupplierEntity> optionalSupplierEntity) {
        if (optionalSupplierEntity.isEmpty()) {
            throw new NoSuchElementException(
                    String.format("Supplier no longer exists for Supplier id = %d", SupplierId));
        }
    }

    private Optional<SupplierEntity> findByIdSupplierEntity(Long SupplierId) {
        return this.supplierRepository.findById(SupplierId);
    }

}

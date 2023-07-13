package br.com.curso.udemy.productapi.adapters.in.controller;

import br.com.curso.udemy.productapi.adapters.in.controller.dto.request.CreateSupplierRequest;
import br.com.curso.udemy.productapi.adapters.in.controller.dto.request.UpdateSupplierRequest;
import br.com.curso.udemy.productapi.adapters.in.controller.dto.response.SupplierResponse;
import br.com.curso.udemy.productapi.aplication.ports.in.supplier.CrudSupplierUseCasePort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final CrudSupplierUseCasePort crudSupplierUseCasePort;

    public SupplierController(CrudSupplierUseCasePort crudSupplierUseCasePort) {
        this.crudSupplierUseCasePort = crudSupplierUseCasePort;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<SupplierResponse> createCategory(
            @RequestBody @Valid CreateSupplierRequest createSupplierRequest,
            UriComponentsBuilder uriBuilder
    ) {

        SupplierResponse supplierResponse = SupplierResponse
                .fromToDomain(crudSupplierUseCasePort.executeCreate(createSupplierRequest.toDomain()));
        URI location = uriBuilder.path("/api/categories/{id}")
                .buildAndExpand(supplierResponse.getSupplierId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Transactional
    @GetMapping("{id}")
    public ResponseEntity<SupplierResponse> getById(@PathVariable Long id) {

        return ResponseEntity.ok(SupplierResponse
                .fromToDomain(crudSupplierUseCasePort.executeFindById(id)));
    }

    @Transactional
    @PutMapping
    public ResponseEntity<SupplierResponse> updateCategory(
            @RequestBody @Valid UpdateSupplierRequest updateSupplierRequest) {

        return ResponseEntity.ok(SupplierResponse
                .fromToDomain(crudSupplierUseCasePort
                        .executeUpdate(updateSupplierRequest.toDomain())
                ));
    }

    @Transactional
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {

        crudSupplierUseCasePort.executeDelete(id);
        return ResponseEntity.noContent().build();
    }
}

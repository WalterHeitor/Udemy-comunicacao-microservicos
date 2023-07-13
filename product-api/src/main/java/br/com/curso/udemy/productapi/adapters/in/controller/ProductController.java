package br.com.curso.udemy.productapi.adapters.in.controller;


import br.com.curso.udemy.productapi.adapters.in.controller.dto.request.CreateProductRequest;
import br.com.curso.udemy.productapi.adapters.in.controller.dto.request.UpdateProductRequest;
import br.com.curso.udemy.productapi.adapters.in.controller.dto.response.ProductResponse;
import br.com.curso.udemy.productapi.aplication.ports.in.product.CrudProductUseCasePort;
import br.com.curso.udemy.productapi.aplication.ports.in.product.GetAllProductsUseCasePort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final GetAllProductsUseCasePort getAllProductsUseCasePort;
    private final CrudProductUseCasePort crudProductUseCasePort;

    public ProductController(GetAllProductsUseCasePort getAllProductsUseCasePort, CrudProductUseCasePort crudProductUseCasePort) {
        this.getAllProductsUseCasePort = getAllProductsUseCasePort;
        this.crudProductUseCasePort = crudProductUseCasePort;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {

      return ResponseEntity.ok(ProductResponse.fromDomain(getAllProductsUseCasePort.execute()));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @RequestBody @Valid CreateProductRequest createProductRequest,
            UriComponentsBuilder uriBuilder
    ) {

        ProductResponse productResponse = ProductResponse
                .fromToDomain(crudProductUseCasePort.executeCreate(createProductRequest.toDomain()));
        URI location = uriBuilder.path("/api/products/{id}")
                .buildAndExpand(productResponse.getProductId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Transactional
    @GetMapping("{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {

        return ResponseEntity.ok(ProductResponse
                .fromToDomain(crudProductUseCasePort.executeFindById(id)));
    }

    @Transactional
    @PutMapping
    public ResponseEntity<ProductResponse> updateProduct(
            @RequestBody @Valid UpdateProductRequest updateProductRequest) {

        return ResponseEntity.ok(ProductResponse
                .fromToDomain(crudProductUseCasePort
                        .executeUpdate(updateProductRequest.toDomain())
                ));
    }

    @Transactional
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        crudProductUseCasePort.executeDelete(id);
        return ResponseEntity.noContent().build();
    }
}

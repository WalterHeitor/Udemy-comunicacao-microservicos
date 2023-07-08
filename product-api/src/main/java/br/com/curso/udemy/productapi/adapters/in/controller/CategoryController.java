package br.com.curso.udemy.productapi.adapters.in.controller;

import br.com.curso.udemy.productapi.adapters.in.controller.dto.request.CreateCategoryRequest;
import br.com.curso.udemy.productapi.adapters.in.controller.dto.request.UpdateCategoryRequest;
import br.com.curso.udemy.productapi.adapters.in.controller.dto.response.CategoryResponse;
import br.com.curso.udemy.productapi.aplication.ports.in.category.CrudCategoryUseCasePort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CrudCategoryUseCasePort crudCategoryUseCasePort;

    public CategoryController(CrudCategoryUseCasePort crudCategoryUseCasePort) {
        this.crudCategoryUseCasePort = crudCategoryUseCasePort;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(
            @RequestBody @Valid CreateCategoryRequest createCategoryRequest,
            UriComponentsBuilder uriBuilder
    ) {

        CategoryResponse categoryResponse = CategoryResponse
                .fromToDomain(crudCategoryUseCasePort.executeCreate(createCategoryRequest.toDomain()));
        URI location = uriBuilder.path("/api/categories/{id}")
                .buildAndExpand(categoryResponse.getCategoryId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Transactional
    @GetMapping("{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Long id) {

        return ResponseEntity.ok(CategoryResponse
                .fromToDomain(crudCategoryUseCasePort.executeFindById(id)));
    }

    @Transactional
    @PutMapping
    public ResponseEntity<CategoryResponse> updateCategory(
            @RequestBody @Valid UpdateCategoryRequest updateCategoryRequest) {

        return ResponseEntity.ok(CategoryResponse
                .fromToDomain(crudCategoryUseCasePort
                        .executeUpdate(updateCategoryRequest.toDomain())
                ));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {

        crudCategoryUseCasePort.executeDelete(id);
        return ResponseEntity.noContent().build();
    }
}

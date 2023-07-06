package br.com.curso.udemy.productapi.adapters.in.controller;


import br.com.curso.udemy.productapi.adapters.in.controller.dto.response.ProductResponse;
import br.com.curso.udemy.productapi.aplication.ports.in.product.GetAllProductsUseCasePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final GetAllProductsUseCasePort getAllProductsUseCasePort;

    public ProductController(GetAllProductsUseCasePort getAllProductsUseCasePort) {
        this.getAllProductsUseCasePort = getAllProductsUseCasePort;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {

      return ResponseEntity.ok(ProductResponse.fromDomain(getAllProductsUseCasePort.execute()));
    }
}

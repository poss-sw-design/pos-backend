package com.pos.backend.controller;

import com.pos.backend.domain.Product;
import com.pos.backend.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService service;

  public ProductController(ProductService service) {
    this.service = service;
  }

  @GetMapping
  public List<Product> getAll() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getById(@PathVariable Long id) {
    Product p = service.findById(id);
    if (p == null) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(p);
  }

  @PostMapping
  public Product create(@RequestBody Product product) {
    return service.create(product);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> update(
    @PathVariable Long id,
    @RequestBody Product product
  ) {
    Product updated = service.update(id, product);
    if (updated == null) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(updated);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}

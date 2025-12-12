package com.pos.backend.controller;

import com.pos.backend.dto.product.ProductCreateRequest;
import com.pos.backend.dto.product.ProductResponse;
import com.pos.backend.dto.product.ProductUpdateRequest;
import com.pos.backend.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProductResponse createProduct(@RequestBody ProductCreateRequest req) {
    return productService.createProduct(req);
  }

  @GetMapping("/{productId}")
  public ProductResponse getProduct(@PathVariable Long productId) {
    return productService.getProduct(productId);
  }

  @PatchMapping("/{productId}")
  public ProductResponse updateProduct(
    @PathVariable Long productId,
    @RequestBody ProductUpdateRequest req
  ) {
    return productService.updateProduct(productId, req);
  }
}

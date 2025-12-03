package com.pos.backend.service;

import com.pos.backend.domain.Product;
import com.pos.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

  private final ProductRepository repo;

  public ProductService(ProductRepository repo) {
    this.repo = repo;
  }

  public List<Product> findAll() {
    return repo.findAll();
  }

  public Product findById(Long id) {
    return repo.findById(id).orElse(null);
  }

  public Product create(Product product) {
    return repo.save(product);
  }

  public Product update(Long id, Product update) {
    return repo.findById(id).map(p -> {
      p.setName(update.getName());
      p.setPrice(update.getPrice());
      p.setCategory(update.getCategory());
      p.setMerchantId(update.getMerchantId());
      return repo.save(p);
    }).orElse(null);
  }

  public void delete(Long id) {
    repo.deleteById(id);
  }
}

package com.example.inventoryrestapi;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductDAO productDAO = new ProductDAO();

    // GET http://localhost:8080/api/products
    @GetMapping
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    // GET http://localhost:8080/api/products/5
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        Product product = productDAO.getProductById(id);
        if(product == null) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        return product;
    }

    // POST http://localhost:8080/api/products
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productDAO.addProduct(product);
    }

    // PUT http://localhost:8080/api/products/1
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        Product updated = productDAO.updateProduct(id, product);
        if(updated == null) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        return updated;
    }

    // DELETE http://localhost:8080/api/products/1
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        boolean deleted = productDAO.deleteProduct(id);
        if(deleted) {
            return "Product deleted successfully";
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }
}
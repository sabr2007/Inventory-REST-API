package com.example.inventoryrestapi;
import org.springframework.web.bind.annotation.*;
import java.util.List;
//http://localhost:8080/api/products/

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductDAO productDAO = new ProductDAO();


    @GetMapping
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }


    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        Product product = productDAO.getProductById(id);
        if(product == null) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        return product;
    }


    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productDAO.addProduct(product);
    }


    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        Product updated = productDAO.updateProduct(id, product);
        if(updated == null) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        return updated;
    }


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
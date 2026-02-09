package com.example.inventoryrestapi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private void validateProduct(Product p) {
        if (p.getName() == null || p.getName().isBlank()) {
            throw new IllegalArgumentException("product name cannot be empty");
        }
        if (p.getPrice() < 0) {
            throw new IllegalArgumentException("price cannot be negative");
        }
        if (p.getBarcode() < 10000 || p.getBarcode() > 99999) {
            throw new IllegalArgumentException("barcode must be exactly 5 digits");
        }
    }

    public Product addProduct(Product p) {
        validateProduct(p);
        String sql = "INSERT INTO Products (name, price, barcode) VALUES(?, ?, ?) RETURNING id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, p.getName());
            pstmt.setDouble(2, p.getPrice());
            pstmt.setInt(3, p.getBarcode());

            ResultSet rs = pstmt.executeQuery(); // вместо executeUpdate()
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                return new Product(generatedId, p.getName(), p.getPrice(), p.getBarcode());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Products";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Product p = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("barcode")
                );
                products.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Product getProductById(int id) {
        String sql = "SELECT * FROM Products WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("barcode")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> sortProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Products ORDER BY PRICE DESC";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Product p = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("barcode")
                );
                products.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }


    public Product updateProduct(int id, Product updatedProduct) {
        validateProduct(updatedProduct);
        String sql = "UPDATE Products SET name = ?, price = ?, barcode = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, updatedProduct.getName());
            pstmt.setDouble(2, updatedProduct.getPrice());
            pstmt.setInt(3, updatedProduct.getBarcode());
            pstmt.setInt(4, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return new Product(id, updatedProduct.getName(),
                        updatedProduct.getPrice(), updatedProduct.getBarcode());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM Products WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
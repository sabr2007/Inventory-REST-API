package com.example.inventoryrestapi;
import java.sql.*;

public class ProductDAO {

    public void addProduct(Product p){
        String sql = "INSERT INTO Products (name, price, barcode) VALUES(?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, p.getName());
            pstmt.setDouble(2, p.getPrice());
            pstmt.setInt(3, p.getBarcode());
            pstmt.executeUpdate();
            System.out.println("product was added succesfuly");

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void getAllProduct(){
        String sql = "SELECT * FROM PRODUCTS";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){

            while(rs.next()){
                System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getDouble("price"));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateProductPrice(int id, double newPrice){
        String sql = "UPDATE Products SET Price = ? WHERE id = ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setDouble(1, newPrice);
            pstmt.setInt(2, id);

            int rowsAffected = pstmt.executeUpdate();
            if(rowsAffected > 0)
                System.out.println("successfully changed");
            else
                System.out.println("no product with such id");

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id){
        String sql = "DELETE FROM Products WHERE ID = ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            if(rowsDeleted > 0)
                System.out.println("successfully deleted");
            else
                System.out.println("no product with such id");


        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void sortProducts(){
        String sql = "SELECT id, name, price, barcode FROM products GROUP BY id ORDER BY price DESC";
        try(Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while(rs.next()){
                System.out.println(rs.getInt("id") + " " + rs.getString("name")
                        + " " + rs.getDouble("price") + " " + rs.getInt("barcode"));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}

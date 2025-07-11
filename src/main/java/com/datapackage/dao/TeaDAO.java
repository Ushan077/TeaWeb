package com.datapackage.dao;

import com.datapackage.model.Tea;
import com.datapackage.util.DBConnection;

import java.sql.*;
import java.util.*;
import java.math.BigDecimal;

public class TeaDAO {

    public void addTea(Tea tea) {
        String sql = "INSERT INTO tea (name, region, grade, weight, strength, price, quantity, image_url,seller) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tea.getName());
            stmt.setString(2, tea.getRegion());
            stmt.setString(3, tea.getGrade());
            stmt.setBigDecimal(4, tea.getWeight());
            stmt.setString(5, tea.getStrength());
            stmt.setBigDecimal(6, tea.getPrice());
            stmt.setInt(7, tea.getQuantity());
            stmt.setString(8, tea.getImageUrl());
            stmt.setString(9, tea.getSeller());



            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Tea> getAllTeas() {
        List<Tea> list = new ArrayList<>();
        String sql = "SELECT * FROM tea";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Tea tea = new Tea();
                tea.setId(rs.getInt("id"));
                tea.setName(rs.getString("name"));
                tea.setRegion(rs.getString("region"));
                tea.setGrade(rs.getString("grade"));
                tea.setWeight(rs.getBigDecimal("weight"));
                tea.setStrength(rs.getString("strength"));
                tea.setPrice(rs.getBigDecimal("price"));
                tea.setQuantity(rs.getInt("quantity"));
                tea.setImageUrl(rs.getString("image_url"));
                tea.setSeller(rs.getString("seller"));  
                tea.setCreatedAt(rs.getTimestamp("created_at"));
                list.add(tea);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Tea getTeaById(int id) {
        Tea tea = null;
        String sql = "SELECT * FROM tea WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                tea = new Tea();
                tea.setId(rs.getInt("id"));
                tea.setName(rs.getString("name"));
                tea.setRegion(rs.getString("region"));
                tea.setGrade(rs.getString("grade"));
                tea.setWeight(rs.getBigDecimal("weight"));
                tea.setStrength(rs.getString("strength"));
                tea.setPrice(rs.getBigDecimal("price"));
                tea.setQuantity(rs.getInt("quantity"));
                tea.setImageUrl(rs.getString("image_url"));
                tea.setSeller(rs.getString("seller")); 
                tea.setCreatedAt(rs.getTimestamp("created_at"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return tea;
    }

    public void updateTea(Tea tea) {
    	String sql = "UPDATE tea SET name=?, region=?, grade=?, weight=?, strength=?, price=?, quantity=?, image_url=?, seller=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

        	stmt.setString(1, tea.getName());
        	stmt.setString(2, tea.getRegion());
        	stmt.setString(3, tea.getGrade());
        	stmt.setBigDecimal(4, tea.getWeight());
        	stmt.setString(5, tea.getStrength());
        	stmt.setBigDecimal(6, tea.getPrice());
        	stmt.setInt(7, tea.getQuantity());
        	stmt.setString(8, tea.getImageUrl());
        	stmt.setString(9, tea.getSeller());
        	stmt.setInt(10, tea.getId()); // ✅ Corrected index


            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteTea(int id) {
        String sql = "DELETE FROM tea WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

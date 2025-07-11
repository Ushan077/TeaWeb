package com.datapackage.dao;

import com.datapackage.model.*;
import com.datapackage.util.DBConnection;

import java.sql.*;
import java.util.List;

public class OrderDAO {
	public int createOrder(User user, List<OrderItem> items) throws SQLException, ClassNotFoundException {
	    int orderId = -1;
	    String insertOrder = "INSERT INTO orders (user_id, username, email, address,seller) VALUES (?, ?, ?, ?,?)";
	    String insertItem = "INSERT INTO order_items (order_id, tea_id, quantity) VALUES (?, ?, ?)";

	    try (Connection conn = DBConnection.getConnection()) {
	        conn.setAutoCommit(false);
	        try (PreparedStatement psOrder = conn.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS)) {
	            psOrder.setInt(1, user.getId());
	            psOrder.setInt(1, user.getId());
	            psOrder.setString(2, user.getUsername());
	            psOrder.setString(3, user.getEmail());
	            psOrder.setString(4, user.getAddress());
	            psOrder.setString(5, items.get(0).getTea().getSeller());

	            psOrder.executeUpdate();

	            ResultSet rs = psOrder.getGeneratedKeys();
	            if (rs.next()) {
	                orderId = rs.getInt(1);
	            }

	            try (PreparedStatement psItem = conn.prepareStatement(insertItem)) {
	                for (OrderItem item : items) {
	                    psItem.setInt(1, orderId);
	                    psItem.setInt(2, item.getTea().getId());
	                    psItem.setInt(3, item.getQuantity());
	                    psItem.addBatch();
	                }
	                psItem.executeBatch();
	            }

	            conn.commit();
	        } catch (SQLException e) {
	            conn.rollback();
	            throw e;
	        }
	    }
	    return orderId;
	}
}
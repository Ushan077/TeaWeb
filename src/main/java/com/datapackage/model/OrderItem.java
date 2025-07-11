package com.datapackage.model;

public class OrderItem {
    private int id;
    private int orderId;
    private Tea tea;
    private int quantity;

    // Getters & Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public Tea getTea() {
        return tea;
    }
    public void setTea(Tea tea) {
        this.tea = tea;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

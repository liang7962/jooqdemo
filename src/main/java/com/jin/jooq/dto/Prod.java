package com.jin.jooq.dto;

public class Prod {

    private String name;
    private String price;
    private boolean inStock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public Prod(String name, String price, boolean inStock) {
        this.name = name;
        this.price = price;
        this.inStock = inStock;
    }

    public Prod() {
    }
}

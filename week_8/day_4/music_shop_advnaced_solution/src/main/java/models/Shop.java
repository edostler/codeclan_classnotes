package models;

import models.StockItem;

import javax.persistence.*;
import java.util.List;

public class Shop {

    private int id;
    private String name;
    private List<StockItem> stock;

    public Shop(String name) {
        this.name = name;
    }

    public Shop() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StockItem> getStock() {
        return stock;
    }

    public void setStock(List<StockItem> stock) {
        this.stock = stock;
    }

    public int stockCount() {
        return this.stock.size();
    }

    public void addToStock(StockItem item) {
        this.stock.add(item);
    }

    public void removeFromStock(StockItem item) {
        this.stock.remove(item);
    }

    public int totalPotentialProfit() {
        int total = 0;
        for (StockItem item : stock) {
            total += item.calculateMarkup();
        }
        return total;
    }
}

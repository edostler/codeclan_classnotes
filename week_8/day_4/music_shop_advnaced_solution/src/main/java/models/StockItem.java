package models;

import behaviours.ISell;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class StockItem implements ISell {
    private int id;
    private int buyPrice;
    private int sellPrice;

    public StockItem(int buyPrice, int sellPrice) {
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
    }

    public StockItem() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="buy_price")
    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    @Column(name="sell_price")
    public int getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int calculateMarkup(){
        return this.sellPrice - this.buyPrice;
    }
}

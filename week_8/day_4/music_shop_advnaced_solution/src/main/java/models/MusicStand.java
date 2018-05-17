package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="stands")
public class MusicStand extends StockItem {

    private String colour;

    public MusicStand(String colour, int buyPrice, int sellPrice) {
        super(buyPrice, sellPrice);
        this.colour = colour;
    }

    public MusicStand(){

    }

    @Column(name="colour")
    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }


}

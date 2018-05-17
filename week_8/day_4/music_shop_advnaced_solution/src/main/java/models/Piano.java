package models;

import models.InstrumentType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="pianos")
public class Piano extends Instrument {
    private String manufacturer;

    public Piano(String manufacturer, String colour, int buyPrice, int sellPrice) {
        super(colour, InstrumentType.KEYBOARD, buyPrice, sellPrice);
        this.manufacturer = manufacturer;
    }

    public Piano(){

    }

    @Column(name="manufacturer")
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }



    public String play() {
        return "Plink Plonk";
    }

}

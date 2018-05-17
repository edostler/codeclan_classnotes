package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="guitars")
public class Guitar extends Instrument  {
    private String model;
    private int numberOfStrings;

    public Guitar(String model, String colour, int numberOfStrings, int costPrice, int retailPrice) {
        super(colour, InstrumentType.STRING, costPrice, retailPrice);
        this.numberOfStrings = numberOfStrings;
        this.model = model;
    }

    public Guitar() {
    }

    @Column(name="model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name="number_of_strings")
    public int getNumberOfStrings() {
        return numberOfStrings;
    }

    public void setNumberOfStrings(int numberOfStrings) {
        this.numberOfStrings = numberOfStrings;
    }

    public String play() {
        return "Kerrang!!!";
    }



}

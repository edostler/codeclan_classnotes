package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="books")
public class MusicBook extends StockItem {
    String title;

    public MusicBook(String title, int buyPrice, int sellPrice) {
        super(buyPrice, sellPrice);
        this.title = title;
    }

    public MusicBook(){

    }

    @Column(name="title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}

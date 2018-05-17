import java.util.ArrayList;

public class Bear {

    private String name;
    private ArrayList<Salmon> belly; //UPDATED

    public Bear(String name){
        this.belly = new ArrayList<Salmon>(); //UPDATED
        this.name = name;
    }

    public int foodCount(){
        return belly.size();
    }

    public void eat(River river){
        Salmon salmon = river.removeSalmon();
        belly.add(salmon);
    }

    public void sleep(){
        belly.clear();
    }
}

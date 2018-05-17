public class Car extends Vehicle {

    private String model;

    public Car(String model)
    {
        super(4);
        this.model = model;
    }

    public String getModel() {
        return this.model;
    }

    public String drive()
    {
        return "use steering wheel";
    }
}

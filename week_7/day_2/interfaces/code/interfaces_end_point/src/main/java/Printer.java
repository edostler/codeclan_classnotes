public class Printer implements IPeripheral, IPrint {
    private String make;
    private String model;

    public Printer(String make, String model) {
        this.make = make;
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String connect() {
        return "connected";
    }

    public String print(String data) {
        return "printing: " + data;
    }
}

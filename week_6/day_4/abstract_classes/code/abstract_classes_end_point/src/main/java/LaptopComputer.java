public class LaptopComputer extends Computer {

    public String runApplication(String appName){
        return super.runApplication(appName) + "Program " + appName + " is running";
    }

    public String closeApplication(String appName){
        return "Program " + appName + " is closing down";
    }
}

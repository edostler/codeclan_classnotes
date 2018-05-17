public class DesktopComputer extends Computer {
    public String runApplication(String appName) {
        return super.runApplication(appName) + "I am running " + appName;
    }

    public String closeApplication(String appName) {
        return "I am closing down " + appName;
    }
}

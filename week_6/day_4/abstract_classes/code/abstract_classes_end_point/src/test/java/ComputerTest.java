import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ComputerTest {
    DesktopComputer desktop;
    LaptopComputer laptop;

    @Before
    public void before() {
        desktop = new DesktopComputer();
        laptop = new LaptopComputer();
    }

    @Test
    public void desktopCanRunApplication() {
        assertEquals("IntelliJ Status: I am running IntelliJ", desktop.runApplication("IntelliJ"));
    }

    @Test
    public void laptopCanRunApplication() {
        assertEquals("Slack: Program Slack is running", laptop.runApplication("Slack"));
    }

    @Test
    public void desktopCanCloseApplication() {
        assertEquals("I am closing down IntelliJ", desktop.closeApplication("IntelliJ"));
    }

    @Test
    public void laptopCanCloseApplication() {
        assertEquals("Program Slack is closing down", laptop.closeApplication("Slack"));
    }

    @Test
    public void desktopCanShutDown() {
        assertEquals("Shutting down...", desktop.shutDown());
    }

    @Test
    public void laptopCanShutDown() {
        assertEquals("Shutting down...", laptop.shutDown());
    }

    @Test
    public void computerCanCloseApplicationAsLaptop() {
        Computer computer = new LaptopComputer();
        assertEquals("Shutting down...",computer.shutDown());
    }

    @Test
    public void computerCanCloseApplicationAsDesktop() {
        Computer computer = new LaptopComputer();
        computer = new DesktopComputer();
        assertEquals("Shutting down...",computer.shutDown());
    }


}

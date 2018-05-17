import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrinterTest {

    Printer printer;

    @Before
    public void before() {
        printer = new Printer("Epson", "Stylus");
    }

    @Test
    public void printerHasMake() {
        assertEquals("Epson", printer.getMake());
    }

    @Test
    public void printerHasModel() {
        assertEquals("Stylus", printer.getModel());
    }

    @Test
    public void canConnect() {
        assertEquals("connected", printer.connect());
    }

    @Test
    public void canPrint() {
        assertEquals("printing: Hello World", printer.print("Hello World"));
    }

    @Test
    public void canConnectAsPeripheral() {
        IPeripheral device = new Printer("Hewlett-Packard", "Deskjet");
        assertEquals("connected", device.connect());
    }

//    @Test
//    public void canPrintAsPeripheral() {
//        IPeripheral device = new Printer("Hewlett-Packard", "Deskjet");
//        assertEquals("printing: Hello World", device.print("Hello World"));
//    }
}

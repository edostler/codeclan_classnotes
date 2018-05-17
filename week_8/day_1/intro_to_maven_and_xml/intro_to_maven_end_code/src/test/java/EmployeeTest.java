import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmployeeTest {

    public Employee employee;

    @Before
    public void before(){
        employee = new Employee("Jack Jarvis", 12000.00);
    }

    @Test
    public void hasName(){
        assertEquals("Jack Jarvis", employee.getName());
    }

    @Test
    public void hasSalary(){
        assertEquals(12000.00, employee.getSalary(), 0.1);
    }

    @Test
    public void canRaiseSalary(){
        employee.raiseSalary(2000.00);
        assertEquals(14000.00, employee.getSalary(), 0.1);
    }
}

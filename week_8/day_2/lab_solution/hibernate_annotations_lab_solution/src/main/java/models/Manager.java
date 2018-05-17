package models;

import db.DBHelper;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="managers")
public class Manager {

    private int id;
    private String firstName;
    private String lastName;
    private int salary;
    private int budget;
    private Department department;
    private Set<Employee> employees;

    public Manager() {
    }

    public Manager(String firstName, String lastName, int salary, int budget) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.budget = budget;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name="last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name="salary")
    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Column(name="budget")
    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    @OneToOne(mappedBy ="manager", fetch = FetchType.EAGER)
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @OneToMany(mappedBy = "manager", fetch = FetchType.EAGER)
    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public void giveRaise(){
        for (Employee employee : this.employees){
            int oldSalary = employee.getSalary();
            double raise = oldSalary * 0.1;
            double newSalary = oldSalary + raise;
            employee.setSalary((int)newSalary);
            this.budget -= raise;
            DBHelper.update(employee);
        }
    }
}

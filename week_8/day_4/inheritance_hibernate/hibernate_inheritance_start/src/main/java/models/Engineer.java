package models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="engineers")
public class Engineer extends Employee {


    public Engineer() {
    }

    public Engineer(String firstName, String lastName, int salary, Department department) {
        super(firstName, lastName, salary, department);
    }

}

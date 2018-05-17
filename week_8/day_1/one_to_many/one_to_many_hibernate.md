# One-To-Many Relationships in Hibernate

## Learning Objectives

- Understand the one-to-many relationship.
- Know how to map one-to-many relationships using hibernate.
- Know how to perform an update, find and delete one entry.

## The one-to-many relationship

With our Employee system we have created database tables for Employee and Department but there is nothing that links the two of them at the moment.

For example, our Department might want information on the Employees it has.

> DRAW THE FOLLOWING

```
Employee
id
first_name
last_name
salary
```

```
Department
id
title
```

In SQL, when we start to have multiple tables, we relate the data row to one or more data rows in another table. ( this is why we call them relational databases )
We can do this a few different ways.

- One to one
- One to many
- Many to many

We relate data by individual table row

## One-to-many

Lets focus on the one-to-many for our application just now.

What if we wanted to associate the employee to the department? How could we do this?

We can setup a one to many association. We can say one department row is associated to many employee rows.  With this setup, we use the department_id as the FOREIGN KEY. We use this foreign key to reference the ID of the associated row in the other table.

So, our employee table should now look like this:

```
Empoyee
id
first_name
last_name
salary
department_id - Linked to id of department.
```

## Mapping one-to-many relationships in hibernate

So we want to be able to link an employee with a department.

> Hand out start point

Lets start off by modifying our Employee class to have a department as a property. (Note not the department id! More on this as we go...).

```java
//Employee.java

package models;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private int salary;
    private Department department;

    public Employee() {}

    public Employee(String firstName, String lastNname, int salary, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.department = department;
    }

    //AS BEFORE

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
```
And amend the department class to include a collectoin of employees.

```java
//Department.java

package models;

public class Department {

    private int id;
    private String title;
    private Set<Employee> employees;

    public Department() {}

    public Department(String title) {
        this.title = title;
    }

    // AS BEFORE

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
```

A `Set` is a Collection, (like an ArrayList) that cannot contain duplicate elements.

Ok so now that we have the department property we will need to amend our mapping file to accommodate this.

To do this we will need to amend both the mapping files for Employee and Department to link them in the correct way.

Hibernate maps the collection of Employees over as a set so we will add this to the mapping file.

```xml
<!-- department.hbn.xml-->

<hibernate-mapping>
    <class name = "models.Department" table = "departments">
        <id name = "id" type = "int" column = "id">
            <generator class="identity"/>
        </id>
        <property name = "title" column = "title" type = "string"/>

        <set name="employees" inverse="true" cascade="all" lazy="false">
            <key>
                <column name="department_id" not-null="true" />
            </key>
            <one-to-many class="models.Employee" />
        </set>
    </class>
</hibernate-mapping>
```

Couple of things to note here....

#### name

The collection property name.


#### inverse

This is the most confusing keyword in Hibernate. The “inverse” keyword is always declare in one-to-many and many-to-many relationship , it means which side is responsible of taking care of the relationship.
Here’s the question, if a save or update operation is performed in `Department` object, should it update the `Employee` relationship?
Answer - Yes it should. So Department is responsible for maintaining the relationship.

#### cascade

Set to all so that when we perform any update, save or delete it will cascade all of the operations. We can change to `persist`, `delete` and a host of others.

#### key

Sets the foreign key on the employee table. So in this case it will set the column department_id on the employee table to reference the id of the department table.

#### lazy

Lazy fetching, the default in hibernate, means that when a record is loaded from the database, the one-to-many relationship child rows are not loaded. We do want our employees to load if we bring back a department so we will set this to false.

#### one-to-many

Specifies the relationship type.

## Many-to-one

Ok now we need to amend the employee mapping file.

```xml
<!-- employee.hbn.xml -->
<hibernate-mapping>
    <!--AS BEFORE -->

    <many-to-one name="department" class="models.Department" column="department_id" lazy="false" not-null="true"/>
    </class>
</hibernate-mapping>
```

Here we need to add the column for the foreign key. We do this by specifying that this is a many-to-one relationship. (Which it is if you think about it... many employees to one department). This will now reference the primary key of the department table.

#### lazy

The first thing that we should discuss here is what lazy loading and eager loading are:

- Eager Loading is a design pattern in which data initialisation occurs on the spot
- Lazy Loading is a design pattern which is used to defer initialisation of an object as long as it’s possible

One Department can have multiple Employees. In eager loading strategy, if we load the User data, it will also load up all orders associated with it and will store it in a memory.

But, when lazy loading is enabled, if we pull up a Department, Employee data won’t be initialised and loaded into a memory until an explicit call is made to it.

## Running the application.

Ok so we now have our models mapped correctly lets try it out.

Open the runner file and lets add a department and employees.

```java
//Runner.java

import db.DBDepartment;
import db.DBEmployee;
import models.Department;
import models.Employee;

import java.util.List;

public class Runner {

    public static void main(String[] args) {
        DBEmployee.deleteAll();
        DBDepartment.deleteAll();

        Department dept1 = new Department("HR");
        DBDepartment.saveDepartment(dept1);

        Department dept2 = new Department("Sales");
        DBDepartment.saveDepartment(dept2);

        Employee employee1 = new Employee("Jack", "Jarvis", 25000, dept1);
        DBEmployee.saveEmployee(employee1);

        Employee employee2 = new Employee("Isa", "Drennan", 30000, dept2);
        DBEmployee.saveEmployee(employee2);

        List<Employee> employees = DBEmployee.getEmployees();

        List<Department> departments = DBDepartment.getDepartments();


    } // DEBUGGER
}
```

Excellent we have the employees saved and can access the department details for each employee.

## Task

Add a method into DBDepartment that takes in a department id and returns a list of all employees in that department.

In runner file get the list of employees back for the department and check in debugger.

### Solution

```java
//DBDepartment.java
public static List<Employee> getEmployees(int id){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Employee> employees = null;
        try {
            transaction = session.beginTransaction();
            String hql = "from Employee WHERE department_id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", id)
            employees =  query.list();
        } catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return employees;
    }
```

Note the use of `:id` in DBDeperatment.

Hibernate has it's own way of adding `Named Parameters`. You will notice the `:id` in the query.

This is a named Parameter. (Kind of like %d in String.format).

> Note if they haven't seen String.format before explain it as a placeholder)

We then set the value of the parameter by using `query.setInteger(key, value)`.

We will talk a bit more about this when we dig deeper into queries.

```java
//Runner.java

List<Employee> employeesByDept = DBDepartment.getEmployees(dept1.getId());

      
```

## Recap
> Instructor note: Ask the class...

When mapping one-to-many what does `inverse` mean?

<details>
<summary>Answer:</summary>

Which side is responsible of taking care of the relationship.
</details>

<br>

And in many-to-one what does lazy mean?

<details>
<summary>Answer:</summary>

If we pull up a Department, Employee data won’t be initialised and loaded into a memory until an explicit call is made to it.

</details>
<br>

## Summary

We've seen:
 - What a one-to-many relationship is
 - How to map one-to-many-relationships in hibernate

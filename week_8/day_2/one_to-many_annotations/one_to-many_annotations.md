# One-To-Many Relationships using annotations

## Learning Objectives

- Understand the one-to-many relationship.
- Know how to map one-to-many relationships using hibernate annotations.

## The one-to-many relationship

We have seen how a One-to-Many relationship can be implemented using a `Set` java collection that does not contain any duplicate element. We already have seen how to map Set collection in xml, we should be all set to go with one-to-many mapping using annotations.

A Set is mapped with a `Set<>` element in the mapping table and initialised with java.util.HashSet. You can use a Set collection in your class when there is no duplicate element required in the collection.

So far we have used annotations to map employees and departments independently.

Let's now add in the relationship between the two.

Again we will map this using many-to-one in the employee class and one-to-many in the department class.  

> Either use code from earlier today or hand out start point.


## Many-to-one

Open up the `Employee` class and add the following code:

```java
// Employee.java

@Entity
@Table(name="employees")
public  class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private int salary;
    private Department department; // ADDED

    //AS Before

    public Department getDepartment() {
        return department;
    } // ADDED

    public void setDepartment(Department department) {
        this.department = department;
    } // ADDED
  }

```

Ok so now we need to add the annotations to map the employee to a department. Again we will ad the annotations to the getter so that hibernate uses this to access the property.

```java
// Employee.java

    @ManyToOne // ADDED
    @JoinColumn(name="department_id", nullable=false) // ADDED
    public Department getDepartment() {
        return department;
    }
```

##### `@ManyToOne`

The `@ManyToOne` annotation is used to set the relationship between employee and department entities.


##### `@JoinColumn`

The `@JoinColumn` annotation will generate a column department_id (a foreign key) in Employee class which will point to Department Class primary key.

To verify that the foreign key is really mapped in the employees table you can use `nullable = false`.

We will also change the constructor of Employee to take in the department on creation of Employee object.


```java
// Employee.java

public Employee(String firstName, String lastName, int salary, Department department) { // MODIFIED
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.department = department; // ADDED
    }

```

## One-to-many

And now we will map the Set of employees in the Department class.

Open the Department file and add the following.

```java
// Department.java

@Entity
@Table(name="departments")
public class Department {


    private int id;
    private String title;
    private Set<Employee> employees; // ADDED

    // AS BEFORE

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
```

So in this case we are mapping the one-to-many relationship so we need to use `@OneToMany` annotation.
Again we will add this above the getter for employees.

```java
// Department.java

    @OneToMany(mappedBy="department", fetch = FetchType.EAGER) // ADDED
    public Set<Employee> getEmployees() {
        return employees;
    }
```

##### `@OneToMany`

The `@OneToMany` annotation indicates that one Department object relates to many Employee objects.

Please note that the `@OneToMany` annotation is used to define the property in Employee class that will be used to map the `mappedBy` variable. That’s why we have a property named `department` in the Employee class.

The `fetch = FetchType.EAGER` is similar to the `lazy` property we used in xml. Setting to `EAGER` means that when we query a department we will also get the set of employees back as well. 


## Getting the Employees back.

Ok so lets open the runner and add 2 employees to the same department.

```java
// Runner.java

public static void main(String[] args) {

        DBHelper.deleteAll(Employee.class);
        DBHelper.deleteAll(Department.class);


        Department dept1 = new Department("HR");
        DBHelper.save(dept1);
        Department dept2 = new Department("Sales");
        DBHelper.save(dept2);

        Employee employee1 = new Employee("Al", "Bundy", 35000, dept1); // MODIFIED
        DBHelper.save(employee1);

        Employee employee2 = new Employee("Peggy", "Bundy", 50000, dept1); //MODIFIED
        DBHelper.save(employee2);
    }

```

Again to be sure this will work, drop and recreate the employeedb.

Run the file and it should now save the Department and Employee entries.
>Note if configuration window pops up select the module to run from drop down (there should only be one option)

We can check this in the terminal by connecting into our database. Our employees table should now include `department_id` column with value of 1 for both entries.


## Getting a list of employees per department.

Let's check that we can get a list of employees by department back from the entries.

To get the list of employees we will need to add a function into the DBHelper file. This function will take in the class type for `Employee` and the instance of `Department` that we want to use.

```java
// DBHelper.java

public static <T> List<T> getEmployeesForDepartment(Class classType, Department dept) {
  session = HibernateUtil.getSessionFactory().openSession();
  List<T> results = null;
  try {
    transaction = session.beginTransaction();
    Criteria cr = session.createCriteria(classType);
    cr.add(Restrictions.eq("department", dept));
    results =  cr.list();
    transaction.commit();
  } catch (HibernateException e) {
    transaction.rollback();
    e.printStackTrace();
  } finally {
    session.close();
  }
  return results;
}
```

Note that in the restrictions we match the department and not the department id?

That is because hibernate is using the class which has a department property. Not the database which has the department_id property.

And lets check results in debugger.

```java
//Runner.java

List<Employee> employees = DBHelper.getEmployeesForDepartment(Employee.class, dept1);  // ADDED
```

We should now see our 2 employees in that department.


## Recap
> Instructor note: Ask the class...

When mapping one-to-many what does `@JoinColumn` do?

<details>
<summary>Answer:</summary>
- Generate a column department_id (a foreign key)
</details>

<br>

And in many-to-one what does `mappedBy` mean?

<details>
<summary>Answer:</summary>

It defines the property in linked class that will be used to map the `mappedBy` variable.

</details>
<br>

## Summary

We've seen:
 - What a one-to-many relationship is
 - How to map one-to-many-relationships in hibernate using annotations
 - How to return a collection from database.

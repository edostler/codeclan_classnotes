# Criteria Queries and Native SQL

## Learning Objectives

- Understand Hibernate Criteria Queries.
- Know how to implement clauses in Criteria Queries.
- Understand Native SQL and when to use it.
- Run queries using both Criteria Queries and Native SQL

## Introduction

So far we have seen a little bit of `HQL`. As we said HQL is similar to SQL.

We have seen a couple of methods where we have used what seems like SQL when returning a list of all employees, etc.

But we are using an ORM so we shouldn't really need to write any SQL like code at all. We have seen this in the save and update methods that hibernate provides for us.

Enter Criteria Queries...

## Criteria Queries

Criteria Queries provide an alternate way of manipulating objects, and in turn data available.

These queries use the Criteria API, which allows you to build up a criteria query object and apply rules and logical conditions.

The Hibernate Session interface provides a createCriteria() method, which can be used to create a Criteria object that returns instances of the persistence object's class when your application executes a criteria query.

## Creating a simple Criteria Query

As we have seen we used a `FROM` clause to return a list of employees or departments. To do this we had to pass in the class name as a string.

This isn't ideal as it can lead to errors in spelling, etc.

With `Critera Queries` we can actually pass in the class itself.

Let's have a look at this.

In the runner we will change the call to the `getAll()` method and pass in the class itself for `Employee`


```java
// Runner.java

List<Employee> employees = DBHelper.getAll(Employee.class); // MODIFIED

System.out.println(); // DEBUGGER line
```

So this passes in the class itself. We now change our `getAll()` method to take in the `Class` instead of a `String`.

```java
//DBHelper.java

public static <T> List<T> getAll(Class classType) //MODIFIED
{
  // AS BEFORE
}
```

To create the Criteria Query we use the session and call the `createCriteria()` method and pass in the class.

```java
// DBHelper.java

try {
  transaction = session.beginTransaction();
  Criteria cr = session.createCriteria(classType); // NEW
  results = cr.list(); // NEW

  transaction.commit();
} catch (HibernateException e) {
}
```

Hibernate queries have a `.list()` method that returns a list of Employee objects. So no need for us to map over result sets ourselves. Neat huh?

Run this and check it out in debugger.

Awesome, we can now get our employees back as a list without having to write any HQL either.

## Getting by ID.

We can also set `restrictions` on criteria queries.

We will use this to get an employee or department by `ID`.

```java
// DBHelper.java

public static <T> T find(Class classType, int id){
  session = HibernateUtil.getSessionFactory().openSession();
  T result = null;
  try {
    transaction = session.beginTransaction();
    Criteria cr = session.createCriteria(classType);
    cr.add(Restrictions.eq("id", id));
    result = (T)cr.uniqueResult();
    transaction.commit();
  } catch (HibernateException e) {
    transaction.rollback();
    e.printStackTrace();
  } finally {
    session.close();
  }
  return result;
}
```

So here we are adding a restriction to the Criteria using the `add()` method and `Restrictions.eq()` (which is the same as saying `WHERE id = id` in SQL)

Let's check that we can find Bruce Wayne.

```java
// Runner.java

Employee bruce = DBHelper.find(Employee.class, employee1.getId());

System.out.println(); // DEBUGGER line
```

Now if we debug we should see that we get `Bruce Wayne` back. Sweet!

There are a number of restrictions we can apply including:

- To get records having salary more than 50000
```java
cr.add(Restrictions.gt("salary", 50000));
```

- To get records having salary less than 50000
```java
cr.add(Restrictions.lt("salary", 50000));
```

- To get records having fistName starting with bru
```java
cr.add(Restrictions.like("firstName", "bru%"));
```

- Case sensitive form of the above restriction.
```java
cr.add(Restrictions.ilike("firstName", "Bru%"));
```

- To get records having salary in between 60000 and 100000
```java
cr.add(Restrictions.between("salary", 60000, 100000));
```

and many more.


## Deleting by ID

We can use Criteria Queries to delete also. In this case we will use the Criteria Query to return an instance of an Employee then use hibernate's built in `delete()` method.

```java
// DBHelper.java

public static <T> void delete(Class classType, int id){
  session = HibernateUtil.getSessionFactory().openSession();
  try {
    transaction = session.beginTransaction();
    Criteria cr = session.createCriteria(classType);
    cr.add(Restrictions.eq("id", id));
    T result = (T)cr.uniqueResult();
    session.delete(result);
  } catch (HibernateException e) {
    transaction.rollback();
    e.printStackTrace();
  } finally {
    session.close();
  }

}
```

Try this out in the `Runner`.

```java
// Runner.java
Employee employee5 = new Employee("Barry", "Allen", 45000, dept1);
DBHelper.save(employee5);

DBHelper.delete(Employee.class, employee5.getId()); // NEW

List<Employee> employees = DBHelper.getAll(Employee.class);

```
Now when we run debugger and check our employee list `Barry Allan` isn't there. Sad! :(

> Comment out the delete line for the next part

## Sorting the Results

The Criteria API provides the `org.hibernate.criterion.Order` class to sort your results in either ascending or descending order, according to one of your object's properties.

Lets find the employees ordered by salary.

```java
// DBHelper.java

public static List<Employee> orderBySalary(){
  session = HibernateUtil.getSessionFactory().openSession();
  List<Employee> employees = null;
  try {
    transaction = session.beginTransaction();
    Criteria cr = session.createCriteria(Employee.class);
    cr.addOrder(Order.desc("salary"));
    employees = cr.list();
  } catch (HibernateException e) {
    transaction.rollback();
    e.printStackTrace();
  } finally {
    session.close();
  }
  return employees;
}
```

And let's check this in `Runner`

```java
// Runner.java

List<Employee> bySalary = DBHelper.orderBySalary();

System.out.println(); // DEBUGGER line

```

Now when we check this in debugger the list should be in order. Check this by clicking on each employee from the list in turn and checking the salary.

## Projections & Aggregations

The Criteria API provides the `org.hibernate.criterion.Projections` class, which can be used to get `average`, `maximum`, or `minimum` of the property values. The `Projections` class is similar to the `Restrictions` class, in that it provides several methods for obtaining Projection instances.

Let's find the `average` salary.

> The average projection returns a double value from the database.

```java
// DBHelper
public static Double getAverageSalary(){
  session = HibernateUtil.getSessionFactory().openSession();
  Double average = null;
  try {
    transaction = session.beginTransaction();
    Criteria cr = session.createCriteria(Employee.class);
    cr.setProjection(Projections.avg("salary"));
    average = (Double)cr.uniqueResult();
  } catch (HibernateException e) {
    transaction.rollback();
    e.printStackTrace();
  } finally {
    session.close();
  }
  return average;
}
```

And now we will check this in the `Runner`.

```java
// Runner.java

double average = DBHelper.getAvaerageSalary();
System.out.println();

```

And we get the average salary back!

`Projections` have a number of handy methods:

- To get total row count.
```java
cr.setProjection(Projections.rowCount());
```

- To get average of a property.
```java
cr.setProjection(Projections.avg("salary"));
```

- To get distinct count of a property.
```java
cr.setProjection(Projections.countDistinct("firstName"));
```

- To get maximum of a property.
```java
cr.setProjection(Projections.max("salary"));
```

- To get minimum of a property.
```java
cr.setProjection(Projections.min("salary"));
```

- To get sum of a property.
```java
cr.setProjection(Projections.sum("salary"));
```

## Native SQL

You can use native SQL to express database queries if you want to access more database-specific features, allowing you to specify handwritten SQL, including stored procedures, for all create, update, delete, and load operations.

You can create a native SQL query from the session with the `createSQLQuery()` method on the Session interface

The most basic SQL query is to get a list of scalars (values) from one or more tables.

This will bring us back a list of `HashMaps` with the column names as keys.

```java
// DBEmployee

public static List<Map> getFirstNamesAndSalaries(){
  session = HibernateUtil.getSessionFactory().openSession();
  List<Map> employees = null;
  try {
    String sql = "SELECT first_name, salary FROM employees";
    SQLQuery query = session.createSQLQuery(sql);
    query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
    employees = query.list();
  } catch (HibernateException e){
    e.printStackTrace();
  } finally {
    session.close();
  }
  return employees;
}

```

And in Runner.java

```java
// Runner.java

List<Map> firstNameSalary = DBHelper.getFirstNamesAndSalaries();
System.out.println(); // DEBUGGER LINE
```

Although we can do this it is still preferable to use `Criteria queries` whenever possible.

## Summary

We've seen:
 - What `Criteria Queries` are.
 - How to execute a simple query.
 - How to execute a more detailed query using `Restrictions`.
 - How to sort results.
 - How to use `Projections`
 - How to execute Native SQL queries.

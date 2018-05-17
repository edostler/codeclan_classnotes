# Refactoring DB files with generics

## Learning Objectives

- Understand how to refactor crud methods
- Know how to use generics when returning database queries.
- Know how to perform an update, find and delete one entry.

## Refactoring the DB files.

Ok so this is now getting a bit tedious. When we are creating a new class we have to write a `save()`, `deleteAll()`, etc twice for each class.

Wouldn't it be better if we could just have one file with methods that could be used for any class?

Well with hibernate we can!

You will notice that save takes in an object of either `Employee` or `Department`. We can just declare that this method takes in a parameter of type `Object`. All classes we write by default inherit from an `Object` class. So using `Polymorphism` we can save whichever class is passed in.

## Save

Create a new file called `DBHelper`.

Let's write the save function and test it out.

This will look the same as the other save methods we have written but this time will take in an object and save that.

```java
// DBHelper.java

public class DBHelper {
  private static Transaction transaction;
  private static Session session;

  public static void save(Object object){
    session = HibernateUtil.getSessionFactory().openSession();
    try {
      transaction = session.beginTransaction();
      session.save(object);
      transaction.commit();
    } catch (HibernateException e) {
      transaction.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
  }
}
```

Now regardless of whether we pass in an `Employee` or a `Department`, or any other hibernate mapped class for that matter, it will save it to the appropriate table.

Try it out by changing the Runner file.

```java
// Runner.jave

public class Runner {

  public static void main(String[] args) {
    DBEmployee.deleteAll();
    DBDepartment.deleteAll();

    Department dept1 = new Department("HR");
    DBHelper.save(dept1); // MODIFIED

    Department dept2 = new Department("Sales");
    DBHelper.save(dept2); // MODIFIED

    Employee employee1 = new Employee("Jack", "Jarvis", 25000, dept1);
    DBHelper.save(employee1); // MODIFIED

    Employee employee2 = new Employee("Isa", "Drennan", 30000, dept2);
    DBHelper.save(employee2); // MODIFIED


    // AS BEFORE
  }
}
```

Now when we run this and check the database in psql we should see our entries saved as usual.

## deleteAll()

Now let's tackle the deleteAll() method

If you look at the deleteAll() in DBEmployee and DBDepartment the only thing changing is the class name used in the hql query.

We could just pass in the class name and use string concatenation to dynamically add to the query.

```java
//DBHelper.java

public static void deleteAll(String className){
  session = HibernateUtil.getSessionFactory().openSession();
  try {
    transaction = session.beginTransaction();
    String hql = "delete from " + className;
    Query query = session.createQuery(hql);
    query.executeUpdate();
  } catch (HibernateException e){
    transaction.rollback();
    e.printStackTrace();
  } finally {
    session.close();
  }
}
```

And modify the runner file.

```java
// Runner.java

public static void main(String[] args) {
  DBHelper.deleteAll("Employee"); // MODIFIED
  DBHelper.deleteAll("Department"); // MODIFIED

  // AS BEFORE

}

```

## List all

Ok so now this is a little but trickier as we are returning specific lists of the class type.

Enter generics...

Generics are used in Java when you want to work on multiple types either using one method or class.

We have actually already seen generics when we use List and HashMap. Have you realised that we can create a List or a HashMap of any type? So `List<Employee>` and `List<Department>` work. This is because the class that List implements uses generics so it can create a list of any type.

You can write a single generic method declaration that can be called with arguments of different types. Based on the types of the arguments passed to the generic method, the compiler handles each method call appropriately.

The standard declaration for generic methods is
`public [static] <T> return_type methodName([arguments]){}`

We declare that our method is a generic method by using the notation `<T>` which stands for generic Type.

Then specify a return type which in our case will be a `List<T>`.

We want to return a generic List of either type `List<Employee>` or `List<Department>` depending on which table we are querying.

Let's give it a go!

```java
  // DBHelper.java

  public static <T> List<T> getAll(String className){
    session = HibernateUtil.getSessionFactory().openSession();
    List<T> results = null;
    try {
      transaction = session.beginTransaction();
      String hql = "from " + className;
      Query query = session.createQuery(hql);
      results = query.list();
    } catch (HibernateException e){
      transaction.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
    return results;
  }
```
Now let's get a list of employees and departments back in the runner and check in debugger.

```java
// Runner.java

public static void main(String[] args) {
  // AS BEFORE

  List<Employee> employees = DBHelper.getAll("Employee"); // NEW


  List<Department> departments = DBHelper.getAll("Department"); // NEW

} // DEBUGGER
```

Cool, when we check in the debugger we should have 2 lists. One with both employees and one with just the department.

### Update

Lets say we wanted to update an employee.

Well we have setters in the class so we can change details of the object.

To update we will write another method in the `DBHelper` to update the details. This will be done in the same way we saved.

For just now we will update by passing in the `Object` we want to update. This will come in useful when we build the web application next week.

```java
  //DBHelper.java

  public static void update(Object object){
    session = HibernateUtil.getSessionFactory().openSession();
    try {
      transaction = session.beginTransaction();
      session.update(object);
      transaction.commit();
    } catch (HibernateException e) {
      transaction.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
  }
```

Lets try this out now in the runner. Put this line of code just below where we created the employees.

```java
//Runner.java

//AS BEFORE
Employee employee2 = new Employee("Isa","Drennan", 30000, dept2);
DBEmployee.saveEmployee(employee2);

employee1.setDepartment(dept2); // ADDED
DBHelper.update(employee1); //ADDED

//AS BEFORE
```

Now when we run the runner and check the employee details in debugger, Jack Jarvis is in the sales department.

## Task

Update the title for the HR department.

## Delete

So let's delete an employee. Again Hibernate has a `delete()` method we can call on and, like `save` ad `update`, it takes in an object.

```java
  //DBHelper.java

  public static void delete(Object object){
       session = HibernateUtil.getSessionFactory().openSession();
       try {
           transaction = session.beginTransaction();
           session.delete(object);
           transaction.commit();
       } catch (HibernateException e) {
           transaction.rollback();
           e.printStackTrace();
       } finally {
           session.close();
       }
   }
```

And let's try deleting `employee2` in the runner

```java
// Runner.java

public static void main(String[] args) {
  //AS BEFORE

  DBHelper.delete(employee2);

  List<Employee> employees = DBHelper.getAll("Employee");


  List<Department> departments = DBHelper.getAll("Department");
}

```

Now when we debug this the employees list should only have one employee in it.

### Task

- Delete HR department.


## Find by ID

Lastly lets find an object by its ID. Like the `getAll()` method we will use generics to help us out here. We will pass in the id of the object and the classname.

In this method we want to return either a department or employee so we will set the return type to `T` to signify that it is a generic type returned and then assign to the appropriate class in the runner.

```java
  //DBHelper.java
  public static <T> T find(int id, String className){
    session = HibernateUtil.getSessionFactory().openSession();
    T result = null;
    try {
      transaction = session.beginTransaction();
      String hql = "from " +  className + " where id = :id";
      Query query = session.createQuery(hql);
      query.setInteger("id", id);
      result = (T)query.uniqueResult();
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

```java
 //Runner.java

 Employee foundEmployee = DBHelper.find(employee1.getId(), "Employee");
```

## Task

Find a department by ID.

Solution:

```java
  // Runner.java

   Department foundDept = DBHelper.find(dept2.getId(), "Department");
```

Nice so we have now been able to run all our basic CRUD functions for any class in one `DBHelper` file.

This makes our code very DRY.

We can actually now delete the `DBEmployee` and `DBDepartment` files as we will no longer need them.

- Right click on DBEmployee and select `refactor` > `Safe Delete...`
- Click Ok.

- If the file is still being used somewhere IntelliJ will warn you of this before deleting.

## Recap
> Instructor note: Ask the class...

What does `<T>` stand for?

<details>
<summary>Answer:</summary>

- Generic Type
</details>

<br>

How do we declare a generic method?

<details>
<summary>Answer:</summary>

`public [static] <T> [return-type] methodName([arguments])`

</details>
<br>

## Summary

We've seen:
 - How save, update and delete work with objects of any type.
 - How to return a list or object of any type using generics.

# Abstract Classes

## Objectives

- Understand what an abstract class is
- Be able to create an abstract class
- Understand that abstract classes cannot be instantiated
- Understand what an abstract method is
- Understand how abstract classes can be used as a type

## Intro

Sometimes we might want to have a class where we just want to use it as a 'superclass', but not create an actual instance of it, as it's not really appropriate.

One example of this is the 'Person' class we created when we talked about inheritance. We are using the 'Person' class as a 'superclass' but do we really want to create an instance of 'Person'? Probably not.

This is where the idea of an abstract class comes in. Abstract classes are used in relation to inheritance, so let's have a wee recap of inheritance.

### Inheritance recap

> Give out the starter code and let the students read it for 2 minutes

Lets make another class, called `LaptopComputer`. This, like `DesktopComputer` will have a `runApplication` method which takes in a `String`, but will return something different.

We'll start with a test:

```java
//ComputerTest.java

public class ComputerTest {
    DesktopComputer desktop;
    LaptopComputer laptop;  //ADDED

    @Before
    public void before() {
        desktop = new DesktopComputer();
        laptop = new LaptopComputer();  //ADDED
    }

    @Test
    public void desktopCanRunApplication() {
        assertEquals("I am running CC Caraoke", desktop.runApplication("CC Caraoke"));
    }


    @Test
    public void laptopCanRunApplication() {  //ADDED TEST
        assertEquals("Program CC Caraoke is running", laptop.runApplication("CC Caraoke"));
    }
}
```

So now we can add our 'LaptopComputer' class:

```Intellij
Right click on the 'java' folder in 'main'  and select New->Java Class

Enter the name 'LaptopComputer'

Click 'OK'
```

```java
//LaptopComputer.java

public class LaptopComputer  {

  public String runApplication(String appName){
    return "Program " + appName + " is running";
  }
}
```

What if we wanted some default text to be displayed as well...?

We would have to implement the default text method in each class.

We don't want to repeat code so lets change this to include a `Computer` class and have `Desktop` and `Laptop` computers inherit from this.

Say we want this default text to be the status of the running app, and this is pre-pended to what the methods in our Laptop and Desktop currently return.

Let's modify our tests:

```java
//ComputerTest.java

@Test
    public void desktopCanRunApplication() {
        assertEquals("CC Caraoke Status: I am running CC Caraoke", desktop.runApplication("CC Caraoke")); //MODIFIED
    }


    @Test
    public void laptopCanRunApplication() {
        assertEquals("CC Caraoke Status: Program CC Caraoke is running", laptop.runApplication("CC Caraoke"));  //MODIFIED
    }
```

First of all let's add our `Computer` class:

```Intellij
Right click on the 'java' folder in 'main'  and select New->Java Class

Enter the name 'Computer'

Click 'OK'
```

```java
//Computer.java
public class Computer  {

  public String runApplication(String appName){
    return appName + " Status: ";
  }
}
```

Now we need to modify our `DesktopComputer` and `LaptopComputer` classes to inherit from the `Computer` class and to have the `runApplication` method in each class prepend the default text to the string returned

> Ask the class if they remember how to do this, perhaps even getting them to try it for themselves

```java
//DesktopComputer.java

public class DesktopComputer extends Computer {  //MODIFIED
    public String runApplication(String appName) {
        return super.runApplication(appName) + "I am running " + appName;  //MODIFIED
    }
}
```


> TASK: do the same for the `LaptopComputer` class

> SOLUTION:
> ```java
> //LaptopComputer.java
>
> public class LaptopComputer extends Computer {
>
>    public String runApplication(String appName){
>        return super.runApplication(appName) + "Program " + appName + " is running";
>    }
> }
> ```

Ok, all good. Both Laptop and Desktop computers can inherit and call the `runApplication` method from the super class. But wait.... we can create a new instance of `Computer` class. What?? We have no clue what type of computer that this is. Is it a laptop computer? Is it a desktop computer? Is it Deep Thought?? We simply don't know.

So we want to have the ability to implement common code with sub classes and make sure that we can't instantiate the `Computer` class.

### Making a class abstract

We can make a class abstract by using the `abstract` keyword.

```java
//Computer.java

public abstract class Computer { //MODIFED

    public String runApplication(String appName){
        return appName + " Status: ";
    }
}

```

Now if we try and create in instance of the `Computer` class then we will get a compiler error.

> Try an create an instance of `Computer` in the test to show what happens
> Error:(14, 29) java: Computer is abstract; cannot be instantiated

## Abstract methods

As well as a class being abstract, methods can also be abstract. Abstract methods have no method body. The method must be defined in the subclass.

Let's add an abstract method `closeApplication()` to our `Computer` class:

```java
//Computer.java

public abstract class Computer  {

    public String runApplication(String appName){
        return appName + " Status: ";
    }

    public abstract String closeApplication(String appName); //ADDED
}

```

Note that this method does not contain any body. What were are basically saying is that any class which inherits from `Computer` must implement a `closeApplication` method which takes a single `String` parameter and returns a string. It doesn't matter what the method does inside the method but it must be called `closeApplication, take a single `String` and return a string.

If we were to try an compile our code now we will get an error saying that the subclasses of `Computer` do not implement the `closeApplication` method. So lets's go and fix this now.

First of all, the tests:

```java
//ComputerTest.java

    @Test
    public void desktopCanCloseApplication() { // NEW TEST
        assertEquals("I am closing down CC Caraoke", desktop.closeApplication("CC Caraoke"));
    }

    @Test
    public void laptopCanCloseApplication() { // NEW TEST
        assertEquals("Program CC Caraoke is closing down", laptop.closeApplication("CC Caraoke"));
    }
```

Now let's fix it in our `DesktopComputer` class:

```java
//DesktopComputer.java

public class DesktopComputer extends Computer {
    public String runApplication(String appName) {
        return super.runApplication(appName) + "I am running " + appName;
    }

    public String closeApplication(String appName) { // ADDED METHOD
        return "I am closing down " + appName;
    }
}
```

> TASK: Fix the `LaptopComputer` class so it compiles and the test passes
> SOLUTION:
> ```java
> //LaptopComputer.java
>
> public class LaptopComputer extends Computer {
>
>     public String runApplication(String appName){
>         return super.runApplication(appName) + "Program " + appName + " is running";
>     }
>
>     public String closeApplication(String appName){
>         return "Program " + appName + " is closing down";
>     }
> }
> ```

## Non-abstract methods

However, abstract classes can still contain implementations. Let's add one, called `Shutdown` which tells us when our computer is shutting down. It will take no arguments and return the string `Shutting down...`:

> If time allows, get the students to do this for themselves

The tests:
```java
//ComputerTest.java

  @Test
    public void desktopCanShutDown() {
        assertEquals("Shutting down...", desktop.shutDown());
    }

    @Test
    public void laptopCanShutDown() {
        assertEquals("Shutting down...", laptop.shutDown());
    }
```

```java
//Computer.java

public abstract class Computer  {

    public String runApplication(String appName){
        return appName + " Status: ";
    }

    public abstract String closeApplication(String appName);

    public String shutDown() { // NEW METHOD
        return "Shutting down...";
    }
}

```


So we can leave our 'Shutdown' method as it is.

For free, our `LaptopComputer` and `DesktopComputer` can use this `Shutdown` method. We can always override this behaviour too if we want to.

All the normal "benefits" of inheritance still apply. We can share properties across all of our objects, e.g. amount of RAM, CPU, stuff that every computer has.

### Abstract Class as Type

Although we cannot create an instance of an abstract class, we can declare a variable of the type of an abstract class. When assigning a value to that variable we have to assign it with an instance of one of its subclasses e.g:

```java
Computer computer = new LaptopComputer();
```

We could then swap out our 'Laptop' for a Desktop:

```java
computer = new DesktopComputer();
```

We can do this because a laptop 'is a' computer, and the same goes for a desktop.

Here are two tests which show this:
```java
//ComputerTest.java


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

```

We can also use the abstract type as a parameter in a method, and then pass in an instance of the subclass to that method:

```java

void printDetails(Computer computer) {
  //...
}

LaptopComputer laptop = new Laptop();
printDetails(laptop);

```

## Open Closed Principle

What we have seen here is how we adhere to the Open Closed principle from our SOLID list.

> Instructor: Write Open Closed under our SOLID heading.

Now our classes should be Open for extension and closed for modification.

This means that if we wanted to add in another type of computer, let's say `TabletComputer`, we won't have to change our Computer class. We can simply create a new class for the Tablet and extend the computer class.

## Disadvantages

There is a problem however. We are still constrained by the same problems as inheritance. We can only have one superclass, and all the methods and properties on that class bleed down to all of our children whether we want them or not.

## In Summary

- Abstract classes may or may not contain abstract methods, i.e., methods without body ( `public void get();` )

- But, if a class has at least one abstract method, then the class must be declared abstract.

- If a class is declared abstract, it cannot be instantiated.

- To use an abstract class, you have to inherit it from another class, provide implementations to the abstract methods in it.

- If you inherit an abstract class, you have to provide implementations to all the abstract methods in it.

- You can declare variables and method paramaters where the type is an abstract class, but they have to take the value of in instance of one its subclasses.

## Task (Optional) - 20 minutes

Create your own inheritance chain for modelling various types of vehicle.

- your base class should be abstract and called `Vehicle`. It should have the following:
    -  a number of wheels,
    -  a getter for the number of wheels
    -  a `startEngine` method which returns a `String`
    -  an abstract method called `Drive` which returns a `String`
- create a subclass of `Vehicle` called `Car`. `Car`s should have the following:
    - a model e.g. 'Ferrari'
    - a getter for the model
    - an implementation of the `Drive` method inherited from the abstract class which returns an appropriate `String`.
- create another sublclass of `Vehicle`: `Motorbike`. `Motorbike`s should also have an implementation of the `Drive` method inherited from the abstract class which returns an appropriate `String`.
- remember to use TDD.

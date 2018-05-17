# Interfaces

## Objectives

* Know what an interface is in Java
* Understand an interface as a contract
* Be able to create an interface
* Be able to implement an interface in a class
* Understand that a class can implement multiple interfaces

## Intro

Java has a concept called ___interfaces___. So what is an interface? An interface is a bit like a class, except that it usually only contains descriptions of methods, not the implementation - like an abstract method in an abstract class.

> Hand out start code. It has an simple `Printer` class, and the tests for the getters.

### How to declare an interface

> TODO sort out interface naming conventions

The naming convention we are going to use for our interfaces is an old one. We are going to put an 'I' before for the interface name. We are going to create an interface for a 'Peripheral', so our interface will be called `IPeripheral`.
Let's create a new interface called `IPeripheral` in the main package.

NOTE: another naming convention that is commonly used is, rather than putting an 'I' at the start of the interface name, is to put the word 'able' at the end e.g. in our example the interface name would be `Peripheralable` - a bit of a mouthful. This means that we can end up with weird looking words.

```
#IntelliJ
Create a new class called IPeripheral.java and set its type to Interface from drop down.

Right click > new > Java Class.
name class IPeripheral and in drop down change to Interface.

```

This will give us the following:

```java
//IPeripheral.java
public interface IPeripheral {
}
```

Let's add a method to this interface, called `connect` which returns a `String`:

```java
//IPeripheral.java
public interface IPeripheral {
  public String connect();
}

```


Note that we are not providing an implementation for the method. We are leaving this up to the classes which use the interface (just like with abstract methods in abstract classes)

NOTE: later versions of Java do allow us to provide default implementations of methods which can be overriden in the classes using the interface. We won't be doing this this week.

We use this interface with the "implements" keyword. So let's get our `Printer` class to use this interface:

```java
//Printer.java

public class Printer implements IPeripheral { // MODIFIED
  //...
}
```

Now our code won't compile. WHY?

> we now get the following error:
> Error:(1, 8) java: Printer is not abstract and does not override abstract method connect() in IPeripheral

## Interface as a Contract

An interface forms a contract. Any class which implements an interface must implement ___ALL___ the methods in that interface. If any are missing then we'll get a compiler error as the class is not fulfilling the contract. The contract doesn't say ___how___ these methods will be implemented, just that they ___are___ implemented.

Perhaps think of a Bank Account example. I may have accounts at several banks, but I expect that for each account, I should be able to pay-in money and withdraw money - that is the 'contract' I have with each bank. Each bank may do things differently, and to be honest, I don't care how, but they all do the things I expect them to do.

So what we are saying in our example is that the `Printer` class, since it implements the `IPeripheral` interface ***MUST*** have a method called `connect` which returns a `String`. In fact ___ANY___ class which implements the `IPeripheral` interface must have a method called `connect`.

```java
//IPeripheral.java

public interface IPeripheral {
  public String connect();
}

```

So we now need to get our `Printer` class to fulfill the contract i.e. we need to add an `connect` method, which simply returns the string `connected`. We'll write the test first:

```java
//PrinterTest.java
    @Test
    public void canConnect() { // NEW TEST
        assertEquals("connected", printer.connect());
    }

```

```java
//Printer.java

public class Printer implements IPeripheral {

  //...

  public String connect() {  //ADDED
    return "connected";
  }
}

```

> perhaps show example of another class using this interface, one which originally may seem unrelated e.g. a mouse
>
> ```java
> //Mouse.java
> public class Mouse implements IPeripheral {
>   public String connect() {
>     return "mouse connected"
>   }
>   
>   public void click() {
>   }
> }
> ```

## Implementing Multiple Interfaces

When we were using inheritance, and abstract classes, our classes could only inherit from ___ONE___ superclass. But with interfaces, it is different. A class can implement as many interfaces as it wants. For example, our `Printer` class already implements the `IPeripheral` interface. But what if we want to say that our `Printer` also needs to be able to print. We could add a `print` method to the `IPeripheral` interface but that wouldn't really make sense. This would also mean that every class which implements `IPeripheral` would now need to have a `print` method. One thing about using interfaces is that we shouldn't force methods onto classes that they don't need.

> perhaps go back to the `Mouse` example - that would mean an mouse would have to be able to print(WTF?)

When creating an interface we should think about the classes which are going to implement it. We might add a lot of methods, but we need to ask ourselves "Do we want to have to implement every method in our class?"

What we can do instead is to create another interface `IPrint` and have our `Printer` class implement both.

Let's create our `IPrint` interface. This will have one method, `print` which will take one `String` parameter and return a `String`.

> Maybe get the students to do this

```java
//IPrint.java

public interface IPrint {
  public String print(String data);
}

```

To add (an)other interface(s) to the `Printer` class we just have list of interfaces after the `implements` keyword, separated by commas:

```java
//Printer.java

public class Printer implements IPeripheral, IPrint { //AMENDED
  //..

  public String connect() {  
    return "connected";
  }
}

```

Now we need to fulfill the contract for the `IPrint` interface

> If time allows, get the students to do this for themselves, including the test

```java
//PrinterTest.java

    @Test //NEW TEST
    public void canPrint() {
        assertEquals("printing: Hello World", printer.print("Hello World"));
    }
```

```java
//Printer.java

public class Printer implements IPeripheral, IPrint { //AMENDED
  //..

  public String connect() {  
    return "connected";
  }

  public String print(String data) { //ADDED
    return "printing: " + data;
  }
}

```

This is actually good practice, to have smaller highly cohesive interfaces rather than larger less specific ones.


## Interface as a Type

A class implementing an interface also takes on the type of that interface. This means that once a Java class implements a Java interface you can use an instance of that class as an instance of that interface. Let's add a new test to show this

```java
//PrinterTest.java

    @Test
    public void canConnectAsPeripheral() { //NEW TEST
      IPeripheral device = new Printer("Hewlett-Packard", "Deskjet");
      assertEquals("connected", device.connect());
    }

```

> we'll see more of this tomorrow when we talk about polymorphism

Note that when we use an interface as a type, we can only access those methods which are listed as part of the interface. If a class which implements the interface has other methods, then we cannot access these when using the type of the interface. For example, if we do the create an instance of `IPeripheral` with a `Printer`, then we cannot access the `print` method. This is because it only knows about those methods which are part of the interface. For example if we tried to create the following:

```java
//PrinterTest.java

    @Test
    public void canPrintAsPeripheral() { //NEW TEST
      IPeripheral device = new Printer("Hewlett-Packard", "Deskjet");
      assertEquals("printing: Hello World", device.print("Hello World"));
    }

```

We get the following error:

```
Error:(44, 53) java: cannot find symbol
  symbol:   method print(java.lang.String)
  location: variable device of type IPeripheral
```

# Abstract classes and superclasses can implement interfaces

One thing to note is that abstract classes and superclasses can also implement interfaces. Going back to the lesson on abstract classes lesson, remember we had an abstract class `Computer`, which had two subclasses, `Desktop` and `Laptop`. If we changed the abstract `Computer` to implement the interface `IPeripheral` then that means that the subclasses now also have to obey the contract. To do this we can do one of two things:

1. Provide a full implementation of the `connect()` method in the abstract class.
2. Make the `connect()` method and abstract method in the `Computer` class and provide full implementations in each of the sub-classes.

# Recap

* An interface is a bit like a class except that it only contains descriptions of methods, not their full implementations.

* Interfaces are implemented by classes and form a contract in that, if a class implements an interface, then it must implement ___all___ the methods declared by that interface.

* Classes can implement multiple interfaces

* It is better to implement multiple, smaller interfaces, than larger less specific ones.

> If students struggle with the last point, then the following ___OPTIONAL___ section might be helpful

## Interface segregation

So say we wanted to create a contract for some Olympic athletes and we think of three events Olympic athletes compete in, e.g. running, cycling and swimming. We could look at the things each of these athletes do and add them to the requirements or contract that an Olympian needs to fulfill:
  * run
  * drink water(to rehydrate)
  * swim
  * come up for air
  * pedal
  * brake

So we could end up with an interface which looks something like this:

```java
//IOlympian.java

public interface IOlympian{
  void run(int distance);
  void drinkWater();
  void swim();
  void comeUpForAir();
  void pedal();
  void brake();
}
```

Now say we wanted to create a class for a Runner, who wants to compete at the Olympics. We could say that, in order to compete, they need to fulfil the requirements needed for an Olympic athlete, i.e. they need to implement our `IOlympian()`  interface.

Thus, in order to fulfill the contract, our `Runner` class has to implement ___all___ the methods in the `IOlympian()`  interface:

```java
//Runner.java

public class Runner implements IOlympian {
  private int totalDistance;
  private int hydration;

  public void run(int distance)
  {
    totalDistance += distance;
  }

  public void drinkWater()
  {
    hydration++;
  }

  public void swim()
  {
    System.out.println("But I can't swim");
  }

  public void comeUpForAir()
  {
    System.out.println("Help")
  }

  public void pedal()
  {
    System.out.println("I can't ride a bike");
  }

  public void brake()
  {
    // do nothing
  }
}
```

But at the end of the day, our `Runner` only wants/needs to be able to run a distance and drink some water to rehydrate themself. But, according to the contract, they need to do all these other things. Our interface can be seen to make us require lots of unnecessary methods. So what can we do?

When creating an interface we should think about the classes which are going to implement it. We might add a lot of methods, but we need to ask ourselves "Do we want to have to implement every method in our class?"

If not, then we need to restructure things. Earlier we said that a class can implement as many interfaces as it wants/needs. Our `Runner` class only needs to implement the `run()` and `drinkWater()` methods. What we can do is split our large `IOlympian` interface into 3 smaller ones:

```java
//IOlympicRunner.java

public interface IOlympicRunner {
  void run(int distance);
  void drinkWater();
}
```

```java
//IOlympicSwimmer.java

public interface IOlympicSwimmer {
  void swim();
  void comeUpForAir();
}
```

```java
//IOlympicCyclist.java

public interface IOlympicCyclist {
  void pedal();
  void brake();
}
```

If we change our `Runner` class to only implement the `IOlympicRunner` interface then it only needs to implement the relevant methods:

```java
//Runner.java

public class Runner implements IOlympicRunner {
  private int totalDistance;
  private int hydration;

  public void run(int distance)
  {
    totalDistance += distance;
  }

  public void drinkWater()
  {
    hydration++;
  }

  //Other methods deleted
}
```

But what if we wanted to model a Triathlete, who runs, swims and cycles? Earlier we said that a class can implement as many interfaces as it needs/wants, so if we wanted to say that a Triathlete has to be able to run, swim, pedal etc, then we could create a `Triathlete` class which implements all our interfaces:

```java
//Triathlete.java

public class Triathlete implements IOlympicRunner, IOlympicSwimmer, IOlympicCyclist {
  private int totalDistance;
  private int hydration;
  private int energyLevel;
  private int currentSpeed;

  public void run(int distance)
  {
    totalDistance += distance;
  }

  public void drinkWater()
  {
    hydration++;
  }

  public void swim()
  {
    totalDistance++;
  }

  public void comeUpForAir()
  {
    energyLevel++;
  }

  public void pedal()
  {
    totalDistance += 5;
  }

  public void brake()
  {
    currentSpeed--;
  }
}
```
So with Interface segregation we need to ensure that no class should be forced to depend on methods it does not use. We should split interfaces that are very large into smaller and more specific ones so that clients will only have to know about the methods that are of interest to them.

> Instructors: Write Interface Segregation on the board under our SOLID header. 

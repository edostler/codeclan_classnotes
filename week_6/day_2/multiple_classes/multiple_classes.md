# Multiple Classes & ArrayLists

### Learning Objectives
- Create multiple classes.
- Create a class with a collection of another class type.
- Be able to create ArrayLists, and add and remove items from them

## Intro

In this lesson we are going to have a look at creating multiple classes and allowing one class to store a collection of the second class in an ArrayList.

Let's rework our bear and bask in the glory of ArrayLists.

## Declaring an array list

First we need to tell our code that we want to use this class, in a similar way that we use require in Ruby.

``` java
//Bear.java
import java.util.*;

public class Bear {
  private String name;
  private ArrayList<Salmon> belly; //UPDATED

  public Bear(String name){
    this.belly = new ArrayList<Salmon>(); //UPDATED
    this.name = name;
  }
}
```

So now we have given our bear an ArrayList belly that can hold instances of our Salmon objects.

## Counting the items

We want to see how much food is in the bear's belly - initially it should be zero.

Let's write the test

```java
//BearTest.java

@Test
public void bellyStartsEmpty(){
  assertEquals(0, bear.foodCount());
}
```

We now have to use a size() method instead of length, which is super helpful.

``` java
//Bear.java
public int foodCount(){
  return belly.size();
}
```

Our eat and sleep methods are about to become beautiful.

## Eating a salmon

Let's try to get our bear to eat a salmon. First, let's add a test salmon to our unit tests.

```java
//BearTest.java
import static org.junit.Assert.assertEquals;
import org.junit.*;

public class BearTest {
  Bear bear;
  Salmon salmon; //NEW

  @Before
  public void before(){
    bear = new Bear("Baloo");
    salmon = new Salmon(); //NEW
  }
  //same as before
 }
 ```

 Cool, now we can use this salmon in our tests.

```java
 //BearTest.java

 @Test
 public void canEatSalmon(){
   bear.eat(salmon);
   assertEquals(1, bear.foodCount());
 }
 ```

 Let's add the eat() method.


``` java
//Bear.java

public void eat(Salmon salmon){
  belly.add(salmon);
}
```

Resetting the array

Lastly, let's add a way for our Bear to go to sleep and let his belly settle.

```java
//BearTest.java

@Test
public void shouldEmptyBellyAfterSleeping(){
  bear.eat(salmon);
  assertEquals(bear.foodCount(), 1);
  bear.sleep();
  assertEquals(bear.foodCount(), 0);
}
```

And lets add the sleep method in the bear class.

```java
//Bear.java

public void sleep(){
  belly.clear();
}
```

Cool our Bear is a happy little chappy now.

### Adding a third class

So lets extend this out a bit more now and add a third class of River to interact with the other two.

```
#Intellij

Create a new class called River and a test called TestRiver
```

Our River is going to contain the salmon and when a bear eats we will get it to take a salmon from the river.

```java
// River.java

import java.util.ArrayList;

public class River {

    ArrayList<Salmon> fish;

    public River() {
        this.fish = new ArrayList<>();
    }
  }  

```

Lets write some tests for our River. Lets start by adding some Salmon to the river.

```java
// RiverTest.java

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RiverTest {

    River river;
    Salmon salmon;

    @Before
    public void before(){
        river = new River();
        salmon = new Salmon();
    }

    @Test
    public void canAddSalmon(){
        river.addFish(salmon);
        assertEquals(1, river.getFishCount());
    }

}

```

Now lets get the test to pass.

```java
// River.java

  //As Before

  public void addFish(Salmon salmon){
       this.fish.add(salmon);
   }

   public int getFishCount() {
       return this.fish.size();
   }
```

Now lets test that we can remove and get a Salmon back out of the River.

```java
// RiverTest.java

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RiverTest {

  //As Before

  @Test
   public void canGetSalmon(){
       river.addFish(salmon);
       assertEquals(1, river.getFishCount());
       river.removeSalmon();
       assertEquals(0, river.getFishCount());
   }

}
```

```java
// River.java

  //As Before

  public Salmon removeSalmon() {
      return this.fish.remove(0);
  }
```

Cool so we can get a Salmon back from the River. So how do we get the Bear to get this salmon from the River?

> Discuss with the students and hope they get to the below solution

So when the bear eats now we will pass in a river for it to eat from.

Lets amend our bear test to set this up.

```java
// BearTest.java

public class BearTest {

    Bear bear;
    Salmon salmon;
    River river; //ADDED
    @Before
    public void before(){
        bear = new Bear("Baloo");
        salmon = new Salmon();
        river = new River();
        river.addFish(salmon);
    }

    @Test
    public void bellyStartsEmpty(){
        assertEquals(0, bear.foodCount());
    }

    @Test
    public void canEatSalmon(){
        bear.eat(river); //AMENDED
        assertEquals(1, bear.foodCount());
        assertEquals(0, river.getFishCount());
    }


    @Test
    public void shouldEmptyBellyAfterSleeping(){
        bear.eat(river); //AMENDED
        assertEquals(bear.foodCount(), 1);
        bear.sleep();
        assertEquals(bear.foodCount(), 0);
    }
}
```

> Task: Amend the Bears eat function so that it takes in a river and adds a salmon from the river to its belly.

```java
// Bear.java

//As Before

    public void eat(River river){ //AMENDED
        Salmon salmon = river.removeSalmon(); //ADDED
        belly.add(salmon);
    }
```


Great stuff! So now our bear gets the salmon from the River and the river deals with removing and returning the salmon.

The way that we have done this works well as the bear doesn't have access to the rivers collection of salmon directly to amend it.

This is known as `Single Responsibility`. The river is responsible for amending its collection of salmon and not the bear.

Single Responsibility applies to both classes and methods.

It simply means that classes and methods should only be responsible for one thing.

So, for example, we wouldn't write a single method that will add a fish to the bears belly and then return the belly count as these are 2 separate actions. 
And the `Bear` class shouldn't directly manipulate any of the properties in the `River`, like its fish count.

> Instructor: Write Single Responsibility under the SOLID heading on the board.

## Summary

We have seen how to

- Create multiple classes in Java.
- Create a class with a collection of another class type.
- Create ArrayLists, and add and remove items from them.
- What the Single Responsibility principle is.

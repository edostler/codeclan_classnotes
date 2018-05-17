# Basic Arrays

### Learning Objectives

- Know how to create a basic array
- Understand `null`
- Be able to use arrays in Java
- Know how to create an ArrayList in Java
- Be able to use ArrayLists in Java

## Intro

In this lesson we are going to have a look at two types of collections in Java. The most basic Java collection - the Array and a more enhanced collection - the ArrayList. We've encountered arrays before and now we are going to see how they work in Java. They are exactly the same in principle - a container to hold a set of items.

In Ruby and many other dynamically typed languages, we could put a mixture of things into an array:

``` ruby
# irb
myArray = [1,2,3,"banana", true];
```
In Java world, we can't do this. We're going to have a look at why this is the case in this lesson.

> Hand out start point

We need to declare the type of things that go into the array, followed by square brackets then the name of the array variable. This means that if we create an array of Strings then only Strings can be stored in the array.

The main concern with arrays is that we need to specify the number of items that can be stored in the array. This must be done when we initialise the array and can't be changed after.

```java
//ArrayExample.java

class ArrayExample {
   private String[] words;

   public ArrayExample(){
     this.words = new String[5];
   }
}
```

So here we have an array of Strings that will only hold 5 string objects inside.

When we add in an item to the array we need to specify the index position that the item will be inserted at.

Lets try this:

```java
//ArrayTest.java
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ArrayTest {

    ArrayExample arrayExample;

    @Before
    public void before(){
        arrayExample = new ArrayExample();
    }

    @Test
    public void addItem(){
        arrayExample.add("Hello");
        assertEquals(1, arrayExample.getWordCount());
    }
}
```

Lets get the word count back.

Arrays don't have many methods attached to them like we had in Ruby. It does however have a length property that we can use.

But it doesn't have an add method so we will need to write this ourselves.... to do this we need to specify the index to add it to.

```java
//ArrayExample

public class ArrayExample {

    private String[] words;

    public ArrayExample(){
        this.words = new String[5];
    }

    public int getWordCount(){
      return this.words.length;
    }

    public void add(String word){
      this.words[0] = word;
    }
}

```

Run the test and see what happens....


Expected 5?? WTF??

This is because we have specified the length as 5 so Java arrays will automatically store something in the 5 spaces.

Run the Runner.java file to see what is actually happening here. (Don't worry about the for loop we will cover this shortly)

We get `["Hello", null, null, null, null]`

In Java, a variable is a reference to an object. A null value indicates an unset reference (i.e. a reference to nothing).

So this could be a problem when it comes to getting the size of the array accurately. We would need conditions for if the entry was null.

Also when we add we are always setting the first element in the array so we would need some kind of counter to check if the entry was null and if so add it at that index. Messy!!

Also we don't have any methods so remove an item so we would have to write that ourselves as well.

Java arrays are still used in some cases to store data where you know the exact size of the collection, but very rarely.

Some Java methods do return Arrays so it is likely that you will still encounter them. Such methods as Enum.values(), String.split(), main method all return arrays.

The more standard collection of this kind is an ArrayList.

## ArrayLists

The ArrayList class is an out of the box Java class that implements the List interface so we can do a lot more with it. ArrayList supports dynamic arrays that can grow as needed.

Array lists are created with an initial size. When this size is exceeded, the collection is automatically enlarged. When objects are removed, the array may be shrunk.

Lets create another class to use these and a test.

```
#IntelliJ
Create a class called ArrayListExample
Create a test called TestArrayList
```

```java
//ArrayListExample

import java.util.ArrayList;

public class ArrayListExample {

    private ArrayList<String> words;

    public ArrayListExample(){
        this.words = new ArrayList<>();
    }
}
```
Yep, ArrayLists live in the java.util "namespace". Namespaces are just a way of bundling up code, like Ruby modules. Util is indeed a shocking name and it should really be "collections" or something. Don't be like Java. Don't call stuff util, or utils. For me. Please.

We can now update all of our code to use this shiny new collection.
When we create a new ArrayList we need to specify again what type is held in the ArrayList. This is done within angular brackets <>. Again only this type can be stored in here.

Also ArrayLists cannot hold primitive types so we couldn't do something like `ArrayList<int>`. However, as we looked at in Reference types, there is normally a class associated to primitive types. So `Integer` for ints, `Double` for double etc. So ArrayList<Integer> would be fine.

Lets test getting the number of words from the ArrayList.

We can do this using the size() method attached to ArrayList class.

```java
// TestArrayList

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestArrayList {

    ArrayListExample arrayListExample;

    @Before
    public void before(){
        arrayListExample = new ArrayListExample();
    }

    @Test
    public void hasNumberOfEntries(){
        assertEquals(0, arrayListExample.getWordCount());
    }
}
```

>Task Write the method so that the test passes.

```java
// ArrayListExample

import java.util.ArrayList;

public class ArrayListExample {

    private ArrayList<String> words;

    public ArrayListExample(){
        this.words = new ArrayList<>();
    }

    public int getWordCount() {
        return this.words.size();
    }
}
```

Sweet our test passes!

Lets add a word to the list.

```java
// TestArrayList

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestArrayList {

    //AS Before


    @Test
    public void canAddWordToArray(){
      arrayListExample.addWord("Hello");
      assertEquals(1, arrayListExample.getWordCount());
    }
}
```

>Task: Write the method to add to the ArrayList.

Investigate the methods on the ArrayList class to see if there is anything to help you here.

```java
// ArrayListExample

import java.util.ArrayList;

public class ArrayListExample {

  //As Before

  public void addWord(String word){
    this.words.add(word);
  }
}
```

Awesome we can now add to the ArrayList.

Every time we add to the list it will always put the new word at the next index number.

Lets see if we can get the first word back out.

```java
// TestArrayList

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestArrayList {

    //AS Before


    @Test
    public void canGetFirstWord(){
      arrayListExample.addWord("Hello");
      assertEquals("Hello", arrayListExample.getWordAtIndex(0));
    }
}
```

>Task: Write the method to get the word from the ArrayList.

```java
// ArrayListExample

import java.util.ArrayList;

public class ArrayListExample {

  //As Before

  public String getWordAtIndex(int index){
    return this.words.get(index);
  }
}
```

So we can get the word at a specific index.

There are numerous methods we can use on ArrayLists:

- clear(). Removes all of the elements from this list.
- contains(Object o). Returns true if this list contains the specified element.
- remove(int index). Removes and returns the element at the specified position in this list.

## Summary

While arrays still have their place in Java and you may see them in use the preferred collection to use is ArrayList<>.

We can perform more functions on an ArrayList without having to write the specific methods ourselves.

ArrayLists also grow in size dynamically.

ArrayLists, like Array, can only hold one specific type of Object. (String, Integer, etc). But this can also include our own objects (e.g. Bear).

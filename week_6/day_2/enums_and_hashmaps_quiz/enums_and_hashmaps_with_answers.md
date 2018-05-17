# Quiz - enums & hashmaps

## Enums

1. What is an `enum`?

A special type of data type which is a collection (set) of constants.

2. What method do we use to get all the entries in an enum? What type of object does this method return?

`.values()` An Array

3. Can you associate more than one value with an entry in an enum?

Yes. 

e.g. 
```java

public enum MetalType {
  GOLD("Gold", "Au"),
  SILVER("Silver", "Ag"),
  PLATINUM("Platinum", "Pt");

  private final String name;
  private final String symbol;

  MetalType(String name, String symbol) {
    this.name = name;
    this.symbol = symbol;
  }

  String getName() {
    return this.name;
  }

  String getSymbol() {
    return this.symbol;
  }

}
```

4. We saw that `enum`s can have constructors. Can you call the constructor in the same way as you would for a class?

No, the constructor is actually private and is used to initialise any variables in the `enum`.


## Hashmaps

1. What is a `HashMap` in Java?

A collection of Key-Value pairs, similar to a hash in Ruby.

2. Can you store value types (e.g. `int`, `char`) in a Hashmap?

No, you can only store reference types. For example, for an `int` you would use the `Integer` class.

3. How do you add a new entry into a HashMap?

`.put()`

4. How do you retrieve a value from a Hashmap?

`.get(key)`

5. Name 3 other methods you can call on a Hashmap and explain what they do

e.g. 
```java
  hashMapName.size() // returns the size of the HashMap as an integer
  hashMapName.clear() // clears all entries from the HashMap
  hashMapName.containsValue(value) // returns true if the HashMap contains the value
```
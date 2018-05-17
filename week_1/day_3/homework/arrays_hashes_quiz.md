# Homework

## A. Given the following data structure:

```ruby
stops = [ "Croy", "Cumbernauld", "Falkirk High", "Linlithgow", "Livingston", "Haymarket" ]
```

1. Add `"Edinburgh Waverley"` to the end of the array
2. Add `"Glasgow Queen St"` to the start of the array
3. Add `"Polmont"` at the appropriate point (between `"Falkirk High"` and `"Linlithgow"`)
4. Work out the index position of `"Linlithgow"`
5. Remove `"Livingston"` from the array using its name
6. Delete `"Cumbernauld"` from the array by index
7. How many stops there are in the array?
8. How many ways can we return `"Falkirk High"` from the array?
9. Reverse the positions of the stops in the array
10. Print out all the stops using a for loop

## B. Given the following data structure:

```ruby
  users = {
    "Jonathan" => {
      :twitter => "jonnyt",
      :favourite_numbers => [12, 42, 75, 12, 5],
      :home_town => "Stirling",
      :pets => {
        "fluffy" => :cat,
        "fido" => :dog,
        "spike" => :dog
      }
    },
    "Erik" => {
      :twitter => "eriksf",
      :favourite_numbers => [8, 12, 24],
      :home_town => "Linlithgow",
      :pets => {
        "nemo" => :fish,
        "kevin" => :fish,
        "spike" => :dog,
        "rupert" => :parrot
      }
    },
    "Avril" => {
      :twitter => "bridgpally",
      :favourite_numbers => [12, 14, 85, 88],
      :home_town => "Dunbar",
      :pets => {
        "colin" => :snake
      }
    },
  }
```

1. Get Jonathan's Twitter handle (i.e. the string `"jonnyt"`)
2. Get Erik's hometown
3. Get the array of Erik's favourite numbers
4. Get the type of Avril's pet Colin
5. Get the smallest of Erik's favourite numbers
6. Add the number `7` to Erik's favourite numbers
7. Change Erik's hometown to Edinburgh
8. Add a pet dog to Erik called "Fluffy"
9. Add yourself to the users hash

## C. Given the following data structure:

```ruby
united_kingdom = [
  {
    name: "Scotland",
    population: 5295000,
    capital: "Edinburgh"
  }, {
    name: "Wales",
    population: 3063000,
    capital: "Swansea"
  }, {
    name: "England",
    population: 53010000,
    capital: "London"
  }
]
```

1. Change the capital of Wales from `"Swansea"` to `"Cardiff"`.
2. Create a Hash for Northern Ireland and add it to the `united_kingdom` array (The capital is Belfast, and the population is 1,811,000).
3. Use a loop to print the names of all the countries in the UK.
4. Use a loop to find the total population of the UK.

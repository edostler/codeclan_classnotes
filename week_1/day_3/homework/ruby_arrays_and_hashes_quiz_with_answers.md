# Homework

## A. Given the following data structure:

```ruby
stops = [ "Croy", "Cumbernauld", "Falkirk High", "Linlithgow", "Livingston", "Haymarket" ]
```

1. Add `"Edinburgh Waverley"` to the end of the array
```ruby
stops.push("Edinburgh Waverley")
```
2. Add `"Glasgow Queen St"` to the start of the array
```ruby
stops.unshift("Glasgow Queen St")
```
3. Add `"Polmont"` at the appropriate point (between `"Falkirk High"` and `"Linlithgow"`)
```ruby
stops.insert(4, "Polmont")
```
4. Work out the index position of `"Linlithgow"`
```ruby
stops.index("Linlithgow")
```
5. Remove `"Livingston"` from the array using its name
```ruby
stops.delete("Livingston")
```
6. Delete `"Cumbernauld"` from the array by index
```ruby
stops.delete_at(2)
```
7. How many stops there are in the array?
```ruby
stops.size()
stops.count()
stops.length()
# => 7
```
8. How many ways can we return `"Falkirk High"` from the array?
```ruby
stops[2]
stops.at(2)
stops[-5]
stops.at(-5)
# loads more!
```
9. Reverse the positions of the stops in the array
```ruby
stops.reverse()
```
10. Print out all the stops using a for loop
```ruby
for stop in stops
  p stop
end
```

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

1. Return Jonathan's Twitter handle (i.e. the string `"jonnyt"`)
```ruby
 users["Jonathan"][:twitter]
```
2. Return Erik's hometown
```ruby
users["Erik"][:home_town]
```
3. Return the array of Erik's favourite numbers
```ruby
users["Erik"][:favourite_numbers]
```
4. Return the type of Avril's pet Colin
```ruby
users["Avril"][:pets]["colin"]
```
5. Return the smallest of Erik's favourite numbers
```ruby
users["Erik"][:favourite_numbers].min
```
6. Return an array of Avril's favourite numbers that are even
```ruby
result = []
for number in users["Avril"][:favourite_numbers]
	result << number if number.even?
end
p result
```
Or
```ruby
def evil_even_numbers(array_of_numbers)
  result = []
  for number in array_of_numbers
    result.push(number) if(number.even?)
  end

  return result
end

array = users["Avril"][:favourite_numbers]
evens = evil_even_numbers(array)

p evens
```

7. Return an array of Jonathan's favourite numbers, sorted in ascending order and excluding duplicates
```ruby
users["Jonathan"][:favourite_numbers].sort.uniq
```

8. Add the number `7` to Erik's favourite numbers
```ruby
users["Erik"][:favourite_numbers] << 7
```

9. Change Erik's hometown to Edinburgh
```ruby
users["Erik"][:home_town] = "Edinburgh"
```

10. Add a pet dog to Erik called "Fluffy"
```ruby
users["Erik"][:pets]["fluffy"] = :dog
```

11. Add yourself to the users hash
```ruby
me =  {
  :twitter => "tgoncalves",
  :favorite_numbers => [14, 28],
  :home_town => "Morningside",
  :pets => {
    "tommy" => :cat
  }
}

users["Tony"] = me
```

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
```ruby
united_kingdom[1]["capital"] = "Cardiff"
```
2. Create a Hash for Northern Ireland and add it to the `united_kingdom` array (The capital is Belfast, and the population is 1,811,000).
```ruby
northern_ireland = {
  name: "Northern Ireland",
  population: 1811000,
  capital: "Belfast"
}
united_kingdom.push(northern_ireland)
```
3. Use a loop to print the names of all the countries in the UK.
```ruby
for country in united_kingdom
  p country[:name]
end
```
4. Use a loop to find the total population of the UK.
```ruby
total = 0
for country in united_kingdom
  total += country[:population]
end
# => total = 63179000
```

use my_coffee_db;

db.dropDatabase();

db.coffees.insert({
	roaster: "Crankhouse",
	country: "Indonesia",
	rating: 4,
	name: "Wahana"
});

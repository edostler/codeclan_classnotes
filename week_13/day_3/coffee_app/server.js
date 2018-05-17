const express = require("express");
const app = express();

const path = require("path");
const bodyParser = require("body-parser");

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));
app.use(express.static("client/build"));

const MongoClient = require("mongodb").MongoClient;

MongoClient.connect("mongodb://localhost:27017", function(err, client){
	if(err){
		console.log(err);
		return;
	}

	const db = client.db("my_coffee_db");

	app.get("/coffees", function(req, res){
		const coffeeCollection = db.collection("coffees");
		coffeeCollection.find().toArray(function(err, coffees){
			if(err){
				console.log(err);
				res.status(500);
				res.send();
			}

			res.json(coffees);
		});
	})

	app.listen(3000, function(){
		console.log("Listening for requests on port 3000!");
	});
});

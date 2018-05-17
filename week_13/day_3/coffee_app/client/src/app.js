const makeRequest = function(url, callback){
	const request = new XMLHttpRequest();
	request.open("GET", url);
	request.addEventListener("load", callback);
	request.send();
};

const coffeeRequestComplete = function(){
	const responseText = this.responseText;
	const coffeeArray = JSON.parse(responseText);
	populateList(coffeeArray);
};

const populateList = function(coffeeArray){
	for(let coffee of coffeeArray){
		const newParagraph = document.createElement("p");

		newParagraph.innerText = `You drank: ${coffee.name} by ${coffee.roaster} from ${coffee.country}. You rated it a ${coffee.rating}!`;

		document.body.appendChild(newParagraph);
	}
};

const app = function(){
	makeRequest("/coffees", coffeeRequestComplete);
};

document.addEventListener("DOMContentLoaded", app);

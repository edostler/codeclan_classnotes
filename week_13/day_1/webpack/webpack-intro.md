# Webpack intro

## Learning objectives
- Understand what webpack does
- Understand how to structure a webpacked app
- Understand how to unit test a webpacked app

## Duration
2.5 hours (maybe)

## Intro

> give out simple app start code

Here we have a simple JavaScript app, which runs in the browser. Our `index.html` loads Lodash from a remote server, then loads `app.js` from our `public` folder. `app.js` grabs an element with the ID `#header` and changes its text content to the output of a Lodash `join` function.

This is a very simple app, compared to what we built last week. But compared to what we wrote in JS week 1, and what we've written all through the course, this is quite messy:

- Using script tags makes it hard to know what depends on what - Our app depends on Lodash being loaded before our `app.js`, i.e our app has a dependency on Lodash, but nothing in our `app.js` declares this.
- It's hard to structure and model our app versus what we did with node in week 1. To do so, we'd have to write lots of little JS files and import them with `<script>` tags in the right order.
- We've lost the ability to use NPM packages. Lodash is on a remote server and if that server goes down, our app is goosed.
- We've got no tests!

We are going to use this simple app to explore Webpack, and the benefits it gives us as developers.

## Module bundling

What we would really like to do is have our browser just pull in one JavaScript file. Then our browser doesn't have to worry about dependencies, or one thing loading before another.

But as developers, we know that writing all our code in one mammoth file is bad. It breaks the S, O and arguably I parts of SOLID. We want to write lots of little files, and have them bundled into one big file for the browser.

There are a few solutions to this problem, like Browserify and RequireJS, but the best and most widely used solution at the moment is Webpack.

## Creating the bundle - Warm-up

Installing Webpack is easy:

```bash
npm install --save-dev webpack@3.11.0
```

...but before we can use it, let's make some changes to our code.

First of all, let's install Lodash from npm, rather than as a script tag:

```bash
npm install --save lodash
```

And so we can delete the `<script>` tags for the Lodash remote server.

```html
<!-- index.html -->
<head>
  <title>Webpack Intro</title>
  <!-- DELETE -->
  <script src='https://cdn.jsdelivr.net/lodash/4.17.4/lodash.min.js'></script>
  <!-- END DELETE -->
  <script src='app.js'></script>
</head>
```

And we'll have to fix our `app.js`, as the global `_` variable is no longer present:

```javascript
// app.js
// Top of file:
var _ = require("lodash");
```

Our app is now ready to be bundled with Webpack, but Webpack likes to have an empty folder to output to. Let's give it a folder, which is traditionally named `build`:

```bash
mkdir build
```

And since we're going to be loading Webpack's output from _that_ folder, we have to change the static location in `server.js`:

```javascript
app.use(express.static('build'));
```
> Restart server if it was running

Finally, we'll update our HTML to pull in Webpack's output, which is traditionally called `bundle.js`:

```html
<head>
  <title>Webpack Intro</title>
  <script src='bundle.js'></script>
</head>
```

## Creating the bundle - For real

Ok, we are ready to bundle! In order to do that, we run a terminal command that has three components:

- A call to the main Webpack script, which is buried in `node_modules` somewhere.
- The entry point for our app, i.e. the top of the dependency tree
- The path to Webpack's output

And so we write:

```bash
./node_modules/.bin/webpack public/app.js build/bundle.js
```

And our app works!

## Quality of life

Ok, so that was a lot of hoops to jump through for not a lot of win. By the end of the lesson, we'll see the real benefits of doing things this way for a larger app with more complex dependencies. For now, let's look at ways we can make things a little easier.

First of all, two of the three things we just had to type into Terminal are probably never going to change. We are probably always going to want our output and `./build/bundle.js`, and we are probably always going to use `app.js` as our entry point. We shouldn't have to type that into Terminal every time, Webpack should just know!

Webpack can be configured with a special file, which it will look for in the top level of our app:

```bash
touch webpack.config.js
```

This file is basically just a JavaScript object with certain properties, which gets `module.exports`ed:

```javascript
// webpack.config.js
var config = {

};
module.exports = config;
```

Inside that `config` object, we tell Webpack our app's entry point:

```javascript
// webpack.config.js
var config = {
  entry: "./public/app.js", // NEW
};
module.exports = config;
```

And our output location, which is itself an object with a filename and a path:

```javascript
// webpack.config.js
var path = require("path"); // NEW

var config = {
  entry: "./public/app.js",
  output: {                 // NEW
    filename: "bundle.js",
    path: path.resolve(__dirname, "build")
  }
};

module.exports = config;
```

With a config file in place, all we need to do from Terminal is call on webpack and it will know what to do!

```bash
./node_modules/.bin/webpack
```

That's still kinda ugly, though. But we've been through this before with Mocha. Rather than accessing a binary buried inside `node_modules`, we can write a quick npm script, and npm will know what to do!

```js
// package.json
"scripts": {
  "start": "node server.js",
  "build": "webpack" // NEW
},
```

And now all we need to do from Terminal is:

```bash
npm run build
```

...And Webpack does its thing.

> Definitely break here!

## Complex apps

> Where is this going?
> - Give out bog standard app
> - Talk through code
> - Think about dependencies with class
> - Move files around, into `client`, `src` and `models`/`views` folders
> - Adjust server
> - Install webpack
> - Set up config
> - Run app, make changes, talk about watch mode
> - Tests

> Give out complex app start code

Let's take a look at this more complex app. We've got an `index.html` with a whole bunch of `<script>` tags, and a whole bunch of `.js` files inside public. We can guess that these files all depend on each other somehow, but right now we have no idea what those dependencies are.

```bash
npm install
npm start
```

We can run the app and see that it is the start of a movie ratings app.

## Dependencies

Now let's start to pick this app apart. Since `index.js` is loaded last, it's pretty safe to assume that it's our entry point, or the top of our dependency tree, as it must rely on one or more of the other scripts being loaded before it.

Actually `index.js` doesn't do much, other than load a new `Ui` object. So it follows that `index.js` depends on `ui.js`:

```js
// index.js

// dependencies: ui.js
```

Similarly, we can look in `ui.js` and see that it makes a new `Films` object.

```js
// ui.js

// dependencies: films.js
```

And onwards, `films.js` makes a couple of new `Film` objects and a couple of new `Review` objects:

```js
// films.js

// dependencies: film.js, review.js
```

`film.js` and `review.js` have no dependencies.

## Structure

Now we know our modules' dependencies, which is awesome. But our app's structure is pretty yucky. Everything is all lumped together in one folder.

In our Ruby/Sinatra projects we learned about the MVC pattern, which we followed somewhat in our Android projects as well. We're going to use the exact same pattern here, where our business logic is kept separate from our user interface logic.

Inside our `public` folder we will make folders called `models` and `views`:

```bash
cd public
mkdir models views
```

And we'll move all the View logic into `views`, in this case just `ui.js`:

```bash
mv ui.js views
```

Everything else is Model logic, so we can move it into `models`:

```bash
mv film.js films.js review.js models
```

Now we've got this folder called `public`. As we saw in the simple example earlier, the browser never has any access to this folder, because the server serves up static files from a `build` folder. This folder is called `public`, but very soon it's not going to be public!

By convention, our source code is stored in a folder called `src`, so let's rename `public` to `src`:

```bash
mv public src
```

While we're in Terminal, we could also make the `build` folder we're going to need later:

```bash
mkdir build
```

To keep things neat, we'll move our `build` and `src` folders into one parent folder, which we'll call `client`:

```bash
mkdir client
mv build src client
```

Now we know our folder structure, we can go about setting up our dependencies properly. Maybe this would be easier if we start at the bottom of the tree. `film.js` and `review.js` don't have any dependencies. If this was a Ruby app, we could leave them be and move on. But this is JavaScript, and we have to explicitly make our modules available to other modules:

```js
// film.js
module.exports = Film;
```
```js
// review.js
module.exports = Review;
```

By doing this, we make them available to the next module up the dependency tree, `films.js`, where we can `require()` them in the usual way:

```js
// films.js
var Film = require('./film.js');
var Review = require('./reviews.js');
// ...
module.exports = Films;
```

Next up the tree is `ui.js`, and we have to be a wee bit more careful because it's in a different folder now:

```js
// ui.js
var Films = require('../models/films.js');
// ...
module.exports = Ui;
```

And finally, our `index.js` lives at the top of the tree, so doesn't have to make itself available. It just has to `require()` in `ui.js`:

```js
// index.js
var Ui = require('./views/ui.js');
```

## Webpack

Whew! We have made order out of chaos! We are ready to install Webpack!

```bash
cd back/to/top/level
npm install --save-dev webpack
```

We could go ahead and run Webpack from the command line, but we know how the config file and npm scripts work now, so let's just do that.

```bash
cd client
touch webpack.config.js
```

```js
// webpack.config.js
var path = require("path"); // write this line when you need to!

var config = {
  entry: "./src/index.js",
  output: {
    filename: "bundle.js",
    path: path.resolve(__dirname + "/build")
  }
};

module.exports = config;
```

And add the npm script:

```js
// package.json
"scripts": {
  "start": "node server.js",
  "build": "cd client && webpack"
},
```

And all we need to do is:

```bash
cd back/to/top
npm run build
```

And webpack does it's thing!

All that's left to do is tell the server to give us static files from `client/build`:

```js
// server.js
```

And load `bundle.js` into the HTML:

```HTML
<head>
  <title>Film Reviews</title>
  <script src='bundle.js'></script>
  <!-- remove all other script tags -->
</head>
```

And our server should run and show what we expect:

```bash
npm start
```

> Break here

## Ch-ch-ch-changes

That's cool and everything, but what if we make a change to our model?

```js
// films.js
var film3 = new Film({
  title: "The Dark Knight",
  actors: ["Christian Bale", "Heath Ledger"]
});

var review3 = new Review({
  comment: "I hate Batman, but it has some pretty cool explosions!",
  rating: 5,
  author: "Graham Bruce"
});

film3.addReview(review3);

return [film1, film2, film3]
```

If we save it and refresh the page... Nothing happens. If we restart the server... nothing happens. What we have to do is run Webpack again. Every time we make a change, we have to re-run webpack.

That's going to get old fast. Luckily the Webpack developers have got our back, and have given us "watch" mode. All we have to do is make a little change to the script calling Webpack:

```js
// package.json
"scripts": {
  // ...
  "build": "cd client && webpack -w"
},
```

And Webpack will "watch" our files for changes, and will re-run itself if it has to. Sweet!

## Accidents Will Happen

So we've finally got structure to our JS. Our browser is happy because it only loads one thing, and we're happy because we get to write lots of little modules.

But we're developers, and developers make mistakes. It's an important part of the job to make mistakes. And most dev environments are kind enough to tell us where our mistakes are when we make them. Let's make a mistake and see what Webpack tells us:

```js
// films.js
film2.addReview(review5);
```

```
# Chrome console
Uncaught ReferenceError: review5 is not defined
    at new Films (bundle.js:162)
    at UI (bundle.js:89)
    at app (bundle.js:75)
```

Uhh... the error log points to `bundle.js`, but we don't ever want to poke around in bundle.js! That's no good!

Again, Webpack has our back. We can instruct it to build a _source map_ along with our bundle. This maps the bundle back to its original source code, so that when an error is logged, it points to the error in the source code and not in the bundle.

Telling Webpack to build a source map is dead easy:

```js
// webpack.config.js
var config = {
  // ...
  devtool: 'source-map'
}
```

And after restarting Webpack, our error looks much nicer:

```
# Chrome console
Uncaught ReferenceError: review5 is not defined
    at new Films (films.js:30)
    at UI (ui.js:5)
    at app (index.js:6)
```

Sanity is restored!

## Benefits

So what was the point of all that? Well...

- Our code is modular, one file is responsible for one thing
- Our code has structure, all models and views are stored together
- Our code can be tested!

Yes, we can write tests again. And we can do it in exactly the same way we did in Week 1.

If you remember we like to keep our tests in a "specs" folder. With our new folder structure, we can pop this inside the models folder.

```zsh
# terminal
mkdir client/src/models/specs
touch client/src/models/specs/film_spec.js
```

> Give this out in Slack to be pasted into film_spec:

```js
//film_spec.js

var Film = require('../film');
var assert = require('assert');

describe('Film', function () {
  var film;

  beforeEach(function () {
    film = new Film({
      title: "Titanic",
      actors: ["Leonardo DiCaprio"]
    });
  });

  it('should have title titanic', function () {
    assert.equal(film.title, "Titanic");
  });

})
```

We just have one little thing to consider, and that's how we run this test.

We're going to set up an npm script to run mocha for us. We can do this to allow our tests to run from anywhere in the project.

First, let's install mocha:

```zsh
# terminal
npm install --save-dev mocha
```

While that's installing we can get our test script set up.

```json
// package.json
"scripts": {
  "test": "mocha client/src/models/specs", // UPDATED
  "start": "node server.js",
  "webpack": "cd client && webpack -w"
},
```

We can use this by typing:

```zsh
# terminal
npm test
```

## Conclusion

In this lesson, we saw how we can take a front-end app, and add structure to it via the power of Webpack.

We can manage our dependencies, test our code, and organise our code in a way that promotes the SOLID principles.

Webpack allows us to do other useful things too, and we'll see some more of its functionality later on.

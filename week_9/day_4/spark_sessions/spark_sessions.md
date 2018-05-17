# Spark Sessions

## Objectives

* Know what a session is in a Spark application
* Be able to get the session from a request
* Be able to add an attribute to a session
* Be able to get the value for an attribute to a session
* Be able to remove an attribute for a session

## Intro

We've all seen websites where we have to login to use them (e.g. Facebook, Amazon, eBay etc.). Once we've logged into one of these sites we can navigate around and the site remembers that we are logged in. Think of shopping sites like Amazon, which allows us to move around the site and it keeps track of the number of items in a shopping basket. So how do these sites do this? One way is to use sessions. A session is data saved on the server that is persisted throughout a user's interaction with the web site or web app. The server keeps a basic data structure in place for each user's session, which enables it to store small pieces of data specific to that user. Each session is unique to each user accessing the app. It works by storing information for each user in a special place on the server, and setting some data on each user's browser so that it can tell which session belongs to which user.

Every time the browser makes a request to the server (e.g. by clicking on a link) the user's browser also passes the data the server gave it to identify that user. The server can then use that information to access the session data stored on the server for that particular user e.g. username.

> A good analogy describing how sessions work can be found [here](https://stackoverflow.com/questions/3804209/what-are-sessions-how-do-they-work)

To show this in action, we are going to modify our employee app so that when we first open the app, we are asked for a username (we'll not worry about passwords, or searching if the user exists). If we don't provide one, then we cannot go any further. Once logged in, wherever we go on the site, the username will be displayed somewhere on the page.

## Create login form

So the first thing we are going to do is create a login form. This will be the page the user is redirected to if they are not logged in. So let's create a file `login.vtl` in the `main/resources/templates` directory.

We want this form to have one input field, for the user name. Submitting the form will result in a `POST` request with the route `/login`

```html
<!-- templates/login.vtl -->

  <form action="/login" method="post">
   <label for="username">Your name</label>
   <input id="username" name="username" type="text">

   <button type="submit">Submit name</button>
 </form>
```

### Create login controller

So now that we have our form, we need to handle the `POST` request sent when the form is submitted. We are going to need a controller for this so let's create a java class called `LoginController` within the `java\controllers` directory.

Like our other controllers this class will have a private method `setupEndPoints` which is called from the default constructor for the class:

 ```java
  //LoginController.java

   public class LoginController {
       public LoginController() {
           this.setupEndpoints();
       }

       private void setupEndpoints() {

      }
   }
  ```

We can now add the route to handle the `POST` request inside the `setupEndpoints` method. This route will simply do a redirect to the default '/' root.

```java
 //LoginController.java

  public class LoginController {
      public LoginController() {
          this.setupEndpoints();
      }

      private void setupEndpoints() {

          post("/login", (req, res) -> { //NEW
              res.redirect("/");
              return null;
          }, new VelocityTemplateEngine());
      }
  }
 ```

### Saving data in the session

It is inside this route where we want to get the username entered by the form and save this in the session. We get the username just as we do for any of our other forms i.e.

```java
    String inputtedUsername = req.queryParams("username");
```

We now want to save this username in the session. The session is created on the server side. Every request can access this session and we can get the session by calling the `session()` method on the request e.g.

```java
  req.session()
```

The session contains attributes, which are stored as a series of key-value pairs. To set a session attribute we call the `attribute()` method on the session, passing in the name of the attribute and its value. For example, if we want to set the 'username' attribute with the value 'isa' we would have:

```java
  req.session().attribute("username", "isa");
```

So now that we have the user name from the form we can use it to set the "username" attribute in the session:

 ```java
 //LoginController.java

  public class LoginController {
      public LoginController() {
          this.setupEndpoints();
      }

      private void setupEndpoints() {
          post("/login", (req, res) -> {
              String inputtedUsername = req.queryParams("username"); // NEW
              req.session().attribute("username", inputtedUsername); // NEW
              res.redirect("/");
              return null;
          }, new VelocityTemplateEngine());
      }
  }
 ```

In order to be able to use the routes in the `LoginController` class we need to create an instance of the class. We do this in the `MainController` class (or wherever we have the `main` method), just as we did for the other controllers i.e.:

```java
 //MainController.java

 staticFileLocation("/public");

 LoginController loginController = new LoginController(); //NEW
 EmployeesController employeesController = new EmployeesController();

```

### Creating a `GET` route for the login form

The login form is going to be displayed as the result of a `GET` request (think of the routes we have in our RESTful routes). The route for this will be `/login`. When the route is handled all it will do is return the template for the login form i.e.:

```java
 //LoginController.java

 private void setupEndpoints() {
  // AS BEFORE

  get ("/login", (req, res) -> {  //NEW
           Map<String, Object> model = new HashMap<>();
           return new ModelAndView(model, "templates/login.vtl");
       }, new VelocityTemplateEngine());
}
```

## Checking For A Logged In User

We now need to check if a user is logged in i.e. if there is an attribute on the session with the name "username". We're going to create a method in our `LoginController` class called `getLoggedInUserName()`. This method will be a class method so it will be declared `static`. It will take two arguments, a `Request` and a `Response`. It will return a `String` as we want to return the actual value of the user name.

```java
//LoginController

    public static String getLoggedInUserName(Request req, Response res) {

    }
```

The first thing we want to do in this method is to check if the session has an attribute with the name "username". We can get the session by calling the `session()` method on the `Request` argument.

So how do we check if the session has an attribute for the user name? We actually call the same method as we did to set it i.e. the `attribute()` method only this time we pass only one parameter, the name of the attribute we are looking for, in this case "username" i.e.

```java

req.session.attribute("username");

```

If an attribute with that name exists, then this will return the value, otherwise it will return `null`

```java
//LoginController

    public static String getLoggedInUserName(Request req, Response res) {
      String username = req.session().attribute("username");
    }
```

So what we can do now is check to see if `username` is either `null` or empty (just in case we've been allowed to enter a blank string). If `username` is `null` or empty, then we can do a redirect to the "/login" route by calling the `redirect()` method on the `Response` argument passed in. Otherwise we simply return the value of the username.

```java
//LoginController

    public static String getLoggedInUserName(Request req, Response res) {
      String username = req.session().attribute("username");
      if (username == null || username.isEmpty()) {
          res.redirect("/login");
      }
      return username;
    }
```

# Getting each route to check for a user

So now we need to get our routes to check for a user. We'll start with the default ("/") route in our `MainController` class. In this route we need to add a call to the `getLoggedInUserName()` method we created in the `LoginController` class, passing in the routes `Request` and `Response` objects. 

```java
//MainController.java
      get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res); // NEW
            model.put("template","templates/main.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

```

So now in our route, if the username exists, it will be return by the call to `getLoggedInUserName()`. If not, then `getLoggedInUserName()` will trigger a redirect to the login page.

But it would also be useful show the actual username of the logged in user. We'd like to do this on every page so rather than adding code to each view we can add it to the `layout.vtl` e.g.

```html
<!-- layout.vtl -->
    <p>Logged in as: $user<p> <!-- NEW -->
    <div class="container">
      #parse( $template )
    </div>
```

To get this to display, we now need to add our user to the model in the route i.e:

```java
//MainController.java
      get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser); //NEW
            model.put("template","templates/main.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

```

Obviously, we now need to add this code to every route.

> maybe just add to a couple of routes to show this working. Leave it as a task for the end of class

> there is a method called `before()` which can be run before every request but it will not work in this case because the redirect itself is a request so we get into an infinite loop.

## Logging out

So now that we can log in, we want to be able to log out i.e. remove the username from the session. 

We'll start by adding a link in our `layout.vtl` which the user can click on to log out. We'll put it next to where we display the user name. Clicking on this link will result in a `GET` request on a "/logout" route:
 
 ```html
 <!-- layout.vtl -->
     <p>Logged in as: $user <a href="/logout">Logout</a> <p> <!-- MODIFIED -->
     <div class="container">
       #parse( $template )
     </div>
 ```

We now need to handle this route in our `LoginController`. It will redirect the user back to the default "/" page:

```java
//LoginController.java

        get ("/logout", (req, res) -> {
            res.redirect("/");
            return null;
        }, new VelocityTemplateEngine());

```

In this route we want to remove an attribute for a user from the session. We can do this by calling the `removeAttribute()` method on the session, passing it in the name of the attribute we want to remove.

```java
  req.session().removeAttribute(<ATTRIBUTE_NAME>); 
```

In our case this is the "username" attribute:

```java
//LoginController.java

        get ("/logout", (req, res) -> {
            req.session().removeAttribute("username"); // NEW
            res.redirect("/");
            return null;
        }, new VelocityTemplateEngine());

```

You should now be logged out.

### TASK 

Go through all the routes, adding the check to see if a user is logged in (tedious I know :-( )

## Summary

* a session is data saved on the server that is persisted throughout our interaction with the web site or web app

* every request can get access to the session by calling the `session()` method.

* we can add attributes to a session by calling the `attribute()` method on the session, passing in the name of the attribute and it's value

* we can get the value of an attribute by calling the `attribute()` method on the session, passing in the name of the attribute

* we can remove an attribute from the session by calling the `removeAttribute()` method on the session, passing in the name of the attribute we want to remove.

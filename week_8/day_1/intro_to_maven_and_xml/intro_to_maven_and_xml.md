# Introduction to Maven and XML

## Learning Objectives
 - Understand how Maven works
 - Create a new maven project
 - Understand how to write XML code
 - Add dependencies to Maven project.

## Maven

So far we have been creating simple Java projects using Gradle as the build tool. We haven't actually looked at Gradle properly as of yet and we won't go into any detail about it here. You will use gradle properly when it comes to creating Android mobile apps.

Gradle is what is known as a `"Build Tool"`.

There are 3 major build tools used in Java. `Ant`, `Gradle` and `Maven`

#### What does this mean?

Well put simply, in order to bring your code into production, you need to have a usable software image that can be run and deployed. That’s where build tools come in. They take your source code and compile it into an executable program. These days, build tools bring more to the table then just building your app, with features like dependency management as well.

Which build tool you use will depend on your needs and preferences as a developer. If build speed is the most important element to you, Maven may be best. If the community and documentation is important, then Gradle could be the way to go. If full control is what you want, then you should lean towards Ant. All three of these are good options, so it really comes down to the different flavours and quirks of you and your environment.

#### Maven

We are going to use Maven as it is a very useful tool for what we are going to be doing this week and next.

#### Creating a Maven Project

Open IntelliJ

To keep things simple we are going to setup Maven to import automatically so it doesn't have to keep asking us.

To avoid having to do this every time lets go to preferences.

- Click `Configure` > `preferences`.
- Expand `Build, Execution, Deployment` and select `Build Tools > Maven`.
- Check the `Import Maven projects automatically` option.
- Click `Ok`.

Now lets create a project


- Select `Create New project`.

- From the left hand menu select `Maven`

Maven provide us with a number of templates, called `archetype`'s depending on what type of application we want to create.

We are not going to use an archetype just yet so leave the checkbox unticked.

- Click `Next`.

We need to assign a `GroupId`. This will identify your project uniquely across all projects, so we need to enforce a naming schema.

- Enter `com.codeclan.example` as GroupId

- Enter `intro_to_maven` as ArtifactId.

- Click `Next`.

- Change location of your project to `codeclan_work/week_8/day_1/intro_to_maven`

- Click `Finish`

Once project opens it may say 'Maven projects need to be imported.' Click `Import Changes`.

The project will open up an XML file called pom.xml. (Name will show name of project in tab and pom.xml in project pane on left, don't know why!).

You are now looking at XML.... looks familiar doesn't it?

## XML

#### What is XML?

- XML stands for `EXtensible Markup Language`
- XML is a markup language much like `HTML`.
- XML was designed to describe data.
- XML tags are not predefined in XML. You must define your own tags.

#### The main difference between XML and HTML

XML is not a replacement for HTML. XML and HTML were designed with different goals:

XML was designed to describe data and to focus on what data is. HTML was designed to display data and to focus on how data looks.

HTML is about displaying information, XML is about describing information.

#### Adding dependencies to XML.

Dependencies are tools that we can use in our Java application. For example JUnit, Postgres, Hibernate, etc.

To be able to use any of these we need to add a tag for them in our pom.xml so that Maven can fetch the files from it's repository that will allow us to use them.

Lets add JUnit for just now.

```xml
#pom.xml
...
 <version>1.0-SNAPSHOT</version>

<dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.8.1</version>
      <scope>test</scope>
    </dependency>
</dependencies>

```

So we can now use JUnit. Lets create something to test.

Lets create a test file for an Employee class under `src/test/java` called `EmployeeTest`

```java
// EmployeeTest.java

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmployeeTest {

    public Employee employee;

    @Before
    public void before(){
        employee = new Employee("Jack Jarvis", 12000.00);
    }

    @Test
    public void hasName(){
        assertEquals("Jack Jarvis", employee.getName());
    }

    @Test
    public void hasSalary(){
        assertEquals(12000.00, employee.getSalary(), 0.1);
    }

    @Test
    public void canRaiseSalary(){
        employee.raiseSalary(2000.00);
        assertEquals(14000.00, employee.getSalary(), 0.1);
    }
}


```

Go ahead and create the `Employee` class and get the tests passing.

Run the tests and make sure they pass.

#### Solution


```java
// Employee.java
public class Employee {

    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void raiseSalary(double amount){
        this.salary += amount;
    }
}


```

Excellent, we have just created our first maven project.

## Recap

> Instructor note: Ask the class...

What is the purpose of build tools?

<details>
<summary>Answer:</summary>

- Build tools are used to compile projects into executable code.
</details>

<br>


What are differences between XML and HTML?

<details>
<summary>Answer:</summary>

- XML was designed to describe data and to focus on what data is. HTML was designed to display data and to focus on how data looks.

- HTML is about displaying information, XML is about describing information.

</details>

<br>

What tags are used to add in dependencies?

<details>
<summary>Answer:</summary>

- `<dependencies>`
- `<dependency>`

</details>

## Summary

We've seen:
 - What a build tool is.
 - How to setup a Maven project.
 - What XML is and how it differs from HTML.
 - Adding dependencies to project using XML.
 - How to write, compile and run a Maven program.

As we go through the next two weeks you will get to see more and more features of Maven and XML and understand how these can be very powerful tools for developers.

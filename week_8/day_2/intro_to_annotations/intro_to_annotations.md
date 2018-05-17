# Introduction to Annotations

## Learning Objectives

- Understand how annotations work
- Add annotations as a dependancy.
- Map Java classes with annotations.
- Create one-to-many relationship with annotations.

## Hibernate Annotations

So far you have seen how Hibernate uses XML mapping file for the transformation of data from POJO to database tables and vice versa.

Hibernate annotations are the newest way to define mappings without the use of XML file.
You can use annotations in addition to or as a replacement of XML mapping metadata.
Hibernate Annotations are a powerful way to provide the metadata for the Object and Relational Table mapping.

All the metadata is clubbed into the Java file along with the code.
This helps the user to understand the table structure and Java class simultaneously during the development.

The Hibernate annotations are included in a set of standards known as EJB 3.
EJB stands for Enterprise Java Beans. This is a development architecture for building highly scalable and robust enterprise level applications.

If you going to make your application portable to other EJB 3 compliant ORM applications, you must use annotations to represent the mapping information.

## Employee system using annotations

> Hand out starter code

Here we have our standard Employee system we created yesterday.

The `hibernate.cfg.xml` file and `hibernate`, `postgres` and `Javassist` dependencies have already been added to the `pom.xml` file.

The `DBHelper` file has already been added as well.

All that remains is to map the `Employee` and `Department` classes to the database using annotations.

## Adding annotations as a dependancy

First we need to add hibernate annotations as dependency.

Open pom.xml and add the following within the `<dependencies>` tag.

```xml
<!-- pom.xml -->

<dependency>
  <groupId>org.hibernate</groupId>
  <artifactId>hibernate-annotations</artifactId>
  <version>3.3.0.ga</version>
</dependency>
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-commons-annotations</artifactId>
    <version>3.2.0.Final</version>
</dependency>
```

## Annotations.

Let's start by simply mapping the Employee and Department classes. We won't worry about the relationships between the 2 classes for now.

We will start off by letting hibernate know that we want to map over the employee class to a database table.

#### Adding table details

```java
// Employee.java

@Entity //ADDED
@Table(name="employees") // ADDED
public class Employee {
// AS BEFORE
}
```

##### `@Entity` Annotation

The EJB 3 standard annotations are contained in the `javax.persistence` package, so we import what we need from this package.

We used the `@Entity` annotation to mark this class as an entity that we want to map. By doing this the class needs to have an constructor which takes no arguments and must be visible with at least protected scope.

##### `@Table` Annotation

The `@Table` annotation allows you to specify the details of the table that will be used to persist the entity in the database.

The `@Table` annotation provides attributes of the table. For now, we are using just `name`, which is `employees`.

#### Adding an ID.

Next step is to add the primary key identifier to our `id` attribute.

We can either annotate a `property` directly or annotate the `getter`. The difference being which one hibernate then uses to access the details. We should never access properties directly so we will add all other annotations to the getters.

```java
// Employee.java

    @Id // ADDED
    @GeneratedValue // ADDED
    @Column(name = "id") // ADDED
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

```

#### `@Id` and `@GeneratedValue` Annotations

Each entity bean will have a primary key, which you annotate on the class with the `@Id` annotation.

By default, the `@Id` annotation will automatically decide on the most appropriate primary key generation strategy to use. However, you can override this by applying the `@GeneratedValue `annotation. This lets Hibernate determine which generator type to use.

##### `@Column` Annotation

The `@Column` annotation is used to specify the details of the column which a field or property will be mapped to. You can use the column annotation with the following most commonly used attributes −

`name` attribute permits the name of the column to be explicitly specified.

`length` attribute permits the size of the column used to map a value particularly for a String value.

`nullable` attribute permits the column to be marked NOT NULL when the schema is generated.

`unique` attribute permits the column to be marked as containing only unique values.

#### Mapping our properties.

Now let's map over the rest of the properties that we want persisted to the database using the `@Column` annotation.

```java

// Employee.java

    @Column(name = "first_name") // ADDED
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name") // ADDED
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "salary") // ADDED
    public int getSalary() {
        return salary;
    }
```

Lastly we need to update `hibernate.cfg.xml` to point to the mapping resource.

As the annotations are declared in the class this is our new mapping resource.

```xml
<!-- hibernate.cfg.xml -->

    <mapping class = "models.Employee"/> <!-- ADDED -->
  </session-factory>
</hibernate-configuration>
```

## Task

Map the Department class in the same way and make sure the `Runner` file saves the 2 departments and employees.

(Note - Don't map the `Set<Employee> employees` as a column)


## Run the file

Ok so both classes are mapped we will now run the main method and see if our instances are saved to the database.

Let's drop and recreate our database from yesterday

```bash
# terminal

dropdb employeedb
createdb employeedb

```

- Run `Runner`

- Check the database

```bash
# terminal

psql
\c employeedb
\dt # Check tables created
SELECT * FROM departments;
SELECT * FROM employees;
```

And we should have our data saved in these tables.


## Recap
> Instructor note: Ask the class...

Why use annotations instead of xml?

<details>
<summary>Answer:</summary>

- All the metadata is added into the Java file along with the code.
- Helps the user to understand the table structure and Java class simultaneously during the development.
</details>

<br>

What does the @Entity annotation do?

<details>
<summary>Answer:</summary>

It marks this class as an entity that we want to map.

</details>
<br>

How do we point configuration to the mapping file?

<details>
<summary>Answer:</summary>

`<mapping class="classname" />`

</details>

## Summary

We've seen:
 - What annotations are in hibernate.
 - Advantages of using annotations.
 - Setting up a Hibernate annotation project,
 - Mapping a class to hibernate using annotations


Annotations are a far more common way of mapping classes to database tables using hibernate so we will continue to use these for the remainder of this module.

There are a number of advantages to using annotations

- Compile-time checking : writing in Java (instead of Xml) is very user-friendly in the IDE nowadays. No more typos discovered when starting your application.

- Localized with the code (class level) : instead of having to open two files (java and xml) to get the full story, with one annotated java file, you open only one file. This is less repetitive, faster in the long run.

- Tools (javadoc, other tools using reflection) can use the annotations for some other requirements.

- annotations are less verbose than their XML equivalents. Let’s see the comparison e.g.

```java
import javax.persistence.* ;
@Entity
public class Sample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String name;
}
```

And compare it with equivalent mapping file.

```xml
<?xml version='1.0' encoding='utf-8'?>
<!DOCTYPE
   hibernate-mapping
   PUBLIC
   "-//Hibernate/Hibernate Mapping DTD//EN"
   "http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd
">
<hibernate-mapping default-access="field">
   <class name="Sample">
      <id type="int" column="id">
         <generator class="native"/>
      </id>
      <property name="name" type="string"/>
   </class>
</hibernate-mapping>
```

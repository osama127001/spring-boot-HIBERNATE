
<h1 align="center">
    Hibernate 🥫 
</h1>

<p align="center">
    Hibernate ORM is an object-relational mapping tool for the Java programming language. It provides a framework for mapping an object-oriented domain model to a relational database.
</p>


## Hibernate Annotations

Annotations | Description
---| ---| 
`@Entity` | Marks the class as an entity.
`@Table(name="table-name")` | Maps the entity to table.
`@Id` | Marks the field as an ID-Column of the table.
`@Column` | Shows that the field is the column of the database.


<details>
<summary>Testing Database Connection</summary>

Following code is used to test the connection with the database.

    package com.osama.springhibernate;
    
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
    
    import java.sql.Connection;
    import java.sql.DriverManager;
    
    @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
    public class SpringHibernateApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(SpringHibernateApplication.class, args);
    
            /*
            * Database: MySQL
            * Testing Database Connection
            */
            String userName = "dbadmin";
            String password = "admin";
            String jdbcUrl = "jdbc:mysql://localhost:3306/testdb";
            try {
                System.out.println("Connecting to database");
                Connection con = DriverManager.getConnection(jdbcUrl, userName, password);
                System.out.println("Connection Successful");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

Make sure to add `@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})` in the annotation.

</details>
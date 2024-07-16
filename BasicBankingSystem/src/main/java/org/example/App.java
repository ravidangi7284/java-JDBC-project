package org.example;

import java.sql.*;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main ( String[] args ) throws SQLException
    {
        String url="jdbc:mysql://localhost:3306/banking_system";
        String username="root";
        String password="Dangi@7284";

        try {
            Connection connection= DriverManager.getConnection(url,username,password);
            Statement statement= connection.createStatement();
            menu.start(statement);

        }catch (SQLException e){
            System.out.println( e.getMessage());
        }


    }
}

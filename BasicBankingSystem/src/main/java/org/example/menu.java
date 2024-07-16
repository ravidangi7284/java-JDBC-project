package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class menu {


    public static void createAccount(Statement statement) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the account No. : ");
        int account =sc.nextInt();
        System.out.println("Enter the Account Holder Name : ");
        String name =sc.next();
        System.out.println("Enter the type of Account : ");
        String type =sc.next();
        System.out.println("Enter the Contact Number : ");
        int contact= sc.nextInt();
        System.out.println("Enter the Opening balance : ");
        double balance= sc.nextDouble();
        try {
            int row= statement.executeUpdate("insert into BankDel values ("+account+",'"+name+"','"+type+"',"+contact+","+balance+");");
            if (row>0){
                System.out.println("insertion successfully");
            }
            else {
                System.out.println("insertion failed");
            }
        }catch (SQLException e){
            e.getMessage();
        }

    }


    public static   void checkBalance(Statement statement) {
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter the Account Number : ");
        int account= sc.nextInt();
        try {
            ResultSet resultSet= statement.executeQuery("select Balance from BankDel where AccountNo= "+account+"");
            resultSet.next();
            System.out.println("Account Balance : "+ resultSet.getDouble("Balance"));
        }catch (SQLException e){
            e.getMessage();
        }

    }

    public static void depositAmount(Statement statement) {
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter the Account Number : ");
        int account= sc.nextInt();
        System.out.print("Enter the Amount : ");
        double damount=sc.nextDouble();
        try {
            ResultSet resultSet= statement.executeQuery("select Balance from BankDel where AccountNo= "+account+"");
            resultSet.next();
            double amount= resultSet.getDouble("Balance");
            double uamount=amount+damount;
            int row= statement.executeUpdate("update BankDel set Balance="+uamount+" where AccountNo="+account+" ");
            if (row>0){
                System.out.println("Deposited successfully");
            }
            else {
                System.out.println("Deposite failed");
            }

        }catch (SQLException e){
            e.getMessage();
        }

    }

    public static  void withdrawAmount(Statement statement) {
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter the Account Number : ");
        int account= sc.nextInt();
        System.out.print("Enter the Amount : ");
        double wamount=sc.nextDouble();
        try {
            ResultSet resultSet= statement.executeQuery("select Balance from BankDel where AccountNo= "+account+"");
            resultSet.next();
            double amount= resultSet.getDouble("Balance");
            double uamount=amount-wamount;
            int row= statement.executeUpdate("update BankDel set Balance="+uamount+" where AccountNo="+account+" ");
            if (row>0){
                System.out.println("Withdrawal successfully");
            }
            else {
                System.out.println("Withdrawal failed");
            }

        }catch (SQLException e){
            e.getMessage();
        }
    }

    public static   void getDetails(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                System.out.println("Account no : " + resultSet.getInt("AccountNo"));
                System.out.println("Account Holder Name : " + resultSet.getString("AccountHolder"));
                System.out.println("Account Type : " + resultSet.getString("AccountType"));
                System.out.println("Contact Number : " + resultSet.getInt("ContactNo"));
                System.out.println("Balance : " + resultSet.getDouble("Balance"));
                System.out.println("--------------------------------------------------------------------------------------------------------------------");
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void start(Statement statement) {


        Scanner sc =new Scanner(System.in);
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("*******Banking System Application*******");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("1. Create new account");
        System.out.println("2. Check Balance");
        System.out.println("3. Deposit the Amount");
        System.out.println("4. Withdraw the Amount");
        System.out.println("5. Watch Demo Account");
        System.out.println("6. Exit");
        int choice= sc.nextInt();

        switch (choice){
            case 1:
                createAccount(statement);
                start(statement);
                break;

            case 2:
                checkBalance(statement);
                start(statement);
                break;
            case 3:
                depositAmount(statement);
                start(statement);
                break;

            case 4:
                withdrawAmount(statement);
                start(statement);
                break;

            case 5:
                try {
                    ResultSet resultSet= statement.executeQuery("select * from BankDel");
                    getDetails(resultSet);
                }catch (SQLException e)
                {
                    e.getMessage();
                }
                start(statement);

                break;

            case 6:
                System.out.println("Thanks for using Banking System Application");
                break;


        }
    }
}

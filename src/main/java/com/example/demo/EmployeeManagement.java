package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class EmployeeManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;
        try {
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "");

           
            System.out.println("enter employee ID:");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("enter employee name:");
            String name = scanner.nextLine();
            System.out.println("enter employee Age:");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.println("is employee present? (true/false):");
            boolean isPresent = scanner.nextBoolean();
            scanner.nextLine();
            System.out.println("enter employee shift:");
            String shift = scanner.nextLine();
            System.out.println("enter employee salary in RS :");
            double salary = scanner.nextDouble();

            
            String sqlEmployee = "INSERT INTO Employee (ID, Name, Age) VALUES (?, ?, ?)";
            String sqlAttendance = "INSERT INTO Attendance (EmployeeID, IsPresent) VALUES (?, ?)";
            String sqlShift = "INSERT INTO Shift (EmployeeID, Shift) VALUES (?, ?)";
            String sqlSalary = "INSERT INTO Salary (EmployeeID, Salary) VALUES (?, ?)";

            
            PreparedStatement pstmtEmployee = connection.prepareStatement(sqlEmployee);
            PreparedStatement pstmtAttendance = connection.prepareStatement(sqlAttendance);
            PreparedStatement pstmtShift = connection.prepareStatement(sqlShift);
            PreparedStatement pstmtSalary = connection.prepareStatement(sqlSalary);

            
            pstmtEmployee.setInt(1, id);
            pstmtEmployee.setString(2, name);
            pstmtEmployee.setInt(3, age);
            pstmtAttendance.setInt(1, id);
            pstmtAttendance.setBoolean(2, isPresent);
            pstmtShift.setInt(1, id);
            pstmtShift.setString(2, shift);
            pstmtSalary.setInt(1, id);
            pstmtSalary.setDouble(2, salary);

            
            pstmtEmployee.executeUpdate();
            pstmtAttendance.executeUpdate();
            pstmtShift.executeUpdate();
            pstmtSalary.executeUpdate();

            System.out.println("Employee details inserted successfully.");

        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        } finally {
            
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
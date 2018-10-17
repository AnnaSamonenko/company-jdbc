package entrypoint;

import dao.EmployeeDAO;
import dao.PositionDAO;
import dao.ProjectDAO;
import dao.WorkingHistoryDAO;
import entity.Employee;
import helper.GenerateContentOfCompanyDatabase;
import utils.MySQLDatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {

    static final String URL = "jdbc:mysql://localhost:3306";
    static final String USER = "root";
    static final String PASSWORD = "root";
    static final String DATABASE_NAME = "company_db";

    public static void main(String[] args) {
        int amountOfProjects = 2;
        int amountOfEmployees = 8;
        createContentOfTables(amountOfProjects, amountOfEmployees);
        printTables();
        System.out.println("------ RESULT OF 1 QUERY ------");
        executeQuery1();
        System.out.println();
        System.out.println("------ RESULT OF 2 QUERY ------");
        executeQuery2();
        //cleanTables();
    }

    //Find the PM of the project with the highest count of java developers
    // and print out his/her name and contact info.
    public static void executeQuery1() {
        try (Connection connection = MySQLDatabaseConnection.getConnection(URL, USER, PASSWORD, DATABASE_NAME)) {
            EmployeeDAO employeeDAO = new EmployeeDAO(connection);
            Employee em = employeeDAO.findPMWithHighestCountOfJavaDev();
            System.out.print("Query1: " + em.getName() + " " + em.getContactInformation());
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    // List all the test engineers that currently work at the company,
    // sorted by count of projects they participated in.
    public static void executeQuery2() {
        try (Connection connection = MySQLDatabaseConnection.getConnection(URL, USER, PASSWORD, DATABASE_NAME)) {
            EmployeeDAO employeeDAO = new EmployeeDAO(connection);
            List<Employee> employees = employeeDAO.findTestEngineersSortedByWorkingHistory();
            printList(employees);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void printList(List<Employee> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static void createContentOfTables(int amountOfProjects, int amountOfEmployees) {
        GenerateContentOfCompanyDatabase createDBContent = new GenerateContentOfCompanyDatabase(URL, USER, PASSWORD, DATABASE_NAME);
        createDBContent.generatePositionTable();
        createDBContent.generateProjectTable(amountOfProjects);
        createDBContent.generateEmployeeTable(amountOfEmployees);
        createDBContent.generateWorkingHistoryTable();
    }

    public static void cleanTables() {
        try (Connection connection = MySQLDatabaseConnection.getConnection(URL, USER, PASSWORD, DATABASE_NAME)) {
            EmployeeDAO employeeDAO = new EmployeeDAO(connection);
            employeeDAO.removeAll();
            ProjectDAO projectDAO = new ProjectDAO(connection);
            projectDAO.removeAll();
            PositionDAO positionDAO = new PositionDAO(connection);
            positionDAO.removeAll();
            WorkingHistoryDAO whDAO = new WorkingHistoryDAO(connection);
            whDAO.removeAll();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void printTables() {
        try (Connection connection = MySQLDatabaseConnection.getConnection(URL, USER, PASSWORD, DATABASE_NAME)) {
            PositionDAO positionDAO = new PositionDAO(connection);
            positionDAO.print();
            EmployeeDAO employeeDAO = new EmployeeDAO(connection);
            employeeDAO.print();
            ProjectDAO projectDAO = new ProjectDAO(connection);
            projectDAO.print();
            WorkingHistoryDAO whDAO = new WorkingHistoryDAO(connection);
            whDAO.print();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
}

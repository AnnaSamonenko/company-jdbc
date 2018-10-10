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

    protected static final String URL = "jdbc:mysql://localhost:3306";
    protected static final String USER = "root";
    protected static final String PASSWORD = "root";
    protected static final String DATABASE_NAME = "company_db";

    public static void main(String[] args) {
        int amountOfProjects = 3;
        int amountOfEmployees = 20;
        //createContentOfTables(amountOfProjects, amountOfEmployees);

        //Find the PM of the project with the highest count of java developers
        // and print out his/her name and contact info.
        try (Connection connection = MySQLDatabaseConnection.getConnection(URL, USER, PASSWORD, DATABASE_NAME)) {
            EmployeeDAO employeeDAO = new EmployeeDAO(connection);
            Employee em = employeeDAO.findPMWithHighestCountOfJavaDev();
            System.out.print("Query1: " + em.getName() + " " + em.getContactInformation());
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }

        // List all the test engineers that currently work at the company,
        // sorted by count of projects they participated in.
        try (Connection connection = MySQLDatabaseConnection.getConnection(URL, USER, PASSWORD, DATABASE_NAME)) {
            EmployeeDAO employeeDAO = new EmployeeDAO(connection);
            List<Employee> employees = employeeDAO.findTestEngineersSortedByWorkingHistory();
            // TODO: output
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void createContentOfTables(int amountOfProjects, int amountOfEmployees) {
        GenerateContentOfCompanyDatabase cretaeDBContent = new GenerateContentOfCompanyDatabase(URL, USER, PASSWORD, DATABASE_NAME);
        cretaeDBContent.generatePositionTable();
        cretaeDBContent.generateProjectTable(amountOfProjects);
        cretaeDBContent.generateEmployeeTable(amountOfEmployees);
        cretaeDBContent.generateWorkingHistoryTable();
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
}

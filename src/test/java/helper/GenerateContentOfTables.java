package helper;

import dao.EmployeeDAO;
import dao.PositionDAO;
import dao.ProjectDAO;
import data.ConnectionData;
import entity.Employee;
import entity.Position;
import entity.Project;
import utils.MySQLDatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GenerateContentOfTables extends ConnectionData {

    public static void generatePositionTable() {
        List<String> positions = GenerateDataHelper.generatePositions();

        try (Connection connection = MySQLDatabaseConnection.getConnection(URL, USER, PASSWORD, DATABASE_NAME)) {
            PositionDAO positionDAO = new PositionDAO(connection);
            for (String position : positions)
                positionDAO.create(position);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void generateProjectTable() {
        List<Project> projects = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Project project = new Project();
            project.setDescription("Some description:)");
            project.setStartDate(GenerateDataHelper.generateRandomDate());
            project.setEndDate(null);
            project.setTitle(GenerateDataHelper.generateProjectName());
            projects.add(project);
        }

        try (Connection connection = MySQLDatabaseConnection.getConnection(URL, USER, PASSWORD, DATABASE_NAME)) {
            ProjectDAO projectDAO = new ProjectDAO(connection);
            for (Project project : projects)
                projectDAO.create(project);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void generateEmployeeTable() {
        List<Employee> employees = new LinkedList<>();
        Random r = new Random();
        List<Position> positions;
        List<Project> project;

        try (Connection connection = MySQLDatabaseConnection.getConnection(URL, USER, PASSWORD, DATABASE_NAME)) {
            PositionDAO positionDAO = new PositionDAO(connection);
            positions = positionDAO.getAll();
            ProjectDAO projectDAO = new ProjectDAO(connection);
            project = projectDAO.getAll();
            for (int i = 0; i < 7; i++) {
                Employee employee = new Employee();
                employee.setName(GenerateDataHelper.getRandomPersonName());
                employee.setContactInformation(GenerateDataHelper.generateContactInfo());
                employee.setPosition(positions.get(r.nextInt(positions.size())));
                employee.setProject(project.get(r.nextInt(project.size())));
                employees.add(employee);
            }
            EmployeeDAO employeeDAO = new EmployeeDAO(connection);
            for (Employee employee : employees)
                employeeDAO.create(employee);

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}

package helper;

import dao.EmployeeDAO;
import dao.PositionDAO;
import dao.ProjectDAO;
import dao.WorkingHistoryDAO;
import data.ConnectionData;
import entity.Employee;
import entity.Position;
import entity.Project;
import entity.WorkingHistory;
import utils.MySQLDatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GenerateContentOfTables extends ConnectionData {

    private static final int AMOUNT_OF_EMPLOYEES = 20;
    private static final int AMOUNT_OF_PROJECTS = 3;

    public static void generatePositionTable() {
        List<String> positions = GenerateDataHelper.generatePositions();

        try (Connection connection = MySQLDatabaseConnection.getConnection(URL, USER, PASSWORD, DATABASE_NAME)) {
            PositionDAO positionDAO = new PositionDAO(connection);
            for (String position : positions)
                positionDAO.create(position);

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void generateProjectTable() {
        List<Project> projects = new ArrayList<>();
        for (int i = 0; i < AMOUNT_OF_PROJECTS; i++) {
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
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void generateWorkingHistoryTable() {
        Random r = new Random();
        try (Connection connection = MySQLDatabaseConnection.getConnection(URL, USER, PASSWORD, DATABASE_NAME)) {
            EmployeeDAO employeeDAO = new EmployeeDAO(connection);
            WorkingHistoryDAO workingHistoryDAO = new WorkingHistoryDAO(connection);
            ProjectDAO projectDAO = new ProjectDAO(connection);
            List<Employee> employees = employeeDAO.getAll();
            List<Project> projects = projectDAO.getAll();
            for (Employee employee : employees) {
                List<LocalDate> dates = GenerateDataHelper.generateListWithRandomDate();
                for (int i = 0; i < dates.size() - 1; i += 2) {
                    WorkingHistory wh = new WorkingHistory();
                    wh.setEmployee(employee);
                    wh.setProject(projects.get(r.nextInt(projects.size())));
                    wh.setStartDate(dates.get(i));
                    wh.setEndDate(dates.get(i + 1));
                    workingHistoryDAO.create(wh);
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
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
            positions = positionDAO.getEngineerPositions();
            ProjectDAO projectDAO = new ProjectDAO(connection);
            project = projectDAO.getAll();
            for (int i = 0; i < AMOUNT_OF_EMPLOYEES; i++) {
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

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        assignPMToProject();
    }

    private static void assignPMToProject() {
        List<Project> projects;
        try (Connection connection = MySQLDatabaseConnection.getConnection(URL, USER, PASSWORD, DATABASE_NAME)) {
            ProjectDAO projectDAO = new ProjectDAO(connection);
            PositionDAO positionDAO = new PositionDAO(connection);
            EmployeeDAO employeeDAO = new EmployeeDAO(connection);
            projects = projectDAO.getAll();
            for (Project project : projects) {
                Employee employee = new Employee();
                employee.setName(GenerateDataHelper.getRandomPersonName());
                employee.setContactInformation(GenerateDataHelper.generateContactInfo());
                employee.setPosition(positionDAO.getManagerPosition());
                employee.setProject(project);
                employeeDAO.create(employee);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
}

package tests;

import dao.EmployeeDAO;
import dao.PositionDAO;
import dao.ProjectDAO;
import helper.GenerateContentOfTables;
import org.junit.Test;
import utils.MySQLDatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Texture extends GenerateContentOfTables {

    @Test
    public void createContentOfTables() {
        GenerateContentOfTables.generatePositionTable();
        GenerateContentOfTables.generateProjectTable();
        GenerateContentOfTables.generateEmployeeTable();
    }

    @Test
    public void cleanTables() {
        try (Connection connection = MySQLDatabaseConnection.getConnection(URL, USER, PASSWORD, DATABASE_NAME)) {
            EmployeeDAO employeeDAO = new EmployeeDAO(connection);
            employeeDAO.removeAll();
            ProjectDAO projectDAO = new ProjectDAO(connection);
            projectDAO.removeAll();
            PositionDAO positionDAO = new PositionDAO(connection);
            positionDAO.removeAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}

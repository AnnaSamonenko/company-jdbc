package tests;

import dao.EmployeeDAO;
import entity.Employee;
import org.junit.Test;
import utils.MySQLDatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * List all the test engineers that currently work at the company, sorted by count of projects they participated in.
 */

public class Test2 extends Texture {

    @Test
    public void test() {
        try (Connection connection = MySQLDatabaseConnection.getConnection(URL, USER, PASSWORD, DATABASE_NAME)) {
            EmployeeDAO employeeDAO = new EmployeeDAO(connection);
            List<Employee> employees = employeeDAO.getExecuteQuery2();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

}

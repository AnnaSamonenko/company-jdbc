package tests;

import dao.EmployeeDAO;
import entity.Employee;
import org.junit.Assert;
import org.junit.Test;
import utils.MySQLDatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Find the PM of the project with the highest count of java developers and print out his/her name and contact info.
 */

public class Test1 extends Texture {

    @Test
    public void test() {
        try (Connection connection = MySQLDatabaseConnection.getConnection(URL, USER, PASSWORD, DATABASE_NAME)) {
            EmployeeDAO employeeDAO = new EmployeeDAO(connection);
            Employee em = employeeDAO.getExecuteQuery1();
            Assert.assertEquals(em.getName(), "Elizabeth Davies");
            Assert.assertEquals(em.getContactInformation(), "phone number: 46025859");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
}


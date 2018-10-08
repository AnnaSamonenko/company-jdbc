package dao;

import entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDAO {

    private Connection connection;
    private String insert = "INSERT INTO employees(name, contact_information, positionId, projectId) VALUES(?, ?, ?, ?)";
    private String removeAll = "DELETE FROM employees";

    public EmployeeDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Employee employee) {
        try (PreparedStatement prSttm = connection.prepareStatement(insert)) {
            prSttm.setString(1, employee.getName());
            prSttm.setString(2, employee.getContactInformation());
            prSttm.setInt(3, employee.getPosition().getId());
            prSttm.setInt(4, employee.getProject().getId());
            prSttm.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void removeAll() {
        try (Statement sttm = connection.createStatement()) {
            sttm.execute(removeAll);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

package dao;

import entity.Employee;
import utils.ReadSQLScript;

import java.sql.*;

public class EmployeeDAO {

    private Connection connection;
    private String insert = "INSERT INTO employees(name, contact_information, position_id, project_id) VALUES(?, ?, ?, ?)";
    private String removeAll = "DELETE FROM employees";
    private String pathToFile = "C:\\Users\\anna.samonenko\\Desktop\\project\\src\\main\\resources\\query1.sql";

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

    public Employee getExecuteQuery1() {
        Employee manager = new Employee();
        try (Statement st = connection.createStatement()) {
            try (ResultSet rs = st.executeQuery(ReadSQLScript.read(pathToFile))) {
                rs.next();
                manager.setName(rs.getString("name"));
                manager.setContactInformation(rs.getString("contact_information"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return manager;
    }
}

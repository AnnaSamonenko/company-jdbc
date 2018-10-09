package dao;

import entity.Employee;
import utils.ReadSQLScript;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    private Connection connection;
    private String insert = "INSERT INTO employees(name, contact_information, position_id, project_id) VALUES(?, ?, ?, ?)";
    private String selectAll = "SELECT * FROM employees";
    private String selectById = "SELECT * FROM employees WHERE employee_id=?;";
    private String removeAll = "DELETE FROM employees";
    private String pathToFile = "C:\\Users\\anna.samonenko\\Desktop\\project\\src\\main\\resources\\query1.sql";
    private String pathToFile2 = "C:\\Users\\anna.samonenko\\Desktop\\project\\src\\main\\resources\\query2.sql";

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

    public Employee get(int id) {
        Employee employee = new Employee();
        try (PreparedStatement prSttm = connection.prepareStatement(selectById)) {
            prSttm.setInt(1, id);
            try (ResultSet rs = prSttm.executeQuery(selectById)) {
                rs.next();
                employee.setName(rs.getString("name"));
                employee.setId(rs.getInt("employee_id"));
                employee.setContactInformation(rs.getString("contact_information"));
                ProjectDAO projectDAO = new ProjectDAO(connection);
                employee.setProject(projectDAO.get(rs.getInt("project_id")));
                PositionDAO positionDAO = new PositionDAO(connection);
                employee.setPosition(positionDAO.get(rs.getInt("position_id")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return employee;
    }

    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement prSttm = connection.prepareStatement(selectAll)) {
            try (ResultSet rs = prSttm.executeQuery()) {
                while (rs.next()) {
                    Employee employee = new Employee();
                    employee.setName(rs.getString("name"));
                    employee.setId(rs.getInt("employee_id"));
                    employee.setContactInformation(rs.getString("contact_information"));
                    ProjectDAO projectDAO = new ProjectDAO(connection);
                    employee.setProject(projectDAO.get(rs.getInt("project_id")));
                    PositionDAO positionDAO = new PositionDAO(connection);
                    employee.setPosition(positionDAO.get(rs.getInt("position_id")));
                    employees.add(employee);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return employees;
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

    public List<Employee> getExecuteQuery2() {
        List<Employee> employees = new ArrayList<>();
        try (Statement sttm = connection.createStatement()) {
            try (ResultSet rs = sttm.executeQuery(ReadSQLScript.read(pathToFile2))) {
                while (rs.next()) {
                    employees.add(get(rs.getInt("employee_id")));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return employees;
    }
}

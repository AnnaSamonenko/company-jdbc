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
    private String pathToQuery1 = "\\src\\main\\resources\\query1.sql";
    private String pathToQuery2 = "\\src\\main\\resources\\query2.sql";
    private String pathToPrintQuery = "\\src\\main\\resources\\display\\displayEmployeeTable.sql";

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
            try (ResultSet rs = prSttm.executeQuery()) {
                rs.next();
                employee.setName(rs.getString("name"));
                employee.setId(rs.getInt("employee_id"));
                employee.setContactInformation(rs.getString("contact_information"));
                ProjectDAO projectDAO = new ProjectDAO(connection);
                employee.setProject(projectDAO.get(rs.getInt("project_id")));
                PositionDAO positionDAO = new PositionDAO(connection);
                employee.setPosition(positionDAO.get(rs.getInt("position_id")));
                WorkingHistoryDAO whDAO = new WorkingHistoryDAO(connection);
                employee.setWorkingHistory(whDAO.getByEmployeeId(id));
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

    public Employee findPMWithHighestCountOfJavaDev() {
        Employee manager = new Employee();
        try (Statement st = connection.createStatement()) {
            try (ResultSet rs = st.executeQuery(ReadSQLScript.read(pathToQuery1))) {
                rs.next();
                manager.setName(rs.getString("name"));
                manager.setContactInformation(rs.getString("contact_information"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return manager;
    }

    public List<Employee> findTestEngineersSortedByWorkingHistory() {
        List<Employee> employees = new ArrayList<>();
        try (Statement sttm = connection.createStatement()) {
            try (ResultSet rs = sttm.executeQuery(ReadSQLScript.read(pathToQuery2))) {
                while (rs.next()) {
                    employees.add(get(rs.getInt("employee_id")));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return employees;
    }

    public void print() {
        System.out.println("EMPLOYEE TABLE:");
        try (Statement sttm = connection.createStatement()) {
            try (ResultSet rs = sttm.executeQuery(ReadSQLScript.read(pathToPrintQuery))) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                for (int i = 1; i <= columnsNumber; i++) {
                    System.out.print(String.format("|%-25s|", rsmd.getColumnLabel(i)));
                }
                System.out.println();
                while (rs.next()) {
                    for (int i = 1; i <= columnsNumber; i++) {
                        String columnValue = rs.getString(i);
                        System.out.print(String.format("|%-25s|", columnValue));
                    }
                    System.out.println();
                }
            }
            System.out.println();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

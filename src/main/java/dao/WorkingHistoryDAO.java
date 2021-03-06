package dao;

import entity.WorkingHistory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkingHistoryDAO {

    private Connection connection;
    private String insert = "INSERT INTO working_history(project_id, employee_id, start_date, end_date) VALUES(?, ?, ?, ?);";
    private String select = "SELECT * FROM working_history";
    private String removeAll = "DELETE FROM working_history";
    private String selectByEmployeeId = "SELECT * FROM working_history WHERE employee_id = ?;";

    public WorkingHistoryDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(WorkingHistory wh) {
        try (PreparedStatement prSttm = connection.prepareStatement(insert)) {
            prSttm.setInt(1, wh.getProject().getId());
            prSttm.setInt(2, wh.getEmployee().getId());
            prSttm.setDate(3, java.sql.Date.valueOf(wh.getStartDate()));
            if (wh.getEndDate() != null)
                prSttm.setDate(4, java.sql.Date.valueOf(wh.getEndDate()));
            else
                prSttm.setDate(4, null);
            prSttm.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<WorkingHistory> getByEmployeeId(int employeeId) {
        List<WorkingHistory> workingHistory = new ArrayList<>();
        ProjectDAO projectDAO = new ProjectDAO(connection);
        try (PreparedStatement prSttm = connection.prepareStatement(selectByEmployeeId)) {
            prSttm.setInt(1, employeeId);
            try (ResultSet rs = prSttm.executeQuery()) {
                while (rs.next()) {
                    WorkingHistory wh = new WorkingHistory();
                    wh.setId(rs.getInt("working_history_id"));
                    wh.setProject(projectDAO.get(rs.getInt("project_id")));
                    wh.setStartDate(rs.getDate("start_date").toLocalDate());
                    if (rs.getDate("end_date") == null)
                        wh.setEndDate(null);
                    else wh.setEndDate(rs.getDate("end_date").toLocalDate());
                    workingHistory.add(wh);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return workingHistory;
    }

    public List<WorkingHistory> getAll() {
        List<WorkingHistory> workingHistories = new ArrayList<>();
        try (Statement sttm = connection.createStatement()) {
            try (ResultSet rs = sttm.executeQuery(select)) {
                while (rs.next()) {
                    WorkingHistory wh = new WorkingHistory();
                    wh.setId(rs.getInt("working_history_id"));
                    EmployeeDAO employeeDAO = new EmployeeDAO(connection);
                    wh.setEmployee(employeeDAO.get(rs.getInt("employee_id")));
                    ProjectDAO projectDAO = new ProjectDAO(connection);
                    wh.setProject(projectDAO.get(rs.getInt("project_id")));
                    wh.setStartDate(rs.getDate("start_date").toLocalDate());
                    wh.setEndDate(rs.getDate("end_date").toLocalDate());
                    workingHistories.add(wh);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return workingHistories;
    }

    public void removeAll() {
        try (Statement sttm = connection.createStatement()) {
            sttm.execute(removeAll);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

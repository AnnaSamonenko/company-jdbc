package dao;

import entity.WorkingHistory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkingHistoryDAO {

    private Connection connection;
    private String insert = "INSERT INTO working_history(project_id, employee_id, start_date, end_date) VALUES(?, ?, ?, ?);";
    private String select = "SELECT * FROM working_history";

    public WorkingHistoryDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(WorkingHistory wh) {
        try (PreparedStatement prSttm = connection.prepareStatement(insert)) {
            prSttm.setInt(1, wh.getProject().getId());
            prSttm.setInt(2, wh.getEmployee().getId());
            prSttm.setDate(3, java.sql.Date.valueOf(wh.getStartDate()));
            prSttm.setDate(4, java.sql.Date.valueOf(wh.getEndDate()));
            prSttm.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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

}

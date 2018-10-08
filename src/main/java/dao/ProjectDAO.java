package dao;

import entity.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {

    private Connection connection;
    private String insert = "INSERT INTO projects(title, description, start, end) VALUES(?, ?, ?, ?)";
    private String selectAll = "SELECT * FROM projects";
    private String removeAll = "DELETE FROM projects";

    public ProjectDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Project project) {
        try (PreparedStatement prSttm = connection.prepareStatement(insert)) {
            prSttm.setString(1, project.getTitle());
            prSttm.setString(2, project.getDescription());
            prSttm.setDate(3, java.sql.Date.valueOf(project.getStartDate()));
            if (project.getEndDate() == null) {
                prSttm.setDate(4, null);
            } else {
                prSttm.setDate(4, java.sql.Date.valueOf(project.getEndDate()));
            }
            prSttm.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Project> getAll() {
        List<Project> projects = new ArrayList<>();
        try (Statement sttm = connection.createStatement()) {
            try (ResultSet rs = sttm.executeQuery(selectAll)) {
                while (rs.next()) {
                    Project project = new Project();
                    project.setTitle(rs.getString("title"));
                    project.setDescription(rs.getString("description"));
                    project.setId(rs.getInt("project_id"));
                    project.setStartDate(rs.getDate("start").toLocalDate());
                    if (rs.getDate("end") == null) {
                        project.setEndDate(null);
                    } else project.setEndDate(rs.getDate("end").toLocalDate());
                    projects.add(project);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return projects;
    }

    public void removeAll() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(removeAll);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

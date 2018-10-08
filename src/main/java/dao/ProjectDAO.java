package dao;

import entity.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProjectDAO {

    private Connection connection;

    private String insert = "INSERT INTO projects(title, description, start, end) VALUES(?, ?, ?, ?)";

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
}

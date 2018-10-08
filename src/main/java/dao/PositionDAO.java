package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PositionDAO {

    private String insert = "INSERT INTO positions(title) VALUE(?);";
    private Connection connection;

    public PositionDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(String position) {
        try (PreparedStatement prSttm = connection.prepareStatement(insert)) {
            prSttm.setString(1, position);
            prSttm.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

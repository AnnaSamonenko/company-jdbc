package dao;

import entity.Position;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PositionDAO {

    private String insert = "INSERT INTO positions(title) VALUE(?);";
    private String selectAll = "SELECT * FROM positions;";
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

    public List<Position> getAll() {
        List<Position> positions = new ArrayList<>();
        try (Statement sttm = connection.createStatement()) {
            try (ResultSet rs = sttm.executeQuery(selectAll)) {
                while (rs.next()) {
                    Position position = new Position();
                    position.setId(rs.getInt("position_id"));
                    position.setTitle(rs.getString("title"));
                    positions.add(position);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return positions;
    }
}

package dao;

import entity.Position;
import utils.ReadSQLScript;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PositionDAO {

    private Connection connection;
    private String insert = "INSERT INTO positions(title) VALUE(?);";
    private String selectAll = "SELECT * FROM positions;";
    private String removeAll = "DELETE FROM positions;";
    private String selectManagerPosition = "SELECT * FROM positions WHERE positions.title = 'Project Manager';";
    private String selectEngineerPosition = "SELECT * FROM positions WHERE positions.title != 'Project Manager';";
    private String selectById = "SELECT * FROM positions WHERE position_id=?;";
    private String pathToPrintQuery = "\\src\\main\\resources\\display\\displayPositionTable.sql";

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

    public Position get(int id) {
        Position position = new Position();
        try (PreparedStatement prSttm = connection.prepareStatement(selectById)) {
            prSttm.setInt(1, id);
            try (ResultSet rs = prSttm.executeQuery()) {
                rs.next();
                position.setId(rs.getInt("position_id"));
                position.setTitle(rs.getString("title"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return position;
    }

    public Position getManagerPosition() {
        Position position = new Position();
        try (Statement st = connection.createStatement()) {
            try (ResultSet rs = st.executeQuery(selectManagerPosition)) {
                rs.next();
                position.setTitle(rs.getString("title"));
                position.setId(rs.getInt("position_id"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return position;
    }

    public List<Position> getEngineerPositions() {
        List<Position> positions = new ArrayList<>();
        try (Statement sttm = connection.createStatement()) {
            try (ResultSet rs = sttm.executeQuery(selectEngineerPosition)) {
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

    public void removeAll() {
        try (Statement sttm = connection.createStatement()) {
            sttm.execute(removeAll);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void print() {
        System.out.println("POSITIONS TABLE:");
        try (Statement sttm = connection.createStatement()) {
            try (ResultSet rs = sttm.executeQuery(ReadSQLScript.read(pathToPrintQuery))) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                for (int i = 1; i <= columnsNumber; i++) {
                    System.out.print(String.format("|%-20s|", rsmd.getColumnLabel(i)));
                }
                System.out.println();
                while (rs.next()) {
                    for (int i = 1; i <= columnsNumber; i++) {
                        String columnValue = rs.getString(i);
                        System.out.print(String.format("|%-20s|", columnValue));
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

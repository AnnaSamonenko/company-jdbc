package helper;

import dao.PositionDAO;
import data.ConnectionData;
import utils.MySQLDatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GenerateContentOfTables extends ConnectionData {

    static void generatePositionTable() {
        List<String> positions = GenerateDataHelper.generatePosition();

        try (Connection connection = MySQLDatabaseConnection.getConnection(URL, USER, PASSWORD, DATABASE_NAME)) {
            PositionDAO positionDAO = new PositionDAO(connection);
            for (String position : positions)
                positionDAO.create(position);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    static void generateProjectTable() {

    }

    static void generateEmployeeTable() {

    }

}

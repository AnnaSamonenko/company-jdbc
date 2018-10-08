package dao;

import java.sql.Connection;

public class ProjectDAO {

    private Connection connection;

    public ProjectDAO(Connection connection){
        this.connection = connection;
    }


}

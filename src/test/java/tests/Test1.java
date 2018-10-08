package tests;

import helper.GenerateContentOfTables;
import org.junit.Test;

/**
 * Find the PM of the project with the highest count of java developers and print out his/her name and contact info.
 */

public class Test1 {

    @Test
    public void createContentOfTables() {
        // GenerateContentOfTables.generatePositionTable();
        // GenerateContentOfTables.generateProjectTable();
        GenerateContentOfTables.generateEmployeeTable();
    }

//    @After
//    public void cleanDatabases() {
//
//    }

}

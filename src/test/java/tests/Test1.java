package tests;

import helper.GenerateContentOfTables;
import org.junit.Before;

/**
 * Find the PM of the project with the highest count of java developers and print out his/her name and contact info.
 */

public class Test1 {

    @Before
    public void createContentOfTables() {
        GenerateContentOfTables.generatePositionTable();

    }

}

package tests.db;

import org.testng.annotations.Test;
import steps.db.DbSteps;

public class AddNewEntryDbTest extends BaseDbTest {
    @Test
    public void dbTestAddNewEntry() {
        DbSteps.printTestsWhere("1 = 1 LIMIT ?", 10);
    }
}

package tests;

import org.testng.annotations.Test;
import steps.db.DbSteps;

public class DbAddNewEntryTest extends BaseTest {
    @Test
    public void testDbAddNewEntry() {
        DbSteps.printTests(10);
    }
}

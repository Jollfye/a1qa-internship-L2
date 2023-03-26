package tests.db;

import db.DbConnection;
import org.testng.annotations.AfterMethod;

public abstract class BaseDbTest {
    @AfterMethod
    public void tearDown() {
        DbConnection.close();
    }
}

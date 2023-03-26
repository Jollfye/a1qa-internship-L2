package tests;

import db.DbConnection;
import org.testng.annotations.AfterMethod;

public abstract class BaseTest {
    @AfterMethod
    public void tearDown() {
        DbConnection.close();
    }
}

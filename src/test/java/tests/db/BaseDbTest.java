package tests.db;

import aquality.selenium.browser.AqualityServices;
import db.DbConnection;
import models.db.Author;
import models.db.Project;
import models.db.Session;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import steps.db.DbSteps;
import utilities.configuration.TestDataProvider;
import utilities.db.DbTimestampUtils;

public abstract class BaseDbTest {
    @BeforeSuite
    public void setUpSuite() {
        AqualityServices.getLogger().info("Starting suite");
        Project project = Project.builder()
                .name(TestDataProvider.getProjectName())
                .build();
        DbSteps.addProject(project);
        Session session = Session.builder()
                .sessionKey(TestDataProvider.getSessionKey())
                .createdTime(DbTimestampUtils.getCurrentTimestampUpToSeconds())
                .buildNumber(TestDataProvider.getBuildNumber())
                .build();
        DbSteps.addSession(session);
        Author author = Author.builder()
                .name(TestDataProvider.getAuthorName())
                .login(TestDataProvider.getAuthorLogin())
                .email(TestDataProvider.getAuthorEmail())
                .build();
        DbSteps.addAuthor(author);
    }

    @BeforeMethod
    public void setUp() {
        AqualityServices.getLogger().info("Starting test");
    }

    @AfterMethod
    public void tearDown() {
        AqualityServices.getLogger().info("Finishing test");
        DbConnection.close();
    }
}

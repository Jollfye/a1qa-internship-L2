package tests.db;

import models.db.Author;
import models.db.Project;
import models.db.Status;
import models.db.TestModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.db.DbSteps;
import utilities.configuration.TestDataProvider;

import java.util.List;

public class ProcessTestDataTest extends BaseDbTest {
    private List<TestModel> tests;

    @Test
    public void testProcessTestData() {
        List<Status> statuses = DbSteps.getAllStatuses();
        DbSteps.simulateTests(tests, statuses, TestDataProvider.getMaxWaitSeconds());
        DbSteps.editTests(tests);
        DbSteps.verifyTests(tests);
        DbSteps.deleteTests(tests);
        DbSteps.verifyTestsDeleted(tests);
    }

    @BeforeMethod
    public void setUp() {
        super.setUp();
        Project project = DbSteps.getProjectByName(TestDataProvider.getProjectName());
        Author author = Author.builder()
                .name(TestDataProvider.getAuthorName())
                .login(TestDataProvider.getAuthorLogin())
                .email(TestDataProvider.getAuthorEmail())
                .build();
        author = DbSteps.getMatchingAuthor(author);
        tests = DbSteps.copySelectedTestsWithNewCurrentProjectAndAuthor(
                DbSteps.getTestsWhereIDContainsTwoRandomRepeatingDigitsWithLimit(TestDataProvider.getTestsSelectLimit()),
                project, author);
        DbSteps.verifyTests(tests);
    }
}

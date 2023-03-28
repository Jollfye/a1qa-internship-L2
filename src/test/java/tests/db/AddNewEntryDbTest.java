package tests.db;

import enums.db.TestStatus;
import models.db.Author;
import models.db.Project;
import models.db.Session;
import models.db.TestModel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import steps.db.DbSteps;
import tests.api.GetPostsTest;
import utilities.configuration.TestDataProvider;
import utilities.db.DbTimestampUtils;

import java.sql.Timestamp;

public class AddNewEntryDbTest extends BaseDbTest {
    private Timestamp startTimestamp;
    private Timestamp endTimestamp;

    @Test
    public void dbTestAddNewEntry() {
        startTimestamp = DbTimestampUtils.getCurrentTimestampUpToSeconds();
        GetPostsTest.testGetPosts();
        endTimestamp = DbTimestampUtils.getCurrentTimestampUpToSeconds();
    }

    @AfterMethod
    public void tearDown() {
        Project project = DbSteps.getProjectByName(TestDataProvider.getProjectName());
        Session session = DbSteps.getSessionBySessionKey(TestDataProvider.getSessionKey());
        Author author = Author.builder()
                .name(TestDataProvider.getAuthorName())
                .login(TestDataProvider.getAuthorLogin())
                .email(TestDataProvider.getAuthorEmail())
                .build();
        author = DbSteps.getMatchingAuthor(author);
        TestModel test = TestModel.builder()
                .name(GetPostsTest.class.getSimpleName())
                .statusId(DbSteps.getStatusByName(TestStatus.PASSED).getId())
                .methodName(GetPostsTest.class.getMethods()[0].getName())
                .projectId(project.getId())
                .sessionId(session.getId())
                .startTime(startTimestamp)
                .endTime(endTimestamp)
                .env(TestDataProvider.getEnv())
                .browser(TestDataProvider.getBrowser())
                .authorId(author.getId())
                .build();
        DbSteps.addTest(test);
        DbSteps.verifyTest(test);
        super.tearDown();
    }
}

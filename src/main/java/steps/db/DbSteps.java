package steps.db;

import aquality.selenium.browser.AqualityServices;
import constants.db.DbDataFilterConditions;
import enums.db.TestStatus;
import lombok.experimental.UtilityClass;
import models.db.Author;
import models.db.Project;
import models.db.Session;
import models.db.Status;
import models.db.TestModel;
import org.testng.Assert;
import utilities.RandomUtils;
import utilities.db.DbTimestampUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@UtilityClass
public class DbSteps {
    public static List<Status> getAllStatuses() {
        return Status.get(DbDataFilterConditions.NO_FILTER);
    }

    public static Status getStatusByName(TestStatus testStatus) {
        return Status.get(DbDataFilterConditions.NAME, testStatus.toString()).get(0);
    }

    public static List<Project> getProjectsByName(String name) {
        return Project.get(DbDataFilterConditions.NAME, name);
    }

    public static Project getProjectByName(String name) {
        return getProjectsByName(name).get(0);
    }

    public static void addProject(Project project) {
        if (getProjectsByName(project.getName()).isEmpty()) {
            verifyAffectedRowsMoreThanZero(Project.add(project));
        }
    }

    public static List<Session> getSessionsBySessionKey(String sessionKey) {
        return Session.get(DbDataFilterConditions.SESSION_KEY, sessionKey);
    }

    public static Session getSessionBySessionKey(String sessionKey) {
        return getSessionsBySessionKey(sessionKey).get(0);
    }

    public static void addSession(Session session) {
        if (getSessionsBySessionKey(session.getSessionKey()).isEmpty()) {
            verifyAffectedRowsMoreThanZero(Session.add(session));
        }
    }

    public static List<Author> getMatchingAuthors(Author author) {
        return Author.get(DbDataFilterConditions.MATCHING_AUTHOR,
                author.getName(), author.getLogin(), author.getEmail());
    }

    public static Author getMatchingAuthor(Author author) {
        return getMatchingAuthors(author).get(0);
    }

    public static void addAuthor(Author author) {
        if (getMatchingAuthors(author).isEmpty()) {
            verifyAffectedRowsMoreThanZero(Author.add(author));
        }
    }

    public static List<TestModel> getMatchingTestsList(List<TestModel> tests) {
        return tests.stream().map(DbSteps::getMatchingTest).toList();
    }

    public static TestModel getMatchingTest(TestModel test) {
        return getMatchingTests(test).get(0);
    }

    public static List<TestModel> getMatchingTests(TestModel test) {
        return TestModel.get(DbDataFilterConditions.MATCHING_TEST,
                test.getName(), test.getStatusId(), test.getMethodName(), test.getProjectId(),
                test.getSessionId(), test.getStartTime(), test.getEndTime(),
                test.getEnv(), test.getBrowser(), test.getAuthorId());
    }

    public static void addTests(List<TestModel> tests) {
        tests.forEach(DbSteps::addTest);
    }

    public static void addTest(TestModel test) {
        verifyAffectedRowsMoreThanZero(TestModel.add(test));
    }

    public static List<TestModel> getTestsWhereIDContainsTwoRandomRepeatingDigitsWithLimit(int limit) {
        return TestModel.get(DbDataFilterConditions.ID_CONTAINS_TWO_RANDOM_REPEATING_DIGITS_LIMIT, limit);
    }

    public static List<TestModel> copySelectedTestsWithNewCurrentProjectAndAuthor(List<TestModel> tests, Project project, Author author) {
        setTestsProjectAndAuthor(tests, project, author);
        addTests(tests);
        return getMatchingTestsList(tests);
    }

    private static void setTestsProjectAndAuthor(List<TestModel> tests, Project project, Author author) {
        tests.forEach(test -> {
            test.setProjectId(project.getId());
            test.setAuthorId(author.getId());
        });
    }

    public static void simulateTests(List<TestModel> tests, List<Status> statuses, int maxWaitSeconds) {
        AtomicInteger testNumber = new AtomicInteger(1);
        tests.forEach(test -> {
            Status status = statuses.get(RandomUtils.getRandomInt(statuses.size()));
            test.setStatusId(status.getId());
            test.setStartTime(DbTimestampUtils.getCurrentTimestampUpToSeconds());
            int waitSeconds = RandomUtils.getRandomInt(maxWaitSeconds);
            AqualityServices.getLogger().info("Simulating test %d: '%s' for %d seconds to status '%s'",
                    testNumber.getAndIncrement(), test.getName(), waitSeconds, status.getName());
            waitForSeconds(waitSeconds);
            test.setEndTime(DbTimestampUtils.getCurrentTimestampUpToSeconds());
        });
    }

    private static void waitForSeconds(int seconds) {
        try {
            Thread.sleep((long) seconds * 1000);
        } catch (InterruptedException e) {
            throw new IllegalStateException("Thread was interrupted", e);
        }
    }

    public static void editTests(List<TestModel> tests) {
        verifyAffectedRowsMoreThanZero(tests.stream().mapToInt(TestModel::edit).sum());
    }

    public static void deleteTests(List<TestModel> tests) {
        tests.forEach(DbSteps::deleteTest);
    }

    public static void deleteTest(TestModel test) {
        verifyAffectedRowsMoreThanZero(TestModel.delete(test));
    }

    public static void verifyTests(List<TestModel> tests) {
        tests.forEach(DbSteps::verifyTest);
    }

    public static void verifyTest(TestModel test) {
        Assert.assertEquals(getMatchingTest(test), test, "Test is not as expected");
    }

    public static void verifyTestsDeleted(List<TestModel> tests) {
        tests.forEach(DbSteps::verifyTestDeleted);
    }

    public static void verifyTestDeleted(TestModel test) {
        Assert.assertTrue(getMatchingTests(test).isEmpty(), "Test was not deleted");
    }

    private static void verifyAffectedRowsMoreThanZero(int actual) {
        Assert.assertTrue(actual > 0, String.format("Expected at least 1 row to be affected, but %d were affected", actual));
    }
}

package models.db;

import lombok.Builder;
import lombok.Data;
import utilities.db.DbQueryUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class Test {
    private Long id;
    private String name;
    private Integer statusId;
    private String  methodName;
    private Long projectId;
    private Long sessionId;
    private Timestamp startTime;
    private Timestamp endTime;
    private String env;
    private String browser;
    private Long authorId;

    public static List<Test> getTestsWhere(String where, Object... params) {
        String selectWhereSql = "SELECT * FROM test WHERE " + where;
        return DbQueryUtils.executeQuery(Test::mapTest, selectWhereSql, params);
    }

    public static int add(Test test) {
        String insertSql = "INSERT INTO test (name, status_id, method_name, project_id, session_id, start_time, end_time, env, browser, author_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return DbQueryUtils.executeUpdate(insertSql, test.getName(), test.getStatusId(), test.getMethodName(), test.getProjectId(),
                test.getSessionId(), test.getStartTime(), test.getEndTime(),
                test.getEnv(), test.getBrowser(), test.getAuthorId());
    }

    public static int edit(Test test) {
        String updateSql = "UPDATE test SET name = ?, status_id = ?, method_name = ?, project_id = ?, session_id = ?, start_time = ?, end_time = ?, env = ?, browser = ?, author_id = ? WHERE id = ?";
        return DbQueryUtils.executeUpdate(updateSql, test.getName(), test.getStatusId(), test.getMethodName(), test.getProjectId(),
                test.getSessionId(), test.getStartTime(), test.getEndTime(),
                test.getEnv(), test.getBrowser(), test.getAuthorId(),
                test.getId());
    }

    public static int delete(Test test) {
        String deleteSql = "DELETE FROM test WHERE id = ?";
        return DbQueryUtils.executeUpdate(deleteSql, test.getId());
    }

    private static Test mapTest(ResultSet resultSet) {
        try {
            return Test.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .statusId(resultSet.getInt("status_id"))
                    .methodName(resultSet.getString("method_name"))
                    .projectId(resultSet.getLong("project_id"))
                    .sessionId(resultSet.getLong("session_id"))
                    .startTime(resultSet.getTimestamp("start_time"))
                    .endTime(resultSet.getTimestamp("end_time"))
                    .env(resultSet.getString("env"))
                    .browser(resultSet.getString("browser"))
                    .authorId(resultSet.getLong("author_id"))
                    .build();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error mapping test", e);
        }
    }
}

package models.db;

import constants.db.DbTable;
import constants.db.DbTableColumn;
import constants.db.DbTableQuery;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import utilities.db.DbQueryUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class TestModel {
    @EqualsAndHashCode.Exclude
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

    public static List<TestModel> get(String where, Object... params) {
        return DbQueryUtils.executeQuery(TestModel::mapTest, String.format(DbTableQuery.SELECT_WHERE, DbTable.TEST, where), params);
    }

    public static int add(TestModel test) {
        return DbQueryUtils.executeUpdate(DbTableQuery.INSERT_TEST, test.getName(), test.getStatusId(), test.getMethodName(), test.getProjectId(),
                test.getSessionId(), test.getStartTime(), test.getEndTime(),
                test.getEnv(), test.getBrowser(), test.getAuthorId());
    }

    public static int edit(TestModel test) {
        return DbQueryUtils.executeUpdate(DbTableQuery.UPDATE_TEST, test.getName(), test.getStatusId(), test.getMethodName(), test.getProjectId(),
                test.getSessionId(), test.getStartTime(), test.getEndTime(),
                test.getEnv(), test.getBrowser(), test.getAuthorId(),
                test.getId());
    }

    public static int delete(String where, Object... params) {
        return DbQueryUtils.executeUpdate(String.format(DbTableQuery.DELETE_WHERE, DbTable.TEST, where), params);
    }

    private static TestModel mapTest(ResultSet resultSet) {
        try {
            return TestModel.builder()
                    .id(resultSet.getLong(DbTableColumn.ID))
                    .name(resultSet.getString(DbTableColumn.NAME))
                    .statusId(resultSet.getInt(DbTableColumn.STATUS_ID))
                    .methodName(resultSet.getString(DbTableColumn.METHOD_NAME))
                    .projectId(resultSet.getLong(DbTableColumn.PROJECT_ID))
                    .sessionId(resultSet.getLong(DbTableColumn.SESSION_ID))
                    .startTime(resultSet.getTimestamp(DbTableColumn.START_TIME))
                    .endTime(resultSet.getTimestamp(DbTableColumn.END_TIME))
                    .env(resultSet.getString(DbTableColumn.ENV))
                    .browser(resultSet.getString(DbTableColumn.BROWSER))
                    .authorId(resultSet.getLong(DbTableColumn.AUTHOR_ID))
                    .build();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error mapping test", e);
        }
    }
}

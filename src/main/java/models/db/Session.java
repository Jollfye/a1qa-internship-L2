package models.db;

import constants.db.DbTable;
import constants.db.DbTableColumn;
import constants.db.DbTableQuery;
import lombok.Builder;
import lombok.Data;
import utilities.db.DbQueryUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class Session {
    private Long id;
    private String sessionKey;
    private Timestamp createdTime;
    private Long buildNumber;

    public static List<Session> get(String where, Object... params) {
        return DbQueryUtils.executeQuery(Session::mapSession, String.format(DbTableQuery.SELECT_WHERE, DbTable.SESSION, where), params);
    }

    public static int add(Session session) {
        return DbQueryUtils.executeUpdate(DbTableQuery.INSERT_SESSION, session.getSessionKey(), session.getCreatedTime(), session.getBuildNumber());
    }

    private static Session mapSession(ResultSet resultSet) {
        try {
            return Session.builder()
                    .id(resultSet.getLong(DbTableColumn.ID))
                    .sessionKey(resultSet.getString(DbTableColumn.SESSION_KEY))
                    .createdTime(resultSet.getTimestamp(DbTableColumn.CREATED_TIME))
                    .buildNumber(resultSet.getLong(DbTableColumn.BUILD_NUMBER))
                    .build();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error mapping session", e);
        }
    }
}

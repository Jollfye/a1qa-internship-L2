package models.db;

import constants.db.DbTable;
import constants.db.DbTableColumn;
import constants.db.DbTableQuery;
import lombok.Builder;
import lombok.Data;
import utilities.db.DbQueryUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Data
@Builder
public class Status {
    private Integer id;
    private String name;

    public static List<Status> get(String where, Object... params) {
        return DbQueryUtils.executeQuery(Status::mapStatus, String.format(DbTableQuery.SELECT_WHERE, DbTable.STATUS, where), params);
    }

    private static Status mapStatus(ResultSet resultSet) {
        try {
            return Status.builder()
                    .id(resultSet.getInt(DbTableColumn.ID))
                    .name(resultSet.getString(DbTableColumn.NAME))
                    .build();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error mapping status", e);
        }
    }
}

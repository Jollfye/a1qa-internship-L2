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
public class Project {
    private Long id;
    private String name;

    public static List<Project> get(String where, Object... params) {
        return DbQueryUtils.executeQuery(Project::mapProject, String.format(DbTableQuery.SELECT_WHERE, DbTable.PROJECT, where), params);
    }

    public static int add(Project project) {
        return DbQueryUtils.executeUpdate(DbTableQuery.INSERT_PROJECT, project.getName());
    }

    private static Project mapProject(ResultSet resultSet) {
        try {
            return Project.builder()
                    .id(resultSet.getLong(DbTableColumn.ID))
                    .name(resultSet.getString(DbTableColumn.NAME))
                    .build();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error mapping project", e);
        }
    }
}

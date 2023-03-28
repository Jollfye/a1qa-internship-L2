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
public class Author {
    private Long id;
    private String name;
    private String login;
    private String email;

    public static List<Author> get(String where, Object... params) {
        return DbQueryUtils.executeQuery(Author::mapAuthor, String.format(DbTableQuery.SELECT_WHERE, DbTable.AUTHOR, where), params);
    }

    public static int add(Author author) {
        return DbQueryUtils.executeUpdate(DbTableQuery.INSERT_AUTHOR, author.getName(), author.getLogin(), author.getEmail());
    }

    private static Author mapAuthor(ResultSet resultSet) {
        try {
            return Author.builder()
                    .id(resultSet.getLong(DbTableColumn.ID))
                    .name(resultSet.getString(DbTableColumn.NAME))
                    .login(resultSet.getString(DbTableColumn.LOGIN))
                    .email(resultSet.getString(DbTableColumn.EMAIL))
                    .build();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error mapping author", e);
        }
    }
}

package utilities.db;

import aquality.selenium.browser.AqualityServices;
import db.DbConnection;
import lombok.experimental.UtilityClass;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

@UtilityClass
public class DbQueryUtils {
    public static <T> List<T> executeQuery(Function<ResultSet, T> rowMapper, String query, Object... params) {
        try (PreparedStatement preparedStatement = DbConnection.get().prepareStatement(query)) {
            addParams(preparedStatement, params);
            AqualityServices.getLogger().info("Executing query '%1$s'",
                    preparedStatement.toString().split(": ", 2)[1]);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return mapResultSet(resultSet, rowMapper);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(
                    String.format("Error executing query '%1$s'", query), e);
        }
    }

    public static int executeUpdate(String query, Object... params) {
        try (PreparedStatement preparedStatement = DbConnection.get().prepareStatement(query)) {
            addParams(preparedStatement, params);
            AqualityServices.getLogger().info("Executing update '%1$s'",
                    preparedStatement.toString().split(": ", 2)[1]);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(
                    String.format("Error executing update '%1$s'", query), e);
        }
    }

    private static void addParams(PreparedStatement preparedStatement, Object[] params) {
        AqualityServices.getLogger().debug("Adding params to prepared statement");
        IntStream.range(0, params.length)
                .forEach(i -> {
                    try {
                        preparedStatement.setObject(i + 1, params[i]);
                    } catch (SQLException e) {
                        throw new IllegalArgumentException(
                                String.format("Error setting parameter with index '%1$s' and value '%2$s'",
                                        i + 1, params[i]), e);
                    }
                });
    }

    private static <T> List<T> mapResultSet(ResultSet resultSet, Function<ResultSet, T> rowMapper) {
        AqualityServices.getLogger().debug("Mapping result set");
        try {
            List<T> results = new ArrayList<>();
            while (resultSet.next()) {
                results.add(rowMapper.apply(resultSet));
            }
            return results;
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error mapping result set", e);
        }
    }
}

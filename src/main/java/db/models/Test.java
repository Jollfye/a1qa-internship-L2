package db.models;

import db.DbConnection;
import lombok.Builder;
import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
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

    public static List<Test> get(int limit) {
        List<Test> tests = new ArrayList<>();
        try (Statement stmt = DbConnection.get().createStatement()) {
            String selectSql = "SELECT * FROM test LIMIT " + limit;
            try (ResultSet resultSet = stmt.executeQuery(selectSql)) {
                while (resultSet.next()) {
                    tests.add(Test.builder()
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
                            .build());
                }
            } catch (SQLException e) {
                throw new IllegalArgumentException(
                        String.format("Could not execute query %1$s", selectSql), e);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Could not create statement", e);
        }
        return tests;
    }

    public static void add(String name) {
        try (Statement stmt = DbConnection.get().createStatement()) {
            stmt.executeUpdate("INSERT INTO test (name) VALUES ('" + name + "')");
        } catch (SQLException e) {
            throw new IllegalStateException("Could not create statement", e);
        }
    }

    public static void edit(Long id, String name) {
        try (Statement stmt = DbConnection.get().createStatement()) {
            stmt.executeUpdate("UPDATE test SET name = '" + name + "' WHERE id = '" + id + "'");
        } catch (SQLException e) {
            throw new IllegalStateException("Could not create statement", e);
        }
    }

    public static void delete(Long id) {
        try (Statement stmt = DbConnection.get().createStatement()) {
            stmt.executeUpdate("DELETE FROM test WHERE id = '" + id + "'");
        } catch (SQLException e) {
            throw new IllegalStateException("Could not create statement", e);
        }
    }
}

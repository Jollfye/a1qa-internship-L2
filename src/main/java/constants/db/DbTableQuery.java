package constants.db;

public class DbTableQuery {
    public static final String SELECT_WHERE = "SELECT * FROM %s WHERE %s";
    public static final String DELETE_WHERE = "DELETE FROM %s WHERE %s";
    public static final String INSERT_TEST = "INSERT INTO test (name, status_id, method_name, project_id, session_id, start_time, end_time, env, browser, author_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_TEST = "UPDATE test SET name = ?, status_id = ?, method_name = ?, project_id = ?, session_id = ?, start_time = ?, end_time = ?, env = ?, browser = ?, author_id = ? WHERE id = ?";
    public static final String INSERT_PROJECT = "INSERT INTO project (name) VALUES (?)";
    public static final String INSERT_SESSION = "INSERT INTO session (session_key, created_time, build_number) VALUES (?, ?, ?)";
    public static final String UPDATE_SESSION = "UPDATE session SET session_key = ?, created_time = ?, build_number = ? WHERE id = ?";
    public static final String INSERT_AUTHOR = "INSERT INTO author (name, login, email) VALUES (?, ?, ?)";
}

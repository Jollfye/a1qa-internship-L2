package constants.db;

public class DbDataFilterConditions {
    public static final String NO_FILTER = "1 = 1";
    public static final String NAME = "name = ?";
    public static final String SESSION_KEY = "session_key = ?";
    public static final String MATCHING_AUTHOR = "name = ? AND login = ? AND email = ?";
    public static final String MATCHING_TEST = "name = ? AND status_id = ? AND method_name = ? AND project_id = ? AND session_id = ? AND start_time = ? AND end_time = ? AND env = ? AND browser = ? AND author_id = ?";
    public static final String ID_CONTAINS_TWO_RANDOM_REPEATING_DIGITS_LIMIT = "id REGEXP '.*(.)\\1.*' LIMIT ?";
}

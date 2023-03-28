package utilities.db;

import lombok.experimental.UtilityClass;

import java.sql.Timestamp;

@UtilityClass
public class DbTimestampUtils {
    public static Timestamp getCurrentTimestampUpToSeconds() {
        return new Timestamp(System.currentTimeMillis() / 1000 * 1000);
    }
}

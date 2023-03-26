package steps.db;

import models.db.Test;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class DbSteps {
    public static void printTestsWhere(String where, Object... params) {
        List<Test> tests = Test.getTestsWhere(where, params);
        tests.forEach(System.out::println);
    }
}

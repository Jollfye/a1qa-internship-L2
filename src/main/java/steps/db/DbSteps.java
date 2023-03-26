package steps.db;

import db.models.Test;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class DbSteps {
    public static void printTests(int limit) {
        List<Test> tests = Test.get(limit);
        tests.forEach(System.out::println);
    }
}

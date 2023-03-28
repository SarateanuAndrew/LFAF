package grammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GrammarGenerator {
    private static final String[] VN = {"S", "A", "B"};
    private static final String[] VT = {"grammar", "b", "c"};
    private static final String[][] P = {
            {"S", "aA"},
            {"A", "bS"},
            {"S", "bB"},
            {"A", "cA"},
            {"A", "aB"},
            {"B", "aB"},
            {"B", "b"}
    };

    private static final Random random = new Random();

    public static List<String> generateValidStrings(int count) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String string = generateValidString();
            result.add(string);
        }

        return result;
    }

    private static String generateValidString() {
        StringBuilder builder = new StringBuilder();
        builder.append("S");
        while (true) {
            int index = builder.indexOf("S");
            if (index < 0) {
                break;
            }
            builder.deleteCharAt(index);
            builder.insert(index, P[0][1]);
        }
        while (true) {
            int index = builder.indexOf("A");
            if (index < 0) {
                break;
            }
            String replacement = P[random.nextInt(4) + 1][1];
            builder.deleteCharAt(index);
            builder.insert(index, replacement);
        }
        while (true) {
            int index = builder.indexOf("B");
            if (index < 0) {
                break;
            }
            String replacement = P[random.nextInt(2) + 5][1];
            builder.deleteCharAt(index);
            builder.insert(index, replacement);
        }
        return builder.toString();
    }
}

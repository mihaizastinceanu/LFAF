import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<String> regexes = Arrays.asList(
                "(a|b)(c|d)E+G?",
                "P(Q|R|S)T(UV|W|X)*Z+",
                "1(0|1)*2((3|4){5})36"
        );

        RegexGenerator generator = new RegexGenerator();

        for (String regex : regexes) {

            System.out.println("====================================");
            System.out.println("Regex: " + regex);
            System.out.println("====================================");

            for (int i = 0; i < 10; i++) {
                String result = generator.generate(regex);
                System.out.println(result);
            }

            System.out.println();
        }
    }
}
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {

    private static String INPUT_FILE_1 = "input_1.txt";
    private static String INPUT_FILE_2 = "input_2.txt";
    private static String INPUT_FILE_3 = "input_3.txt";
    private static String OUTPUT_FILE_1 = "output_1.txt";
    private static String OUTPUT_FILE_2 = "output_2.txt";
    private static String OUTPUT_FILE_3 = "output_3.txt";

    private static FileIO file = new FileIO();

    public static void main(String[] args) {

        Calc simple = new SimpleCalc();
        Calc advanced = new AdvancedCalc();

        compute(simple, INPUT_FILE_1, OUTPUT_FILE_1);
        compute(advanced, INPUT_FILE_2, OUTPUT_FILE_2);
        compute(advanced, INPUT_FILE_3, OUTPUT_FILE_3);
    }

    private static void compute(Calc calc, String inputFile2, String outputFile2) {
        try {

            List<String> outStrings = new ArrayList<>();
            List<String> inStrings = file.read(inputFile2);

            for (String s : inStrings) {
                try {
                    outStrings.add(s + " = " + calc.compute(s));

                } catch (IllegalStateException e) {
                    outStrings.add(s + " = " + e.getMessage());
                }
            }
            file.save(outputFile2, outStrings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

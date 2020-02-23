import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {

    public static final Converter converter = new ConsecutiveRadix();

    public static final Converter sexagesimalConverter = new SexagesimalAdapter(new SexagesimalConverter());

    public static final long MAX_VALUE = 20000L;

    public static void main(String[] args) {
        var list = new ArrayList<Long>();

        for (var counter = 0L; counter <= MAX_VALUE; counter++) {
            list.add(counter);
        }

        try (var writer = new PrintWriter(new BufferedWriter(new FileWriter("output.csv")))) {
            for (var value : list) {
                for (var counter = Converter.MINIMUM_RADIX; counter <= Converter.MAXIMUM_CONSECUTIVE_RADIX + 1; counter++) {
                    writer.printf("%d,%d,%s%n",
                            value, counter, counter > Converter.MAXIMUM_CONSECUTIVE_RADIX ?
                                    sexagesimalConverter.decimalToAlternateRadix(value, counter) :
                                    converter.decimalToAlternateRadix(value, counter));
                }
            }
        } catch (IOException iOEx) {
            System.err.println(iOEx.getMessage());
        }
    }
}
/**
 * An interface for conversions between radices.
 *
 * Due to a lack of logical consecutive glyphs the max radix is 36
 * and the minimum radix is 2.
 *
 * The special instance is Radix 60 (Sexagesimal) which does not use a single
 * glyph for each position.
 *
 * @author Shane Carroll May
 */
public interface Converter {

    /**
     * The possible glyphs in order of position for each consecutive radix.
     */
    String GLYPHS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * The minimum radix.
     */
    int MINIMUM_RADIX = 2;

    /**
     * The maximum radix.
     */
    int MAXIMUM_CONSECUTIVE_RADIX = 36;

    /**
     * Base 60 number.
     */
    int SEXAGESIMAL_RADIX = 60;

    /**
     * The delimiter used to distinguish sexagesimal "glyphs".
     */
    char SEXAGESIMAL_DELIMITER = ';';

    /**
     * Converts the given base 10 number into the given radix.
     *
     * @param value the number to convert.
     *
     * @param radix the final radix.
     *
     * @return the equivalent number in the given radix.
     *
     * @throws IllegalArgumentException if the given radix is invalid.
     */
    String decimalToAlternateRadix(long value, int radix);

    /**
     * Converts the given number and radix to its equivalent (radix 10 Decimal) number.
     *
     * @param value the number to convert.
     *
     * @param radix the current number's radix.
     *
     * @return the decimal (radix 10) equivalent value.
     *
     * @throws IllegalArgumentException if the given radix is invalid or the value is empty.
     *
     * @throws NullPointerException if value is <code>null</code>.
     *
     * @throws ConversionException if the value contains invalid glyphs as defined by
     * the given radix (e.g. if the radix is 10, but the value is 13A)
     */
    long alternateRadixToDecimal(String value, int radix);

    /**
     * Converts a number from its current radix to the given radix.
     *
     * @param value the number to convert.
     *
     * @param currentRadix the given number's current radix.
     *
     * @param targetRadix the desired radix.
     *
     * @return the equivalent number in the desired radix.
     *
     * @throws IllegalArgumentException if the given currentRadix or targetRadix is invalid.
     *
     * @throws NullPointerException if value is <code>null</code>.
     *
     * @throws ConversionException if the value contains invalid glyphs as defined by
     * the currentRadix (e.g. if the radix is 10, but the value is 13A)
     */
    String radixConversion(String value, int currentRadix, int targetRadix);
}
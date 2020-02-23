import java.util.Objects;

/**
 * This class contains methods for converting between bases, from
 * base (Radix 2) through base (Radix 36). See {@link Converter#MINIMUM_RADIX} and
 * {@link Converter#MAXIMUM_CONSECUTIVE_RADIX}
 *
 * This choice is due to the lack of logical consecutive glyphs for number of a base higher than 36.
 *
 * @author Shane Carroll May
 */
public final class ConsecutiveRadix
        implements Converter {
    /**
     * {@inheritDoc}
     */
    @Override
    public String decimalToAlternateRadix(long value, int radix) {
        if (value < 0 || radix < MINIMUM_RADIX || radix > MAXIMUM_CONSECUTIVE_RADIX) {
            return "";
        } // else value and radix are within range. doNothing();

        var number = new StringBuilder();
        while (value != 0) {
            var radixDigit = GLYPHS.charAt((int) (value % radix));
            number.insert(0, radixDigit);
            value /= radix;
        }
        return number.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long alternateRadixToDecimal(String value, int radix) {
        Objects.requireNonNull(value);

        if (radix < MINIMUM_RADIX || radix > MAXIMUM_CONSECUTIVE_RADIX) {
            throw new IllegalArgumentException("Radix out of range");
        } // else valid radix doNothing();

        if (value.isEmpty()) {
            throw new IllegalArgumentException("The given value cannot be empty");
        } // else, we have something (may not be valid, but it is something), doNothing();

        var number = 0L;
        var exponent = 0;
        // So we do not have to worry about lower / upper case values.
        value = value.toUpperCase();
        for (var index = value.length() - 1; index >= 0; index--) {
            var digitValue = GLYPHS.indexOf(value.charAt(index));
            if (digitValue >= 0) {
                // Because Math.pow only returns a double, we get the double value
                // then truncate it - we are not rounding here.
                var temp = (long) (Math.pow(radix, exponent++) * digitValue);
                number += temp;
            } else { // something in the string number that is not a valid glyph.
                throw new ConversionException(String.format("%s is invalid for radix %d", value, radix));
            }
        }
        return number;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String radixConversion(String value, int currentRadix, int targetRadix) {
        var decimalNumber = this.alternateRadixToDecimal(value, currentRadix);
        return this.decimalToAlternateRadix(decimalNumber, targetRadix);
    }
}
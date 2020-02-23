/**
 * A class which converts from Radix 10 Decimal to Radix 60 Sexagesimal.
 * The notation used to describe Base 60 numbers is based (pun intended) on
 * the work of Asger Hartvig Aaboe - a mathematician who's research and studies
 * was on understanding how the Babylonians conceived their computational schemes which were
 * in Base 60.
 *
 * Each Base 60 "glyph" which may be a number from 1 to 59 is separated by a ,
 * For example 1,0 in Base 60 => 60 in Base 10.
 *
 */
public final class SexagesimalConverter {
    /**
     * Converts a number from Radix 10 (Decimal) to Radix 60.
     * If the number provided is negative, an Empty String is returned.
     *
     * @param value the number to convert in Base 10
     *
     * @return the equivalent number in Base 60.
     */
    public String decimalToSexagesimal(long value) {
        if (value < 0) {
            return "";
        } // else value and radix are within range. doNothing();

        StringBuilder number = new StringBuilder();
        while (value != 0) {
            var digit = value % Converter.SEXAGESIMAL_RADIX;
            number.insert(0, Converter.SEXAGESIMAL_DELIMITER).insert(1, digit);
            value /= Converter.SEXAGESIMAL_RADIX;
        }
        // remove the starting Delimiter from the string.
        number.deleteCharAt(0);
        return number.toString();
    }

    /**
     * Converts a Base 60 Sexagesimal value to an equivalent decimal Base 10 number.
     * NOTE: It is possible given the high radix of sexagesimal numbers that the result
     * could overflow a long value.
     *
     * The value must be in the format of number,number,number,etc...
     * commas separate each sexagesimal "glyph" with each "glyph" being a positive
     * integer value from 0 to 59.
     *
     * @param value the sexagesimal number to convert
     *
     * @return the decimal base 10 equivalent
     */
    public long sexagesimalToDecimal(String value) {
        if (value == null || value.isEmpty()) {
            return 0L;
        } // else valid value DoNothing();

        var number = 0L;
        var exponent = 0;
        var digits = value.split(String.valueOf(Converter.SEXAGESIMAL_DELIMITER));
        try {
            for (var index = digits.length - 1; index >= 0; index--) {
                var currentDigit = Long.parseLong(digits[index]);

                if (currentDigit < 0 || currentDigit > Converter.SEXAGESIMAL_RADIX) {
                    return 0L;
                } // else validDigit so move on.

                number += currentDigit *
                        (long)(Math.pow(Converter.SEXAGESIMAL_RADIX, exponent++));
            }
        }
        catch (NumberFormatException ex) { // for some reason one of the "glyphs" did not parse to an integer value.
            // nothing we can do at this point. Just return zero (0)
            return 0L;
        }
        return number;
    }
}
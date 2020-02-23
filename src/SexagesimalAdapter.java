import java.util.Objects;

/**
 * This class models the adapter design pattern and allows us to make the existing {@link ConsecutiveRadix} and
 * {@link Converter} interface work with converting Radix 60 (Sexagesimal) numbers,
 * without changing the code of the existing class.
 *
 * This way we can use the methods of an existing class to work with other existing libraries.
 *
 * The end result is that the SexagesimalAdapter can have the methods of the existing class
 * (since the Adapter inherits from the existing class) and can work in the existing library
 * (since the Adapter implements the interface of the existing library).
 *
 * @author Shane Carroll May 2017
 */
public final class SexagesimalAdapter implements Converter {
    /**
     * A converter to convert to and from Radix 60 numbers.
     */
    private SexagesimalConverter converter;

    /**
     * Constructs a new SexagesimalAdapter.
     *
     * @param converter the convert to adapt to a {@link Converter}
     */
    public SexagesimalAdapter(SexagesimalConverter converter) {
        Objects.requireNonNull(converter);
        this.converter = converter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String decimalToAlternateRadix(long value, int radix) {
        return this.converter.decimalToSexagesimal(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long alternateRadixToDecimal(String value, int radix) {
        return this.converter.sexagesimalToDecimal(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String radixConversion(String value, int currentRadix, int targetRadix) {
        var decimalNumber =
                currentRadix == Converter.SEXAGESIMAL_RADIX ?
                this.converter.sexagesimalToDecimal(value) :
                new ConsecutiveRadix().alternateRadixToDecimal(value, currentRadix);

        return targetRadix == Converter.SEXAGESIMAL_RADIX ?
               this.converter.decimalToSexagesimal(decimalNumber) :
               new ConsecutiveRadix().decimalToAlternateRadix(decimalNumber, targetRadix);
    }
}
/**
 * Thrown when an exception occurs when converting between number systems.
 * For example, a base 10 number with an invalid glyph (e.g. 13A) would cause
 * an instance of this class to be thrown.
 *
 * @author Shane Carroll May
 */
public class ConversionException
    extends RuntimeException {

    /**
     * Constructs an instance of this exception with the given message.
     *
     * @param message a message with the exception details.
     */
    public ConversionException(String message) {
        super(message);
    }
}
import org.example.StringCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    private StringCalculator calc;

    @BeforeEach
    void setUp() {
        calc = new StringCalculator();
    }

    @Test
    @DisplayName("Empty string returns zero")
    void emptyStringReturnsZero() {
        int actual = calc.add("");
        int expected = 0;

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Single number returns number")
    void oneNumberReturnsSum() {
        int actual = calc.add("5");
        int expected = 5;

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Two numbers returns sum")
    void twoNumbersReturnSum() {
        int actual = calc.add("5,3");
        int expected = 8;

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Two numbers with a newLine Character")
    void twoNumbersWithNewLineCharacterReturnSum() {
        int actual = calc.add("5\n3");
        int expected = 8;

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Multiple Numbers separated by newLine Characters and Numbers")
    void multipleNumbersSumWithNewLineAndCommas() {
        int actual = calc.add("1\n2,3");
        int expected = 6;

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Sum of Multiple Numbers with Different Delimiters")
    void multipleNumbersSumWithDifferentDelimiters() {
        int actual = calc.add("//;\n1;2");
        int expected = 3;

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Throw Exception for Negative Numbers")
    void throwExceptionIfNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> calc.add("5,-3"));
    }

    @Test
    @DisplayName("Don't add numbers higher than 1000")
    void sumOfNumbersLessThanThousand() {
        int actual = calc.add("2,1001");
        int expected = 2;

        assertEquals(expected, actual);
    }

}
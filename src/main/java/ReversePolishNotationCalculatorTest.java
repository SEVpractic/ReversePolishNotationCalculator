import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReversePolishNotationCalculatorTest {

    @Test
    public void shouldCalculateAddition() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();

        assertEquals(10, calculator.calculatePolishNotation("5 5 +"));
    }

    @Test
    public void shouldCalculateSubtraction() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();

        assertEquals(0, calculator.calculatePolishNotation("5 5 -"));
    }

    @Test
    public void shouldCalculateMultiplication() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();

        assertEquals(25, calculator.calculatePolishNotation("5 5 *"));
    }

    @Test
    public void shouldCalculateAdditionWithANegativeNumber() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();

        assertEquals(0, calculator.calculatePolishNotation("5 -5 +"));
    }

    @Test
    public void shouldCalculateSubtractionWithANegativeNumber() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();

        assertEquals(10, calculator.calculatePolishNotation("5 -5 -"));
    }

    @Test
    public void shouldCalculateMultiplicationWithANegativeNumber() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();

        assertEquals(-25, calculator.calculatePolishNotation("5 -5 *"));
    }

    @Test
    public void shouldCalculateAdditionSubtractionAndMultiplication() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();

        assertEquals(25, calculator.calculatePolishNotation("5 5 5 5 - + *"));
    }

    @Test
    public void shouldCalculateAdditionWithDifferentSpaces() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();

        assertEquals(20, calculator.calculatePolishNotation("5    5  5   5   + +  +   "));
    }
}

class ReversePolishNotationCalculator {

    public int calculatePolishNotation(String str) {
        String[] parts = str.split(" ");
        Deque<Integer> numbers = new ArrayDeque<>();
        int index = 0;

        while (index != parts.length) {

            if (parts[index].isBlank()) {
                index++;
                continue;
            }

            if (isOperation(parts[index])) {
                int operandOne = numbers.pop();
                int operandTwo = numbers.pop();

                if (parts[index].equals("+")) {
                    numbers.push(operandOne + operandTwo);
                } else if (parts[index].equals("-")) {
                    numbers.push(operandTwo - operandOne);
                } else if (parts[index].equals("*")) {
                    numbers.push(operandOne * operandTwo);
                }

            } else {
                numbers.push(Integer.parseInt(parts[index]));
            }

            index++;
        }

        return numbers.pop();
    }

    private boolean isOperation(String part) {
        if (part.equals("+")
                || part.equals("-")
                || part.equals("*")) {
            return true;
        }

        return false;
    }
}

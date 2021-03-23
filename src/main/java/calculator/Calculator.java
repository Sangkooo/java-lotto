package calculator;

import java.util.Arrays;

public class Calculator {
    private static final String EMPTY = "";
    private static final int ZERO = 0;
    private static Calculator calculator;

    private Calculator() {

    }

    public static Calculator getInstance() {
        if (calculator == null) {
            calculator = new Calculator();
        }
        return calculator;
    }

    public int calculate(String input) {
        if (EMPTY.equals(input) || input == null) {
            return ZERO;
        }
        return Arrays.stream(new StringAdditionExpression(input)
                .array())
                .mapToInt(this::toNumber)
                .peek(this::assurePositive)
                .sum();
    }

    public int toNumber(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new RuntimeException("숫자 이외의 값을 입력할 수 없습니다.");
        }
    }

    public void assurePositive(int number) {
        if (number < 0) {
            throw new RuntimeException("음수를 입력할 수 없습니다.");
        }
    }

}

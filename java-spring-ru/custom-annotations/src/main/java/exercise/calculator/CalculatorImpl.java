package exercise.calculator;

import exercise.Inspect;
import org.springframework.stereotype.Component;

@Component
@Inspect(level = "debug")
public class CalculatorImpl implements Calculator {
    public int sum(int a, int b) {
        return a + b;
    }

    public int mult(int a, int b) {
        return a * b;
    }
}

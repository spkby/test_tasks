import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdvancedCalc implements Calc {

    private final String OPERATORS = "+-/*()^√";
    private final String NUMBERS = "0123456789.";
    private final String FUNCTIONS = "sincostg";

    private final String DIVISION_BY_ZERO = "Division by zero";
    private final String ROOT_NEGATIVE = "Root is negative";

    private enum State {
        NUMBER, OPERATOR, FUNCTION, START
    }

    // Reverse Polish notation
    public String compute(String line) {
        return calc(parse(line));
    }

    private String calc(String postfix) {
        Stack<Double> stack = new Stack<>();

        for (String token : postfix.split("\\s+")) {
            switch (token) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    stack.push(-stack.pop() + stack.pop());
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    double divisor = stack.pop();
                    if (divisor == 0.0) {
                        return DIVISION_BY_ZERO;
                    }
                    stack.push(stack.pop() / divisor);
                    break;
                case "^":
                    double exponent = stack.pop();
                    stack.push(Math.pow(stack.pop(), exponent));
                    break;
                case "√":
                    double root = stack.pop();
                    if (root < 0) {
                        return ROOT_NEGATIVE;
                    }
                    stack.push(Math.sqrt(root));
                    break;
                case "sin":
                    stack.push(Math.sin(stack.pop()));
                    break;
                case "cos":
                    stack.push(Math.cos(stack.pop()));
                    break;
                case "tg":
                    stack.push(Math.tan(stack.pop()));
                    break;
                default:
                    stack.push(Double.parseDouble(token));
                    break;
            }
        }
        return String.valueOf(new BigDecimal(stack.pop()).setScale(5, RoundingMode.HALF_UP).floatValue());
    }

    // infix to postfix
    private String parse(String infix) {

        if (!checkIllegalParentheses(infix)) {
            throw new IllegalStateException("Not equal count of opening and closing parentheses");
        }
        infix = tuning(infix);
        Stack<String> stack = new Stack<>();

        StringBuilder num = new StringBuilder();
        StringBuilder func = new StringBuilder();
        StringBuilder output = new StringBuilder();
        State state = State.START;

        for (int i = 0; i < infix.length(); i++) {

            String sym = infix.substring(i, i + 1);

            // check for stack
            if (OPERATORS.contains(sym)) {

                switch (state) {

                    // if state NUMBER - add to output
                    case NUMBER:
                        output.append(num).append(" ");
                        num = new StringBuilder();
                        break;

                    // if state FUNCTION - push stack
                    case FUNCTION:
                        stack.push(func.toString());
                        func = new StringBuilder();
                        break;
                }

                state = State.OPERATOR;

                if (sym.equals("(")) {
                    stack.push(sym);
                    continue;
                }

                // check for ")"
                if (sym.equals(")")) {
                    while (!stack.empty()) {
                        if (stack.peek().equals("(")) {
                            stack.pop();
                            break;
                        } else {
                            output.append(stack.pop()).append(" ");
                        }
                    }
                    continue;
                }

                // compare
                while (!stack.empty() && checkToPop(sym, stack.peek())) {
                    output.append(stack.pop()).append(" ");
                }

                stack.push(sym);

            } else if (NUMBERS.contains(sym)) {
                state = State.NUMBER;
                num.append(sym);

            } else if (FUNCTIONS.contains(sym)) {
                state = State.FUNCTION;
                func.append(sym);
            } else {
                throw new IllegalStateException("Illegal symbol");
            }

        }
        if (num.length() > 0) output.append(num).append(" ");
        while (!stack.empty()) {
            output.append(stack.pop()).append(" ");
        }
        return output.toString().trim();
    }

    // remove illegal
    private String tuning(String line) {

        line = line.replace(" ", "").replace("(-", "(0-")
                .replace("–", "-").replace(",", ".");
        if (line.charAt(0) == '-') {
            line = "0" + line;
        }
        return line;
    }

    // get priority operation
    private byte getPriority(String sym) {
        switch (sym) {
            case "(":
            case ")":
                return 0;
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            case "√":
            case "sin":
            case "cos":
            case "tg":
                return 4;
            default:
                return 5;
        }
    }

    // check
    private boolean checkToPop(String sym, String stack) {
        if (getPriority(stack) > getPriority(sym)) {
            return true;
        } else if (getPriority(stack) == getPriority(sym)) {
            return !sym.equals("^");
        }
        return false;
    }

    private boolean checkIllegalParentheses(String line) {

        Pattern pattern = Pattern.compile("\\(");
        Matcher matcher = pattern.matcher(line);

        int parentheses = 0;
        while (matcher.find())
            parentheses++;

        pattern = Pattern.compile("\\)");
        matcher = pattern.matcher(line);
        while (matcher.find())
            parentheses--;

        return parentheses == 0;
    }
}
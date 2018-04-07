import java.math.BigDecimal;
import java.math.RoundingMode;

public class SimpleCalc implements Calc{

    private double num1;
    private double num2;
    private Ops operation;

    private final String DIVISION_BY_ZERO = "Division by zero";

    private enum Ops {
        ADD,
        SUB,
        MULT,
        DIV,
        POW,
        PERCENTS
    }

    private enum State {
        NUM1, NUM2
    }

    public String compute(String str) {
        parse(str);

        double res = 0;
        switch (operation) {
            case ADD:
                res = num1 + num2;
                break;
            case SUB:
                res = num1 - num2;
                break;
            case MULT:
                res = num1 * num2;
                break;
            case DIV:
                if (num2 == 0.0) {
                    return DIVISION_BY_ZERO;
                } else res = num1 / num2;
                break;
            case POW:
                res = Math.pow(num1,num2);
                break;
            case PERCENTS:
                res = num1 * num2 / 100;
                break;
        }
        return String.valueOf(new BigDecimal(res).setScale(5, RoundingMode.HALF_UP).floatValue());
    }

    private void parse(String line) {

        StringBuilder num1 = new StringBuilder();
        StringBuilder num2 = new StringBuilder();

        State state = State.NUM1;
        Ops op = null;

        for (int i = 0; i < line.length(); i++) {
            String tmp = line.substring(i, i + 1);
            if (state == State.NUM1) {
                switch (tmp) {
                    case "+":
                        op = Ops.ADD;
                        state = State.NUM2;
                        break;
                    case "-":
                        op = Ops.SUB;
                        state = State.NUM2;
                        break;
                    case "*":
                        op = Ops.MULT;
                        state = State.NUM2;
                        break;
                    case "/":
                        op = Ops.DIV;
                        state = State.NUM2;
                        break;
                    case "^":
                        op = Ops.POW;
                        state = State.NUM2;
                        break;
                    case "%":
                        op = Ops.PERCENTS;
                        state = State.NUM2;
                        break;
                    default:
                        num1.append(tmp);
                }
            } else {
                num2.append(tmp);
            }
        }
        this.num1 = Double.parseDouble(num1.toString());
        this.num2 = Double.parseDouble(num2.toString());
        this.operation = op;
    }

}

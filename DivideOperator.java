public class DivideOperator extends Operator {
    public int priority() { return 2; }

    public Operand execute(Operand op1, Operand op2) {
        if (op2.getValue() == 0) throw new ArithmeticException("Division by zero");
        return new Operand(op1.getValue() / op2.getValue());
    }
}

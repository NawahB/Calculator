public class AddOperator extends Operator {
    public int priority() { return 1; }

    public Operand execute(Operand op1, Operand op2) {
        return new Operand(op1.getValue() + op2.getValue());
    }
}


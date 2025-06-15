public abstract class Operator {
    public abstract int priority();
    public abstract Operand execute(Operand op1, Operand op2);
}

public class Operand {
    private int value;

    public Operand(int value) {
        this.value = value;
    }

    public Operand(String token) {
        this.value = Integer.parseInt(token);
    }

    public int getValue() {
        return value;
    }
}

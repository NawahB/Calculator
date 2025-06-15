import java.util.*;

public class Evaluator {
    private Map<String, Operator> operators = new HashMap<>();

    public Evaluator() {
        operators.put("+", new AddOperator());
        operators.put("-", new SubtractOperator());
        operators.put("*", new MultiplyOperator());
        operators.put("/", new DivideOperator());
        operators.put("^", new PowerOperator());
    }

    public int evaluateExpression(String expression) {
        Stack<Operand> operandStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();
        StringTokenizer tokens = new StringTokenizer(expression, "()+-*/^ ", true);

        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken().trim();
            if (token.isEmpty()) continue;

            if (isNumber(token)) {
                operandStack.push(new Operand(token));
            } else if (operators.containsKey(token)) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(") &&
                       operators.get(token).priority() <= operators.get(operatorStack.peek()).priority()) {
                    processOperator(operandStack, operatorStack.pop());
                }
                operatorStack.push(token);
            } else if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorStack.peek().equals("(")) {
                    processOperator(operandStack, operatorStack.pop());
                }
                operatorStack.pop(); // remove '('
            }
        }

        while (!operatorStack.isEmpty()) {
            processOperator(operandStack, operatorStack.pop());
        }

        return operandStack.pop().getValue();
    }

    private void processOperator(Stack<Operand> operandStack, String opToken) {
        Operand right = operandStack.pop();
        Operand left = operandStack.pop();
        Operand result = operators.get(opToken).execute(left, right);
        operandStack.push(result);
    }

    private boolean isNumber(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

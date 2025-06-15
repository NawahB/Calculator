import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EvaluatorUI extends JFrame implements ActionListener {
    private JTextField inputField;
    private Evaluator evaluator;

    public EvaluatorUI() {
        evaluator = new Evaluator();

        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.PINK);
        setLayout(new BorderLayout());

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.BOLD, 28));
        inputField.setBackground(Color.PINK);
        inputField.setForeground(Color.BLACK);
        inputField.setHorizontalAlignment(JTextField.RIGHT);
        inputField.setEditable(false);
        add(inputField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4));
        buttonPanel.setBackground(Color.PINK);

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "(", ")", "+",
            "C", "^", "=", ""
        };

        for (String text : buttons) {
            if (text.isEmpty()) {
                buttonPanel.add(new JLabel());
                continue;
            }

            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 22));
            btn.setBackground(Color.decode("#FFB6C1")); // Light pink
            btn.addActionListener(this);
            buttonPanel.add(btn);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = ((JButton) e.getSource()).getText();
        if (command.equals("=")) {
            try {
                int result = evaluator.evaluateExpression(inputField.getText());
                inputField.setText(String.valueOf(result));
            } catch (Exception ex) {
                inputField.setText("Error");
            }
        } else if (command.equals("C")) {
            inputField.setText("");
        } else {
            inputField.setText(inputField.getText() + command);
        }
    }

    public static void main(String[] args) {
        new EvaluatorUI();
    }
}

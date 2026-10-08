import java.awt.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class Calculator {
    int windowWidth = 360;
    int windowHeight = 540;


    Color customLightGray = new Color(131, 139, 167);
    Color customDarkGray = new Color(65, 69, 89);
    Color customDarkBlue = new Color(15, 17, 26);
    Color customBlue = new Color(32, 159, 181);


    String[] buttonValues = {
        "AC", "+/-", "%", "÷", 
        "7", "8", "9", "×", 
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "√", "="
    };
    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"};


    JFrame frame = new JFrame("Calculator");
    JLabel displayLabel = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();
    
    String A = "0";
    String B = null;
    String operator = null;

    Calculator () {
        frame.setVisible(true);
        frame.setSize(windowWidth, windowHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        displayLabel.setBackground(customDarkBlue);
        displayLabel.setForeground(Color.WHITE);
        displayLabel.setFont(new Font("JetBrains Mono", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel,  BorderLayout.NORTH);

        buttonsPanel.setLayout(new GridLayout(5,4));
        buttonsPanel.setBackground(customDarkBlue);
        frame.add(buttonsPanel);

        for (String value : buttonValues) {
            JButton button = new JButton();
            String buttonText = value;
            button.setFont(new Font("JetBrains Mono", Font.PLAIN, 30));
            button.setText(buttonText);
            button.setFocusable(false);
            button.setBorder(new LineBorder(customDarkBlue));

            if (Arrays.asList(topSymbols).contains(buttonText)) {
                button.setBackground(customLightGray);
                button.setForeground(customDarkBlue);
            }  else if (Arrays.asList(rightSymbols).contains(buttonText)) {
                button.setBackground(customBlue);
                button.setForeground(Color.WHITE);
            } else {
                button.setBackground(customDarkGray);
                button.setForeground(Color.WHITE);
            }
            
            buttonsPanel.add(button);

            button.addActionListener(e -> {

                    JButton sourceButton = (JButton) e.getSource();
                    String buttonValue = sourceButton.getText();
                    if (Arrays.asList(rightSymbols).contains(buttonValue)) {
                        if ("=".equals(buttonValue)) {
                            if (A != null) {
                                B = displayLabel.getText();
                                double numA = Double.parseDouble(A);
                                double numB = Double.parseDouble(B);
                                switch (operator) {
                                    case "+" -> displayLabel.setText(isZeroDecimal(numA + numB));
                                    case "-" -> displayLabel.setText(isZeroDecimal(numA - numB));
                                    case "×" -> displayLabel.setText(isZeroDecimal(numA * numB));
                                    case "÷" -> {
                                        if (numB == 0) {
                                            displayLabel.setText("Error");
                                        } else {
                                            displayLabel.setText(isZeroDecimal(numA / numB));
                                        }
                                    }
                                    default -> displayLabel.setText(isZeroDecimal(numB));
                                }
                                clearAll();
                            }
                        } else if ("+-×÷".contains(buttonValue)) {
                            if (operator == null && !"Error".equals(displayLabel.getText())) {
                                A = displayLabel.getText();
                                displayLabel.setText("0");
                                B = "0";
                            }
                            operator = buttonValue;
                        }
                    } else if (Arrays.asList(topSymbols).contains(buttonValue)) {
                        switch (buttonValue) {
                            case "AC" -> {
                                clearAll();
                                displayLabel.setText("0");
                            }
                            case "+/-" -> {
                                double numDisplay = Double.parseDouble(displayLabel.getText());
                                numDisplay *= -1; 
                                displayLabel.setText(isZeroDecimal(numDisplay));
                            }
                            case  "%" -> {
                                double numDisplay1 = Double.parseDouble(displayLabel.getText());
                                numDisplay1 /= 100; 
                                displayLabel.setText(isZeroDecimal(numDisplay1));
                            }
                        }
                    } else {
                        if (".".equals(buttonValue)) {
                            if (!displayLabel.getText().contains(buttonValue)) {
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        } else if ("0123456789".contains(buttonValue)) {
                            if ("0".equals(displayLabel.getText()) || "Error".equals(displayLabel.getText())) {
                                displayLabel.setText(buttonValue);
                            } else {
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        } else if ("√".equals(buttonValue)) {
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay = Math.sqrt(numDisplay);
                            displayLabel.setText(isZeroDecimal(numDisplay));

                        }
                    }
                
            });
            
        }
    }

    void clearAll() {
        A = "0";
        operator = null;
        B = null;
    }

    String isZeroDecimal(double numDisplay) {
        if (numDisplay % 1 == 0) {
            return Integer.toString((int) numDisplay);
        } else {
            return Double.toString(numDisplay);
        }
    }
}


// TODO: make operators show up when pressed instead of 0
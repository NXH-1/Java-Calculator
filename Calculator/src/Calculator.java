import java.awt.*;
import javax.swing.*;

public class Calculator {
    int windowWidth = 360;
    int windowHeight = 540;

    JFrame frame = new JFrame("Calculator");

    Calculator () {
        frame.setVisible(true);
        frame.setSize(windowWidth, windowHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
    }
}

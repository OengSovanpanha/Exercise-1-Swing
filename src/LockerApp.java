import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LockerApp extends JFrame {
    private JPasswordField passwordField;
    private JLabel statusLabel;
    private String savedPassword = null;

    public LockerApp() {
        createUI();
    }

    private void createUI() {
        setTitle("Lock Class");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel for number buttons
        JPanel numberPanel = new JPanel(new GridLayout(3, 3));
        for (int i = 1; i <= 9; i++) {
            JButton numberButton = new JButton(String.valueOf(i));
            numberButton.addActionListener(new NumberButtonListener());
            numberPanel.add(numberButton);
        }
        add(numberPanel, BorderLayout.CENTER);
        // Panel for control buttons and password field
        JPanel controlPanel = new JPanel(new BorderLayout());

        JPanel subControlPanel = new JPanel(new GridLayout(1, 3));
        JButton clearButton = new JButton("Clear");

        clearButton.addActionListener(new ClearButtonListener());
        subControlPanel.add(clearButton);

        passwordField = new JPasswordField();
        subControlPanel.add(passwordField);

        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(new EnterButtonListener());
        subControlPanel.add(enterButton);

        controlPanel.add(subControlPanel, BorderLayout.CENTER);

        // Status label to the right of the "Enter" button
        statusLabel = new JLabel("Enter Password", SwingConstants.RIGHT);
        controlPanel.add(statusLabel, BorderLayout.EAST);

        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class NumberButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String currentText = passwordField.getText();
            if (currentText.length() < 9) {  // Limit to 9 digits
                passwordField.setText(currentText + source.getText());
            }
        }
    }

    private class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            passwordField.setText("");
        }
    }

    private class EnterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String enteredPassword = new String(passwordField.getPassword());

            if (savedPassword == null) {
                savedPassword = enteredPassword;
                statusLabel.setText("Password Set");
            } else {
                if (enteredPassword.equals(savedPassword)) {
                    statusLabel.setText("Correct Password");
                } else {
                    statusLabel.setText("Incorrect Password");
                }
            }

            passwordField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LockerApp::new);
    }
}
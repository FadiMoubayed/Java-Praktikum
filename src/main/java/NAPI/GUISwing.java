package NAPI;

import javax.swing.*;

public class GUISwing {
    private JTextField startTextField;
    private JTextField textField2;
    private JButton button1;
    private JButton button2;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JPanel GUISwing;
    private JLabel ueberschrift;

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUISwing");
        frame.setContentPane(new GUISwing().GUISwing);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        startTextField = new JTextField;

    }
}

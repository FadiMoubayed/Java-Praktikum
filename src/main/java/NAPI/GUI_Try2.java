package NAPI;

import javax.swing.*;

public class GUI_Try2 {
    private JPanel panel1;
    private JButton button1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI_Try2");
        frame.setContentPane(new GUI_Try2().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

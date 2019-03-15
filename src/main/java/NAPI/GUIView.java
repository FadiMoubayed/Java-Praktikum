package NAPI;

import javax.swing.*;

public class GUIView extends View {
    private JTextField startTextField;
    private JTextField destTextfield;
    private JButton startCheckButton;
    private JButton destCheckButton;
    private JPanel GUISwing;
    private JLabel ueberschrift;
    private JLabel startLabel;
    private JLabel destLabel;
    private JComboBox startCombo;
    private JComboBox destCombo;

/*
    public static void main(String[] args) {
        JFrame frame = new JFrame("GUIView");
        frame.setContentPane(new GUIView().GUISwing);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
*/

    @Override
    public void draw() {
        // Create views swing UI components
        startCheckButton = new JButton("check");

        /*

        JTextField searchTermTextField = new JTextField(26);
        JButton filterButton = new JButton("Filter");
        JTable table = new JTable();

        // Create table model
        RequestHandler model = new RequestHandler();
        table.setModel(model);

        // Create controller
        Controller controller = new Controller(searchTermTextField, model);
        filterButton.addActionListener(controller);

        // Set the view layout
        JPanel ctrlPane = new JPanel();
        ctrlPane.add(searchTermTextField);
        ctrlPane.add(filterButton);

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(700, 182));
        tableScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Market Movers",
                TitledBorder.CENTER, TitledBorder.TOP));

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, ctrlPane, tableScrollPane);
        splitPane.setDividerLocation(35);
        splitPane.setEnabled(false);

        // Display it all in a scrolling window and make the window appear
        JFrame frame = new JFrame("Swing MVC Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(splitPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        */
    }
}

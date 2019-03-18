package NAPI.View;

import NAPI.Controller.GuiController;
import NAPI.Model.RequestHandler;

import javax.swing.*;
import java.awt.*;

public class GUIView{
    private JTextField startTextField;
    private JTextField destTextField;
    private JButton startCheckButton;
    private JButton destCheckButton;
    private JPanel GUISwing;
    private JLabel ueberschrift;
    private JLabel startLabel;
    private JLabel destLabel;
    private JComboBox startCombo;
    private JComboBox destCombo;
    private JTextField outputTextField;
    private JFrame frame;

/*
    public static void main(String[] args) {
        JFrame frame = new JFrame("GUIView");
        frame.setContentPane(new GUIView().GUISwing);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
*/
public GUIView()
{
    this.draw();
}

    public void draw() {

        // Create views swing UI components
        startLabel = new JLabel("start address: ");
        startTextField = new JTextField(26);
        startCheckButton = new JButton("check");
        startCombo = new JComboBox();

        destLabel = new JLabel("dest address: ");
        destTextField = new JTextField(26);
        destCheckButton = new JButton("check");
        destCombo = new JComboBox();

        outputTextField = new JTextField(26);


/*
        JTextField searchTermTextField = new JTextField(26);
        JButton filterButton = new JButton("Filter");
        JTable table = new JTable();
*/
        // Create table model
        RequestHandler model = new RequestHandler();
        //table.setModel(model);

        // Create guiController
        /*
        GuiController guiController = new GuiController(startTextField, destTextField, startCheckButton, destCheckButton, model, this);
        startCheckButton.addActionListener(guiController);
        destCheckButton.addActionListener(guiController);
        */

        // Set the view layout
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel ctrlPane = new JPanel();
        ctrlPane.setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        ctrlPane.add(startLabel, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 200;
        gbc.gridx = 1;
        gbc.gridy = 0;
        ctrlPane.add(startTextField,gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.gridx = 2;
        gbc.gridy = 0;
        ctrlPane.add(startCheckButton, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        gbc.gridy = 0;
        ctrlPane.add(startCombo, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        ctrlPane.add(destLabel, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        ctrlPane.add(destTextField,gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 0;
        gbc.gridx = 2;
        gbc.gridy = 1;
        ctrlPane.add(destCheckButton, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        gbc.gridy = 1;
        ctrlPane.add(destCombo, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        gbc.gridx = 1;
        gbc.gridy = 2;
        ctrlPane.add(outputTextField);


        /*
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(700, 182));
        tableScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Market Movers",
                TitledBorder.CENTER, TitledBorder.TOP));

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, ctrlPane, tableScrollPane);
        splitPane.setDividerLocation(35);
        splitPane.setEnabled(false);
*/
        // Display it all in a scrolling window and make the window appear
        JFrame frame = new JFrame("NAPI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(ctrlPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void updateCombos(String startAddress, String destAddress)
    {
        startCombo.removeAllItems();
        startCombo.addItem(startAddress);
        destCombo.removeAllItems();
        destCombo.addItem(destAddress);
        // SwingUtilities.updateComponentTreeUI(frame);
    }
}

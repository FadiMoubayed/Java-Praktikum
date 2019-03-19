package NAPI.View;

import NAPI.Controller.GuiController;
import NAPI.Model.RequestHandler;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.List;

public class GUIView{
    private JTextField startTextField;
    private JTextField destTextField;
    private JButton startCheckButton;
    private JButton destCheckButton;
    private JPanel GUISwing;
    private JLabel ueberschrift;
    private JLabel startLabel;
    private JLabel destLabel;
    private JLabel startCheckLabel;
    private JLabel destCheckLabel;
    private JTextArea outputTextArea;
    private JButton calculateButton;
    private JLabel vehicleLabel;
    private JRadioButton carRadio;
    private JRadioButton footRadio;
    private JRadioButton bikeRadio;
    private ButtonGroup vehicleRadio;
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
        startCheckLabel = new JLabel();

        destLabel = new JLabel("dest address: ");
        destTextField = new JTextField(26);
        destCheckButton = new JButton("check");
        destCheckLabel = new JLabel();

        vehicleLabel = new JLabel("vehicle type");
        carRadio = new JRadioButton("car");
        footRadio = new JRadioButton("foot");
        bikeRadio = new JRadioButton("bike");
        vehicleRadio = new ButtonGroup();
        vehicleRadio.add(carRadio);
        vehicleRadio.add(bikeRadio);
        vehicleRadio.add(footRadio);

        calculateButton = new JButton("calculate");
        outputTextArea = new JTextArea();
        JScrollPane outputPane = new JScrollPane(outputTextArea);


/*
        JTextField searchTermTextField = new JTextField(26);
        JButton filterButton = new JButton("Filter");
        JTable table = new JTable();
*/
        // Create table model
        RequestHandler model = new RequestHandler();
        //table.setModel(model);

        // Create guiController
        GuiController guiController = new GuiController(startTextField, destTextField, startCheckButton, destCheckButton, calculateButton, model, this);
        startCheckButton.addActionListener(guiController);
        destCheckButton.addActionListener(guiController);
        calculateButton.addActionListener(guiController);

        // Set the view layout
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel ctrlPane = new JPanel();
        ctrlPane.setLayout(new GridBagLayout());
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        ctrlPane.add(startLabel, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        gbc.gridx = 1;
        gbc.gridy = 0;
        ctrlPane.add(startTextField,gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 4;
        gbc.gridy = 0;
        ctrlPane.add(startCheckButton, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 5;
        gbc.gridy = 0;
        ctrlPane.add(startCheckLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        ctrlPane.add(destLabel, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        gbc.gridx = 1;
        gbc.gridy = 1;
        ctrlPane.add(destTextField,gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.ipady = 0;
        gbc.gridx = 4;
        gbc.gridy = 1;
        ctrlPane.add(destCheckButton, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 5;
        gbc.gridy = 1;
        ctrlPane.add(destCheckLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        ctrlPane.add(vehicleLabel, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        ctrlPane.add(carRadio, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 2;
        ctrlPane.add(footRadio, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        gbc.gridy = 2;
        ctrlPane.add(bikeRadio, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 3;
        ctrlPane.add(calculateButton, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 5;
        gbc.ipady = 300;
        gbc.gridx = 1;
        gbc.gridy = 4;
        ctrlPane.add(outputPane,gbc);

        // Display it all in a scrolling window and make the window appear
        JFrame frame = new JFrame("NAPI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(ctrlPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void updateLabels(String startAddress, String destAddress)
    {
        if(startAddress != "") {
            startCheckLabel.setText(startAddress);
        }
        if(destAddress != "") {
            destCheckLabel.setText(destAddress);
        }
        // SwingUtilities.updateComponentTreeUI(frame);
    }
    public void updateOutput(List<String> instructions)
    {
        outputTextArea.setRows(instructions.size() + 1);
        for(int i = 0; i<instructions.size();i++) {
            outputTextArea.append(instructions.get(i) + "\n");
            // The class of instructions.get(i) is class java.lang.String
        }
    }
}

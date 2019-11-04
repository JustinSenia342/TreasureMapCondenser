package src.main.java.tmapcondenser.gui;

import javax.swing.*;
import java.awt.*;

/*
    Written By: Justin Senia
    Written: 11/02/2019
    Last Updated: 11/03/2019
    TreasureMapCondenserGui.java (class): Provides a GUI, so users can both
    View the information stored in the Model and interact with that model using
    some of the controllers provided (modeled after a MVC framework)
*/

class TreasureMapCondenserGui{

    public static void main(String[] args){

        // Default text to display as instructions in the GUI
        String instructionText = "  " + "\n" +
        "  *************************************************************************************************" + "\n" +
        "  *                                Welcome To The Treasure Map Condenser Software                                 " + "\n" +
        "  *************************************************************************************************" + "\n\n" +
        "    What does this software do? " + "\n" +
        "      -  Parses thousands of lines of seemingly aimless treasure map directions to extract" + "\n" +
        "         the steps that lead treasure seekers to their destination" + "\n" +
        "      -  Factors all those data points into a data model, runs calculations, and gives exact" + "\n" +
        "         instructions on how to walk a straight line to the treasure " + "\n" +
        "      -  No more insane riddles or absurd ambiguity!" + "\n" +
        "      -  It even tells you how much time you didn't waste by choosing our product!" + "\n\n" +
        "    ---------------------------------------------------------------------------" + "\n\n" +
        "    Directions: " + "\n" +
        "      -  This software already comes with a treasure map, so you can just click the" + "\n" + 
        "        \"Parse Current Treasure Map\" button to see it in action, and get a readout of details" + "\n" +
        "      -  If you'd like to generate a new \"Test\" map, enter in a positive integer value into the" + "\n" + 
        "         Text box on the bottom and click the \"Generate New Map\" Button (then hit the" + "\n" +
        "        \"Parse Current Treasure Map\" button to get a processed readout of the new Map" + "\n" +
        "      -  You can also choose the \"Instructions Page\" from the \"Start Here\" menu above at" + "\n" + 
        "         at any time to see this instruction menu again" + "\n\n" +
        "    ---------------------------------------------------------------------------" + "\n\n" +
        "    This Software is brought to you by Ninjas: Raining on pirate's parades since 1472"  + "\n\n" +
        "  *************************************************************************************************" + "\n\n";


        // Creating new treasureMapCondenser object to act as the Model
        // The GUI is going to interact with
        src.main.java.tmapcondenser.model.TreasureMapCondenser tMapCondenser = 
        new src.main.java.tmapcondenser.model.TreasureMapCondenser();

        // Create new JFrame object with window title
        JFrame tMapFrame = new JFrame("Treasure Map Condenser");

        // Setting close operation behavior
        tMapFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Setting JFrame size
        tMapFrame.setSize(800, 600);


        // Creating Menu Bar, Menu Items, and adding them together
        JMenuBar tMapMenuBar = new JMenuBar();
        JMenu tMapMenuBarM01 = new JMenu("Start Here");
        tMapMenuBar.add(tMapMenuBarM01);
        JMenuItem tMapMenuBarM0101 = new JMenuItem("Instructions Page");
        tMapMenuBarM01.add(tMapMenuBarM0101);


        // Creating footer panel, footer Items (buttons, labels, textfield), and adding them together
        JPanel tMapFooterPanel = new JPanel();
        JLabel mapStepsLabel = new JLabel("Number Of Map Steps");
        JTextField mapStepsInput = new JTextField(10);
        JButton genNewMap = new JButton("Generate New Map");
        JLabel buttonSpacerLabel = new JLabel("     (Or)      ");
        JButton parseMap = new JButton("Parse Current Treasure Map");
        tMapFooterPanel.add(mapStepsLabel);
        tMapFooterPanel.add(mapStepsInput);
        tMapFooterPanel.add(genNewMap);
        tMapFooterPanel.add(buttonSpacerLabel);
        tMapFooterPanel.add(parseMap);

        
        // Creating text area to display output
        JTextArea tMapTextArea = new JTextArea();
        // Setting text area to give it a scroll pane
        JScrollPane scrollPane = new JScrollPane(tMapTextArea);
        // Setting text area so it's no longer directly editable by users
        //tMapTextArea.setEditable(false);


        // Setting content to layout
        tMapFrame.getContentPane().add(BorderLayout.SOUTH, tMapFooterPanel);
        tMapFrame.getContentPane().add(BorderLayout.NORTH, tMapMenuBar);
        tMapFrame.getContentPane().add(BorderLayout.CENTER, scrollPane);
        tMapFrame.setVisible(true);

        // Sets text to include
        tMapTextArea.setText(instructionText);

        // Sets default number of generated map steps to 1000
        mapStepsInput.setText("1000");

        // Assigning event Listeners for interactive functions
        genNewMap.addActionListener(event -> tMapTextArea.setText(
            src.main.java.tmapcondenser.treasuremaps.TreasureMapDirectionsGenerator.GenerateNewMap(Integer.parseInt(mapStepsInput.getText()))));
        parseMap.addActionListener(event -> tMapTextArea.setText(tMapCondenser.condenseTreasureMap()));

       // Action Listener for "Start Here" > "Instructions Page" Dropdown
       tMapMenuBarM0101.addActionListener(event -> tMapTextArea.setText(instructionText));

    }

}
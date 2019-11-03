import javax.swing.*;
import java.awt.*;

class TreasureMapCondenserGui{

    public static void main(String[] args){

        TreasureMapCondenser tMapCondenser = new TreasureMapCondenser();

        


        JFrame frame = new JFrame("Treasure Map Condenser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600);

        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Start Here");
        JMenu m2 = new JMenu("Map Generator");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Instructions");
        JMenuItem m21 = new JMenuItem("View Current Map");
        JMenuItem m22 = new JMenuItem("Generate New Map");
        m1.add(m11);
        m2.add(m21);
        m2.add(m22);

        m22.addActionListener(event -> JOptionPane.showMessageDialog(mb, "Would you like to Generate a new Map?", "New Map Generation", JOptionPane.QUESTION_MESSAGE));

        JPanel footerPanel = new JPanel();
        JLabel mapStepsLabel = new JLabel("Number Of Map Steps");
        JTextField mapStepsInput = new JTextField(10);
        JButton genNewMap = new JButton("Generate New Map");
        JLabel buttonSpacerLabel = new JLabel("  -   Or   -   ");
        JButton parseMap = new JButton("Parse Current Treasure Map");
        footerPanel.add(mapStepsLabel);
        //panel.add(label);
        footerPanel.add(mapStepsInput);
        footerPanel.add(genNewMap);
        footerPanel.add(buttonSpacerLabel);
        footerPanel.add(parseMap);

        

        JTextArea textArea = new JTextArea(20, 100);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);


        frame.getContentPane().add(BorderLayout.SOUTH, footerPanel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, textArea);
        frame.setVisible(true);


        genNewMap.addActionListener(event -> textArea.append(TreasureMapDirectionsGenerator.GenerateNewMap(Integer.parseInt(mapStepsInput.getText()))));
        parseMap.addActionListener(event -> textArea.append(TreasureMapCondenser.condenseTreasureMap()));
    }

}
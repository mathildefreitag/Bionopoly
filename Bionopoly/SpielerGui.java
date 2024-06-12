package gui;

import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
//import java.awt.Dimension;
import java.awt.FlowLayout;

public class SpielerGui extends JFrame {

    public SpielerGui() {
        setTitle("Bionopoly");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        //Spieler Panels Container
        JPanel leftContainer = new JPanel();
        leftContainer.setLayout(new BorderLayout()); //BorderLayout zum Hinzufügen des linken Container
        leftContainer.setPreferredSize(new Dimension(378, getHeight()));

        JPanel rightContainer = new JPanel();
        rightContainer.setLayout(new BorderLayout()); //BorderLayout zum Hinzufügen des rechten Container
        rightContainer.setPreferredSize(new Dimension(378, getHeight()));

        //Spieler Panels
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(3, 1)); //3 Zeilen, 1 Spalte ohne Abstände
        leftPanel.setPreferredSize(new Dimension(200, 300)); //Maße der Kästen

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(3, 1)); //3 Zeilen, 1 Spalte mit Abständen
        rightPanel.setPreferredSize(new Dimension(200, 300)); //Maße der Kästen
    
    //Zusätzliches Panel unten
    JPanel bottomPanelLeft = new JPanel();
    bottomPanelLeft.setPreferredSize(new Dimension(200, 300)); //Höhe des zusätzlichen Panels

    JPanel bottomPanelRight = new JPanel();
    bottomPanelRight.setPreferredSize(new Dimension(200, 300)); //Höhe des zusätzlichen Panels
   
    
    
    //Spieler initialisieren
    for (int i = 1; i <= 3; i++) {
        leftPanel.add(createPlayerPanel("Spieler " + i));
        rightPanel.add(createPlayerPanel("Spieler " + (i + 3)));
    	}
    
    
    
    //Ausrichtung Container für die Spieler Panels und zusätzliches Panel
    leftContainer.add(leftPanel, BorderLayout.CENTER);
    leftContainer.add(bottomPanelLeft, BorderLayout.SOUTH);

    rightContainer.add(rightPanel, BorderLayout.CENTER);
    rightContainer.add(bottomPanelRight, BorderLayout.SOUTH);

    //Panels zum Container hinzufügen
    add(leftContainer, BorderLayout.WEST);
    add(rightContainer, BorderLayout.EAST);
    //Spielbrettasurichtung: add(boardPanel, BorderLayout.CENTER);
}

    private JPanel createPlayerPanel(String playerName) {
        JPanel playerPanel = new JPanel();
        playerPanel.setPreferredSize(new Dimension(200, 100));
        playerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        playerPanel.add(new JLabel(playerName));
        return playerPanel;
    }

   public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        SpielerGui spielerGui = new SpielerGui();
        spielerGui.setVisible(true);
    });
   }
}

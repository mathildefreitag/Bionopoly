package gui;

import javax.swing.*;
import java.awt.*;

public class SpielerGui extends JFrame {

    public SpielerGui() {
        setTitle("Spiel mit sechs Spielern");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Spieler Panels Container
        JPanel leftContainer = new JPanel();
        leftContainer.setLayout(new BorderLayout()); // BorderLayout für einfaches Hinzufügen unten
        leftContainer.setPreferredSize(new Dimension(200, getHeight()));

        JPanel rightContainer = new JPanel();
        rightContainer.setLayout(new BorderLayout()); // BorderLayout für einfaches Hinzufügen unten
        rightContainer.setPreferredSize(new Dimension(200, getHeight()));

        // Spieler Panels
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(3, 1, 0, 20)); // 3 Zeilen, 1 Spalte mit Abständen
        leftPanel.setPreferredSize(new Dimension(200, 300)); // Halbe Höhe des Fensters

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(3, 1, 0, 20)); // 3 Zeilen, 1 Spalte mit Abständen
        rightPanel.setPreferredSize(new Dimension(200, 300)); // Halbe Höhe des Fensters

        // Spielfeld Panel
        JPanel boardPanel = new JPanel();
        boardPanel.setPreferredSize(new Dimension(400, 400));
        boardPanel.setBackground(Color.GRAY);

        // Zusätzliches Panel unten
        JPanel bottomPanelLeft = new JPanel();
        bottomPanelLeft.setPreferredSize(new Dimension(200, 300)); // 1.5 mal die Höhe der Spieler-Panels
        //bottomPanelLeft.setBackground(Color.BLUE); // Beispielhintergrundfarbe

        JPanel bottomPanelRight = new JPanel();
        bottomPanelRight.setPreferredSize(new Dimension(200, 300)); // 1.5 mal die Höhe der Spieler-Panels
       //bottomPanelRight.setBackground(Color.RED); // Beispielhintergrundfarbe

        // Spieler initialisieren
        for (int i = 1; i <= 3; i++) {
            leftPanel.add(createPlayerPanel("Spieler " + i));
            rightPanel.add(createPlayerPanel("Spieler " + (i + 3)));
        }

        // Container für die Spieler Panels und Zusätzliches Panel unten hinzufügen
        leftContainer.add(leftPanel, BorderLayout.CENTER);
        leftContainer.add(bottomPanelLeft, BorderLayout.SOUTH);

        rightContainer.add(rightPanel, BorderLayout.CENTER);
        rightContainer.add(bottomPanelRight, BorderLayout.SOUTH);

        // Panels zum Frame hinzufügen
        add(leftContainer, BorderLayout.WEST);
        add(rightContainer, BorderLayout.EAST);
        add(boardPanel, BorderLayout.CENTER);
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

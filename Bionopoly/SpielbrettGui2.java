package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class SpielbrettGui2 extends JFrame {
    private JLabel augenZahlValue;
    private JLabel currentPlayerLabel;
    private List<String> spielerNamen;
    private boolean canRollDice = true;
    private int currentPlayerIndex = 0;

    public SpielbrettGui2(int anzahlSpieler) {
        setTitle("Bionopoly");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 800);
        setResizable(false);
        setLayout(new BorderLayout());

        // Spieler Panels Container
        JPanel leftContainer = new JPanel();
        leftContainer.setLayout(new BorderLayout());
        leftContainer.setPreferredSize(new Dimension(380, getHeight()));

        JPanel rightContainer = new JPanel();
        rightContainer.setLayout(new BorderLayout());
        rightContainer.setPreferredSize(new Dimension(376, getHeight()));

        // Spieler Panels
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(3, 1));
        leftPanel.setPreferredSize(new Dimension(200, 300));

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(3, 1));
        rightPanel.setPreferredSize(new Dimension(200, 300));

        // Zusätzliches Panel unten
        JPanel bottomPanelLeft = new JPanel();
        bottomPanelLeft.setPreferredSize(new Dimension(200, 300));

        JPanel bottomPanelRight = new JPanel();
        bottomPanelRight.setPreferredSize(new Dimension(200, 300));

        //  Liste von Spielernamen
        spielerNamen = Arrays.asList("Paramecium", "Regenwurm", "Heuschrecke", "Seestern", "Fisch", "Schwein");

        // Spieler initialisieren mit individuellen Namen
        for (int i = 0; i < spielerNamen.size(); i++) {
            String spielerName = spielerNamen.get(i);
            if (i < 3) {
                leftPanel.add(createPlayerPanel(spielerName));
            } else {
                rightPanel.add(createPlayerPanel(spielerName));
            }
        }

        // Container für die Spieler Panels und zusätzliches Panel Ausrichtung
        leftContainer.add(leftPanel, BorderLayout.CENTER);
        leftContainer.add(bottomPanelLeft, BorderLayout.SOUTH);

        rightContainer.add(rightPanel, BorderLayout.CENTER);
        rightContainer.add(bottomPanelRight, BorderLayout.SOUTH);

        // Panels in Container einfügen
        add(leftContainer, BorderLayout.WEST);
        add(rightContainer, BorderLayout.EAST);

        // Spielbrett als Bild mittig einfügen
        JPanel boardPanel = new JPanel();
        boardPanel.setPreferredSize(new Dimension(400, 400));

        // Bild einfügen (Pfad zum PNG-Bild anpassen)
        ImageIcon icon = new ImageIcon("spielbrett2.0.png");
        int imageSize = 730;
        ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH));
        JLabel label = new JLabel(scaledIcon);
        label.setBorder(new EmptyBorder(0, 0, 0, 0));
        boardPanel.add(label);
        add(boardPanel, BorderLayout.CENTER);

        // Würfel GUI erstellen
        createDicePanel(bottomPanelRight);

        // Aktuellen Spieler anzeigen
        updateCurrentPlayer();
    }

    // Würfel und Text erstellen
    private void createDicePanel(JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Panel für aktuellen Spieler
        JPanel namenAnzeige = new JPanel();
        namenAnzeige.setPreferredSize(new Dimension(400, 30));
        currentPlayerLabel = new JLabel("Aktueller Spieler: ", SwingConstants.CENTER);
        currentPlayerLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        namenAnzeige.add(currentPlayerLabel);
        panel.add(namenAnzeige);

        // Panel für Würfel
        JPanel dicePanel = new JPanel();
        dicePanel.setPreferredSize(new Dimension(400, 100));

        JLabel dice1 = new JLabel("1", SwingConstants.CENTER);
        dice1.setFont(new Font("Arial", Font.BOLD, 18));
        dice1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        dice1.setPreferredSize(new Dimension(40, 40));
        dicePanel.add(dice1);

        JLabel dice2 = new JLabel("1", SwingConstants.CENTER);
        dice2.setFont(new Font("Arial", Font.BOLD, 18));
        dice2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        dice2.setPreferredSize(new Dimension(40, 40));
        dicePanel.add(dice2);

        panel.add(dicePanel);

        // "Würfeln"-Button
        JButton rollButton = new JButton("Würfeln");
        rollButton.setPreferredSize(new Dimension(200, 25));
        rollButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (canRollDice) {
                    // Würfeln und Augenzahl anzeigen
                    int würfel1 = (int) (Math.random() * 6 + 1);
                    int würfel2 = (int) (Math.random() * 6 + 1);
                    dice1.setText(String.valueOf(würfel1));
                    dice2.setText(String.valueOf(würfel2));
                    augenZahlValue.setText(String.valueOf(würfel1 + würfel2));
                    canRollDice = false;
                    if (würfel1 == würfel2) {
                        JOptionPane.showMessageDialog(panel, "Pasch! Du darfst noch einmal würfeln.");
                        canRollDice = true; // Pasch erlaubt ein weiteres Würfeln
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Du hast bereits gewürfelt!", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        panel.add(rollButton);

        // Panel für gewürfelte Augenzahl
        JPanel augenZahlPanel = new JPanel();
        augenZahlPanel.setPreferredSize(new Dimension(400, 30));
        JLabel augenZahlLabel = new JLabel("Gewürfelte Augenzahl: ");
        augenZahlPanel.add(augenZahlLabel);
        augenZahlValue = new JLabel("0"); // Initialwert
        augenZahlPanel.add(augenZahlValue);
        panel.add(augenZahlPanel);

        // Panel für Buttons "Fortfahren" und "Zug beenden"
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(400, 30));

        JButton fortfahrenButton = new JButton("Fortfahren");
        fortfahrenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Hier Code für Fortfahren
                // Weitere Aktionen implementieren
            }
        });
        buttonPanel.add(fortfahrenButton);

        JButton zugBeendenButton = new JButton("Zug beenden");
        zugBeendenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                zugBeenden();
            }
        });
        buttonPanel.add(zugBeendenButton);

        panel.add(buttonPanel);
    }

    // Methode zum Erstellen eines Spieler-Panels
    private JPanel createPlayerPanel(String playerName) {
        JPanel playerPanel = new JPanel();
        playerPanel.setPreferredSize(new Dimension(200, 100));
        playerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        playerPanel.add(new JLabel(playerName));
        return playerPanel;
    }

    private void zugBeenden() {
        currentPlayerIndex++;
        if (currentPlayerIndex >= spielerNamen.size()) {
            currentPlayerIndex = 0;
        }
        canRollDice = true; // Würfeln für den nächsten Spieler wieder ermöglichen
        updateCurrentPlayer();
    }

    private void updateCurrentPlayer() {
        String currentPlayer = spielerNamen.get(currentPlayerIndex);
        currentPlayerLabel.setText("Aktueller Spieler: " + currentPlayer);
    }

    // Main-Methode zum Testen
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SpielbrettGui2 gui = new SpielbrettGui2(6); // Beispiel: 6 Spieler
                gui.setVisible(true);
            }
        });
    }
}

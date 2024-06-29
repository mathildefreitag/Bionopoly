package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartbildschirmGui extends JPanel {
    private JButton startButton;
    private JButton beendenButton;
    private JComboBox<String> spielerAnzahlComboBox;
    private int anzahlSpieler; // Variable zur Speicherung der Spieleranzahl
    private SpielbrettGui2 spielbrettGui; // Referenz auf das Spielbrett

    public StartbildschirmGui() {
        setLayout(new GridBagLayout()); // Verwendung eines GridBagLayouts für präzisere Platzierung
        setBackground(Color.LIGHT_GRAY);

        // Begrüßungstext
        JLabel willkommenLabel = new JLabel("<html><div style='font-size: 24px; text-align: center;'>Willkommen zu Bionopoly</div><br>" +
                                            "<div style='font-size: 14px; text-align: center;'>Bitte wählen Sie die gewünschte Spieleranzahl aus</div></html>");
        willkommenLabel.setPreferredSize(new Dimension(400, 100)); // Größe anpassen

        JLabel spielerAnzahlLabel = new JLabel("Anzahl der Spieler:");
        spielerAnzahlComboBox = new JComboBox<>(new String[]{"2 Spieler", "3 Spieler", "4 Spieler", "5 Spieler", "6 Spieler"});
        spielerAnzahlComboBox.setPreferredSize(new Dimension(120, 30));

        startButton = new JButton("Spiel starten");
        startButton.setBackground(Color.GREEN);
        startButton.setPreferredSize(new Dimension(120, 30));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAnzahl = (String) spielerAnzahlComboBox.getSelectedItem();
                anzahlSpieler = Integer.parseInt(selectedAnzahl.substring(0, 1)); // Speichern der Spieleranzahl

                // Erzeugung des SpielbrettGui, aber nicht anzeigen
                spielbrettGui = new SpielbrettGui2(anzahlSpieler);

                // Schließen des Startbildschirm-Fensters
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(StartbildschirmGui.this);
                frame.dispose();

                // SpielbrettGui anzeigen
                SwingUtilities.invokeLater(() -> {
                    spielbrettGui.setVisible(true);
                });
            }
        });

        beendenButton = new JButton("Beenden");
        beendenButton.setBackground(Color.RED);
        beendenButton.setPreferredSize(new Dimension(120, 30));
        beendenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Verwendung von GridBagConstraints, um Komponenten zentriert anzuordnen
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(50, 0, 20, 0); // Abstände anpassen
        add(willkommenLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 10, 0); // Abstände anpassen
        add(spielerAnzahlLabel, gbc);

        gbc.gridx = 1;
        add(spielerAnzahlComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // Zentrierung der Buttons
        gbc.insets = new Insets(20, 0, 0, 0); // Abstände anpassen
        add(startButton, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(10, 0, 50, 0); // Abstände anpassen
        add(beendenButton, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Startbildschirm");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.getContentPane().add(new StartbildschirmGui());

            // Setzen des Vollbildmodus
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setUndecorated(true); // Rahmen entfernen für Vollbild

            // Fenster zentrieren
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int centerX = screenSize.width / 2;
            int centerY = screenSize.height / 2;
            frame.setLocation(centerX - frame.getWidth() / 2, centerY - frame.getHeight() / 2);

            frame.setVisible(true);
        });
    }
}

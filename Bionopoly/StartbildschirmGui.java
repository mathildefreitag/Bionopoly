package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import bionopoly.Spielfeld;
import bionopoly.Spielfigur;
import bionopoly.Währung;

public class StartbildschirmGui extends JPanel {
    private JButton startButton;
    private JButton beendenButton;
    private JComboBox<String> spielerAnzahlComboBox;
    private int anzahlSpieler;
    private List<String> spielerNamen;

    // Definiere eine Liste von Farben für die Spielfiguren
    private static final Color[] farben = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.MAGENTA};

    public StartbildschirmGui() {
        setLayout(new GridBagLayout());
        setBackground(Color.LIGHT_GRAY);

        JLabel willkommenLabel = new JLabel("<html><div style='font-size: 24px; text-align: center;'>Willkommen zu Bionopoly</div><br>" +
                                            "<div style='font-size: 14px; text-align: center;'>Bitte wählen Sie die gewünschte Spieleranzahl aus</div></html>");
        willkommenLabel.setPreferredSize(new Dimension(400, 100));

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
                anzahlSpieler = Integer.parseInt(selectedAnzahl.substring(0, 1));

                // Initialisiere Spielernamen
                spielerNamen = Arrays.asList("Paramecium", "Regenwurm", "Heuschrecke", "Seestern", "Fisch", "Schwein");

                // Initialisiere Währung und Spielfeld
                Währung währung = new Währung();
                Spielfeld spielfeld = new Spielfeld(50, 50, 612, 612);
                Spielfigur[] spieler = new Spielfigur[anzahlSpieler];

                for (int i = 0; i < anzahlSpieler; i++) {
                    // Wähle eine Farbe für den Spieler basierend auf dem Index
                    Color farbe = farben[i % farben.length];
                    spieler[i] = new Spielfigur(spielerNamen.get(i), spielfeld.feldAmOrt(0), farbe);
                }

                währung.setSpieler(spieler);

                // Erzeuge und zeige das SpielbrettGui-Fenster
                SwingUtilities.invokeLater(() -> {
                    SpielbrettGui spielbrettGui = new SpielbrettGui(anzahlSpieler, währung);
                    spielbrettGui.setVisible(true);
                });

                // Schließe das Startbildschirm-Fenster
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(StartbildschirmGui.this);
                frame.dispose();
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

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(50, 0, 20, 0);
        add(willkommenLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(spielerAnzahlLabel, gbc);

        gbc.gridx = 1;
        add(spielerAnzahlComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 0, 0);
        add(startButton, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(10, 0, 50, 0);
        add(beendenButton, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Startbildschirm");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.getContentPane().add(new StartbildschirmGui());

            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setUndecorated(true);

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int centerX = screenSize.width / 2;
            int centerY = screenSize.height / 2;
            frame.setLocation(centerX - frame.getWidth() / 2, centerY - frame.getHeight() / 2);

            frame.setVisible(true);
        });
    }
}

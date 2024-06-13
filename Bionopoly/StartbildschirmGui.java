package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartbildschirmGui extends JPanel {
    private JButton startButton;
    private JButton beendenButton;
    private JComboBox<String> spielerAnzahlComboBox;
    private int anzahlSpieler; //Speicherung der Spieleranzahl
    private SpielbrettGui2 spielbrettGui; //auf das Spielbrett zugreifen

    public StartbildschirmGui() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        setBackground(Color.LIGHT_GRAY);

        JLabel spielerAnzahlLabel = new JLabel("Anzahl der Spieler:");
        spielerAnzahlComboBox = new JComboBox<>(new String[]{"2 Spieler", "3 Spieler", "4 Spieler", "5 Spieler", "6 Spieler"});
        spielerAnzahlComboBox.setPreferredSize(new Dimension(120, 30));

        startButton = new JButton("Spiel starten");
        startButton.setBackground(Color.GREEN);
        startButton.setPreferredSize(new Dimension(120, 30));
        startButton.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent e) {
                String selectedAnzahl = (String) spielerAnzahlComboBox.getSelectedItem();
                anzahlSpieler = Integer.parseInt(selectedAnzahl.substring(0, 1)); //Spieleranzahl speichern

                //SpielbrettGui erstellen, aber nicht anzeigen
                spielbrettGui = new SpielbrettGui2(anzahlSpieler);

                //Startbildschirm-Fensters schließen
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(StartbildschirmGui.this);
                frame.dispose();

                //SpielbrettGui anzeigen
                SwingUtilities.invokeLater(() -> {
                    spielbrettGui.setVisible(true);
                });
            }
        });

        beendenButton = new JButton("Beenden");
        beendenButton.setBackground(Color.RED);
        beendenButton.setPreferredSize(new Dimension(120, 30));
        beendenButton.addActionListener(new ActionListener() {
        	
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(spielerAnzahlLabel);
        add(spielerAnzahlComboBox);
        add(startButton);
        add(beendenButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Startbildschirm");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.getContentPane().add(new StartbildschirmGui());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

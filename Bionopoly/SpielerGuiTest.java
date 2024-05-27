package gui;

import spiel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SpielerGuiTest extends JFrame {

    private List<Spieler> spielerListe;
    private Wuerfel wuerfel;
    private Feld[] spielfeld;
    private JTextArea infoArea;
    private JButton wuerfelnButton, zugBeendenButton;
    private int aktuellerSpielerIndex;

    public SpielerGuiTest() {
        setTitle("Spiel mit sechs Spielern");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Spielfeld initialisieren
        spielfeld = new Feld[20];
        for (int i = 0; i < spielfeld.length; i++) {
            spielfeld[i] = new Feld("Feld " + (i + 1), (i + 1) * 10);
        }

        // Spieler initialisieren
        spielerListe = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            spielerListe.add(new Spieler("Spieler " + i, 100));
        }

        wuerfel = new Wuerfel();
        aktuellerSpielerIndex = 0;

        // Info Bereich
        infoArea = new JTextArea();
        infoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(infoArea);
        add(scrollPane, BorderLayout.CENTER);

        // Kontrollbereich
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        wuerfelnButton = new JButton("Würfeln");
        wuerfelnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wuerfeln();
            }
        });
        controlPanel.add(wuerfelnButton);

        zugBeendenButton = new JButton("Zug beenden");
        zugBeendenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zugBeenden();
            }
        });
        controlPanel.add(zugBeendenButton);

        add(controlPanel, BorderLayout.SOUTH);

        updateInfo();
    }

    private void wuerfeln() {
        int augenzahl = wuerfel.werfen();
        Spieler aktuellerSpieler = spielerListe.get(aktuellerSpielerIndex);
        int neuePosition = (aktuellerSpieler.getPosition() + augenzahl) % spielfeld.length;
        aktuellerSpieler.setPosition(neuePosition);

        Feld aktuellesFeld = spielfeld[neuePosition];
        infoArea.append(aktuellerSpieler.getName() + " hat eine " + augenzahl + " geworfen und ist auf " + aktuellesFeld.getName() + " gelandet.\n");

        if (aktuellesFeld.istVerfuegbar()) {
            int preis = aktuellesFeld.getPreis();
            if (aktuellerSpieler.getKontostand().getBetrag() >= preis) {
                int result = JOptionPane.showConfirmDialog(this, aktuellerSpieler.getName() + ", möchtest du " + aktuellesFeld.getName() + " für " + preis + " kaufen?", "Feld kaufen", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    aktuellerSpieler.kaufeFeld(aktuellesFeld);
                    infoArea.append(aktuellerSpieler.getName() + " hat " + aktuellesFeld.getName() + " gekauft.\n");
                }
            } else {
                infoArea.append(aktuellerSpieler.getName() + " hat nicht genug Geld, um " + aktuellesFeld.getName() + " zu kaufen.\n");
            }
        } else {
            infoArea.append(aktuellerSpieler.getName() + " ist auf " + aktuellesFeld.getName() + ", das bereits von " + aktuellesFeld.getBesitzer().getName() + " besessen wird.\n");
        }

        updateInfo();
    }

    private void zugBeenden() {
        aktuellerSpielerIndex = (aktuellerSpielerIndex + 1) % spielerListe.size();
        infoArea.append("Zug von " + spielerListe.get(aktuellerSpielerIndex).getName() + " beginnt.\n");
    }

    private void updateInfo() {
        StringBuilder info = new StringBuilder();
        for (Spieler spieler : spielerListe) {
            info.append(spieler.getName()).append(" - Kontostand: ").append(spieler.getKontostand().getBetrag()).append(" - Besitz: ");
            for (Feld feld : spieler.getBesitz()) {
                info.append(feld.getName()).append(" ");
            }
            info.append("\n");
        }
        infoArea.setText(info.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SpielerGui spielerGui = new SpielerGui();
            spielerGui.setVisible(true);
        });
    }
}

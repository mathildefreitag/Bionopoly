package gui;

import bionopoly.Spielfigur;
import bionopoly.Würfel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class SpielfigurGui extends JFrame {
    private Map<String, JLabel> spielfigurenLabels;
    private JPanel spielfeld;

    public SpielfigurGui() {
        setTitle("Bionopoly");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        spielfeld = new JPanel();
        spielfeld.setLayout(null);
        spielfigurenLabels = new HashMap<>();

        // Beispielhafte Spielfiguren hinzufügen
        addSpielfigur(new Spielfigur("Regenwurm"), "regenwurm.png");
        addSpielfigur(new Spielfigur("Paramecium"), "paramecium.png");
        addSpielfigur(new Spielfigur("Heuschrecke"), "heuschrecke.png");
        addSpielfigur(new Spielfigur("Fisch"), "fisch.png");
        addSpielfigur(new Spielfigur("Seestern"), "seestern.png");
        addSpielfigur(new Spielfigur("Schwein"), "schwein.png");

        JButton würfelnButton = new JButton("Würfeln");
        würfelnButton.setBounds(350, 700, 100, 30);
        würfelnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Würfel würfel = new Würfel();
                würfel.würfel();
                int augensumme = würfel.getAugensumme();
                System.out.println("Gewürfelte Augensumme: " + augensumme);

                // Beispiel: Bewege die Spielfigur "Regenwurm"
                verschiebeSpielfigur("Regenwurm", augensumme * 10, augensumme * 10);
            }
        });

        spielfeld.add(würfelnButton);
        add(spielfeld);
    }

    private void addSpielfigur(Spielfigur spielfigur, String bildDatei) {
        // Überprüfe, ob die Ressource vorhanden ist
        java.net.URL bildURL = getClass().getResource("/" + bildDatei);
        if (bildURL == null) {
            System.out.println("Bilddatei nicht gefunden: " + bildDatei);
            return;
        }
        
        ImageIcon icon = new ImageIcon(bildURL);
        JLabel label = new JLabel(icon);
        label.setBounds(50, 50, icon.getIconWidth(), icon.getIconHeight());
        spielfeld.add(label);
        spielfigurenLabels.put(spielfigur.getName(), label);
    }

    private void verschiebeSpielfigur(String name, int x, int y) {
        JLabel label = spielfigurenLabels.get(name);
        if (label != null) {
            label.setLocation(x, y);
        } else {
            System.out.println("Spielfigur mit Namen " + name + " nicht gefunden.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SpielfigurGui().setVisible(true);
            }
        });
    }
}
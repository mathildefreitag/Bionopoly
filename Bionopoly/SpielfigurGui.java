package gui;

import bionopoly.Spielfigur;
import bionopoly.Spielfeld;
import bionopoly.Feld;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpielfigurGui {
    private Map<String, JLabel> spielfigurenLabels;

    public SpielfigurGui() {
        this.spielfigurenLabels = new HashMap<>();
    }

    public void initializeSpielfeld(Spielfeld spielfeld, List<Spielfigur> spielfiguren) {
        for (Spielfigur spielfigur : spielfiguren) {
            addSpielfigur(spielfigur);
        }
    }

    private void addSpielfigur(Spielfigur spielfigur) {
        JLabel label = new JLabel(spielfigur.getName());
        Feld aktuellesFeld = spielfigur.getAktuellesFeld();
        label.setBounds(aktuellesFeld.getX(), aktuellesFeld.getY(), 50, 50); // Adjust size as needed
        spielfigurenLabels.put(spielfigur.getName(), label);
    }

    public void verschiebeSpielfigur(String name, int x, int y) {
        JLabel label = spielfigurenLabels.get(name);
        if (label != null) {
            label.setLocation(x, y);
        } 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SpielfigurGui gui = new SpielfigurGui();

                // Initialize the Spielfeld and Spielfiguren
                Spielfeld spielfeld = new Spielfeld(50, 50, 612, 612);
                List<Spielfigur> spielfiguren = Spielfigur.initSpielfiguren(spielfeld, gui);

                gui.initializeSpielfeld(spielfeld, spielfiguren);

                // Test movement for one Spielfigur
                Spielfigur figur = spielfiguren.get(0); // First Spielfigur
                spielfeld.würfelnUndBewegen(figur); // Assuming this method moves the figure and updates the GUI
            }
        });
    }
}

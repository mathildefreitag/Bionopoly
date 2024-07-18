package gui;

import bionopoly.Spielfigur;
import bionopoly.Spielfeld;
import bionopoly.Feld;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpielfigurGui {
    private Map<String, JLabel> spielfigurenLabels; //zuordnen von Strings zu Labeln

    public SpielfigurGui() {
        this.spielfigurenLabels = new HashMap<>(); //hinzufügen HashMap, erfüllt ihre Funktion leider nicht
    }

    public void initializeSpielfeld(Spielfeld spielfeld, List<Spielfigur> spielfiguren) { //Spielfelder sollen mit Spielfiguren Initialisiert werden
        for (Spielfigur spielfigur : spielfiguren) {
            addSpielfigur(spielfigur); //Spielfiguren werden auf Felder hinzugefügt
        }
    }

    private void addSpielfigur(Spielfigur spielfigur) {
        JLabel label = new JLabel(spielfigur.getName()); //Namen der Figuren als Label
        Feld aktuellesFeld = spielfigur.getAktuellesFeld(); //Aktuelles Feld des aktuellen Spielers aufgerufen
        label.setBounds(aktuellesFeld.getX(), aktuellesFeld.getY(), 50, 50); //Größe und Koordinaten festgelegt
        spielfigurenLabels.put(spielfigur.getName(), label); //Name wird zum Label
    }

    public void verschiebeSpielfigur(String name, int x, int y) {
        JLabel label = spielfigurenLabels.get(name); //Namen der Label werden aufgerufen
        if (label != null) { //wenn ein Label vorhanden sind, bekommt das Label eine Position
            label.setLocation(x, y);
        } 
    }
}

package bionopoly;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

import javax.swing.JFrame;

import gui.SpielfigurGui;

public class Spielfigur extends Spieler {
    private String name;
    private int intelligenz;
    private boolean pleite;
    private Feld aktuellesFeld;
    private Spielfeld spielfeld;
    private static SpielfigurGui gui;
    private Color farbe;
    private Point position;
    private boolean inNachpruefung;
    private int nachpruefungVersuche;


    // Statische Liste, die die aktuellen Felder aller Spielfiguren speichert
    private static List<Feld> spielfigurenFelder = new ArrayList<>();

    public Spielfigur(String name, Feld startFeld, Color farbe) {
        super(name, 1500, 0, null);
        this.name = name;
        this.intelligenz = 1500; // Startkapital
        this.pleite = false;
        this.aktuellesFeld = spielfeld.getFeld(0);
        this.farbe = farbe;
        spielfigurenFelder.add(startFeld); // Initialisiere das Startfeld in der Liste
        this.position = new Point(startFeld.getX(), startFeld.getY());
    }
    
    public Color getFarbe() {
        return farbe;
    }

    public String getName() {
        return name;
    }
    public Point getPosition() {
        return position;
    }
    
    

    public void felderVorrücken(int augensumme) {
        if (spielfeld == null) {
            throw new IllegalStateException("Spielfeld wurde nicht initialisiert.");
        }

        // Berechne den neuen Index basierend auf dem aktuellen Feld
        int aktuellerIndex = aktuellesFeld.getIndex();
        int neuesFeldIndex = (aktuellerIndex + augensumme) % spielfeld.getAlleFelder().size();
        
        Feld neuesFeld = spielfeld.feldAmOrt(neuesFeldIndex);
        setPosition(new Point(neuesFeld.getX(), neuesFeld.getY()));
        setAktuellesFeld(neuesFeld); // Aktuelles Feld aktualisieren
    }

    private void aktualisiereFeldInListe(Feld neuesFeld) {
        int index = spielfigurenFelder.indexOf(aktuellesFeld);
        if (index != -1) {
            spielfigurenFelder.set(index, neuesFeld);
        }
    }

    public void zugBeenden() {
        System.out.println(name + " hat seinen Zug beendet.");
    }
    public void setPosition(Point position) {
        this.position = position;
        if (gui != null) {
            gui.verschiebeSpielfigur(name, position.x, position.y);
        }
    }

    public static ArrayList<Spielfigur> initSpielfiguren(Spielfeld spielfeld, SpielfigurGui gui) {
        ArrayList<Spielfigur> spielfiguren = new ArrayList<>();

        String[] namen = {"Paramecium", "Regenwurm", "Heuschrecke", "Seestern", "Fisch", "Schwein"};
        Color[] farben = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.MAGENTA};

        for (int i = 0; i < namen.length; i++) {
            Spielfigur figur = new Spielfigur(namen[i], spielfeld.feldAmOrt(0), farben[i]);
            spielfiguren.add(figur);
            figur.setSpielfeld(spielfeld);
            figur.setGui(gui);
        }

        return spielfiguren;
    }

    public void setSpielfeld(Spielfeld spielfeld) {
        this.spielfeld = spielfeld;
    }
    public boolean isInNachpruefung() {
        return inNachpruefung;
    }

    public void setInNachpruefung(boolean inNachpruefung) {
        this.inNachpruefung = inNachpruefung;
    }

    public int getNachpruefungVersuche() {
        return nachpruefungVersuche;
    }

    public void setNachpruefungVersuche(int nachpruefungVersuche) {
        this.nachpruefungVersuche = nachpruefungVersuche;
    }

    public void decreaseNachpruefungVersuche() {
        this.nachpruefungVersuche--;
    }


    public void setGui(SpielfigurGui gui) {
        this.gui = gui;
    }

    public int getIntelligenz() {
        return intelligenz;
    }

    public void setIntelligenz(int intelligenz) {
        this.intelligenz = intelligenz;
    }

    public boolean isPleite() {
        return pleite;
    }

    public void setPleite(boolean pleite) {
        this.pleite = pleite;
    }

    public Feld getAktuellesFeld() {
        return aktuellesFeld;
    }

    public void setAktuellesFeld(Feld aktuellesFeld) {
        this.aktuellesFeld = aktuellesFeld;
        if (gui != null) {
            gui.verschiebeSpielfigur(name, aktuellesFeld.getX(), aktuellesFeld.getY());
        }
    }

    public Spielfeld getSpielfeld() {
        return spielfeld;
    }

    public void landeAufFeld(Feld feld) {
        this.aktuellesFeld = feld;
        aktualisiereFeldInListe(feld);
        
        // Setze die Position auf die Koordinaten des Feldes
        setPosition(new Point(feld.getX(), feld.getY()));
    }

    public Feld getLandeFeld() {
        return aktuellesFeld;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Spielbrett Übersicht");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1775, 850);

        // Initialisiere das Spielfeld und die Spielfiguren
        Spielfeld spielfeld = new Spielfeld(50, 50, 612, 612);
        frame.add(spielfeld);

        SpielfigurGui gui = new SpielfigurGui(); // Beispiel für SpielfigurGui, anpassen wenn nötig
        List<Spielfigur> spielfiguren = initSpielfiguren(spielfeld, gui);
        frame.setVisible(true);

        // Testbewegung für eine Spielfigur
        Spielfigur figur = spielfiguren.get(0); // Erste Spielfigur
        spielfeld.würfelnUndBewegen(figur);
    }
}

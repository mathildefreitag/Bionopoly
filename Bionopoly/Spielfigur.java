package bionopoly;

import java.util.ArrayList;
import gui.SpielfigurGui;

public class Spielfigur extends Spieler {
    private String name;
    private int intelligenz;
    private boolean pleite;
    private Feld aktuellesFeld;
    private Spielfeld spielfeld;
    private static SpielfigurGui gui;

    // Konstruktor zum Initialisieren des Namens und des Startfelds
    public Spielfigur(String name, Feld startFeld) {
        super(name, 1500, 0, null); // Aufruf des Superkonstruktors
        this.name = name;
        this.intelligenz = 1500; // Startkapital
        this.pleite = false;
        this.aktuellesFeld = startFeld;
    }

    public String getName() {
        return name;
    }

    public void felderVorrücken(int augensumme) {
        if (spielfeld == null) {
            throw new IllegalStateException("Spielfeld wurde nicht initialisiert.");
        }
        int neuesFeldIndex = (aktuellesFeld.getIndex() + augensumme) % spielfeld.getAlleFelder().size();
        Feld neuesFeld = spielfeld.feldAmOrt(neuesFeldIndex);
        setAktuellesFeld(neuesFeld);
    }

    public void zugBeenden() {
        System.out.println(name + " hat seinen Zug beendet.");
    }

    public static ArrayList<Spielfigur> initSpielfiguren(Spielfeld spielfeld, SpielfigurGui gui) {
        ArrayList<Spielfigur> spielfiguren = new ArrayList<>();

        spielfiguren.add(new Spielfigur("Paramecium", spielfeld.feldAmOrt(0)));
        spielfiguren.add(new Spielfigur("Regenwurm", spielfeld.feldAmOrt(0)));
        spielfiguren.add(new Spielfigur("Heuschrecke", spielfeld.feldAmOrt(0)));
        spielfiguren.add(new Spielfigur("Seestern", spielfeld.feldAmOrt(0)));
        spielfiguren.add(new Spielfigur("Fisch", spielfeld.feldAmOrt(0)));
        spielfiguren.add(new Spielfigur("Schwein", spielfeld.feldAmOrt(0)));

        for (Spielfigur figur : spielfiguren) {
            figur.setSpielfeld(spielfeld);
            figur.setGui(gui);
        }

        return spielfiguren;
    }

    public void setSpielfeld(Spielfeld spielfeld) {
        this.spielfeld = spielfeld;
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


    /*public static void main(String[] args) {

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
    }*/
}

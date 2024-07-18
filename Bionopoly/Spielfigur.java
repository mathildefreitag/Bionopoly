package bionopoly;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import gui.SpielfigurGui;

public class Spielfigur extends Spieler {
	
	//Initialisierung der Variablen, Listen etc.
	private Feld aktuellesFeld;
    private Spielfeld spielfeld;
    private static SpielfigurGui gui;
    
    private String name;
    
    private boolean pleite;
    private boolean inNachprüfung;
    
    private int intelligenz;
    private int nachprüfungsVersuche;
    
    private Color farbe;
 
    
    private static List<Feld> spielfigurenFelder = new ArrayList<>(); //Liste, die die aktuellen Felder aller Spielfiguren speichert

    public Spielfigur(String name, Feld startFeld, Color farbe, Waehrung währung) {
        super(name, 1500, null); //wegen "extends Spieler"
        this.name = name;
        this.intelligenz = währung.getStartgeld(); //Startkapital auf Währungsklasse geu´zogen
        this.pleite = false;
        this.aktuellesFeld = spielfeld.getFeld(0); //Startfeld festgelegt
        this.farbe = farbe;
        spielfigurenFelder.add(startFeld); //Initialisierung des Startfeldes
        
    }
    
    //Getter und Setter verschiedener Methoden
    public Color getFarbe() {
        return farbe;
    }

    public void setSpielfeld(Spielfeld spielfeld) {
        this.spielfeld = spielfeld;
    }
    public boolean isInNachpruefung() {
        return inNachprüfung;
    }

    public void setInNachpruefung(boolean inNachprüfung) {
        this.inNachprüfung = inNachprüfung;
    }

    public int getNachprüfungsVersuche() {
        return nachprüfungsVersuche;
    }

    public void setNachprüfungsVersuche(int nachprüfungsVersuche) {
        this.nachprüfungsVersuche = nachprüfungsVersuche;
    }

    public void reduzierungNachprüfungVersuche() {
        this.nachprüfungsVersuche--;
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
            gui.verschiebeSpielfigur(name, aktuellesFeld.getX(), aktuellesFeld.getY()); //Spielfigur soll auf der Gui verschoben werden, funktioniert leider nicht
        }
    }

    public Spielfeld getSpielfeld() {
        return spielfeld;
    }

    public void landeAufFeld(Feld feld) {
        this.aktuellesFeld = feld;
        aktualisiereFeldInListe(feld);  //soll aktuellen Standort speichern, funktioniert leider nicht
    }

    public void felderVorrücken(int augensumme) {
        if (spielfeld == null) {
            throw new IllegalStateException("Spielfeld wurde nicht initialisiert."); //Wenn vorher ein Fehler passiert ist und das Spielfeld nicht innitialisiert wurde
        }
        int aktuellerIndex = aktuellesFeld.getIndex();  //Berechnet den neuen Index basierend auf dem aktuellen Feld
        int neuesFeldIndex = (aktuellerIndex + augensumme) % spielfeld.getAlleFelder().size();
        Feld neuesFeld = spielfeld.feldAmOrt(neuesFeldIndex);
        setAktuellesFeld(neuesFeld); //Aktuelles Feld  wird aktualisiert
    }

    //Methode zum Aktualisieren der Spielerpositionen: Funktioniert nicht
    private void aktualisiereFeldInListe(Feld neuesFeld) {
        int index = spielfigurenFelder.indexOf(aktuellesFeld);
        if (index != -1) {
            spielfigurenFelder.set(index, neuesFeld);
        }
    }

    //Methode um den Zug zu beenden
    public void zugBeenden() {
        System.out.println(name + " hat seinen Zug beendet."); //Output zum beenden des Zuges
    }
   

    public static ArrayList<Spielfigur> initSpielfiguren(Spielfeld spielfeld, SpielfigurGui gui, Waehrung währung) { //Liste in welcher Atribute der Figuren festgelegt werden
        ArrayList<Spielfigur> spielfiguren = new ArrayList<>(); //neue Liste erstellt

        String[] namen = {"Paramecium", "Regenwurm", "Heuschrecke", "Seestern", "Fisch", "Schwein"}; //Namen der Spielfiguren werden festgelegt
        Color[] farben = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.MAGENTA}; //Farben der Spielfiguren werden festgelegt

        for (int i = 0; i < namen.length; i++) {
            Spielfigur figur = new Spielfigur(namen[i], spielfeld.feldAmOrt(0), farben[i], währung); //Spielfiguren werden auf dem Startfeld initialisiert
            spielfiguren.add(figur); //Spielfiguren werden hinzugefügt
            figur.setSpielfeld(spielfeld);
            figur.setGui(gui);
        }
        return spielfiguren;
    }
}

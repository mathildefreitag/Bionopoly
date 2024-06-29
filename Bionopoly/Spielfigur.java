package bionopoly;

import java.util.ArrayList;
import java.util.List;

public class Spielfigur {
    //Namen deklarieren
    private String name;
    private int intelligenz;
    private boolean pleite;
    private Feld aktuellesFeld;
    private Spielfeld spielfeld;
  

    //Konstruktor zum Initialisieren des Namens
    public Spielfigur(String name, Feld startFeld) {
        this.name = name;
        this.intelligenz = 1500; //Startkapital
        this.pleite = false;
        this.aktuellesFeld = startFeld;
        
    }

    //Methode zum Aufrufen des Namens
    public String getName() {
        return name;
    }
   
    public void felderVorrücken( int augensumme) {
    	if (spielfeld == null) {
            throw new IllegalStateException("Spielfeld wurde nicht initialisiert.");
        }
        int neuesFeldIndex = (aktuellesFeld.getIndex() + augensumme) % spielfeld.getAlleFelder().size();
        Feld neuesFeld = spielfeld.feldAmOrt(neuesFeldIndex);
        setAktuellesFeld(neuesFeld);
    }
    
    public static void main(String[] args) {
        // Initialisiere das Spielfeld und die Spielfiguren
        Spielfeld spielfeld = new Spielfeld(50, 50, 612, 612);
        List<Spielfigur> spielfiguren = initSpielfiguren(spielfeld);
 // Testbewegung für eine Spielfigur
    Spielfigur figur = spielfiguren.get(0); // Erste Spielfigur
    spielfeld.würfelnUndBewegen(figur);
}
    
    
   
    public static List<Spielfigur> initSpielfiguren(Spielfeld spielfeld) {
        List<Spielfigur> spielfiguren = new ArrayList<>();
       
        spielfiguren.add(new Spielfigur("Paramecium", spielfeld.feldAmOrt(0)));
        spielfiguren.add(new Spielfigur("Regenwurm", spielfeld.feldAmOrt(0)));
        spielfiguren.add(new Spielfigur("Heuschrecke", spielfeld.feldAmOrt(0)));
        spielfiguren.add(new Spielfigur("Seestern", spielfeld.feldAmOrt(0)));
        spielfiguren.add(new Spielfigur("Fisch", spielfeld.feldAmOrt(0)));
        spielfiguren.add(new Spielfigur("Schwein", spielfeld.feldAmOrt(0)));
        
        return spielfiguren;
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
        }
        
        public Spielfeld getSpielfeld() {
            return spielfeld;
        }
}

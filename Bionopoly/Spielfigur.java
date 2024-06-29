package bionopoly;

import java.util.ArrayList;
import java.util.List;

public class Spielfigur {
    //Namen deklarieren
    private String name;
    private int intelligenz;
    private boolean pleite;
    private Feld aktuellesFeld;
  

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
   
    public void felderVorrücken(Spielfeld spielfeld, int augensumme) {
        int neuesFeldIndex = (aktuellesFeld.getIndex() + augensumme) % spielfeld.getAlleFelder().size();
        Feld neuesFeld = spielfeld.feldAmOrt(neuesFeldIndex);
        setAktuellesFeld(neuesFeld);
        System.out.println(name + " ist auf " + neuesFeld.getName() + " gelandet.");
    }
    
    public static void main(String[] args) {
        // Initialisiere das Spielfeld und die Spielfiguren
        Spielfeld spielfeld = new Spielfeld(50, 50, 612, 612);
        List<Spielfigur> spielfiguren = initSpielfiguren(spielfeld);
 // Testbewegung für eine Spielfigur
    Spielfigur figur = spielfiguren.get(0); // Erste Spielfigur
    spielfeld.würfelnUndBewegen(figur);
}
    
    /*public static void main(String[] args) {
    	 //Initialisiere das Spielfeld und die Spielfiguren
        Spielfeld spielfeld = new Spielfeld(0, 0, 800, 800);
        List<Spielfigur> spielfiguren = initSpielfiguren(spielfeld);
        Spielfigur figur = spielfiguren.get(0); // Erste Spielfigur, noch für die anderen machen?
        Würfel würfel = new Würfel();
        würfel.würfel();
        int augensumme = würfel.getAugensumme();
        figur.felderVorrücken(spielfeld, augensumme);
    }
    //Initialisiere die Spielfiguren auf dem Startfeld
    List<Spielfigur> spielfiguren = new ArrayList<>();
    Feld startFeld = spielfelder.feldAmOrt(0);*/
   
    private static List<Spielfigur> initSpielfiguren(Spielfeld spielfeld) {
        List<Spielfigur> spielfiguren = new ArrayList<>();
        spielfiguren.add(new Spielfigur("Regenwurm", spielfeld.feldAmOrt(0)));
        spielfiguren.add(new Spielfigur("Paramecium", spielfeld.feldAmOrt(1)));
        spielfiguren.add(new Spielfigur("Heuschrecke", spielfeld.feldAmOrt(2)));
        spielfiguren.add(new Spielfigur("Fisch", spielfeld.feldAmOrt(3)));
        spielfiguren.add(new Spielfigur("Seestern", spielfeld.feldAmOrt(4)));
        spielfiguren.add(new Spielfigur("Schwein", spielfeld.feldAmOrt(5)));
        return spielfiguren;
    }
    
 //Methode zum Anzeigen aller Spielfiguren
    /*private static void zeigeSpielfiguren(List<Spielfigur> spielfiguren) {
        for (int i = 0; i < spielfiguren.size(); i++) {
            System.out.println((i + 1) + ". " + spielfiguren.get(i).getName());
        }
    }*/
 
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
}

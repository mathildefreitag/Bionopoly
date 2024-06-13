package bionopoly;

import java.util.ArrayList;
import java.util.List;

public class Spielfigur {
    // Name deklarieren
    private String name;
    private int intelligenz;
    private boolean pleite;
    private Feld aktuellesFeld;
    private Spielfeld spielfeld;

    // Konstruktor zum Initialisieren des Namens
    public Spielfigur(String name, Feld startFeld, Spielfeld spielfeld) {
        this.name = name;
        this.intelligenz = 1500; // Startkapital
        this.pleite = false;
        this.aktuellesFeld = startFeld;
        this.spielfeld = spielfeld; // Spielfeld zuweisen
        
    }

    // Methode zum Aufrufen des Namens
    public String getName() {
        return name;
    }
    

    // Methode um auf augensumme Zugriff zu haben und Spielfigur basierend auf der Augensumme zu verschieben
   /* public void felderVorrücken(Würfel würfel) {
        int augensumme = würfel.getAugensumme();
        System.out.println("Die Spielfigur " + name + " rückt um " + augensumme + " Felder vor.");
    }
	*/
    public void felderVorrücken(Spielfeld spielfeld, int augensumme) {
        int neuesFeldIndex = (aktuellesFeld.getIndex() + augensumme) % spielfeld.getAlleFelder().size();
        Feld neuesFeld = spielfeld.feldAmOrt(neuesFeldIndex);
        setAktuellesFeld(neuesFeld);
        System.out.println(name + " ist auf " + neuesFeld.getName() + " gelandet.");
    }
    
    public static void main(String[] args) {
    	 // Initialisiere das Spielfeld und die Spielfiguren
        Spielfeld spielfeld = new Spielfeld(0, 0, 800, 800);
        /*List<Spielfigur> spielfiguren = new ArrayList<>();
        initSpielfiguren(spielfiguren, spielfeld);
		*/
        List<Spielfigur> spielfiguren = initSpielfiguren(spielfeld);
        /*// Setze die Anfangspositionen der Spielfiguren
        for (Spielfigur figur : spielfiguren) {
            figur.setAktuellesFeld(spielfeld.feldAmOrt(0));
        }
		*/
        // Beispielhafte Verwendung der Spielfigur und Würfeln
        Spielfigur figur = spielfiguren.get(0); // Erste Spielfigur
        Würfel würfel = new Würfel();
        würfel.würfel();
        int augensumme = würfel.getAugensumme();
        figur.felderVorrücken(spielfeld, augensumme);
    }
    // Initialisiere die Spielfiguren auf dem Startfeld
    List<Spielfigur> spielfiguren = new ArrayList<>();
    Feld startFeld = spielfeld.feldAmOrt(00);
    //void initSpielfiguren(spielfiguren, startFeld);

    // Initialisiert die 6 verschiedenen Spielfiguren
    /*private static void initSpielfiguren(List<Spielfigur> spielfiguren, Spielfeld spielfeld) {
        Feld startFeld = spielfeld.feldAmOrt(00);
    	spielfiguren.add(new Spielfigur("Regenwurm"));
        spielfiguren.add(new Spielfigur("Paramecium"));
        spielfiguren.add(new Spielfigur("Heuschrecke"));
        spielfiguren.add(new Spielfigur("Fisch"));
        spielfiguren.add(new Spielfigur("Seestern"));
        spielfiguren.add(new Spielfigur("Schwein"));
    }
	*/
    private static List<Spielfigur> initSpielfiguren(Spielfeld spielfeld) {
        List<Spielfigur> spielfiguren = new ArrayList<>();
        spielfiguren.add(new Spielfigur("Regenwurm", spielfeld.feldAmOrt(0), spielfeld));
        spielfiguren.add(new Spielfigur("Paramecium", spielfeld.feldAmOrt(0), spielfeld));
        spielfiguren.add(new Spielfigur("Heuschrecke", spielfeld.feldAmOrt(0), spielfeld));
        spielfiguren.add(new Spielfigur("Fisch", spielfeld.feldAmOrt(0), spielfeld));
        spielfiguren.add(new Spielfigur("Seestern", spielfeld.feldAmOrt(0), spielfeld));
        spielfiguren.add(new Spielfigur("Schwein", spielfeld.feldAmOrt(0), spielfeld));
        return spielfiguren;
    }
    
 // Methode zum Anzeigen aller Spielfiguren
    private static void zeigeSpielfiguren(List<Spielfigur> spielfiguren) {
        for (int i = 0; i < spielfiguren.size(); i++) {
            System.out.println((i + 1) + ". " + spielfiguren.get(i).getName());
        }
    }
    // Methode zum Verschieben einer Spielfigur von einer Position zu einer anderen
   
    ////Spielfigur aktuellerSpieler = spielfiguren.get(0);
    ////Würfel würfel = new Würfel();

   /* int paschCount = 0

    for (int i = 0; i < 3; i++) { // Simuliert drei Würfe
        würfel.würfel();

        if (würfel.istPasch()) {
            paschCount++;
            if (paschCount == 3) {
                aktuellerSpieler.zieheNachPrüfung();
                break;
            }
        } else {
            paschCount = 0;
        }

        ////aktuellerSpieler.felderVorrücken(würfel);
        */
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
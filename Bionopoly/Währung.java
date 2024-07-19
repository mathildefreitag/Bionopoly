package bionopoly;

// In der Klasse Waehrung wird die Starintelligenz festgelegt.

public class Waehrung {
    
	//Intitialisierung verschiedener Variablen etc. 
    private Spielfeld[][] feld;
    private Spielfigur[] spieler; 
    
    private int startgeld;
    private int überStart;

    
    public Waehrung() {
    	this.startgeld = 200;
    }
    
    public Waehrung(Spielfeld[][] feld, Spielfigur[] spieler, int überStart) { //Konstrukter mit den Parametern Spielfeld, Spielfigur,ÜberStart und Transaktion
    	
    	//Deklarierung einiger Variablen
        this.feld = feld;
        this.spieler = spieler;
        this.überStart = überStart;
    }
    
    //Getter und Setter für verschiedene Methoden
    public void setStartgeld(int startgeld) {
        this.startgeld = startgeld;
    }
 
    public Spielfeld[][] getFeld() { 
        return feld;
    }

    public void setFeld(Spielfeld[][] feld) {
        this.feld = feld;
    }

    public Spielfigur[] getSpieler() {
		return spieler;
    }

    public void setSpieler(Spielfigur[] spieler) {
        this.spieler = spieler;
    }

    public int getStartgeld() {
        return startgeld;
    }

    public int getÜberStart() {
        return überStart;
    }

    public void setÜberStart(int überStart) {
        this.überStart = überStart;
    }

    public void überStart(Spielfigur spieler) { //Methode die den Spielern 200 Intelligenzpunkte gibt wenn sie über Start ziehen
        spieler.setIntelligenz(spieler.getIntelligenz() + 200); //Spieler erhält 200 Intelligenz wenn er über das Startfeld zieht
    }
}

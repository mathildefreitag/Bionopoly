package bionopoly;

public class Währung {
    
    private Spielfeld[][] feld;
    private Spielfigur[] spieler; // Nicht static machen, damit jedes Währungsobjekt sein eigenes Spieler-Array hat
    private int startgeld;
    private int überStart;
    private int transaktion;

    // Standardkonstruktor
    public Währung() {
    	
    }
    
    // Konstruktor mit Parametern
    public Währung(Spielfeld[][] feld, Spielfigur[] spieler, int überStart, int transaktion) {
        this.feld = feld;
        this.spieler = spieler;
        this.überStart = überStart;
        this.transaktion = transaktion;
    }
    
    // Setzen des Startgelds
    public void setStartgeld(int startgeld) {
    	this.startgeld = startgeld;
    }

    // Rückgabe des Spielfelds
    public Spielfeld[][] getFeld() {
        return feld;
    }

    // Setzen des Spielfelds
    public void setFeld(Spielfeld[][] feld) {
        this.feld = feld;
    }

    // Rückgabe der Spieler
    public Spielfigur[] getSpieler() {
		return spieler;
    }

    // Setzen der Spieler
    public void setSpieler(Spielfigur[] spieler) {
        this.spieler = spieler;
    }

    // Rückgabe des Startgelds
    public int getStartgeld() {
        return startgeld;
    }

    // Rückgabe des Über-Start-Felds
    public int getÜberStart() {
        return überStart;
    }

    // Setzen des Über-Start-Felds
    public void setÜberStart(int überStart) {
        this.überStart = überStart;
    }

    // Rückgabe der Transaktion
    public int getTransaktion() {
        return transaktion;
    }

    // Setzen der Transaktion
    public void setTransaktion(int transaktion) {
        this.transaktion = transaktion;
    }

    // Methode zur Initialisierung der Spieler mit StartIntelligenz
    public void initialisiereSpieler() {
        for (Spielfigur s : spieler) {
            s.setIntelligenz(1500); // Jeder Spieler erhält 1500 Währung als StartIntelligenz
        }
    }

    // Methode, die einem Spieler 200 Währung gibt, wenn er über das Startfeld kommt
    public void überStart(Spielfigur spieler) {
        spieler.setIntelligenz(spieler.getIntelligenz() + 200); // Spieler erhält 200 Währung
    }
}

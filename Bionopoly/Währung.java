package bionopoly;

public class Währung {
    
    private Spielfeld[][] feld;
    private Spielfigur[] spieler;
    private int startgeld;
    private int überStart;
    private int transaktion;

    
    public Währung() {
    	
    }
    // Konstruktor
    public Währung(Spielfeld[][] feld, Spielfigur[] spieler, int überStart, int transaktion) {
        this.feld = feld;
        this.spieler = spieler;
        this.überStart = überStart;
        this.transaktion = transaktion;
    }
    
    public void Startgeld(int startgeld) {
    	this.startgeld = startgeld;
    }

    // Getter und Setter für 'feld'
    public Spielfeld[][] getFeld() {
        return feld;
    }

    public void setFeld(Spielfeld[][] feld) {
        this.feld = feld;
    }

    // Getter und Setter für 'spieler'
    public Spielfigur[] getSpieler() {
        return spieler;
    }

    public void setSpieler(Spielfigur[] spieler) {
        this.spieler = spieler;
    }

    // Getter und Setter für 'startgeld'
    public int getStartgeld() {
        return startgeld;
    }

    public void setStartgeld(int startgeld) {
        this.startgeld = startgeld;
    }

    // Getter und Setter für 'überStart'
    public int getStartfeldgeld() {
        return überStart;
    }

    public void setStartfeldgeld(int überStart) {
        this.überStart = überStart;
    }

    // Getter und Setter für 'transaktion'
    public int getTransaktion() {
        return transaktion;
    }

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


    /*public Währung (String spieler1, String spieler2, String spieler3, String spieler4, String spieler5, String spieler6) {
		
		spieler = new Spieler [6];
		
		spieler [0] = new Spieler (spieler1, "Paramecium");
		spieler [1] = new Spieler (spieler2, "Heuschrecke");
		spieler [2] = new Spieler (spieler3, "Fisch");
		spieler [3] = new Spieler (spieler4, "Seestern");
		spieler [4] = new Spieler (spieler5, "Schwein");
		spieler [5] = new Spieler (spieler6, "Regenwurm");
		
	}*/

}

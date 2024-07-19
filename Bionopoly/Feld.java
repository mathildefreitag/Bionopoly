package bionopoly;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingConstants;

//Die Klasse Feld dient hauptsächlich dem Festlegen von Besitzern der Module und dem Zeichnen der Felder

public class Feld extends JLabel {
	
	//Initialisierung von Variablen und Listen etc.
    private Spielfigur spielfigur;
    private Spieler besitzer;
    
    private int rotation;
    private int preis;
    private int miete;
    private int index;
    
    private String name;
    
    private Color feldFarbe;
    
    private List<Spielfigur> spielfiguren = new ArrayList<>();
    private static ArrayList<Feld> alleFelder = new ArrayList<>();

    public Feld(int x, int y, int width, int height, String name, int rotation, Color feldFarbe) {
    	
    	//deklarierung einiger Variablen
        this.name = name;
        this.rotation = rotation;
        this.preis = 0;
        this.miete = 0;
        this.feldFarbe = feldFarbe;
        this.besitzer = null;

        setLayout(null);
        JLabel nameLabel = new JLabel(name); //Label für die Namen der Spielfelder
        nameLabel.setBounds(0, 0, width, height); //Größe des labels festgelegt
        add(nameLabel); //Label hinzugefügt
        setBounds(x, y, width, height); //Größe und Koordinaten festgelegt
        setOpaque(true); //Das ganze ist durchscheinend, damit die Namen der Felder lesbar sind
        setBackground(feldFarbe); //Feldfarbe wird als Hintergrundfarbe festgelegt
        setHorizontalAlignment(SwingConstants.CENTER); //Horizontal zentrieren
        setVerticalAlignment(SwingConstants.CENTER); //Vertikal zentriert
    }
    
    // verschiedene Getter und Setter zu Methoden
    public String getName() {
        return name;
    }

    public void setPreis(int price) {
        this.preis = price;
    }

    public int getPreis() {
        return preis;
    }

    public void setMiete(int rent) {
        this.miete = rent;
    }

    public int getMiete() {
        return miete;
    }

    public Spieler getBesitzer() {
        return besitzer;
    }

    public void setBesitzer(Spieler besitzer) {
        if (this.besitzer == null) {
            this.besitzer = besitzer; //wenn das Feld noch keinen Besitzer hat, wird der aktuelle Spieler zum Besitzer
        } else {
            throw new UnsupportedOperationException("Das Feld hat bereits einen Besitzer.");
        }
    }

    public int getIndex() {
        return index;
    }

    public void spielfigurHinzufügen(Spielfigur figur) {
        spielfiguren.add(figur);
    }

    public void spielfigurWegnehmen(Spielfigur figur) {
        spielfiguren.remove(figur);
    }

    public List<Spielfigur> getSpielfiguren() {
        return spielfiguren;
    }
   

    protected void paintComponent(Graphics spielfelder) {
    	
    	Graphics2D zeichnung = (Graphics2D) spielfelder; //erweiterte Zeichenoptionen
        zeichnung.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //Aktiviert Anti-Aliasing für glattere Zeichnungen
        AffineTransform transformation = zeichnung.getTransform(); //Speichert die aktuelle Transformation
        Shape form = zeichnung.getClip(); //Speichert die aktuelle Form
        double x = getWidth() / 2.0; //berechnung der mitte des Panels
        double y = getHeight() / 2.0;
        transformation.rotate(Math.toRadians(rotation), x, y); //rotert die Felder um ihre vorgegebene Rotation
        zeichnung.setTransform(transformation); //die rotierten Zeichnungen werden in das 2D-Objekt eingefügt
        zeichnung.setClip(form); //aktuelle form wird geklippt
        super.paintComponent(spielfelder); //Elternklasse wird aufgerufen, damit das ganze gezeichnet werden kann
       
        for (Feld feld : alleFelder) { //Die Felder werden alle gezeichnet
        	
        	zeichnung.setColor(feld.feldFarbe); //festlegen der farbe
        	zeichnung.fillRect(feld.getX(), feld.getY(), 200, 200); //Anpassen der Größe
        }

        //Die Spielfiguren sollen eigentlich gezeichnet werden, klappt leider nicht
        for (Spielfigur figur : spielfiguren) {
        	
            zeichnung.setColor(figur.getFarbe()); //Farbe wird aus Spielfigur gehohlt
            int quadratGröße = 50; //Größe der Spielfiguren
            int xPos = (getWidth() - quadratGröße) / 2; //Horizontal in der Mitte des Feldes platziert
            int yPos = (getHeight() - quadratGröße) / 2; //Vertikal in der Mitte des Feldes platziert
            zeichnung.fillRect(xPos, yPos, quadratGröße, quadratGröße); //Quadrat wird ausgefüllt
        }

        

        zeichnung.setTransform(transformation); //Zurücksetzen der Transformation für andere Zeichnungen
    }
}

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

public class Feld extends JLabel {
    private Spielfigur spielfigur;
    private int rotation;
    private int preis;
    private int miete;
    private Spieler besitzer;
    private int index;
    
    private String name;
    private List<Spielfigur> spielfiguren = new ArrayList<>();
    private static ArrayList<Feld> alleFelder = new ArrayList<>();
    private Color feldColor; // Add color attribute

    public Feld(int x, int y, int width, int height, String name, int rotation, Color feldColor) {
        this.name = name;
        this.index = index;
        this.rotation = rotation;
        this.preis = 0;
        this.miete = 0;
        this.feldColor = feldColor;
        this.besitzer = null;

        setLayout(null);
        JLabel nameLabel = new JLabel(name);
        nameLabel.setBounds(0, 0, width, height);
        add(nameLabel);
        setBounds(x, y, width, height);
        setOpaque(true);
        setBackground(feldColor);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
    }

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
            this.besitzer = besitzer;
        } else {
            throw new UnsupportedOperationException("Das Feld hat bereits einen Besitzer.");
        }
    }

    public int getIndex() {
        return index;
    }

    public void addSpielfigur(Spielfigur figur) {
        spielfiguren.add(figur);
    }

    public void removeSpielfigur(Spielfigur figur) {
        spielfiguren.remove(figur);
    }

    public List<Spielfigur> getSpielfiguren() {
        return spielfiguren;
    }
   

    protected void paintComponent(Graphics g) {
    	Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        AffineTransform aT = g2.getTransform();
        Shape oldshape = g2.getClip();
        double x = getWidth() / 2.0;
        double y = getHeight() / 2.0;
        aT.rotate(Math.toRadians(rotation), x, y);
        g2.setTransform(aT);
        
        int boxWidth = 50;
        int boxHeight = 20;
        int padding = 10;
        g2.setClip(oldshape);
        super.paintComponent(g);
        g2.setTransform(aT);
        super.paintComponent(g);
        // Paint each player's figure as a colored square
        for (Feld feld : alleFelder) {
        	g2.setColor(feld.feldColor);
            for (Spielfigur figur : spielfiguren) {
                g2.setColor(Color.BLACK);
                g2.fillRect(feld.getX(), feld.getY(), 200, 200); // Adjust size as needed
            }
        }
    }
}

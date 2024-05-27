package Bionopoly;

	import javax.swing.JLabel;
	import java.awt.Color;
	import java.awt.Graphics;
	import java.awt.Graphics2D;
	import java.awt.RenderingHints;
	import java.awt.Shape;
	import java.awt.geom.AffineTransform;
	import javax.swing.SwingConstants;

	public class Feld extends JLabel {
	    private int rotation;
	    private int preis;
	    private int miete;

	    public Feld(int x, int y, int width, int height, String name, int rotation) {
	        super(name); // Setze den Text des Labels
	        this.rotation = rotation;
	        this.preis = 0; 
	        this.miete = 0;
	        setBounds(x, y, width, height);
	        setOpaque(true);
	        setBackground(Color.LIGHT_GRAY);
	        setHorizontalAlignment(SwingConstants.CENTER);
	        setVerticalAlignment(SwingConstants.CENTER);
	    }
	    public void setPrice(int price) {
	        this.preis = price;
	    }

	    // Neue Methode zum Abrufen des Preises
	    public int getPrice() {
	        return preis;
	    }
	    
	    public void setMiete(int rent) {
	        this.miete = rent;
	    }

	    // Methode zum Abrufen der Miete
	    public int getMiete() {
	        return miete;
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        Graphics2D g2 = (Graphics2D) g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        AffineTransform aT = g2.getTransform();
	        Shape oldshape = g2.getClip();
	        double x = getWidth() / 2.0;
	        double y = getHeight() / 2.0;
	        aT.rotate(Math.toRadians(rotation), x, y);
	        g2.setTransform(aT);
	        g2.setClip(oldshape);
	        super.paintComponent(g);
	        g2.setTransform(aT); // Restore original transform
	    }
	}



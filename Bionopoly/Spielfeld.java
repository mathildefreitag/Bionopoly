package Bionopoly;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Spielfeld extends JPanel {
	

	//private Spielbrett spielbrett;
	//private Spielfigur charakter;
	//private Spieler[] spieler;
	private Währung intelligenz;

	static int miete;
	static boolean besitzer;
	static String besucher;
	static String feldname;

	//public static boolean isFree(boolean besitzer) {
		//if (besitzer == null) {
		//	return true;
		//}
		//return false;
	//}

	/*public static void feldKaufen(double intellligenz, double preis) {

		if (isFree == true) {

			if (intelligenz >= preis) {
				System.out.println(besucher + " hat " + feldname + " für " + preis + " Intelligenz erhalten.");
				// preis muss von jetzigem intelligenzstand abgezogen werden
			} else {
				System.out.println("Du besitz nicht genug Intelligenz um dieses Modul zu erhalten.");
			}
		} else {
			System.out.println("Das Feld wurde bereits von " + besitzer + " geakuft. Die zu zahlende Miete beträgt: "
					+ miete + " Intelligenz.");
		}
	}
	*/

	/*private ArrayList<Feld> alleFelder = new ArrayList<Feld>();
	private ArrayList<Feld> unkaufbareFelder = new ArrayList<Feld>();

	public ArrayList<Feld> getAlleFelder() {
		return alleFelder;
	}

	public ArrayList<Feld> getUnkaufbareFelder() {
		return unkaufbareFelder;
	}

	public Feld feldAmOrt(int ort) {
		return alleFelder.get(ort);
	}

	public Spielbrett(int xKoord, int yKoord, int breite, int höhe) {
		setBorder(new LineBorder(new Color (0, 0, 0)));
		setBounds(xKoord, yKoord, 612, 612);
		this.setLayout(null);
		initialisierungModule();
	}
	*/
	private ArrayList<Feld> alleFelder = new ArrayList<Feld>();
    private ArrayList<Feld> unkaufbareFelder = new ArrayList<Feld>();

    public ArrayList<Feld> getAlleFelder() {
        return alleFelder;
    }

    public ArrayList<Feld> getUnkaufbareFelder() {
        return unkaufbareFelder;
    }

    public Feld feldAmOrt(int ort) {
        return alleFelder.get(ort);
    }

    public Spielfeld(int xKoord, int yKoord, int breite, int hoehe) {
        setBorder(new LineBorder(new Color(0, 0, 0)));
        setBounds(xKoord, yKoord, breite, hoehe);
        this.setLayout(null);
        initialisierungModule();
    }
        
        // JLabel erstellen und konfigurieren
       /* JLabel lblMonopoly = new JLabel("MONOPOLY") {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                AffineTransform aT = g2.getTransform();
                Shape oldshape = g2.getClip();
                double x = getWidth() / 2.0;
                double y = getHeight() / 2.0;
                aT.rotate(Math.toRadians(-35), x, y);
                g2.setTransform(aT);
                g2.setClip(oldshape);
                super.paintComponent(g);
            }
        };
        

        lblMonopoly.setForeground(Color.WHITE);
        lblMonopoly.setBackground(Color.RED);
        lblMonopoly.setOpaque(true);
        lblMonopoly.setHorizontalAlignment(SwingConstants.CENTER);
        lblMonopoly.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
        lblMonopoly.setBounds(179, 277, 263, 55);
        this.add(lblMonopoly);
    }
*/

    public static void main(String[] args) {
        JFrame frame = new JFrame("Spielbrett Beispiel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.add(new Spielfeld(50, 50, 612, 612));
        frame.setVisible(true);
    }

	private void initialisierungModule() {
		String[] modulNamen = { "Start", 
				"Digital Learning/ Englisch", 
				"Gemeinschaftsfeld", 
				"Präperationstechnik",
				"O-Woche", 
				"Werder Straße", 
				"Allg. Biologie/ Bionik", 
				"Mathe/ Informatik", 
				"Statistische Datenanalyse",
				"Chemie/ Physik", 
				"Nachprüfung/ Klausureinsicht", 
				"Spezielle Biologie", 
				"Bibilothek", 
				"Physiologie",
				"Material/ Mechanik", 
				"Neustadswall", 
				"Konstruktion/ CAD", 
				"Gemeinschaftsfeld", 
				"Projekt Management",
				"Wahlpflichtmodul", 
				"Urlaubssemester", 
				"Lokomotion", 
				"Ereignisfeld", 
				"Finite Elemente Methode",
				"Spezirlle Werkstoffkunde", 
				"Airport", 
				"Interkulturelle Kompetenz", 
				"Auslandssemester",
				"Hochschulsport", 
				"Auslandsnachbereitung", 
				"Prüfung nicht bestanden", 
				"Entwichlungsprojekt 'Bionik' ",
				"Organisationsbionik/ BWL", 
				"Gemeinschaftsfeld", 
				"Optimierungsverfahren", 
				"Bionik Innovations-Centrum",
				"Ereignisfeld", 
				"Bachelor Thesis", 
				"Bachelor Abschluss-Party", 
				"Bachelorabschluss" };
		
		int[] preise = { 0, 60, 0, 60, 0,  200, 100, 100, 100, 120, 0, 140, 150, 140, 160, 200, 180, 0, 180, 200, 0, 220, 0, 220, 240, 200, 260, 260, 150, 280, 0, 300, 300, 0, 320, 200, 0, 350, 0, 400};
		int[] miete = {0, 2, 0, 4, 0, 25, 6, 6, 6, 8, 0, 10, 0, /*statt der vorherigen 0 augenzahl *4 ,*/ 10, 12, 25, 14, 0, 14, 16, 0, 18, 0, 18, 20, 25, 22, 22, 0, /* statt der vorherigen 0 augenzahl * 4,*/ 24, 0, 26, 26, 0, 28, 25, 0, 35, 0, 50};
		 
		
		//System.out.println("Länge von modulNamen: " + modulNamen.length);
	    //System.out.println("Länge von preise: " + preise.length);
	    //System.out.println("Länge von miete: " + miete.length);
	    
		for (int i = 0; i < modulNamen.length; i++) {
	            Feld feld = new Feld(6, 1000 - 100 * i, 100, 100, modulNamen[i], 90);
	            feld.setPrice(preise[i]);
	            feld.setMiete(miete[i]);
	            this.add(feld);
	            alleFelder.add(feld);
	            unkaufbareFelder.add(feld);
	        }
	    }
		// Felder Links
		/*
		Feld feld00 = new Feld(6, 10006, 100, 100, modulNamen[0], 45);
		this.add(feld00);
		feld.setPrice(preise[i]);
		alleFelder.add(feld00);
		unkaufbareFelder.add(feld00);

		Feld feld01 = new Feld(6, 906, 100, 100, modulNamen[1], 90);
		this.add(feld01);
		alleFelder.add(feld01);
		unkaufbareFelder.add(feld01);

		Feld feld02 = new Feld(6, 806, 100, 100, modulNamen[2], 90);
		this.add(feld02);
		alleFelder.add(feld02);
		unkaufbareFelder.add(feld02);

		Feld feld03 = new Feld(6, 706, 100, 100, modulNamen[3], 90);
		this.add(feld03);
		alleFelder.add(feld03);
		unkaufbareFelder.add(feld03);

		Feld feld04 = new Feld(6, 606, 100, 100, modulNamen[4], 90);
		this.add(feld04);
		alleFelder.add(feld04);
		unkaufbareFelder.add(feld04);

		Feld feld05 = new Feld(6, 506, 100, 100, modulNamen[5], 90);
		this.add(feld05);
		alleFelder.add(feld05);
		unkaufbareFelder.add(feld05);

		Feld feld06 = new Feld(6, 406, 100, 100, modulNamen[6], 90);
		this.add(feld06);
		alleFelder.add(feld06);
		unkaufbareFelder.add(feld06);

		Feld feld07 = new Feld(6, 306, 100, 100, modulNamen[7], 90);
		this.add(feld07);
		alleFelder.add(feld07);
		unkaufbareFelder.add(feld07);

		Feld feld08 = new Feld(6, 206, 100, 100, modulNamen[8], 90);
		this.add(feld08);
		alleFelder.add(feld08);
		unkaufbareFelder.add(feld08);

		Feld feld09 = new Feld(6, 106, 100, 100, modulNamen[9], 90);
		this.add(feld09);
		alleFelder.add(feld09);
		unkaufbareFelder.add(feld09);

		// Felder Oben
		Feld feld10 = new Feld(106, 6, 100, 100, modulNamen[10], 135);
		this.add(feld10);
		alleFelder.add(feld10);
		unkaufbareFelder.add(feld10);

		Feld feld11 = new Feld(206, 6, 100, 100, modulNamen[11], 180);
		this.add(feld11);
		alleFelder.add(feld11);
		unkaufbareFelder.add(feld11);

		Feld feld12 = new Feld(306, 6, 100, 100, modulNamen[12], 180);
		this.add(feld12);
		alleFelder.add(feld12);
		unkaufbareFelder.add(feld12);

		Feld feld13 = new Feld(406, 6, 100, 100, modulNamen[13], 180);
		this.add(feld13);
		alleFelder.add(feld13);
		unkaufbareFelder.add(feld13);

		Feld feld14 = new Feld(506, 6, 100, 100, modulNamen[14], 180);
		this.add(feld14);
		alleFelder.add(feld14);
		unkaufbareFelder.add(feld14);

		Feld feld15 = new Feld(606, 6, 100, 100, modulNamen[15], 180);
		this.add(feld15);
		alleFelder.add(feld15);
		unkaufbareFelder.add(feld15);

		Feld feld16 = new Feld(706, 6, 100, 100, modulNamen[16], 180);
		this.add(feld16);
		alleFelder.add(feld16);
		unkaufbareFelder.add(feld16);

		Feld feld17 = new Feld(806, 6, 100, 100, modulNamen[17], 180);
		this.add(feld17);
		alleFelder.add(feld17);
		unkaufbareFelder.add(feld17);

		Feld feld18 = new Feld(906, 6, 100, 100, modulNamen[18], 180);
		this.add(feld18);
		alleFelder.add(feld18);
		unkaufbareFelder.add(feld18);

		Feld feld19 = new Feld(1006, 6, 100, 100, modulNamen[19], 180);
		this.add(feld19);
		alleFelder.add(feld19);
		unkaufbareFelder.add(feld19);

		// Felder Rechts
		Feld feld20 = new Feld(1006, 106, 100, 100, modulNamen[20], -135);
		this.add(feld20);
		alleFelder.add(feld20);
		unkaufbareFelder.add(feld20);

		Feld feld21 = new Feld(1006, 206, 100, 100, modulNamen[21], -90);
		this.add(feld21);
		alleFelder.add(feld21);
		unkaufbareFelder.add(feld21);

		Feld feld22 = new Feld(1006, 306, 100, 100, modulNamen[22], -90);
		this.add(feld22);
		alleFelder.add(feld22);
		unkaufbareFelder.add(feld22);

		Feld feld23 = new Feld(1006, 406, 100, 100, modulNamen[23], -90);
		this.add(feld23);
		alleFelder.add(feld23);
		unkaufbareFelder.add(feld23);

		Feld feld24 = new Feld(1006, 506, 100, 100, modulNamen[24], -90);
		this.add(feld24);
		alleFelder.add(feld24);
		unkaufbareFelder.add(feld24);

		Feld feld25 = new Feld(1006, 606, 100, 100, modulNamen[25], -90);
		this.add(feld25);
		alleFelder.add(feld25);
		unkaufbareFelder.add(feld25);

		Feld feld26 = new Feld(1006, 706, 100, 100, modulNamen[26], -90);
		this.add(feld26);
		alleFelder.add(feld26);
		unkaufbareFelder.add(feld26);

		Feld feld27 = new Feld(1006, 806, 100, 100, modulNamen[27], -90);
		this.add(feld27);
		alleFelder.add(feld27);
		unkaufbareFelder.add(feld27);

		Feld feld28 = new Feld(1006, 906, 100, 100, modulNamen[28], -90);
		this.add(feld28);
		alleFelder.add(feld28);
		unkaufbareFelder.add(feld28);

		Feld feld29 = new Feld(1006, 1006, 100, 100, modulNamen[29], -90);
		this.add(feld29);
		alleFelder.add(feld29);
		unkaufbareFelder.add(feld29);

		// Felder Unten
		Feld feld30 = new Feld(1006, 1006, 100, 100, modulNamen[30], -45);
		this.add(feld30);
		alleFelder.add(feld30);
		unkaufbareFelder.add(feld30);

		Feld feld31 = new Feld(906, 1006, 100, 100, modulNamen[31], 0);
		this.add(feld31);
		alleFelder.add(feld31);
		unkaufbareFelder.add(feld31);

		Feld feld32 = new Feld(806, 1006, 100, 100, modulNamen[32], 0);
		this.add(feld32);
		alleFelder.add(feld32);
		unkaufbareFelder.add(feld32);

		Feld feld33 = new Feld(706, 1006, 100, 100, modulNamen[33], 0);
		this.add(feld33);
		alleFelder.add(feld33);
		unkaufbareFelder.add(feld33);

		Feld feld34 = new Feld(606, 1006, 100, 100, modulNamen[34], 0);
		this.add(feld34);
		alleFelder.add(feld34);
		unkaufbareFelder.add(feld34);

		Feld feld35 = new Feld(506, 1006, 100, 100, modulNamen[35], 0);
		this.add(feld35);
		alleFelder.add(feld35);
		unkaufbareFelder.add(feld35);

		Feld feld36 = new Feld(406, 1006, 100, 100, modulNamen[36], 0);
		this.add(feld36);
		alleFelder.add(feld36);
		unkaufbareFelder.add(feld36);

		Feld feld37 = new Feld(306, 1006, 100, 100, modulNamen[37], 0);
		this.add(feld37);
		alleFelder.add(feld37);
		unkaufbareFelder.add(feld37);

		Feld feld38 = new Feld(206, 1006, 100, 100, modulNamen[38], 0);
		this.add(feld38);
		alleFelder.add(feld38);
		unkaufbareFelder.add(feld38);

		Feld feld39 = new Feld(106, 1006, 100, 100, modulNamen[39], 0);
		this.add(feld39);
		alleFelder.add(feld39);
		unkaufbareFelder.add(feld39);

		// preise festlegen
		
		 */

		/*square01.setPrice(60);
		square03.setPrice(60);
		square05.setPrice(200);
		square06.setPrice(100);
		square07.setPrice(100);
		square08.setPrice(100);
		square09.setPrice(120);

		square11.setPrice(140);
		square12.setPrice(150);
		square13.setPrice(140);
		square14.setPrice(160);
		square15.setPrice(200);
		square16.setPrice(180);
		square18.setPrice(180);
		square19.setPrice(200);

		square21.setPrice(220);
		square23.setPrice(220);
		square24.setPrice(240);
		square25.setPrice(200);
		square26.setPrice(260);
		square27.setPrice(260);
		square28.setPrice(150);
		square29.setPrice(280);

		square31.setPrice(300);
		square32.setPrice(300);
		square34.setPrice(320);
		square35.setPrice(200);
		square37.setPrice(350);
		square39.setPrice(400);
		*/

	/*// Miete festlegen
		square01.setRentPrice(2);
		square03.setRentPrice(4);

		square05.setRentPrice(25); // extra Regel

		square06.setRentPrice(6);
		square07.setRentPrice(6);
		square08.setRentPrice(6);
		square09.setRentPrice(8);

		square11.setRentPrice(10);

		square12.setRentPrice(); // extra Regel

		square13.setRentPrice(10);
		square14.setRentPrice(12);

		square15.setRentPrice(200); // extra Regel

		square16.setRentPrice(14);
		square18.setRentPrice(14);
		square19.setRentPrice(16);

		square21.setRentPrice(18);
		square23.setRentPrice(18);
		square24.setRentPrice(20);

		square25.setRentPrice(25); // extra Regel

		square26.setRentPrice(22);
		square27.setRentPrice(22);

		square28.setRentPrice(); // extra Regel

		square29.setRentPrice(24);

		square31.setRentPrice(26);
		square32.setRentPrice(26);
		square34.setRentPrice(28);

		square35.setRentPrice(25); // extra Regel

		square37.setRentPrice(35);
		square39.setRentPrice(50);

	}
*/
	public Spielfeld() {
		setLayout(null); // Verwenden Sie null Layout, um absolute Positionen zu verwenden

		JLabel lblMonopoly = new JLabel("MONOPOLY") {
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				AffineTransform aT = g2.getTransform();
				Shape oldshape = g2.getClip();
				double x = getWidth() / 2.0;
				double y = getHeight() / 2.0;
				aT.rotate(Math.toRadians(-35), x, y);
				g2.setTransform(aT);
				g2.setClip(oldshape);
				super.paintComponent(g);
			}
		};

		lblMonopoly.setForeground(Color.WHITE);
		lblMonopoly.setBackground(Color.RED);
		lblMonopoly.setOpaque(true);
		lblMonopoly.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonopoly.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		lblMonopoly.setBounds(179, 277, 263, 55);
		add(lblMonopoly);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	private static void createAndShowGUI() {
		JFrame frame = new JFrame("Monopoly Label Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Spielfeld());
		frame.setSize(700, 600);
		frame.setVisible(true);
	}

	public static void swingUntilities(String[] args) {
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() {
				createAndShowGUI();
			}
		}
		);
}}
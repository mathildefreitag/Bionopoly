package Bionopoly;

import java.util.ArrayList;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Spielfeld extends JPanel {

    // private Spielbrett spielbrett;
    // private Spielfigur charakter;
    // private Spieler[] spieler;
    private Währung intelligenz;

    static int miete;
    static boolean besitzer;
    static String besucher;
    static String feldname;

    // public static boolean isFree(boolean besitzer) {
    // if (besitzer == null) {
    // return true;
    // }
    // return false;
    // }

    /*
     * public static void feldKaufen(double intellligenz, double preis) {
     * 
     * if (isFree == true) {
     * 
     * if (intelligenz >= preis) { System.out.println(besucher + " hat " + feldname
     * + " für " + preis + " Intelligenz erhalten."); // preis muss von jetzigem
     * intelligenzstand abgezogen werden } else { System.out.
     * println("Du besitz nicht genug Intelligenz um dieses Modul zu erhalten."); }
     * } else { System.out.println("Das Feld wurde bereits von " + besitzer +
     * " geakuft. Die zu zahlende Miete beträgt: " + miete + " Intelligenz."); } }
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Spielbrett Übersicht");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1775, 850);
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
                "Spezielle Werkstoffkunde",
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
                "Bachelor \nThesis",
                "Bachelor Abschluss-Party",
                "Bachelorabschluss" };

        int[] preis = { 0, 60, 0, 60, 0, 200, 100, 100, 100, 120, 0, 140, 150, 140, 160, 200, 180, 0, 180, 200, 0, 220, 0, 220, 240, 200, 260, 260, 150, 280, 0, 300, 300, 0, 320, 200, 0, 350, 0, 400 };
        int[] miete = { 0, 2, 0, 4, 0, 25, 6, 6, 6, 8, 0, 10, 0, /* statt der vorherigen 0 augenzahl *4 , */ 10, 12, 25, 14, 0, 14, 16, 0, 18, 0, 18, 20, 25, 22, 22, 0, /* statt der vorherigen 0 augenzahl * 4, */ 24, 0, 26, 26, 0, 28, 25, 0, 35, 0, 50 };

        for (int i = 0; i < modulNamen.length; i++) {
            int x, y;
            int rotation = 90;

            if (0 < i && i < 10) { // Felder 00 bis 09 auf der linken Seite
                x = 390;
                y = 655 - 64 * i;

            } else if (10 < i && i < 20) { // Felder 10 bis 19 oben
                x = 408 + 64 * (i - 10);
                y = 0;
                rotation = 180; // Text um 90° drehen, damit er nach außen zeigt

            } else if (20 < i && i < 30) { // Felder 20 bis 29 rechts
                x = 1068;
                y = 15 + 64 * (i - 20);
                rotation = -90;

            } else if (30 < i && i < 40) { // Felder 31 bis 39 unten
                x = 1049 - 64 * (i - 30);
                y = 670;
                rotation = 0; // Text um 90° drehen, damit er nach außen zeigt
            } else {
                continue;
            }

            Feld feld = new Feld(x, y, 64, 100, modulNamen[i], rotation);
            feld.setPreis(preis[i]);
            feld.setMiete(miete[i]);
            this.add(feld);
            alleFelder.add(feld);
            if (preis[i] == 0) {
                unkaufbareFelder.add(feld);
            }
        }

        for (int i = 0; i <= 30; i += 10) {
            int z, a;
            int rotationb = 90;

            if (i == 0) { // Feld 00
                z = 372;
                a = 670;

            } else if (i == 10) { // Feld 10
                z = 372;
                a = 0;
                rotationb = 180;

            } else if (i == 20) { // Feld 20
                z = 1050;
                a = 0;
                rotationb = -90;

            } else if (i == 30) { // Feld 30
                z = 1050;
                a = 670;
                rotationb = 0;

            } else {
                continue;
            }

            Feld feld = new Feld(z, a, 100, 100, modulNamen[i], rotationb);
            feld.setPreis(preis[i]);
            feld.setMiete(miete[i]);
            this.add(feld);
            alleFelder.add(feld);
            if (preis[i] == 0) {
                unkaufbareFelder.add(feld);
            }
        }
    }
}

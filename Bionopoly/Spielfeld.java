package bionopoly;

import java.util.ArrayList;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.Scanner; //brauchen wir später vermutlich nicht mehr, hab das jetzt erstaml importiert damit der code erstmal funktioniert

public class Spielfeld extends JPanel {

    // private Spielbrett spielbrett;
    // private Spielfigur charakter;
    // private Spieler[] spieler;
    private Spielfigur intelligenz;

    static int miete;
    static boolean besitzer;
    static String besucher;
    static String feldname;
    private int anzahlPasche;

    private ArrayList<Feld> alleFelder = new ArrayList<Feld>();
    private ArrayList<Feld> unkaufbareFelder = new ArrayList<Feld>();
    private ArrayList<Spielfigur> spielerListe;

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
            } 
            else if (10 < i && i < 20) { // Felder 10 bis 19 oben
                x = 408 + 64 * (i - 10);
                y = 0;
                rotation = 180; // Text um 90° drehen, damit er nach außen zeigt
            } 
            else if (20 < i && i < 30) { // Felder 20 bis 29 rechts
                x = 1068;
                y = 15 + 64 * (i - 20);
                rotation = -90;
            } 
            else if (30 < i && i < 40) { // Felder 31 bis 39 unten
                x = 1049 - 64 * (i - 30);
                y = 670;
                rotation = 0; // Text um 90° drehen, damit er nach außen zeigt
            } 
            else {
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
            }   
            else if (i == 10) { // Feld 10
                z = 372;
                a = 0;
                rotationb = 180;
            } 
            else if (i == 20) { // Feld 20
                z = 1050;
                a = 0;
                rotationb = -90;
            } 
            else if (i == 30) { // Feld 30
                z = 1050;
                a = 670;
                rotationb = 0;
            } 
            else {
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
    
    public boolean isPasch(int würfel1, int würfel2) {
        return würfel1 == würfel2;
    }
    
    public void würfelnUndBewegen(Spielfigur spieler) {
        Würfel würfel = new Würfel();
        würfel.würfel();
        int augensumme = würfel.getAugensumme();
        
        //Feld 20: Urlaubssemester
        if (spieler.getAktuellesFeld().getName().equalsIgnoreCase("Urlaubssemester")) {
            System.out.println(spieler.getName() + " genießt das Urlaubssemester und kann einfach mal nichts tun.");
            return;
        }
        
        //Feld 30: Prüfug nicht bestanden (Spieler wird zu Feld 10 verbannt)
        if (spieler.getAktuellesFeld().getName().equalsIgnoreCase("Prüfung nicht bestanden")) {
            spieler.setAktuellesFeld(feldAmOrt(10));
            System.out.println(spieler.getName() + " wurde zur Nachprüfung auf Feld 10 verbannt.");
            return;
        }
        
        //Feld 10: Sonderregelung für Rückkehr ---> bin mir nicht sicher ob das aktuell nur gilt wenn man von Feld 30 kommt bzw 3x einen Pasch gewürfelt hat oder einfach allgemein für das feld
        if (spieler.getAktuellesFeld().getName().equalsIgnoreCase("Nachprüfung/ Klausureinsicht")) {
            //Spieler muss innerhalb der nächsten 3 Runden einen Pasch würfeln
        	anzahlPasche = 0;
            for (int i = 0; i < 3; i++) {
                würfel.würfel();
                int würfel1 = würfel.getWürfel1();
                int würfel2 = würfel.getWürfel2();
                if (isPasch(würfel1, würfel2)) {
                    anzahlPasche++;
                    if (anzahlPasche == 3) {
                        System.out.println(spieler.getName() + " hat einen Pasch gewürfelt und ist frei von der Nachprüfung auf Feld 10.");
                        return;
                    }
                } 
                else {
                    break; //Spieler hat keinen Pasch gewürfelt
                }
            }
            
            //Spieler hat keinen Pasch gewürfelt und muss 50 Intelligenz Strafe zahlen
            spieler.setIntelligenz(spieler.getIntelligenz() - 50);
            System.out.println(spieler.getName() + " hat keinen Pasch gewürfelt und muss 50 Intelligenz Strafe zahlen.");
        }
        //Normale Bewegung basierend auf der Augensumme
        int neuesFeldIndex = (spieler.getAktuellesFeld().getIndex() + augensumme) % alleFelder.size();
        Feld neuesFeld = alleFelder.get(neuesFeldIndex);
        spieler.setAktuellesFeld(neuesFeld);
        System.out.println(spieler.getName() + " hat " + augensumme + " gewürfelt und ist auf " + neuesFeld.getName() + " gelandet.");
    }
    
    //Methode, um zu überprüfen, ob der Spieler Felder zu verkaufen hat
    public boolean spielerHatFelderZuVerkaufen(Spielfigur spieler) {
        for (Feld feld : alleFelder) {
            if (feld.getBesitzer() != null && feld.getBesitzer().equals(spieler)) {
                return true;
            }
        }
        return false;
    }

    //Methode, um ein Feld zu verkaufen
    public void feldVerkaufen(Spielfigur spieler) {
        for (Feld feld : alleFelder) {
            if (feld.getBesitzer() != null && feld.getBesitzer().equals(spieler)) {
                spieler.setIntelligenz(spieler.getIntelligenz() + feld.getPreis() / 2); //Verkauf für die Hälfte des Preises
                feld.setBesitzer(null);
                System.out.println(spieler.getName() + " hat im Gegenzug für " + (feld.getPreis() / 2) + " Intelligenz das Modul "+ feld.getName() + " abgebrochen");
                return;
            }
        }
    }
   
    
    public void anzeigenAktuellerStand() {
        for (Spielfigur spieler : spielerListe) {
            System.out.println(spieler.getName() + " steht auf Feld: " + spieler.getAktuellesFeld().getName());
        }
    }
}

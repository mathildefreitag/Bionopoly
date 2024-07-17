package bionopoly;
import gui.SpielfigurGui;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Spielfeld extends JPanel {
    private static final long serialVersionUID = 1L;

    private static ArrayList<Feld> alleFelder = new ArrayList<>();
    private ArrayList<Spielfigur> spielerListe = new ArrayList<>();
    private List<Feld> unkaufbareFelder = new ArrayList<>();
    private static int anzahlFelder = 40;
    private boolean canRollDice = true;
    private static int currentPlayerIndex = 0;
    private Spielfigur intelligenz;
    static int miete;
    static String farbe;
    static boolean besitzer;
    static String besucher;
    static String feldname;
    private int anzahlPasche;
    

    public ArrayList<Feld> getAlleFelder() {
        return alleFelder;
        
    }

    public List<Feld> getUnkaufbareFelder() {
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
    
    

    private void initialisierungModule() {
        String[] modulNamen = { "Start", "Digital Learning/ Englisch", "Gemeinschaftsfeld", "Präperationstechnik",
                "O-Woche", "Werder Straße", "Allg. Biologie/ Bionik", "Mathe/ Informatik",
                "Statistische Datenanalyse", "Chemie/ Physik", "Nachprüfung/ Klausureinsicht", "Spezielle Biologie",
                "Bibilothek", "Physiologie", "Material/ Mechanik", "Neustadswall", "Konstruktion/ CAD",
                "Gemeinschaftsfeld", "Projekt Management", "Wahlpflichtmodul", "Urlaubssemester", "Lokomotion",
                "Ereignisfeld", "Finite Elemente Methode", "Spezielle Werkstoffkunde", "Airport",
                "Interkulturelle Kompetenz", "Auslandssemester", "Hochschulsport", "Auslandsnachbereitung",
                "Prüfung nicht bestanden", "Entwicklungsprojekt 'Bionik'", "Organisationsbionik/ BWL",
                "Gemeinschaftsfeld", "Optimierungsverfahren", "Bionik Innovations-Centrum", "Ereignisfeld",
                "Bachelor Thesis", "Bachelor Abschluss-Party", "Bachelorabschluss" };
        int[] preis = { 0, 60, 0, 60, 0, 200, 100, 100, 100, 120, 0, 140, 150, 140, 160, 200, 180, 0, 180, 200, 0, 220, 0,
                220, 240, 200, 260, 260, 150, 280, 0, 300, 300, 0, 320, 200, 0, 350, 0, 400 };
        int[] miete = { 0, 2, 0, 4, 0, 25, 6, 6, 6, 8, 0, 10, 15, 10, 12, 25, 14, 0, 14, 16, 0, 18, 0, 18, 20, 25, 22, 22,
                15, 24, 0, 26, 26, 0, 28, 25, 0, 35, 0, 50 };
        String[] farbe = { "weiß", "braun", "weiß", "braun", "weiß", "grau", "hellblau", "hellblau", "hellblau",
                "hellblau", "weiß", "pink", "schwarz", "pink", "pink", "grau", "orange", "weiß", "orange", "orange",
                "weiß", "rot", "weiß", "rot", "rot", "grau", "gelb", "gelb", "schwarz", "gelb", "weiß", "grün", "grün",
                "weiß", "grün", "grau", "weiß", "dunkelblau", "weiß", "dunkelblau" };
        

        for (int i = 0; i < modulNamen.length; i++) {
            int x, y;
            int rotation = 90;

            if (i == 0) {
                x = 400;
                y = 637;
            } else if (0 < i && i < 10) {
                x = 400;
                y = 620 - 60 * i;
            } else if (i == 10) {
                x = 400;
                y = 0;
                rotation = 180;
            } else if (10 < i && i < 20) {
                x = 420 + 60 * (i - 10);
                y = 0;
                rotation = 180;
            } else if (i == 20) {
                x = 1040;
                y = 0;
                rotation = -90;
            } else if (20 < i && i < 30) {
                x = 1040;
                y = 19 + 60 * (i - 20);
                rotation = -90;
            } else if (i == 30) {
                x = 1040;
                y = 639;
                rotation = 0;
            } else if (30 < i && i < 40) {
                x = 1020 - 60 * (i - 30);
                y = 639;
                rotation = 0;
            } else {
                continue;
            }
            

            Feld feld = new Feld(x, y, 60, 99, modulNamen[i], rotation);
            feld.setPreis(preis[i]);
            feld.setMiete(miete[i]);
            this.add(feld);
            alleFelder.add(feld);
            if (preis[i] == 0) {
                unkaufbareFelder.add(feld);
            }
        }
    }

    public static int getAnzahlFelder() {
        return anzahlFelder;
    }

    public static Feld getFeld(int index) {
        return alleFelder.get(index);
    }

    public void addSpieler(Spielfigur spieler) {
        spielerListe.add(spieler);
    }

    public boolean isPasch(int würfel1, int würfel2) {
        return würfel1 == würfel2;
    }
    

    public void würfelnUndBewegen(Spielfigur spieler) {
        if (!canRollDice) {
            System.out.println("Sie können nicht würfeln, bevor Sie den aktuellen Zug beendet haben.");
            return;
        }
        Würfel würfel = new Würfel();
        würfel.würfel();
        int augensumme = würfel.getAugensumme();
        spieler.felderVorrücken(augensumme);
        System.out.println("\n" +spieler.getName() + " hat " + augensumme + " gewürfelt und ist auf " + spieler.getAktuellesFeld().getName() + " gelandet.");
        canRollDice = false;
        if (spieler.getAktuellesFeld().getName().equalsIgnoreCase("Urlaubssemester")) {
            System.out.println(spieler.getName() + " genießt das Urlaubssemester und kann einfach mal nichts tun.");
            canRollDice = true; // Nächster Spieler kann würfeln
            return;
        }
        if (spieler.getAktuellesFeld().getName().equalsIgnoreCase("Prüfung nicht bestanden")) {
            spieler.setAktuellesFeld(feldAmOrt(10));
            System.out.println(spieler.getName() + " wurde zur Nachprüfung auf Feld 10 verbannt.");
            canRollDice = true; // Nächster Spieler kann würfeln
            return;
        }
        if (spieler.getAktuellesFeld().getName().equalsIgnoreCase("Nachprüfung/ Klausureinsicht")) {
            int anzahlPasche = 0;
            for (int i = 0; i < 3; i++) {
                würfel.würfel();
                int würfel1 = würfel.getWürfel1();
                int würfel2 = würfel.getWürfel2();
                if (isPasch(würfel1, würfel2)) {
                    anzahlPasche++;
                
            
            if (anzahlPasche == 3) {
                System.out.println(spieler.getName() + " hat einen Pasch gewürfelt und hat die Nachprüfung bestanden.");
                } 
            }
                else {
                System.out.println(spieler.getName() + " hat 3x keinen Pasch gewürfelt und verliert 50 Punkte Intelligenz.");
                spieler.setIntelligenz(spieler.getIntelligenz() - 50);
            }
            canRollDice = true; // Nächster Spieler kann würfeln
            return;
        }
            spieler.getAktuellesFeld().removeSpielfigur(spieler); // Remove from current field
            spieler.felderVorrücken(augensumme);
            spieler.getAktuellesFeld().addSpielfigur(spieler); // Add to new field
            repaint(); // Trigger a repaint to show the figure movement
            
            }
        
        if (spieler.getAktuellesFeld().getName().equalsIgnoreCase("Ereignisfeld")) {
            int randomEreignis = (int) (Math.random() * 2) + 1;
            switch (randomEreignis) {
                case 1:
                    spieler.setIntelligenz(spieler.getIntelligenz() + 100);
                    System.out.println(spieler.getName() + " hat ein Ereignisfeld getroffen und gewinnt 100 Intelligenz.");
                    break;
                case 2:
                    spieler.setIntelligenz(spieler.getIntelligenz() - 50);
                    System.out.println(spieler.getName() + " hat ein Ereignisfeld getroffen und verliert 50 Intelligenz.");
                    break;
            }
            canRollDice = true; // Nächster Spieler kann würfeln
            return;
        }
        if (spieler.getAktuellesFeld().getPreis() != 0) {
            System.out.println("Das Feld '" + spieler.getAktuellesFeld().getName() + "' kann gekauft werden.");
            System.out.println("Preis: " + spieler.getAktuellesFeld().getPreis() + " Intelligenz");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Möchten Sie das Feld kaufen? (ja/nein): ");
            String antwort = scanner.nextLine().trim().toLowerCase();
            if (antwort.equals("ja")) {
                if (spieler.getIntelligenz() > spieler.getAktuellesFeld().getPreis()) {
                    spieler.setIntelligenz(spieler.getIntelligenz() - spieler.getAktuellesFeld().getPreis());
                    System.out.println(spieler.getName() + " hat " + spieler.getAktuellesFeld().getName() + " gekauft und verliert " + spieler.getAktuellesFeld().getPreis() + " Punkte Intelligenz.\n");
                } else {
                    System.out.println(spieler.getName() + " hat nicht genug Intelligenz, um das Feld zu kaufen.");
                }
            } else {
                System.out.println(spieler.getName() + " hat sich gegen den Kauf von " + spieler.getAktuellesFeld().getName() + " entschieden.\n");
            }
        }
        canRollDice = true; // Nächster Spieler kann würfeln
    }
    
    public void zugBeenden() {
        Spielfigur spieler = spielerListe.get(currentPlayerIndex);
        spieler.zugBeenden();
        currentPlayerIndex++;
        if (currentPlayerIndex >= spielerListe.size()) {
            currentPlayerIndex = 0;
        }
        canRollDice = true;
    }
    public Feld feldAmOrt2(int index) {
        if (index < 0 || index >= alleFelder.size()) {
            throw new IllegalArgumentException("Ungültiger Index: " + index);
        }
        return alleFelder.get(index);
    }

    public boolean isCanRollDice() {
        return canRollDice;
    }

    public void setCanRollDice(boolean canRollDice) {
        this.canRollDice = canRollDice;
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Spielbrett Übersicht");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1775, 850);

        Spielfeld spielfeld = new Spielfeld(50, 50, 612, 612); // Declare spielfeld here
        frame.add(spielfeld);
        frame.setVisible(true);

        SpielfigurGui gui = new SpielfigurGui();
        ArrayList<Spielfigur> spielfiguren = Spielfigur.initSpielfiguren(spielfeld, gui);

        for (Spielfigur figur : spielfiguren) {
            spielfeld.addSpieler(figur);
        }

        frame.setVisible(true);

        int currentPlayerIndex = 0;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Spielfigur currentPlayer = spielfiguren.get(currentPlayerIndex);
            spielfeld.würfelnUndBewegen(currentPlayer);
            System.out.println(currentPlayer.getName() + " hat seinen Zug beendet. Drücken Sie Enter für den nächsten Spieler.");
            scanner.nextLine();
            currentPlayerIndex = (currentPlayerIndex + 1) % spielfiguren.size();
        }
    }

}

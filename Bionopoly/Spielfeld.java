package bionopoly;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Spielfeld extends JPanel {
    private static ArrayList<Feld> alleFelder = new ArrayList<>();
    private ArrayList<Spielfigur> spielerListe = new ArrayList<>();
    private static int anzahlFelder = 40;
    private static int currentPlayerIndex = 0;
    private boolean canRollDice = true;

    public Spielfeld(int xKoord, int yKoord, int breite, int hoehe) {
        setBorder(new LineBorder(new Color(0, 0, 0)));
        setBounds(xKoord, yKoord, breite, hoehe);
        setLayout(null);
        initialisierungModule();
    }

    private void initialisierungModule() {
        String[] modulNamen = {
                "Start", "Digital Learning/ Englisch", "Gemeinschaftsfeld", "Präperationstechnik", "O-Woche",
                "Werder Straße", "Allg. Biologie/ Bionik", "Mathe/ Informatik", "Statistische Datenanalyse",
                "Chemie/ Physik", "Nachprüfung/ Klausureinsicht", "Spezielle Biologie", "Bibilothek", "Physiologie",
                "Material/ Mechanik", "Neustadswall", "Konstruktion/ CAD", "Gemeinschaftsfeld", "Projekt Management",
                "Wahlpflichtmodul", "Urlaubssemester", "Lokomotion", "Ereignisfeld", "Finite Elemente Methode",
                "Spezielle Werkstoffkunde", "Airport", "Interkulturelle Kompetenz", "Auslandssemester", "Hochschulsport",
                "Auslandsnachbereitung", "Prüfung nicht bestanden", "Entwichlungsprojekt 'Bionik' ",
                "Organisationsbionik/ BWL", "Gemeinschaftsfeld", "Optimierungsverfahren", "Bionik Innovations-Centrum",
                "Ereignisfeld", "Bachelor \nThesis", "Bachelor Abschluss-Party", "Bachelorabschluss"
        };

        int[] preis = {
                0, 60, 0, 60, 0, 200, 100, 100, 100, 120, 0, 140, 150, 140, 160, 200, 180, 0, 180, 200, 0, 220, 0, 220, 240,
                200, 260, 260, 150, 280, 0, 300, 300, 0, 320, 200, 0, 350, 0, 400
        };

        int[] miete = {
                0, 2, 0, 4, 0, 25, 6, 6, 6, 8, 0, 10, 0, 10, 12, 25, 14, 0, 14, 16, 0, 18, 0, 18, 20, 25, 22, 22, 0, 24, 0,
                26, 26, 0, 28, 25, 0, 35, 0, 50
        };

        for (int i = 0; i < modulNamen.length; i++) {
            int x, y;
            int rotation = 90;

            if (0 < i && i < 10) {
                x = 390;
                y = 655 - 64 * i;
            } else if (10 < i && i < 20) {
                x = 408 + 64 * (i - 10);
                y = 0;
                rotation = 180;
            } else if (20 < i && i < 30) {
                x = 1068;
                y = 15 + 64 * (i - 20);
                rotation = -90;
            } else if (30 < i && i < 40) {
                x = 1049 - 64 * (i - 30);
                y = 670;
                rotation = 0;
            } else {
                continue;
            }

            Feld feld = new Feld(x, y, 64, 100, modulNamen[i], rotation);
            feld.setPreis(preis[i]);
            feld.setMiete(miete[i]);
            add(feld);
            alleFelder.add(feld);
        }

        for (int i = 0; i <= 30; i += 10) {
            int z, a;
            int rotationb = 90;

            if (i == 0) {
                z = 372;
                a = 670;
            } else if (i == 10) {
                z = 372;
                a = 0;
                rotationb = 180;
            } else if (i == 20) {
                z = 1050;
                a = 0;
                rotationb = -90;
            } else if (i == 30) {
                z = 1050;
                a = 670;
                rotationb = 0;
            } else {
                continue;
            }

            Feld feld = new Feld(z, a, 100, 100, modulNamen[i], rotationb);
            feld.setPreis(preis[i]);
            feld.setMiete(miete[i]);
            add(feld);
            alleFelder.add(feld);
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

    public void würfelnUndBewegen() {
        if (!canRollDice) {
            System.out.println("Sie können nicht würfeln, bevor Sie den aktuellen Zug beendet haben.");
            return;
        }

        Spielfigur spieler = spielerListe.get(currentPlayerIndex);
        Würfel würfel = new Würfel();
        würfel.würfel();
        int augensumme = würfel.getAugensumme();

        spieler.felderVorrücken(augensumme);
        System.out.println(spieler.getName() + " hat " + augensumme + " gewürfelt und ist auf " + spieler.getAktuellesFeld().getName() + " gelandet.");

        canRollDice = false; // Der Spieler kann erst wieder würfeln, nachdem der Zug beendet wurde
    }

    public void zugBeenden() {
        Spielfigur spieler = spielerListe.get(currentPlayerIndex);
        spieler.zugBeenden();

        currentPlayerIndex++;
        if (currentPlayerIndex >= spielerListe.size()) {
            currentPlayerIndex = 0;
        }

        canRollDice = true; // Der nächste Spieler kann würfeln
    }
}

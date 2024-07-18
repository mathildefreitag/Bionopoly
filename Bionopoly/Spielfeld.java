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
    
    private boolean kannWürfeln = true;
    static boolean besitzer;
    
    private static int aktuellerSpielerIndex = 0;
    static int miete;

    static String farbe;
    static String besucher;
    static String feldname;
    
    //Initialisierung des Spielfeldes
    public Spielfeld(int xKoord, int yKoord, int breite, int hoehe) {
        setBorder(new LineBorder(new Color(0, 0, 0))); 
        setBounds(xKoord, yKoord, breite, hoehe); 
        this.setLayout(null);
        initialisierungModule();
    }
    //Initialisierung der Variablenlisten etc.
    public ArrayList<Feld> getAlleFelder() {
        return alleFelder;  
    }

    public List<Feld> getUnkaufbareFelder() {
        return unkaufbareFelder;
    }

    public Feld feldAmOrt(int ort) {
        return alleFelder.get(ort);
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
    
    public boolean iskannWürfeln() {
        return kannWürfeln;
    }

    public void setkannWürfeln(boolean kannWürfeln) {
        this.kannWürfeln = kannWürfeln;
    }
	public void entferneSpieler(Spieler spieler) {
		spielerListe.remove(spieler);
	}

  
    //Initialisierung der Module
    private void initialisierungModule() {
    	
        String[] modulNamen = { "Start", "Digital Learning/Englisch", "Gemeinschaftsfeld", "Präperationstechnik", //Festlegen der Modulnamen als String Array
                "O-Woche", "Werder Straße", "Allg. Biologie/ Bionik", "Mathe/ Informatik",
                "Statistische Datenanalyse", "Chemie/ Physik", "Nachprüfung/ Klausureinsicht", "Spezielle Biologie",
                "Bibilothek", "Physiologie", "Material/ Mechanik", "Neustadswall", "Konstruktion/ CAD",
                "Gemeinschaftsfeld", "Projekt Management", "Wahlpflichtmodul", "Urlaubssemester", "Lokomotion",
                "Ereignisfeld", "Finite Elemente Methode", "Spezielle Werkstoffkunde", "Airport",
                "Interkulturelle Kompetenz", "Auslandssemester", "Hochschulsport", "Auslandsnachbereitung",
                "Prüfung nicht bestanden", "Entwicklungsprojekt 'Bionik'", "Organisationsbionik/ BWL",
                "Gemeinschaftsfeld", "Optimierungsverfahren", "Bionik Innovations-Centrum", "Ereignisfeld",
                "Bachelor Thesis", "Bachelor Abschluss-Party", "Bachelorabschluss" };
        
        int[] preis = { 0, 60, 0, 60, 0, 200, 100, 100, 100, 120, 0, 140, 150, 140, 160, 200, 180, 0, 180, 200, 0, 220, 0, //Festlegen der Preise für die Module
                220, 240, 200, 260, 260, 150, 280, 0, 300, 300, 0, 320, 200, 0, 350, 0, 400 };
        
        int[] miete = { 0, 2, 0, 4, 0, 25, 6, 6, 6, 8, 0, 10, 15, 10, 12, 25, 14, 0, 14, 16, 0, 18, 0, 18, 20, 25, 22, 22, //Festlegen der Preise für die Nachhilfe (Miete) in einem Array
                15, 24, 0, 26, 26, 0, 28, 25, 0, 35, 0, 50 };
        
        Color[] feldFarben = new Color[] { //Array zum Festlegen der Farben der Module
        		Color.WHITE, Color.MAGENTA, Color.WHITE, Color.MAGENTA, Color.WHITE, Color.LIGHT_GRAY,Color.CYAN, Color.CYAN, Color.CYAN, Color.CYAN, Color.WHITE, Color.PINK, Color.GRAY, Color.PINK, Color.PINK, Color.LIGHT_GRAY, Color.ORANGE, Color.WHITE, Color.ORANGE, Color.ORANGE, Color.WHITE, Color.RED, Color.WHITE, Color.RED, Color.RED, Color.LIGHT_GRAY, Color.YELLOW, Color.YELLOW, Color.GRAY, Color.YELLOW, Color.WHITE, Color.GREEN, Color.GREEN, Color.WHITE, Color.GREEN, Color.LIGHT_GRAY, Color.WHITE, Color.BLUE, Color.WHITE, Color.BLUE};

        //Formatierung der Felder
        for (int i = 0; i < modulNamen.length; i++) {
            int x, y;
            int rotation = 90;

            if (i == 0) {
                x = 400;
                y = 703;
            } 
            else if (0 < i && i < 10) {
                x = 400;
                y = 703 - 70 * i;
            } 
            else if (i == 10) {
                x = 400;
                y = 0;
                rotation = 180;
            } 
            else if (10 < i && i < 20) {
                x = 400 + 70 * (i - 10);
                y = 0;
                rotation = 180;
            } 
            else if (i == 20) {
                x = 1103;
                y = 2;
                rotation = -90;
            } 
            else if (20 < i && i < 30) {
                x = 1103;
                y = 3 + 70 * (i - 20);
                rotation = -90;
            } 
            else if (i == 30) {
                x = 1104;
                y = 705;
                rotation = 0;
            } 
            else if (30 < i && i < 40) {
                x = 1103 - 70 * (i - 30);
                y = 703;
                rotation = 0;
            } 
            else {
                continue;
            }
            Color feldFarbe = feldFarben[i];

            Feld feld = new Feld(x, y, 70, 73, modulNamen[i], rotation, feldFarbe);
            feld.setPreis(preis[i]);
            feld.setMiete(miete[i]);
            this.add(feld);
            alleFelder.add(feld);
            if (preis[i] == 0) {
                unkaufbareFelder.add(feld);
            }
        }
    }


    
    //Würfeln und Bewegen der Spielfiguren
    public void würfelnUndBewegen(Spielfigur spieler) {
    	
        if (spieler.istPleite()) { //Wenn Spieler keine Intelligenzpunkte mehr hat
            System.out.println(spieler.getName() + " ist pleite und kann nicht mehr studieren.");
            return;
        }
        if (!kannWürfeln) {
            System.out.println("Sie können nicht würfeln, bevor der aktuellen Zug beendet ist."); //
            return;
        }

        if (spieler.isInNachpruefung()) { //Spieler ist auf Nachprüfungsfeld
            if (spieler.getNachprüfungsVersuche() > 0) { //Wenn noch nicht alle Nachprüfungsversuche verbraucht sind, kann der Spieler versuchen sich rauszuwürfeln
                Wuerfel würfel = new Wuerfel();
                würfel.würfel();
                int würfel1 = würfel.getWürfel1();
                int würfel2 = würfel.getWürfel2();
                if (isPasch(würfel1, würfel2)) { //durch Pasch kann Nachfrüfung bestanden werden
                    System.out.println(spieler.getName() + " hat die Nachprüfung bestanden."); //Ausgabe, wenn von Nachprüfungsfeld runter
                    spieler.setInNachpruefung(false); //Spieler wird nicht mehr auf dem Nachprüfungsfeld festgehalten 
                    spieler.setNachprüfungsVersuche(0); //Nachprüfungsversuche werden auf null gesetzt
                } 
                else {
                    spieler.reduzierungNachprüfungVersuche(); //wenn kein Pasch gewürfelt wird, dann verringern sich die Nachprüfungsversuche
                    System.out.println(spieler.getName() + " hat die Nachprüfung nicht bestanden und hat noch " + spieler.getNachprüfungsVersuche() + " Versuche übrig."); //Ausgabe (in der Konsole)
                    if (spieler.getNachprüfungsVersuche() == 0) { //wenn keine Versuche mehr für die Nachprüfung übrig sind
                        System.out.println(spieler.getName() + " hat 3x die Prüfung nicht bestanden und verliert 50 Punkte Intelligenz."); //Ausgabe
                        spieler.setIntelligenz(spieler.getIntelligenz() - 50); //Der Spieler verliert 50 Intelligenzpunkte, weil er die Nachprüfung 3x nicht bestanden hat
                        spieler.setInNachpruefung(false); //Spieler wird nicht mehr auf dem Nachprüfungsfeld festgehalten
                    }
                }
                kannWürfeln = true; //Nächster Spieler kann würfeln
                return;
            }
        } 
        else {
            Wuerfel würfel = new Wuerfel();
            würfel.würfel();
            int augensumme = würfel.getAugensumme(); //holt die Augensumme aus der Würfelklasse
            spieler.felderVorrücken(augensumme); //Spieler rückt um die Zahl der Augensumme vor
            System.out.println("\n" + spieler.getName() + " hat eine " + augensumme + " gewürfelt und ist auf " + spieler.getAktuellesFeld().getName() + " gelandet."); //Ausgabe (in Konsole)
            kannWürfeln = false; //es kann noch nicht weiter gewürfelt werden

            if (spieler.getAktuellesFeld().getName().equalsIgnoreCase("Urlaubssemester")) { //Sonderfeld Urlaubssemester
                System.out.println(spieler.getName() + " genießt das Urlaubssemester und kann einfach mal nichts tun.");
                kannWürfeln = true; //es kann gewürfelt werden
                return;
            }

            if (spieler.getAktuellesFeld().getName().equalsIgnoreCase("Prüfung nicht bestanden")) { //Sonderfeld Prüfung nicht bestanden
                spieler.setAktuellesFeld(feldAmOrt(10));
                System.out.println(spieler.getName() + " muss in die Nachprüfung."); //Ausgabe (in der Konsole)
                kannWürfeln = true; //es kann gewürfelt werden (der nächste Spieler ist dran)
                return;
            }

            if (spieler.getAktuellesFeld().getName().equalsIgnoreCase("O-Woche")) { //Sonderfeld O-Woche
                spieler.setIntelligenz(spieler.getIntelligenz() - 200); //der aktuelle Spieler verliert 200 Intelligenzpunkte
                System.out.println(spieler.getName() + " hat 200 Intelligenz aufgrund von zu hohem Alkoholkonsum verloren."); //Ausgabe (in der Konsole)
                kannWürfeln = true; //es kann gewürfelt werden (der nächste Spieler ist dran)
                return;
            } 

            if (spieler.getAktuellesFeld().getName().equalsIgnoreCase("Bachelorabschlussparty")) { //Sonderfeld Bachelorabschlussparty
                spieler.setIntelligenz(spieler.getIntelligenz() - 200); //der aktuelle Spieler verliert 200 Intelligenzpunkte
                System.out.println(spieler.getName() + " hat 200 Intelligenz aufgrund zu starkem Feierns auf der Bachelorabschlussparty verloren."); //Ausgabe (in der Konsole)
                kannWürfeln = true; //es kann gewürfelt werden (der nächste Spieler ist dran)
                return;
            }

            if (spieler.getAktuellesFeld().getName().equalsIgnoreCase("Nachprüfung/ Klausureinsicht")) { //Sonderfeld Nachprüfung/ Klausureinsicht
                spieler.setInNachpruefung(true); //aktueller Spieler muss in die Nachprüfung
                spieler.setNachprüfungsVersuche(3); //es gibt drei Nachprüfungsversuche
                System.out.println(spieler.getName() + " ist in der Nachprüfung und hat 3 Versuche, zu bestehen."); //Ausgabe (in der Konsole)
                kannWürfeln = true; //es kann gewürfelt werden (der nächste Spieler ist dran)
                return;
            }

            if (spieler.getAktuellesFeld().getName().equalsIgnoreCase("Gemeinschaftsfeld")) { //Sonderfeld Gemeinschaftsfeld
                int randomEreignis = (int) (Math.random() * 2) + 1; //das Ereignis der Gemeinschaftskarte wird zufällig bestimmt
                switch (randomEreignis) { //Switch für Ereignisse
                    case 1:
                        spieler.setIntelligenz(spieler.getIntelligenz() + 100); //aktueller Spieler bekommt 100 Intelligenzpunkte
                        System.out.println(spieler.getName() + " hat einen HiWi Job angenommen und erhält 100 Intelligenz."); //Ausgabe (in der Konsole)
                        break;
                    case 2:
                        spieler.setIntelligenz(spieler.getIntelligenz() - 50); //aktueller Spieler verliert 50 Intelligenzpunkte
                        System.out.println(spieler.getName() + " hat zu viele Vorlesungen verpasst und verliert 50 Intelligenz."); //Ausgabe (in der Konsole)
                        break;
                }
                kannWürfeln = true; //es kann gewürfelt werden (der nächste Spieler ist dran)
                return;
            }
            if (spieler.getAktuellesFeld().getName().equalsIgnoreCase("Ereignisfeld")) { //Sonderfeld Ereignisfeld
                int randomEreignis = (int) (Math.random() * 2) + 1; //das Ereignis der Ereigniskarte wird zufällig bestimmt
                switch (randomEreignis) { //Switch für Ereignisse
                    case 1:
                        spieler.setIntelligenz(spieler.getIntelligenz() + 100); //aktueller Spieler bekommt 100 Intelligenzpunkte
                        System.out.println(spieler.getName() + " hat sich eine Auszeit gegönnt und gewinnt 100 Intelligenz."); //Ausgabe (in der Konsole)
                        break;
                    case 2:
                        spieler.setIntelligenz(spieler.getIntelligenz() - 50); //aktueller Spieler verliert 50 Intelligenzpunkte
                        System.out.println(spieler.getName() + " hat nachts zu viel gezockt, deshalb zu wenig geschlafen und verliert 50 Intelligenz."); //Ausgabe (in der Konsole)
                        break;
                }
                kannWürfeln = true; //es kann gewürfelt werden (der nächste Spieler ist dran)
                return;
            }

            if (spieler.getAktuellesFeld().getPreis() != 0 && spieler.getAktuellesFeld().getBesitzer() == null) { //Wenn aktueller Spieler auf ein Feld kommt, wo der Preis nicht null ist und das Modul noch keinem Spieler gehört, dann beginnt die if-Schleife.
                System.out.println("Das Modul '" + spieler.getAktuellesFeld().getName() + "' kann bestanden werden."); //Ausgabe (in der Konsole)
                System.out.println("Preis: " + spieler.getAktuellesFeld().getPreis() + " Intelligenz"); //Ausgabe (in der Konsole)
                Scanner scanner = new Scanner(System.in); //Scanner prüft die Eingabe in der Konsole
                System.out.print("Möchten Sie das Modul bestehen? (ja/nein): "); //Ausgabe (in der Konsole): Auswählmöglichkeit
                String antwort = scanner.nextLine().trim().toLowerCase(); //auch wenn Großbuchstaben eingegeben werden, wird mit Kleinbuchstaben weitergearbeitet
                if (antwort.equals("ja")) { //für den Fall, dass der aktuelle Spieler das Modul belegen möchte
                    if (spieler.getIntelligenz() > spieler.getAktuellesFeld().getPreis()) { //prüft ob aktueller Spieler genug Intelligenz hat um das Modul zu belegen
                        spieler.setIntelligenz(spieler.getIntelligenz() - spieler.getAktuellesFeld().getPreis()); //Preis vom Modul wird von den Intelligenzpunkten des aktuellen Spielers abgezogen
                        spieler.getAktuellesFeld().setBesitzer(spieler); //Modul bekommt einen Besitzer (der aktuelle Spieler, der gerade das Modul belegt hat)
                        System.out.println(spieler.getName() + " hat " + spieler.getAktuellesFeld().getName() + " betanden und verliert " + spieler.getAktuellesFeld().getPreis() + " Intelligenz.\n"); //Ausgabe (in der Konsole)
                    } 
                    else {
                        System.out.println(spieler.getName() + " hat nicht genug Intelligenz, um das Modul zu bestehen."); //Ausgabe (in der Konsole): nicht genug Intelligenz
                    }
                } 
                else { //für den Fall, dass der aktuell Spieler das Modul nicht belegen möchte
                    System.out.println(spieler.getName() + " hat sich gegen das belegen von " + spieler.getAktuellesFeld().getName() + " entschieden.\n"); //Ausgabe (in der Konsole)
                }
                kannWürfeln = true; //es kann gewürfelt werden (der nächste Spieler ist dran)
                return;
            } 
            else if (spieler.getAktuellesFeld().getBesitzer() != null && spieler.getAktuellesFeld().getBesitzer() != spieler) { //für den Fall, dass der aktuelle Spieler auf ein Feld gelangt, welches schon einem Spieler gehört, der nicht er selbst ist
            	spieler.mieteZahlen(spieler.getAktuellesFeld()); //aktueller Spieler muss Miete/Nachhilfe bezahlen
            }
            kannWürfeln = true; //es kann gewürfelt werden (der nächste Spieler ist dran)
        }
   }
   
    //Methode um den Spielzug zu beenden
    public void zugBeenden() {
        Spielfigur spieler = spielerListe.get(aktuellerSpielerIndex); //die Liste für den aktuellen Spieler wird aufgerufen
        spieler.zugBeenden(); //der aktuelle Spieler beendet seinen Zug
        aktuellerSpielerIndex++; //der nächste Spieler aus der Liste ist als nächstes an der Reihe
        if (aktuellerSpielerIndex >= spielerListe.size()) { //Wenn alle Spieler dran waren, dann ist wieder der Spieler an der Reihe, der angefangen hat
            aktuellerSpielerIndex = 0;
        }
        kannWürfeln = true; //es kann gewürfelt werden (der nächste Spieler ist dran)
    }
    
    
    
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Spielbrett Übersicht"); //neues Fenster wird erstellt
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //wenn X gedrückt, wird das Fenster geschlossen
        frame.setSize(1775, 850); //Größe des Fensters

        Spielfeld spielfeld = new Spielfeld(50, 50, 612, 612); //Größe der Spielfelder wird festgelegt
        frame.add(spielfeld); //Rahmen außenrum
        frame.setVisible(true); //Rahmen wird sichtbar gemacht
        
        Waehrung währung = new Waehrung();

        SpielfigurGui gui = new SpielfigurGui();
        ArrayList<Spielfigur> spielfiguren = Spielfigur.initSpielfiguren(spielfeld, gui, währung); //Array Liste für die Spielfiguren

        for (Spielfigur figur : spielfiguren) { //für jede Spielfigur wird eine Figur hinzugefügt
            spielfeld.addSpieler(figur);
        }

        int aktuellerSpielerIndex = 0; //aktueller Spielerindex wird deklariert
        Scanner scanner = new Scanner(System.in); //Scanner wird hinzugefügt

        while (spielfiguren.size() > 1) { //solange es mehr als eine Spielfigur gibt, wird geprüft, ob der aktuelle Spieler Pleite ist und sein Zug wird beendet
            Spielfigur aktuellerSpieler = spielfiguren.get(aktuellerSpielerIndex);

            if (!aktuellerSpieler.istPleite()) {
            	System.out.println(aktuellerSpieler.getName() + " ist dran. \nIntelligenz: " + aktuellerSpieler.getIntelligenz()); //Ausgabe (in der Konsole)
                spielfeld.würfelnUndBewegen(aktuellerSpieler);
                System.out.println(aktuellerSpieler.getName() + " hat seinen Zug beendet. Drücken Sie Enter für den nächsten Spieler."); //Ausgabe (in der Konsole)
                scanner.nextLine();
            }
            else {
                //Spieler ist pleite und wird aus der Liste entfernt
                System.out.println(aktuellerSpieler.getName() + " all seine Intelligenz verloren und muss das Bionik Studium schmeißen.");
                spielfiguren.remove(aktuellerSpielerIndex);
                if (spielfiguren.size() == 1) {
                    break; //Wenn nur noch ein Spieler übrig ist, wird die Schleife beendet
                }
                //Den Index anpassen, damit der nächste Spieler richtig angesprochen wird
                aktuellerSpielerIndex = aktuellerSpielerIndex % spielfiguren.size();
                continue; //Nächste Runde der Schleife
            }

            aktuellerSpielerIndex = (aktuellerSpielerIndex + 1) % spielfiguren.size();
        }

        System.out.println("Das Studium ist beendet. Der Gewinner ist: " + spielfiguren.get(0).getName()+ "und hat so als einziger Spieler das Bionik Studium bezwungen."); //Ausgabe (in der Konsole): Spielende
    }
}

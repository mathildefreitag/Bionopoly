
package bionopoly;

import java.util.ArrayList;
import java.util.List;

public class Spielfigur {
    // Name deklarieren
    private String name;

    // Konstruktor zum Initialisieren des Namens
    public Spielfigur(String name) {
        this.name = name;
    }

    // Methode zum Aufrufen des Namens
    public String getName() {
        return name;
    }

    // Methode um auf augensumme Zugriff zu haben und Spielfigur basierend auf der Augensumme zu verschieben
    public void felderVorrücken(Würfel würfel) {
        int augensumme = würfel.getAugensumme();
        System.out.println("Die Spielfigur " + name + " rückt um " + augensumme + " Felder vor.");
    }

    public static void main(String[] args) {
        // Liste zum Verwalten der Spielfiguren
        List<Spielfigur> spielfiguren = new ArrayList<>();
        initSpielfiguren(spielfiguren);

        // Initiale Spielfiguren anzeigen
        System.out.println("Initiale Spielfiguren:");
        zeigeSpielfiguren(spielfiguren);

        // Spielfigur von Position 1 zu Position 3 verschieben
        System.out.println("\nVerschiebe Spielfigur von Position 1 zu Position 3:");
        verschiebeSpielfigur(spielfiguren, 0, 2);
        zeigeSpielfiguren(spielfiguren);

        // Spielfigur von Position 5 zu Position 1 verschieben
        System.out.println("\nVerschiebe Spielfigur von Position 5 zu Position 1:");
        verschiebeSpielfigur(spielfiguren, 4, 0);
        zeigeSpielfiguren(spielfiguren);

        // Beispielhafte Verwendung der Spielfigur und Würfeln
        Spielfigur figur = spielfiguren.get(0); // Erste Spielfigur
        Würfel würfel = new Würfel();
        würfel.würfel();
        figur.felderVorrücken(würfel);
    }

    // Initialisiert die 6 verschiedenen Spielfiguren
    private static void initSpielfiguren(List<Spielfigur> spielfiguren) {
        spielfiguren.add(new Spielfigur("Regenwurm"));
        spielfiguren.add(new Spielfigur("Paramecium"));
        spielfiguren.add(new Spielfigur("Heuschrecke"));
        spielfiguren.add(new Spielfigur("Fisch"));
        spielfiguren.add(new Spielfigur("Seestern"));
        spielfiguren.add(new Spielfigur("Schwein"));
    }

    // Methode zum Verschieben einer Spielfigur von einer Position zu einer anderen
    private static void verschiebeSpielfigur(List<Spielfigur> spielfiguren, int vonIndex, int zuIndex) {
        if (vonIndex < 0 || vonIndex >= spielfiguren.size() || zuIndex < 0 || zuIndex >= spielfiguren.size()) {
            System.out.println("Ungültiger Index. Bitte geben Sie einen gültigen Index ein.");
            return;
        }
        Spielfigur figur = spielfiguren.remove(vonIndex);
        spielfiguren.add(zuIndex, figur);
    }

    // Methode zum Anzeigen aller Spielfiguren
    private static void zeigeSpielfiguren(List<Spielfigur> spielfiguren) {
        for (int i = 0; i < spielfiguren.size(); i++) {
            System.out.println((i + 1) + ". " + spielfiguren.get(i).getName());
        }
    }
}
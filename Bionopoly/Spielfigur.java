package bionopoly;

import java.util.ArrayList;
import java.util.List;

// Klasse Spielfigur
class Spielfigur {
//	Name deklarieren
	private String name;

//    Wenn ein Name geändert wird werden nicht alle Namen geändert sondern nur der der Instanz
	public Spielfigur(String name) {
		this.name = name;
	}

//    Methode zum Aufrufen des Names
	public String getName() {
		return name;
	}

// um auf augensumme Zugriff zu haben
private void felderVorrücken (Würfel würfel) {
int augensumme = würfel.getAugensumme();
    // Hier können Sie Logik hinzufügen, um die Spielfigur basierend auf der Augensumme zu verschieben
    System.out.println("Die Spielfigur " + name + " rückt um " + augensumme + " Felder vor.");
}

public static void main(String[] args) {
    // Beispielhafte Verwendung
    Spielfigur figur = new Spielfigur("Regenwurm");
    Würfel würfel = new Würfel();
    würfel.würfel();
    figur.felderVorrücken(würfel);
}
}

// Klasse MonopolySpiel
	public class MonopolySpiel {
		private List<Spielfigur> spielfiguren;

		public MonopolySpiel() {
			spielfiguren = new ArrayList<>();
			initSpielfiguren();
		}

		// Initialisiert die 6 verschiedenen Spielfiguren
		private void initSpielfiguren() {
			spielfiguren.add(new Spielfigur("Regenwurm"));
			spielfiguren.add(new Spielfigur("Paramecium"));
			spielfiguren.add(new Spielfigur("Heuschrecke"));
			spielfiguren.add(new Spielfigur("Fisch"));
			spielfiguren.add(new Spielfigur("Seestern"));
			spielfiguren.add(new Spielfigur("Schwein"));
		}

		// Methode zum Verschieben einer Spielfigur von einer Position zu einer anderen
		public void verschiebeSpielfigur(int vonIndex, int zuIndex) {
			if (vonIndex < 0 || vonIndex >= spielfiguren.size() || zuIndex < 0 || zuIndex >= spielfiguren.size()) {
				System.out.println("Ungültiger Index. Bitte geben Sie einen gültigen Index ein.");
				return;
			}
			Spielfigur figur = spielfiguren.remove(vonIndex);
			spielfiguren.add(zuIndex, figur);
		}

		// Methode zum Anzeigen aller Spielfiguren
		public void zeigeSpielfiguren() {
			for (int i = 0; i < spielfiguren.size(); i++) {
				System.out.println((i + 1) + ". " + spielfiguren.get(i).getName());
			}
		}

		public static void main(String[] args) {
			MonopolySpiel spiel = new MonopolySpiel();

			System.out.println("Initiale Spielfiguren:");
			spiel.zeigeSpielfiguren();

			System.out.println("\nVerschiebe Spielfigur von Position 1 zu Position 3:");
			spiel.verschiebeSpielfigur(0, 2);
			spiel.zeigeSpielfiguren();

			System.out.println("\nVerschiebe Spielfigur von Position 5 zu Position 1:");
			spiel.verschiebeSpielfigur(4, 0);
			spiel.zeigeSpielfiguren();
		}
	}
}

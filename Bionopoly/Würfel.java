package bionopoly;

import java.util.*; // Package damit man später Zufallszahlen bekommt

public class Würfel {
	private int augensumme;
	private int würfel1;
	private int würfel2;
	
	public void würfel() {
		
		Random rand = new Random(); //für Zufallszahlen

		{
		    würfel1 = rand.nextInt(6) + 1; //Zufallszahl für würfel1 erstellen (initialisieren und deklarieren)
			würfel2 = rand.nextInt(6) + 1; //Zufallszahl für würfel2 erstellen (initialisieren und deklarieren)
			this.augensumme = würfel1 + würfel2;//Augensumme der beiden Würfel addieren
			
			//(Ausgabe der Werte)
			System.out.print("Würfel 1: " + würfel1);
			System.out.println();
			System.out.print("Würfel 2: " + würfel2);
			System.out.println();
			System.out.print("Augensumme: " + augensumme + "\n");
			
		}
	}
	// Methode zum Abrufen der Augensumme
    public int getAugensumme() {
        return augensumme;
    }
    // Methode zum Abrufen der Augenzahl des ersten Würfels
    public int getWürfel1() {
        return würfel1;
    }

    // Methode zum Abrufen der Augenzahl des zweiten Würfels
    public int getWürfel2() {
        return würfel2;
    }
    // Methode zur Erkennung eines Paschs
    public boolean istPasch() {
        return würfel1 == würfel2;
    }
}

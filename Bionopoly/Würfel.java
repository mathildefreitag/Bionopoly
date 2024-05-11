package bionopoly;

import java.util.*; // Package damit man später Zufallszahlen bekommt

public class Würfel {

	public static void main(String[] args) {
		
		Random rand = new Random(); //für Zufallszahlen

		{
			int würfel1 = rand.nextInt(6) + 1; //Zufallszahl für würfel1 erstellen (initialisieren und deklarieren)
			int würfel2 = rand.nextInt(6) + 1; //Zufallszahl für würfel2 erstellen (initialisieren und deklarieren)
			int augensumme = würfel1 + würfel2;//Augensumme der beiden Würfel addieren
			
			//(Ausgabe der Werte)
			System.out.print("Würfel 1: " + würfel1);
			System.out.println();
			System.out.print("Würfel 2: " + würfel2);
			System.out.println();
			System.out.print("Augensumme: " + augensumme);
		}
	}
}

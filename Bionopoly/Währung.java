package Bionopoly;

import tictactoe.Player;

public class Währung {
	
	private Spielfeld [] [] feld;
	private Spieler [] spieler;
	
	
	
	private int startkapital;
	private int startfeldgeld;
	private int transaktion;
	
	public Währung (String spieler1, String spieler2, String spieler3, String spieler4, String spieler5, String spieler6) {
		
		spieler = new Spieler [6];
		
		spieler [0] = new Spieler (spieler1, "Paramecium");
		spieler [1] = new Spieler (spieler2, "Heuschrecke");
		spieler [2] = new Spieler (spieler3, "Fisch");
		spieler [3] = new Spieler (spieler4, "Seestern");
		spieler [4] = new Spieler (spieler5, "Schwein");
		spieler [5] = new Spieler (spieler6, "Regenwurm");
		
	}

}

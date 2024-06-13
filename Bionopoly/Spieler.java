package bionopoly;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Spieler {
    private String name;
    private int kontostand;
    private List<Feld> besitz;
    private int position;
    private int intelligenz;
    private boolean pleite;

    public Spieler(String name, int startgeld, int startIntelligenz) {
        this.name = name;
        Währung währung = new Währung();
        währung.Startgeld(startgeld);
        this.kontostand = währung.getStartgeld();
        this.besitz = new ArrayList<>();
        this.position = 0;
        this.intelligenz = startIntelligenz;
        this.pleite = false;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getIntelligenz() {
        return intelligenz;
    }
    public void setIntelligenz(int intelligenz) {
        this.intelligenz = intelligenz;
	}
    
    
    public List<Feld> getBesitz() {
        return besitz;
    }
    
    
    public boolean istPleite() {
        return pleite;
    }

    public void setPleite(boolean pleite) {
        this.pleite = pleite;
    }

 //Methode zur Überprüfung, ob ein Feld einen Besitzer hat
    public boolean hatBesitzer(Feld feld) {
        return feld.getBesitzer() != null;
    }

    //Methode, um ein Feld zu kaufen, wenn es noch keinen Besitzer hat
    public void feldKaufen(Feld feld, List<Feld> unkaufbareFelder) {
        if (!hatBesitzer(feld) && !unkaufbareFelder.contains(feld)) {
            System.out.println("Möchtest du " + feld.getPreis() + " Intelligenz in das Modul " + feld.getName() + " investieren? (Ja/Nein)");
            Scanner scanner = new Scanner(System.in);
            String antwort = scanner.nextLine();
            
            if (antwort.equalsIgnoreCase("ja")) {
                if (intelligenz >= feld.getPreis()) {
                    setIntelligenz(getIntelligenz() - feld.getPreis());
                    feld.setBesitzer(this);
                    besitz.add(feld); //Hier das Feld zur Liste hinzufügen
                    System.out.println(name + " hat " + feld.getName() + " für " + feld.getPreis() + " Intelligenz erhalten.");
                } else {
                    System.out.println(name + " hat nicht genug Intelligenz, um " + feld.getName() + " zu belegen.");
                }
            } else {
                System.out.println("Du hast entschieden, " + feld.getName() + " zu schieben.");
            }
        } else {
            System.out.println(feld.getName() + " kann nicht erhalten werden.");
        }
    }

    //Methode, um die Miete für ein Feld zu zahlen
    public void mieteZahlen(Feld feld, Spielfigur spieler) {
        if (hatBesitzer(feld) && !feld.getBesitzer().equals(spieler)) {
            int miete = feld.getMiete();
            Spieler besitzer = feld.getBesitzer();
            if (spieler.getIntelligenz() >= miete) {
                spieler.setIntelligenz(spieler.getIntelligenz() - miete);
                besitzer.setIntelligenz(besitzer.getIntelligenz() + miete);
                System.out.println(spieler.getName() + " hat " + miete + " Intelligenz an " + besitzer.getName() + " für Nachhilfe in dem Modul " + feld.getName() + " gegeben.");
            } 
            else {
                System.out.println(spieler.getName() + " hat nicht genug Intelligenz, um die Nachilfe für das Modul " + feld.getName() + " zu verstehen. ");
            }
            
            //Behandlung spezifischer Ereignisse für bestimmte Felder
            if (feld.getName().equalsIgnoreCase("O-Woche")) {
                spieler.setIntelligenz(spieler.getIntelligenz() - 200);
                System.out.println(spieler.getName() + " hat 200 Intelligenz aufgrund von zu hohem Alkoholkonsum verloren.");
            } 
            else if (feld.getName().equalsIgnoreCase("Bachelorabschlussparty")) {
                spieler.setIntelligenz(spieler.getIntelligenz() - 200);
                System.out.println(spieler.getName() + " hat 200 Intelligenz aufgrund zu starkem Feierns auf der Bachelorabschlussparty verloren.");
            } 
        }
    }
    
 //Methode zur Überprüfung, ob ein Spieler pleite ist
    public void spielerPleite(Spielfigur spieler) {
        System.out.println(spieler.getName() + " hat all seine Intelligenz verloren und muss das Bionik Studium schmeißen. ");
        spieler.setPleite(true);
    }
    
}




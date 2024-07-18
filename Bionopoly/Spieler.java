package bionopoly;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Spieler {
	
    private String name;
    
    private int intelligenz;
    
    private boolean pleite;
    
    private List<Feld> besitz;
    private ArrayList<Spielfigur> spielerListe = new ArrayList<>();
   
    private Spielfeld spielfeld;

    public Spieler(String name, int startgeld, Spielfeld spielfeld) {
        this.name = name;
        Währung währung = new Währung();
        währung.setStartgeld(startgeld);
        this.besitz = new ArrayList<>();
        this.pleite = false;
        this.spielfeld = spielfeld;
    }

    public String getName() {
        return name;
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

    public boolean hatBesitzer(Feld feld) {
        return feld.getBesitzer() != null;
    }

    public Spielfeld getSpielfeld() {
        return spielfeld;
    }
    
    public void spielerPleite(Spieler spieler) {
        System.out.println(spieler.getName() + " hat all seine Intelligenz verloren und muss das Bionik Studium schmeißen.");
        spieler.setPleite(true);
    }


    public void feldKaufen(Feld feld) {
        if (!hatBesitzer(feld) && !spielfeld.getUnkaufbareFelder().contains(feld)) {
            System.out.println("Möchtest du " + feld.getPreis() + " Intelligenz in das Modul " + feld.getName() + " investieren? (Ja/Nein)");
            Scanner scanner = new Scanner(System.in);
            String antwort = scanner.nextLine();

            if (antwort.equalsIgnoreCase("ja")) {
                if (intelligenz >= feld.getPreis()) {
                    setIntelligenz(getIntelligenz() - feld.getPreis());
                    feld.setBesitzer(this);
                    besitz.add(feld);
                    System.out.println(name + " hat " + feld.getName() + " für " + feld.getPreis() + " Intelligenz erhalten.");
                } 
                else {
                    System.out.println(name + " hat nicht genug Intelligenz, um " + feld.getName() + " zu belegen.");
                }
            } 
            else {
                System.out.println("Du hast entschieden, " + feld.getName() + " zu schieben.");
            }
        } 
        else {
            System.out.println(feld.getName() + " kann nicht erhalten werden.");
        }
    }

    public void mieteZahlen(Feld feld) {
        Spieler besitzer = feld.getBesitzer();
        if (besitzer != null && !besitzer.equals(this)) {
            int miete = feld.getMiete();
            if (this.getIntelligenz() >= miete) {
                this.setIntelligenz(this.getIntelligenz() - miete);
                besitzer.setIntelligenz(besitzer.getIntelligenz() + miete);
                System.out.println(this.getName() + " hat " + miete + " Intelligenz an " + besitzer.getName() + " für Nachhilfe in dem Modul " + feld.getName() + " gezahlt.");
            } else {
                System.out.println(this.getName() + " hat nicht genug Intelligenz, um die Nachhilfe zu zahlen.");
                this.spielerPleite(this);
                spielerListe.remove(this);
            }
        }
    }
}

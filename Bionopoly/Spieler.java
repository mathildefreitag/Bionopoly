package bionopoly;

import java.awt.Point;
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
    private Spielfeld spielfeld;

    public Spieler(String name, int startgeld, int startIntelligenz, Spielfeld spielfeld) {
        this.name = name;
        Währung währung = new Währung();
        währung.setStartgeld(startgeld);
        this.kontostand = währung.getStartgeld();
        this.besitz = new ArrayList<>();
        this.position = 0;
        this.intelligenz = startIntelligenz;
        this.pleite = false;
        this.spielfeld = spielfeld;
    }

    public String getName() {
        return name;
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

    public boolean hatBesitzer(Feld feld) {
        return feld.getBesitzer() != null;
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

    public void mieteZahlen(Feld feld, Spielfigur spieler) {
        if (hatBesitzer(feld) && !feld.getBesitzer().equals(spieler)) {
            int miete = feld.getMiete();
            Spieler besitzer = feld.getBesitzer();
            if (spieler.getIntelligenz() >= miete) {
                spieler.setIntelligenz(spieler.getIntelligenz() - miete);
                besitzer.setIntelligenz(besitzer.getIntelligenz() + miete);
                System.out.println(spieler.getName() + " hat " + miete + " Intelligenz an " + besitzer.getName() + " für Nachhilfe in dem Modul " + feld.getName() + " gegeben.");
            } else {
                System.out.println(spieler.getName() + " hat nicht genug Intelligenz, um die Nachilfe für das Modul " + feld.getName() + " zu verstehen.");
            }

            if (feld.getName().equalsIgnoreCase("O-Woche")) {
                spieler.setIntelligenz(spieler.getIntelligenz() - 200);
                System.out.println(spieler.getName() + " hat 200 Intelligenz aufgrund von zu hohem Alkoholkonsum verloren.");
            } else if (feld.getName().equalsIgnoreCase("Bachelorabschlussparty")) {
                spieler.setIntelligenz(spieler.getIntelligenz() - 200);
                System.out.println(spieler.getName() + " hat 200 Intelligenz aufgrund zu starkem Feierns auf der Bachelorabschlussparty verloren.");
            }
        }
    }

    public void spielerPleite(Spielfigur spieler) {
        System.out.println(spieler.getName() + " hat all seine Intelligenz verloren und muss das Bionik Studium schmeißen.");
        spieler.setPleite(true);
    }

    public Spielfeld getSpielfeld() {
        return spielfeld;
    }
    
}

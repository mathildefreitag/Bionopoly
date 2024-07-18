package bionopoly;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Spieler {
	
    private String name;
    
    private int intelligenz;
    
    private boolean pleite;
    
    private List<Feld> besitz;
    private ArrayList<Spielfigur> spielerListe = new ArrayList<>(); //Initialisierung der Arrayliste für die Zuordnung von Spielfiguren zu Spielern
   
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
    
    //Methode für den Fall, dass ein Spieler pleite ist (keine Intelligenz)
    public void spielerPleite(Spieler spieler) {
        System.out.println(spieler.getName() + " hat all seine Intelligenz verloren und muss das Bionik Studium schmeißen.");
        spieler.setPleite(true);
    }

    //Methode zum Kaufen/Bestehen eines Moduls
    public void feldKaufen(Feld feld) {
        if (!hatBesitzer(feld) && !spielfeld.getUnkaufbareFelder().contains(feld)) {
            System.out.println("Möchtest du " + feld.getPreis() + " Intelligenz in das Modul " + feld.getName() + " investieren? (Ja/Nein)");
            Scanner scanner = new Scanner(System.in);
            String antwort = scanner.nextLine();

            if (antwort.equalsIgnoreCase("ja")) { //wenn sich der Spieler entscheidet das Modul zu kaufen bzw. zu belegen 
                if (intelligenz >= feld.getPreis()) { //wird geprüft ob genug Intelligenz vorhanden ist
                    setIntelligenz(getIntelligenz() - feld.getPreis()); //Preis des Moduls wird von der Intelligenz abgezogen
                    feld.setBesitzer(this); //Feld bekommt einen Besitzer (der Spieler der es gekauft hat)
                    besitz.add(feld); //Besitzer wird dem Feld hinzugefügt
                    System.out.println(name + " hat " + feld.getName() + " für " + feld.getPreis() + " Intelligenz erhalten."); //Ausgabe (in Konsole)
                } 
                else {
                    System.out.println(name + " hat nicht genug Intelligenz, um " + feld.getName() + " zu belegen."); //Ausgabe falls nicht genug Intelligenz vorhanden ist 
                }
            } 
            else {
                System.out.println("Du hast entschieden, " + feld.getName() + " zu schieben."); //für den Fall, dass der Spieler das Modul nicht belegen/kaufen möchte
            }
        } 
        else {
            System.out.println(feld.getName() + " kann nicht erhalten werden."); //Falls ein Fehler im Verkaufsvorgang/ bei der Mieteinnahme auftritt
        }
    }

    //Methode zum Zahlen der Nachhilfe (Miete)
    public void mieteZahlen(Feld feld) {
        Spieler besitzer = feld.getBesitzer();
        if (besitzer != null && !besitzer.equals(this)) { //Wenn das Feld bereits einen Besitzer hat, der nicht der aktuelle Spieler ist, wird diese Schleife gestartet. 
            int miete = feld.getMiete(); //die Miete entspricht der für das Feld festgelegten Miete
            if (this.getIntelligenz() >= miete) { //Prüfen ob genug Intelligenz vorhanden ist um Nachhilfestunden zu nehmen
                this.setIntelligenz(this.getIntelligenz() - miete); //Intelligenzpunkte in Höhe der Miete werden vom aktuellen Spieler abgezogen
                besitzer.setIntelligenz(besitzer.getIntelligenz() + miete); // Der Besitzer des Feldes bekommt die Intelligenzpunkte für die Nachhilfe
                System.out.println(this.getName() + " hat " + miete + " Intelligenz an " + besitzer.getName() + " für Nachhilfe in dem Modul " + feld.getName() + " gezahlt."); //Ausgabe für die Ausführung der Aktion
            } 
            else {
                System.out.println(this.getName() + " hat nicht genug Intelligenz, um die Nachhilfe zu zahlen."); // wenn nicht genug Intelligenz vorhanden um Miete/Nachhilfe zu zahlen
                this.spielerPleite(this); //aktueller Spieler ist pleite
                spielerListe.remove(this); //Der aktueller Spieler wird entfernt, weil er pleite ist: Der Spieler muss das Studium abbrechen, weil er nicht genug Intelligenz hat.
            }
        }
    }
}

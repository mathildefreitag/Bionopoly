package bionopoly;

public class Spielfigur {
    private String name;
    private int intelligenz;
    private boolean pleite;
    private Feld aktuellesFeld;

    public Spielfigur(String name, Feld startFeld) {
        this.name = name;
        this.intelligenz = 1500; // Startkapital
        this.pleite = false;
        this.aktuellesFeld = startFeld;
    }

    public String getName() {
        return name;
    }

    public void felderVorrücken(int augensumme) {
        int neuesFeldIndex = (aktuellesFeld.getIndex() + augensumme) % Spielfeld.getAnzahlFelder();
        Feld neuesFeld = Spielfeld.getFeld(neuesFeldIndex);
        setAktuellesFeld(neuesFeld);
    }

    public void zugBeenden() {
        System.out.println(name + " hat seinen Zug beendet.");
    }

    public int getIntelligenz() {
        return intelligenz;
    }

    public void setIntelligenz(int intelligenz) {
        this.intelligenz = intelligenz;
    }

    public boolean isPleite() {
        return pleite;
    }

    public void setPleite(boolean pleite) {
        this.pleite = pleite;
    }

    public Feld getAktuellesFeld() {
        return aktuellesFeld;
    }

    public void setAktuellesFeld(Feld aktuellesFeld) {
        this.aktuellesFeld = aktuellesFeld;
    }
}

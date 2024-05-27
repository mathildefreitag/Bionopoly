package bionopoly;

import java.util.ArrayList;
import java.util.List;

public class Spieler {
    private String name;
    private Waehrung kontostand;
    private List<Feld> besitz;
    private int position;

    public Spieler(String name, int startgeld) {
        this.name = name;
        this.kontostand = new Waehrung(startgeld);
        this.besitz = new ArrayList<>();
        this.position = 0;
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

    public Waehrung getKontostand() {
        return kontostand;
    }

    public List<Feld> getBesitz() {
        return besitz;
    }

    public void kaufeFeld(Feld feld) {
        if (feld.istVerfuegbar() && kontostand.subtrahiere(feld.getPreis())) {
            feld.setBesitzer(this);
            besitz.add(feld);
        }
    }
}

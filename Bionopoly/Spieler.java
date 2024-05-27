package bionopoly;

import java.util.ArrayList;
import java.util.List;

public class Spieler {
    private String name;
    private Währung kontostand;
    private List<Spielfeld> besitz;
    private int position;

    public Spieler(String name, int startgeld) {
        this.name = name;
        this.kontostand = new Währung(startgeld);
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

    public Währung getKontostand() {
        return kontostand;
    }

    public List<Spielfeld> getBesitz() {
        return besitz;
    }

    public void kaufeFeld(Spielfeld feld) {
        if (feld.istVerfuegbar() && kontostand.subtrahiere(feld.getPreis())) {
            feld.setBesitzer(this);
            besitz.add(feld);
        }
    }
}

package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import bionopoly.Spielfeld;
import bionopoly.Spielfigur;
import bionopoly.Währung;

public class StartbildschirmGui extends JPanel {
	
	//Initialisierung verschiederner Variablen, Listen etc.
    private JButton startButton;
    private JButton beendenButton;
    
    private JComboBox<String> spielerAnzahlComboBox;

    private List<String> spielerNamen;
    
    private int anzahlSpieler;
    
    private static final Color[] farben = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.MAGENTA}; //Definiert eine Liste von Farben für die Spielfiguren

    
    public StartbildschirmGui() {
    	
        setLayout(new GridBagLayout()); //Startbildschirmlayout
        setBackground(Color.LIGHT_GRAY); //Hintergrund vom Startbildschirm

        JLabel willkommenLabel = new JLabel("<html><div style='font-size: 24px; text-align: center;'>Willkommen zu Bionopoly</div><br>" +
                                            "<div style='font-size: 14px; text-align: center;'>Bitte wählen Sie die gewünschte Spieleranzahl aus</div></html>"); //Label/Satz der auf dem Startbildschirm die Spieler begrüßt
        willkommenLabel.setPreferredSize(new Dimension(400, 100)); //Die Größe des Begrüßungslabels

        JLabel spielerAnzahlLabel = new JLabel("Anzahl der Spieler:"); //Auswahlmöglichkeit wie viele Spieler spielen möchten
        spielerAnzahlComboBox = new JComboBox<>(new String[]{"2 Spieler", "3 Spieler", "4 Spieler", "5 Spieler", "6 Spieler"}); //Aufgelistete Asuwahlmöglichkeiten
        spielerAnzahlComboBox.setPreferredSize(new Dimension(120, 30)); //Größe der Auswahlbox

        startButton = new JButton("Spiel starten"); //Button um das Spiel zu starten
        startButton.setBackground(Color.GREEN); //Farbe des Buttons
        startButton.setPreferredSize(new Dimension(120, 30)); //Größe des Buttons
        startButton.addActionListener(new ActionListener() { //Sorgt dafür das Spiel gestartet wird, wenn Button gedrückt wurde
        	
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAnzahl = (String) spielerAnzahlComboBox.getSelectedItem(); //Je nachdem welche Spieleranzahl ausgewählt wird, werden die entsprechenden Spielerpanels angezeigt
                anzahlSpieler = Integer.parseInt(selectedAnzahl.substring(0, 1)); //Ausgewählte Anzahl wird angezeigt

                spielerNamen = Arrays.asList("Paramecium", "Regenwurm", "Heuschrecke", "Seestern", "Fisch", "Schwein"); //Initialisiert Spielernamen

                Währung währung = new Währung(); //Initialisiert Währung
                Spielfeld spielfeld = new Spielfeld(50, 50, 612, 612); //Initialisiert Spielfeld
                Spielfigur[] spieler = new Spielfigur[anzahlSpieler]; //Initialisiert Spielfigur

                for (int i = 0; i < anzahlSpieler; i++) {
                    Color farbe = farben[i % farben.length];
                    spieler[i] = new Spielfigur(spielerNamen.get(i), spielfeld.feldAmOrt(0), farbe, währung); //Wählt eine Farbe für den Spieler basierend auf der Position in der Liste
                }

                währung.setSpieler(spieler); //Währung für die Spieler aus Währungklasse wird hinzugefügt (Startkapital)

                //Erzeugt und zeigt das SpielbrettGui-Fenster
                SwingUtilities.invokeLater(() -> {
                    SpielbrettGui spielbrettGui = new SpielbrettGui(anzahlSpieler, währung); //Spielerpanels werden angezeigt basierend auf ausgewählter ANzahl
                    spielbrettGui.setVisible(true);
                });
                
                JFrame fenster = (JFrame) SwingUtilities.getWindowAncestor(StartbildschirmGui.this); //Schließt das Startbildschirm-Fenster
                fenster.dispose();
            }
        });

        beendenButton = new JButton("Beenden"); //Erstellt einen Beenden Button, durch den das Fenster geschlossen werden kann
        beendenButton.setBackground(Color.RED); //Farbe des Beenden Button
        beendenButton.setPreferredSize(new Dimension(120, 30)); //Größe des Button
        beendenButton.addActionListener(new ActionListener() { //Sorgt dafür das Fenster geschlossen wird, wenn Button gedrückt wurde
        	
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); //Fenster schließen wird ausgeführt
            }
        });

        GridBagConstraints gitter = new GridBagConstraints(); //Dadurch wird die Position und Ausrichtung der einzelnen Startbildschirmkomponenten spezifiziert
        gitter.gridx = 0; //Für Willkommenslabel Gitterposition (Spalte) auf wird 0 gesetzt
        gitter.gridy = 0; //Für Willkommenslabel Gitterposition (Zeile) wird auf 0 gesetzt
        gitter.insets = new Insets(50, 0, 20, 0); //äußere Abstände um das Willkommenslabel
        add(willkommenLabel, gitter); //Label wird an der spezifizierten Position im Layout hinzugefügt

        gitter.gridy = 1; //Gitterposition (Zeile) auf 1, damit es unter dem Willkommenslabel eingefügt wird
        gitter.insets = new Insets(10, 0, 10, 0); //äußere Abstände um das Spieleranzahllabel
        add(spielerAnzahlLabel, gitter); //Spieleranzahllabel wird an der spezifizierten Position im Layout hinzugefügt

        gitter.gridx = 1; //Gitterposition (Spalte) auf 1, um Auswahlbox neben Spieleranzahllabel zu positionieren
        add(spielerAnzahlComboBox, gitter); //Fügt die Auswahlbox an der spezifizierten Position im Layout hinzu

        gitter.gridx = 0; //Gitterposition (Spalte) wird auf 0 zurückgesetzt
        gitter.gridy = 2; //Gitterposition (Zeile) wird auf 2 gesetzt, damit Startbutton unter die Auswahlbox eingefügt wird
        gitter.gridwidth = 2; //Breite des Startbuttons ist über 2 Gitterspalten
        gitter.anchor = GridBagConstraints.CENTER; //Startbuttonposition wird festgelegt, dass dieser mittig ist
        gitter.insets = new Insets(20, 0, 0, 0); //äußere Abstände um den Startbutton
        add(startButton, gitter); //Startbutton wird an spezifizierter Position im Layout hinzugefügt

        gitter.gridy = 3; //Gitterposition (Zeile) auf 3 damit der Beendenbutton unter dem Startbutton platziert wird
        gitter.insets = new Insets(10, 0, 50, 0); //äußere Abstände um den Beendenbutton
        add(beendenButton, gitter); //Beendenbutton wird an spezifizierter Position im Layout hinzugefügt
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame fenster = new JFrame("Startbildschirm"); //Neues JFrame mit dem Namen Startbildschirm wird erstellt
            fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Fenster kann nur durch Beendenbutton geschlossen werden, "Standardkreuz" zum schließen von Bildschirmfenstern wird entfernt
            fenster.setResizable(false); //Größe des Bildschirmfenster kann nicht verändert werden
            fenster.getContentPane().add(new StartbildschirmGui()); //StarbildschirmGui wird dem JFRame hinzugefügt und somit die darin initialisierten Komponente angezeigt

            fenster.setExtendedState(JFrame.MAXIMIZED_BOTH); //Bildschirmenster wird im Vollbild angezeigt
            fenster.setUndecorated(true); //"Fensterdekoration" (z.B. Titel,Rahmen, usw.) wird entfernt, damit Vollbildmodus sauber angezeigt wird

            Dimension bildschrimGröße = Toolkit.getDefaultToolkit().getScreenSize(); //Bildschirmgröße wird abgerufen
            int mitteX = bildschrimGröße.width / 2; //X-Koordinate für die Zentrierung des Fensters wird berechnet
            int mitteY = bildschrimGröße.height / 2; //Y-Koordinate für die Zentrierung des Fensters wird berechnet
            fenster.setLocation(mitteX - fenster.getWidth() / 2, mitteY - fenster.getHeight() / 2); //Die Position des Fensters wird auf die berechnete Mitte des Bildschirms gesetzt

            fenster.setVisible(true); //Macht das Startbildschirmfenster sichtbar
        });
    }
}

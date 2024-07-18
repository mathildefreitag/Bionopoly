package gui;

import bionopoly.Wuerfel;
import bionopoly.Feld;
import bionopoly.Spielfigur;
import bionopoly.Waehrung;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class SpielbrettGui extends JFrame {
	
	//Intitalisierung der Variablen, Listen etc.
    private JLabel augenZahlValue;
    private JLabel aktuellerSpielerLabel;
    private JLabel[] spielerIntelligenzLabel;
    
    private List<String> spielerNamen;
    
    private boolean kannWürfeln = true;
    
    private int aktuellerSpielerIndex = 0;
    
    private Waehrung währung;
    

    public SpielbrettGui(int anzahlSpieler, Waehrung währung) { //Spielbrett wird erzeugt
    	
        this.währung = währung; //Varaible deklarieren
        setTitle("Bionopoly"); //Titel festlegen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Schließt sich beim anklicken des X
        setSize(1500, 800); //Größe festlegen
        setResizable(false); //Größe kann nicht verändert werden
        setLayout(new BorderLayout());
        
        // Spieler Panels (Pro Spieler wird ein Panel erstellt)
        JPanel linkesPanel = new JPanel(); //linkes Panel erstellt
        linkesPanel.setLayout(new BorderLayout());
        linkesPanel.setPreferredSize(new Dimension(380, getHeight())); //Größe festlegen

        JPanel rechtesPanel = new JPanel(); //rechts Panel erstellt
        rechtesPanel.setLayout(new BorderLayout());
        rechtesPanel.setPreferredSize(new Dimension(376, getHeight())); //Größe festlegen

        JPanel panelLinks = new JPanel();
        panelLinks.setLayout(new GridLayout(3, 1)); //Anordnung imm inneren des Panels festgelegt
        panelLinks.setPreferredSize(new Dimension(200, 300)); //Größe festlegen

        JPanel panelRechts = new JPanel();
        panelRechts.setLayout(new GridLayout(3, 1)); //Anordnung imm inneren des Panels festgelegt
        panelRechts.setPreferredSize(new Dimension(200, 300)); //Größe festlegen

        // Zusätzliches Panel unten für weitere Anzeigen oder Buttons
        JPanel panelUntenLinks = new JPanel();
        panelUntenLinks.setPreferredSize(new Dimension(200, 300)); //Größe festlegen

        JPanel panelUntenRechts = new JPanel();
        panelUntenRechts.setPreferredSize(new Dimension(200, 300)); //Größe festlegen

        
        spielerNamen = Arrays.asList("Paramecium", "Regenwurm", "Heuschrecke", "Seestern", "Fisch", "Schwein"); //Liste von Spielernamen

        spielerIntelligenzLabel = new JLabel[anzahlSpieler]; //Anzahl der Spieler

        // Spieler initialisieren mit individuellen Namen
        for (int i = 0; i < anzahlSpieler; i++) {
            String spielerName = währung.getSpieler()[i].getName(); //Name aus der Währungsklasse
            if (i < 3) {
                panelLinks.add(erstelleSpielerPanel(spielerName, i)); //Pro Spieler ein Panel erstellen
            } else {
                panelRechts.add(erstelleSpielerPanel(spielerName, i)); //Wenn es mehr als drei SPieler sind, Panels auf der Rechten Seite erstellen
            }
        }

        //Kontainer für die Spieler Panels und weitere Panels und deren Ausrichtung wurde festgelegt
        linkesPanel.add(panelLinks, BorderLayout.CENTER);
        linkesPanel.add(panelUntenLinks, BorderLayout.SOUTH);

        rechtesPanel.add(panelRechts, BorderLayout.CENTER);
        rechtesPanel.add(panelUntenRechts, BorderLayout.SOUTH);

        //Panels in Kontainer auf bestimmter Seite einfügen
        add(linkesPanel, BorderLayout.WEST);
        add(rechtesPanel, BorderLayout.EAST);

        JPanel SpielbrettPanel = new JPanel();  //Spielbrett Panel mittig eingefügt
        SpielbrettPanel.setPreferredSize(new Dimension(400, 400)); //Größe festlegen

        ImageIcon icon = new ImageIcon("spielbrett2.0.png"); //Bild eingefügt
        int bildgröße = 730; //Zahl für die Pizelgröße des Spielbretts
        ImageIcon skalierungIcon = new ImageIcon(icon.getImage().getScaledInstance(bildgröße, bildgröße, Image.SCALE_SMOOTH)); //Bild wird skaliert
        JLabel label = new JLabel(skalierungIcon);
        label.setBorder(new EmptyBorder(0, 0, 0, 0));
        SpielbrettPanel.add(label); //Label zum Panel hinzufügel
        add(SpielbrettPanel, BorderLayout.CENTER); //Das ganze wird ins mittlere Panel eingefügt

        würfelPanelErstellen(panelUntenRechts);//Wuerfel Panel erstellt

        updateAktuellerSpieler(); // Aktueller Spieler  wird angezeigt
    }

    //Wuerfel und Aktions-Buttons werden in einem Panel unten rechts erstellt und erhalten ihre Funktionen
    private void würfelPanelErstellen(JPanel panel) { 
    	
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //Panel für den aktuellen Spieler
        JPanel namenAnzeige = new JPanel();
        namenAnzeige.setPreferredSize(new Dimension(400, 30)); // Größe der Panels der Anzeige des aktuellen Spielers
        aktuellerSpielerLabel = new JLabel("Aktueller Spieler: ", SwingConstants.CENTER); //Text und Positionierung im Panel
        aktuellerSpielerLabel.setFont(new Font("Arial", Font.PLAIN, 15)); //Schriftart und Schriftgröße
        namenAnzeige.add(aktuellerSpielerLabel); //Spieleralbel wird zur Namenanzeige hinzugefügt
        panel.add(namenAnzeige);

        //Panel für die Wuerfel
        JPanel würfelPanel = new JPanel(); //neues Panel für die Wuerfel hinzugefügt
        würfelPanel.setPreferredSize(new Dimension(400, 100)); //Größe des Panels der Wuerfel

        JLabel würfel1 = new JLabel("1", SwingConstants.CENTER); //Erster Wert für den Anfang, Positionierung der Zahl
        würfel1.setFont(new Font("Arial", Font.BOLD, 18)); //Schriftart und Größe der Zahl auf dem Wuerfel
        würfel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); //Outline für den Wuerfel
        würfel1.setPreferredSize(new Dimension(40, 40)); //Größe des Würfels
        würfelPanel.add(würfel1);

        JLabel würfel2 = new JLabel("1", SwingConstants.CENTER); //das selbe füf den 2. Wuerfel
        würfel2.setFont(new Font("Arial", Font.BOLD, 18));
        würfel2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        würfel2.setPreferredSize(new Dimension(40, 40));
        würfelPanel.add(würfel2);

        panel.add(würfelPanel); //Wuerfel Panel hinzugefügt

        //"Würfeln"-Button
        JButton würfelButton = new JButton("Würfeln");
        würfelButton.setPreferredSize(new Dimension(200, 25)); //Festlegung der Größe
        würfelButton.addActionListener(new ActionListener() { //ActionListener hinzugefügt
        	
            public void actionPerformed(ActionEvent e) { //wird ausgeführt wenn Button gedrückt wurde
            	
                if (kannWürfeln) {
                    Wuerfel würfel = new Wuerfel(); //Wuerfel erstellt/importiert
                    würfel.würfel(); //Wuerfel aus Wuerfel-Klasse werden aufgerufen
                    würfel1.setText(String.valueOf(würfel.getWürfel1())); //Die Werte der Wuerfel werden gehohlt
                    würfel2.setText(String.valueOf(würfel.getWürfel2()));
                    augenZahlValue.setText(String.valueOf(würfel.getAugensumme())); //Die gewürfelte Augenzahl wird angezeigt

                    kannWürfeln = false;

                    if (würfel.istPasch()) {
                        JOptionPane.showMessageDialog(panel, "Pasch! Du darfst noch einmal würfeln."); //Pop-Up Nachricht
                        kannWürfeln = true; //nach einem Pasch darf nochmal gewürfelt werden, ein Pop-Up weißt den Spieler darauf hin
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Du hast bereits gewürfelt!", "Fehler", JOptionPane.ERROR_MESSAGE); //Der Spieler darf nicht nochmal würfeln, deshalb taucht ein Pop-Up als Hinweiß auf
                }
            }
        });

        panel.add(würfelButton); //Button zum Panel hinzugefügt

        //Panel für gewürfelte Augenzahl
        JPanel augenZahlPanel = new JPanel(); //neues Panel erstellt 
        augenZahlPanel.setPreferredSize(new Dimension(400, 30)); //Größe festgelegt
        JLabel augenZahlLabel = new JLabel("Gewürfelte Augenzahl: "); //Inhalt des Panels festgelegt
        augenZahlPanel.add(augenZahlLabel); //Panel zum Label hinzugefügt
        augenZahlValue = new JLabel("0"); //Wert für den Anfang
        augenZahlPanel.add(augenZahlValue);
        panel.add(augenZahlPanel); //Augenzahl Panel hinzugefügt

        //Panel für Buttons "Kaufen" und "Zug beenden"
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(400, 30)); //Größe festgelegt
        
        //"Kaufen"-Button
        JButton KaufenButton = new JButton("Kaufen"); //Button zum Kaufen erstellt
        KaufenButton.addActionListener(new ActionListener() { //ActionListener hinzugefügt
        	
            public void actionPerformed(ActionEvent e) { //wird ausgeführt wenn Button gedrückt wurde
            	
                Spielfigur aktuellerSpieler = währung.getSpieler()[aktuellerSpielerIndex]; //Wer ist der aktuelle Spieler?
                Feld aktuellesFeld = aktuellerSpieler.getAktuellesFeld(); //Was ist der Standort des aktuellen Spielers?
                
                if (aktuellesFeld.getBesitzer() == null && !aktuellerSpieler.getSpielfeld().getUnkaufbareFelder().contains(aktuellesFeld)) { //Überprüfung ob das Feld zum kaufen zur Verfügung steht
                    int feldPreis = aktuellesFeld.getPreis();

                    if (aktuellerSpieler.getIntelligenz() >= feldPreis) { //Der Spieler kann sich das Modul leisten
                        aktuellerSpieler.setIntelligenz(aktuellerSpieler.getIntelligenz() - feldPreis); //Modulpreis wird von Intteligenz abgezogen
                        aktuellesFeld.setBesitzer(aktuellerSpieler); //Spieler wird zum Besitzer des Moduls
                        spielerIntelligenzLabel[aktuellerSpielerIndex].setText("Intelligenz: " + aktuellerSpieler.getIntelligenz()); //Menge an Intteligenz des Spielers wird angezeigt
                        JOptionPane.showMessageDialog(panel, "Du hast das Modul " + aktuellesFeld.getName() + " erfolgreich bestanden!"); //Output erfolgreicher Kauf
                    } 
                    else {
                        JOptionPane.showMessageDialog(panel, "Du hast nicht genug Intelligenz, um dieses Modul zu bestehen!", "Fehler", JOptionPane.ERROR_MESSAGE); //Der Spieler kann sich das Feld nicht leisten
                    }
                } 
                else {
                    JOptionPane.showMessageDialog(panel, "Dieses Feld kann nicht bestanden werden!", "Fehler", JOptionPane.ERROR_MESSAGE); //Das Feld gehöhrt entwerder zu den unkaufbaren Feldern oder ist bereits verkauft
                }
            }
        });
        
        buttonPanel.add(KaufenButton); //Button wird zum Panel hinzugefügt
        
        //"Zug Beenden"-Button
        JButton zugBeendenButton = new JButton("Zug beenden"); //Das steht auf dem Button drauf
        zugBeendenButton.addActionListener(new ActionListener() { //ActionListener hinzugefügt
        	
            public void actionPerformed(ActionEvent e) { //wird ausgeführt wenn Button gedrückt wurde
            	
                zugBeenden();
            }
        });
        
        buttonPanel.add(zugBeendenButton); //Button wird zum Panel hinzugefügt
        panel.add(buttonPanel); //Panel wird hinzugefügt
    }

    //Erstellen eines Spieler-Panels
    private JPanel erstelleSpielerPanel(String spielerName, int spielerIndex) { //Die Spieler Panel werden durch diese Methode erstellt
    	
        JPanel SpielerPanel = new JPanel(); //Name für das Panel
        SpielerPanel.setPreferredSize(new Dimension(200, 120)); //Höhe erhöht, damit der Rset darunter passt
        SpielerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Schwarze Outline
        SpielerPanel.setLayout(new BorderLayout());

        
        JLabel nameLabel = new JLabel(spielerName); //Label für die Spielernamen
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER); //Festlegung der Ausrichtung des Labels
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16)); //Festlegen der Schriftgröße und Schriftart
        SpielerPanel.add(nameLabel, BorderLayout.NORTH);//Hinzufügen des Labels zum Spielerpanel

        
        JPanel IntelligenzPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5)); //Panel für die Intelligenzanzeige
        IntelligenzPanel.setOpaque(false); //Transparentes Panel für Hintergrund der Intelligenzanzeige

        //Label für Waehrung (Intelligenz) mit angepasster Schriftgröße und Textposition
        JLabel intelligenzLabel = new JLabel("Intelligenz: " + währung.getSpieler()[spielerIndex].getIntelligenz()); //Aktueller Stand der Intelligenz des Spielers wird angezeigt 
        intelligenzLabel.setHorizontalAlignment(SwingConstants.CENTER);//Festlegung der Ausrichtung des Labels
        intelligenzLabel.setFont(new Font("Arial", Font.PLAIN, 14)); //Festlegen der Schriftgröße und Schriftart
        IntelligenzPanel.add(intelligenzLabel); //Label wird zum Panel hinzugefügt

        SpielerPanel.add(IntelligenzPanel, BorderLayout.CENTER); //Hinzufügen des Labels zum Spielerpanel

        spielerIntelligenzLabel[spielerIndex] = intelligenzLabel;

        return SpielerPanel;
    }

    private void updateAktuellerSpieler() { //Methode für das wechseln von einem Spieler zum nächsten
        if (aktuellerSpielerIndex < spielerNamen.size()) { //Anzeige nur, solange es Spieler gibt
            aktuellerSpielerLabel.setText("Aktueller Spieler: " + währung.getSpieler()[aktuellerSpielerIndex].getName()); //Anzeige, wer aktuell drann ist
        }
    }

    private void zugBeenden() { //Methode zum beenden des Zuges
        kannWürfeln = true; //Der nächste Spieler ist dran darf wieder würfeln
        aktuellerSpielerIndex = (aktuellerSpielerIndex + 1) % währung.getSpieler().length; //Der aktuelle Spielert wird auf den nächsten Spieler gesetzt
        updateAktuellerSpieler();
    }
}

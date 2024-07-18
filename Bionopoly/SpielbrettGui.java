package gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import bionopoly.Feld;
import bionopoly.Spielfigur;
import bionopoly.Waehrung;
import bionopoly.Wuerfel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
//import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class SpielbrettGui extends JFrame {
	
	//Intitalisierung der Variablen, Listen etc.
    private JLabel wertAugenzahl;
    private JLabel aktuellerSpielerLabel;
    private JLabel[] spielerIntelligenzLabel;
    
    private List<String> spielerNamen;
    
    private boolean kannWürfeln = true;
    
    private int aktuellerSpielerIndex = 0;
    
    private Waehrung währung;
    
    private JLabel[] spielerStandortLabel;
	private JLabel[] spielerBestandeneModuleLabels;
    

    public SpielbrettGui(int anzahlSpieler, Waehrung währung) { //Spielbrett wird erzeugt
    	
        this.währung = währung; //Variable deklarieren
        setTitle("Bionopoly"); //Titel festlegen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Schließt sich beim anklicken des X
        setSize(1500, 800); //Größe festlegen
        setResizable(false); //Größe kann nicht verändert werden
        setLayout(new BorderLayout());

        //Spieler Panels Container, in dem einzelne Panels für jeden Spieler sind
        JPanel linkerContainer = new JPanel(); //linker Container wird erstellt
        linkerContainer.setLayout(new BorderLayout());
        linkerContainer.setPreferredSize(new Dimension(380, getHeight())); //Größe festlegen

        JPanel rechterContainer = new JPanel(); //rechter Container wird erstellt
        rechterContainer.setLayout(new BorderLayout());
        rechterContainer.setPreferredSize(new Dimension(376, getHeight())); //Größe festlegen
        
        JPanel linkePanels = new JPanel();
        linkePanels.setLayout(new GridLayout(3, 1)); //Anordnung im inneren des Containers festgelegt
        linkePanels.setPreferredSize(new Dimension(200, 300)); //Größe festlegen

        JPanel rechtePanels = new JPanel(); //Anordnung im inneren des Containers festgelegt
        rechtePanels.setLayout(new GridLayout(3, 1));
        rechtePanels.setPreferredSize(new Dimension(200, 300)); //Größe festlegen

        //Zusätzliches Panel unten für weitere Anzeigen oder Buttons
        JPanel panelUntenLinks = new JPanel();
        panelUntenLinks.setPreferredSize(new Dimension(200, 300)); //Größe festlegen

        JPanel panelUntenRechts = new JPanel();
        panelUntenRechts.setPreferredSize(new Dimension(200, 300)); //Größe festlegen

        spielerNamen = Arrays.asList("Paramecium", "Regenwurm", "Heuschrecke", "Seestern", "Fisch", "Schwein");

        spielerIntelligenzLabel = new JLabel[anzahlSpieler]; //Anzahl der Spieler
        spielerStandortLabel = new JLabel[anzahlSpieler]; //Labels für Standortanzeige
        spielerBestandeneModuleLabels = new JLabel[anzahlSpieler]; //Label für Besitz Anzeige

        //Spieler initialisieren mit individuellen Namen
        for (int i = 0; i < anzahlSpieler; i++) {
            String spielerName = spielerNamen.get(i); //Name aus der Liste der Spielernamen
            if (i < 3) {
                linkePanels.add(erstelleSpielerPanel(spielerName, i)); //Pro Spieler ein Panel erstellen
            } else {
                rechtePanels.add(erstelleSpielerPanel(spielerName, i)); //Wenn es mehr als drei Spieler sind, Panels auf der rechten Seite erstellen für mehr Spieler (bis zu 6)
            }
        }

        //Container für die Spieler Panels und weitere Panels und deren Ausrichtung wurde festgelegt
        linkerContainer.add(linkePanels, BorderLayout.CENTER);
        linkerContainer.add(panelUntenLinks, BorderLayout.SOUTH);

        rechterContainer.add(rechtePanels, BorderLayout.CENTER);
        rechterContainer.add(panelUntenRechts, BorderLayout.SOUTH);

        //Panels in Container einfügen auf bestimmter Seite einfügen
        add(linkerContainer, BorderLayout.WEST);
        add(rechterContainer, BorderLayout.EAST);

        JPanel SpielbrettPanel = new JPanel();  //Spielbrett als Bild mittig einfügen
        SpielbrettPanel.setPreferredSize(new Dimension(400, 400)); //Größe festlegen

        ImageIcon icon = new ImageIcon("spielbrett2.0.png"); //Bild eingefügt
        int imageSize = 730; //Zahl für die Pixelgröße des Spielbrett
        ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH)); //Bild wird skaliert
        JLabel label = new JLabel(scaledIcon);
        label.setBorder(new EmptyBorder(0, 0, 0, 0));
        SpielbrettPanel.add(label); //Label zum Panel hinzufügen
        add(SpielbrettPanel, BorderLayout.CENTER); //Das ganze wird ins mittlere Panel eingefügt

        // Würfel GUI erstellen
        würfelPanelErstellen(panelUntenRechts); //Wuerfel Panel erstellt
        InformationenPanelErstellen(panelUntenLinks); //Informationen Panel erstellt

        updateAktuellerSpieler(); //Aktueller Spieler  wird angezeigt
    }
    
  //Feldinformation wird in einem Panel unten linkss erstellt 
   private void InformationenPanelErstellen(JPanel panel) {
	   
	   JLabel fieldInfoLabel = new JLabel("Feldinformation:"); //Text im Panel
	   fieldInfoLabel.setHorizontalAlignment(SwingConstants.CENTER); //Positionierung im Panel
	   fieldInfoLabel.setFont(new Font("Arial", Font.BOLD, 20)); //Schriftart und Schriftgröße
	   panel.add(fieldInfoLabel); //Feldinformationslabel wird hinzugefügt
   }
   
 //Wuerfel und Aktions-Buttons werden in einem Panel unten rechts erstellt und erhalten ihre Funktionen
    private void würfelPanelErstellen(JPanel panel) {
    	
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //Panel für aktuellen Spieler
        JPanel namenAnzeige = new JPanel();
        namenAnzeige.setPreferredSize(new Dimension(400, 30)); //Größe der Panels der Anzeige des aktuellen Spielers
        aktuellerSpielerLabel = new JLabel("Aktueller Spieler: ", SwingConstants.CENTER); //Text und Positionierung im Panel
        aktuellerSpielerLabel.setFont(new Font("Arial", Font.PLAIN, 15)); //Schriftart und Schriftgröße
        namenAnzeige.add(aktuellerSpielerLabel); //Spielerlabel wird zur Namensanzeige hinzugefügt
        panel.add(namenAnzeige);

        //Panel für Wuerfel
        JPanel würfelPanel = new JPanel(); //neues Panel für die Wuerfel hinzugefügt
        würfelPanel.setPreferredSize(new Dimension(400, 100)); //Größe des Panels der Wuerfel

        JLabel würfel1 = new JLabel("1", SwingConstants.CENTER); //Erster Wert für den Anfang, Positionierung der Zahl
        würfel1.setFont(new Font("Arial", Font.BOLD, 18)); //Schriftart und Größe der Zahl auf dem Wuerfel
        würfel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); //Outline für den Wuerfel
        würfel1.setPreferredSize(new Dimension(40, 40)); //Größe des Würfels
        würfelPanel.add(würfel1);

        JLabel würfel2 = new JLabel("1", SwingConstants.CENTER); //das selbe für den 2. Wuerfel
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
                    wertAugenzahl.setText(String.valueOf(würfel.getAugensumme())); //Die gewürfelte Augenzahl wird angezeigt

                    kannWürfeln = false;

                    if (würfel.istPasch()) {
                        JOptionPane.showMessageDialog(panel, "Pasch! Du darfst noch einmal würfeln."); //Pop-Up Nachricht
                        kannWürfeln = true; //nach einem Pasch darf nochmal gewürfelt werden, ein Pop-Up weißt den Spieler darauf hin
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Du hast bereits gewürfelt!", "Fehler", JOptionPane.ERROR_MESSAGE); //Der Spieler darf nicht nochmal würfeln, deshalb taucht ein Pop-Up als Hinweis auf
                }
            }
        });         

        panel.add(würfelButton); //Button zum Panel hinzugefügt

        //Panel für gewürfelte Augenzahl
        JPanel augenZahlPanel = new JPanel(); //neues Panel erstellt
        augenZahlPanel.setPreferredSize(new Dimension(400, 30)); //Größe festgelegt
        JLabel augenZahlLabel = new JLabel("Gewürfelte Augenzahl: "); //Inhalt des Panels festgelegt
        augenZahlPanel.add(augenZahlLabel); //Panel zum Label hinzugefügt
        wertAugenzahl = new JLabel("0"); //Wert für den Anfang
        augenZahlPanel.add(wertAugenzahl);
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
                        aktuellerSpieler.setIntelligenz(aktuellerSpieler.getIntelligenz() - feldPreis); //Modulpreis wird von Intelligenz abgezogen
                        aktuellesFeld.setBesitzer(aktuellerSpieler); //Spieler wird zum Besitzer des Moduls
                        spielerIntelligenzLabel[aktuellerSpielerIndex].setText("Intelligenz: " + aktuellerSpieler.getIntelligenz()); //Menge an Intelligenz des Spielers wird angezeigt
                        JOptionPane.showMessageDialog(panel, "Du hast das Feld " + aktuellesFeld.getName() + "erfolgreich gekauft!"); //Output erfolgreicher Kauf
                    } else {
                        JOptionPane.showMessageDialog(panel, "Du hast nicht genug Intelligenz, um dieses Feld zu kaufen!", "Fehler", JOptionPane.ERROR_MESSAGE); //Der Spieler kann sich das Feld nicht leisten
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(panel, "Dieses Feld kann nicht gekauft werden!", "Fehler", JOptionPane.ERROR_MESSAGE); //Das Feld gehöhrt entwerder zu den unkaufbaren Feldern oder ist bereits verkauft               
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
        
        buttonPanel.add(zugBeendenButton); //Button wird zum Panel hinzugefüg
        panel.add(buttonPanel); //Panel wird hinzugefügt
    }

    //Erstellen eines Spieler-Panels
    private JPanel erstelleSpielerPanel(String spielerName, int spielerIndex) { //Die Spieler Panel werden durch diese Methode erstellt
    	
        JPanel spielerPanel = new JPanel(); //Name für das Panel
        spielerPanel.setPreferredSize(new Dimension(200, 140)); //Höhe erhöht, damit die anderen Komponenten darunter passen
        spielerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //Schwarze Outline
        spielerPanel.setLayout(new BorderLayout());

       
        JLabel nameLabel = new JLabel(spielerName); //Label für die Spielernamen
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER); //Festlegung der Ausrichtung des Labels
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16)); //Festlegen der Schriftgröße und Schriftart
        spielerPanel.add(nameLabel, BorderLayout.NORTH); //Hinzufügen des Labels zum Spielerpanel

        //Panel für Intelligenzanzeige
        JPanel intelligenzPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5)); //Panel für die Intelligenzanzeige
        intelligenzPanel.setOpaque(false); // Transparentes Panel für Hintergrund des Währungstextes

        //Label für Waehrung (Intelligenz) mit angepasster Schriftgröße und Textposition
        JLabel intelligenzLabel = new JLabel("Intelligenz: " + währung.getSpieler()[spielerIndex].getIntelligenz()); //Aktueller Stand der Intelligenz des Spielers wird angezeigt
        intelligenzLabel.setHorizontalAlignment(SwingConstants.CENTER); //Festlegung der Ausrichtung des Labels
        intelligenzLabel.setFont(new Font("Arial", Font.PLAIN, 14)); //Festlegen der Schriftgröße und Schriftart
        intelligenzPanel.add(intelligenzLabel); //Label wird zum Panel hinzugefügt

        spielerPanel.add(intelligenzPanel, BorderLayout.CENTER); //Hinzufügen des Labels zum Spielerpanel

        spielerIntelligenzLabel[spielerIndex] = intelligenzLabel;

        //Panel für Standortanzeige
        JPanel standortPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5)); //Panel für Standortanzeige
        standortPanel.setOpaque(false); //Transparentes Panel für Hintergrund des Standorttextes

        //Label für Standort mit angepasster Schriftgröße und Textposition
        JLabel standortLabel = new JLabel("Aktueller Standort: " + währung.getSpieler()[spielerIndex].getAktuellesFeld().getName()); //Aktueller Standort des Spielers
        standortLabel.setHorizontalAlignment(SwingConstants.CENTER); //Festlegung der Ausrichtung des Labels
        standortLabel.setFont(new Font("Arial", Font.PLAIN, 14)); //Festlegen der Schriftgröße und Schriftart für Standortanzeige
        standortPanel.add(standortLabel); //Label wird zum Panel hinzugefügt

        spielerPanel.add(standortPanel, BorderLayout.SOUTH); //Hinzufügen des Labels zum Spielerpanel

        spielerStandortLabel[spielerIndex] = standortLabel;
        
        //Panel für bestandene Module
        JPanel bestandeneModulePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5)); //Panel für Standortanzeige
        bestandeneModulePanel.setOpaque(false); //Transparentes Panel für Hintergrund des Standorttextes
        
       //Label für bestandene Module mit angepasster Schriftgröße und Textposition
        JLabel bestandeneModuleLabel = new JLabel("   Bestandene Module:"); //Hier sollen bestandene Module angezeigt werden
        bestandeneModuleLabel.setHorizontalAlignment(SwingConstants.CENTER); //Festlegung der Ausrichtung des Labels
        bestandeneModuleLabel.setFont(new Font("Arial", Font.PLAIN, 14)); //Festlegen der Schriftgröße und Schriftart für Standortanzeige
        bestandeneModulePanel.add(bestandeneModuleLabel); //Label wird zum Panel hinzugefügt
        
        spielerPanel.add(bestandeneModuleLabel, BorderLayout.WEST); //Hinzufügen des Labels zum Spielerpanel
        
		spielerBestandeneModuleLabels[spielerIndex] = bestandeneModuleLabel;

        return spielerPanel;
    }

    private void updateAktuellerSpieler() { //Methode für das wechseln von einem Spieler zum nächsten
        if (aktuellerSpielerIndex < spielerNamen.size()) { //Anzeige nur, solange es Spieler gibt
            aktuellerSpielerLabel.setText("Aktueller Spieler: " + währung.getSpieler()[aktuellerSpielerIndex].getName()); //Anzeige, wer aktuell drann ist
        }
    }
    

    private void zugBeenden() { //Methode zum beenden des Zuges
        kannWürfeln = true; //Der nächste Spieler ist dran darf wieder würfeln
        aktuellerSpielerIndex = (aktuellerSpielerIndex + 1) % währung.getSpieler().length; //Der aktuelle Spieler wird auf den nächsten Spieler gesetzt
        updateAktuellerSpieler();
    }
}

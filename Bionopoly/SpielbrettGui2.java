package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import bionopoly.Würfel;

public class SpielbrettGui2 extends JFrame {
	 private Würfel würfel = new Würfel();
	//SpielerGui und SpielbrettGui zusammengeführt, Probleme behoben, deswegen 2
    public SpielbrettGui2 (int anzahlSpieler) {
        setTitle("Bionopoly");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        //Spieler Panels Container
        JPanel leftContainer = new JPanel();
        leftContainer.setLayout(new BorderLayout()); //BorderLayout zum Hinzufügen des linken Container
        leftContainer.setPreferredSize(new Dimension(380, getHeight()));

        JPanel rightContainer = new JPanel();
        rightContainer.setLayout(new BorderLayout()); //BorderLayout zum Hinzufügen des rechten Conatainer
        rightContainer.setPreferredSize(new Dimension(376, getHeight()));

        //Spieler Panels
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(3, 1)); //3 Zeilen, 1 Spalte ohne Abstände
        leftPanel.setPreferredSize(new Dimension(200, 300)); //Maße der Kästen

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(3, 1)); //3 Zeilen, 1 Spalte mit Abständen
        rightPanel.setPreferredSize(new Dimension(200, 300)); //Maße der Kästen

        //Zusätzliches Panel unten
        JPanel bottomPanelLeft = new JPanel();
        bottomPanelLeft.setPreferredSize(new Dimension(200, 300)); //Höhe des zusätzlichen Panels

        JPanel bottomPanelRight = new JPanel();
        bottomPanelRight.setPreferredSize(new Dimension(200, 300)); //Höhe des zusätzlichen Panels

        //Spieler initialisieren
        for (int i = 1; i <= anzahlSpieler; i++) {
            if (i <= 3) {
                leftPanel.add(createPlayerPanel("Spieler " + i));
            } else {
                rightPanel.add(createPlayerPanel("Spieler " + i));
            }
        }


        //Container für die Spieler Panels und zusätzliches Panel Ausrichtung
        leftContainer.add(leftPanel, BorderLayout.CENTER);
        leftContainer.add(bottomPanelLeft, BorderLayout.SOUTH);

        rightContainer.add(rightPanel, BorderLayout.CENTER);
        rightContainer.add(bottomPanelRight, BorderLayout.SOUTH);

        //Panels in Container einfügen
        add(leftContainer, BorderLayout.WEST);
        add(rightContainer, BorderLayout.EAST);

        // Spielbrett als Bild mittig einfügen
        JPanel boardPanel = new JPanel();
        boardPanel.setPreferredSize(new Dimension(400, 400));

        //Pfad zum PNG-Bild
        String imagePath = "C:\\Users\\sina3\\OneDrive\\Dokumente\\HSB 2. Semester\\Informatik\\Softwareprojekt\\src\\bionopoly\\spielbrett2.0.png";

        //ImageIcon erstellen und dem JLabel zuweisen
        ImageIcon icon = new ImageIcon(imagePath);
        //Bildgröße anpassen
        int imageSize = 780; //Quadratische Größe für das Bild (angepasst an die Bildschirmgröße)
        ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(imageSize, imageSize, java.awt.Image.SCALE_SMOOTH));
        JLabel label = new JLabel(scaledIcon);

        //Rahmen hinzufügen
        label.setBorder(new EmptyBorder(0, 0, 0, 0)); //0 Pixel Abstand zum Rahmen
  
        boardPanel.add(label);
        add(boardPanel, BorderLayout.CENTER);
     //Würfel GUI erstellen
        createDicePanel(bottomPanelRight);
    }

    //Methode des Würfel Panels
    private void createDicePanel(JPanel panel) {
        JPanel dicePanel = new JPanel(new GridLayout(1, 2, 10, 10));
        dicePanel.setPreferredSize(new Dimension(100, 50));
        
        JLabel dice1 = new JLabel("1", SwingConstants.CENTER);
        dice1.setFont(new Font("Arial", Font.BOLD, 24));
        dice1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        JLabel dice2 = new JLabel("1", SwingConstants.CENTER);
        dice2.setFont(new Font("Arial", Font.BOLD, 24));
        dice2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        JButton rollButton = new JButton("Würfeln");
        rollButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                würfel.würfel();  //wuerfel-Methode aufrufen
                dice1.setText(String.valueOf(würfel.getWürfel1()));
                dice2.setText(String.valueOf(würfel.getWürfel2()));
            }
        });
        
        dicePanel.add(dice1);
        dicePanel.add(dice2);
        
        panel.add(dicePanel, new GridBagConstraints());
        panel.add(rollButton, new GridBagConstraints());
    }
    //Hinzufügen "Spielernamen"
    private JPanel createPlayerPanel(String playerName) {
        JPanel playerPanel = new JPanel();
        playerPanel.setPreferredSize(new Dimension(200, 100));
        playerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        playerPanel.add(new JLabel(playerName));
        return playerPanel;
    }
}

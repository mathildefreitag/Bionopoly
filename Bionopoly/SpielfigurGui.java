package gui;

import bionopoly.Spielfigur;
import bionopoly.Würfel;
import bionopoly.Spielfeld;
import bionopoly.Feld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class SpielfigurGui extends JFrame {
    private Map<String, JLabel> spielfigurenLabels;
    private JPanel spielfeld;
    private Spielfeld feldAmOrt;

    public SpielfigurGui() {
        setTitle("Bionopoly");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        spielfeld = new JPanel();
        spielfeld.setLayout(null);
        spielfigurenLabels = new HashMap<>();
        int startfeld = 0;

        
        spielfeld = new Spielfeld(50, 50, 612, 612);
        spielfeld.setLayout(null); // Layout sollte nicht null sein, besser wäre eine Layout-Manager zu verwenden
        spielfigurenLabels = new HashMap<>();
        
       A 
        // Beispielhafte Spielfiguren hinzufügen
        /*addSpielfigur(new Spielfigur("Regenwurm", spielfeld.feldAmOrt(0), spielfeld), "regenwurm.png");
        addSpielfigur(new Spielfigur("Paramecium", spielfeld.feldAmOrt(0), spielfeld), "paramecium.png");
        addSpielfigur(new Spielfigur("Heuschrecke", spielfeld.feldAmOrt(0), spielfeld), "heuschrecke.png");
        addSpielfigur(new Spielfigur("Fisch", spielfeld.feldAmOrt(0), spielfeld), "fisch.png");
        addSpielfigur(new Spielfigur("Seestern", spielfeld.feldAmOrt(0), spielfeld), "seestern.png");
        addSpielfigur(new Spielfigur("Schwein", spielfeld.feldAmOrt(0), spielfeld), "schwein.png");*/

     // Spielfiguren hinzufügen
        /*Spielfigur regenwurm = new Spielfigur("Regenwurm");
        addSpielfigur(regenwurm, "regenwurm.png");
        Spielfigur paramecium = new Spielfigur("Paramecium");
        addSpielfigur(paramecium, "paramecium.png");
        Spielfigur heuschrecke = new Spielfigur("Heuschrecke");
        addSpielfigur(heuschrecke, "heuschrecke.png");
        Spielfigur fisch = new Spielfigur("Fisch");
        addSpielfigur(fisch, "fisch.png");
        Spielfigur seestern = new Spielfigur("Seestern");
        addSpielfigur(seestern, "seestern.png");
        Spielfigur schwein = new Spielfigur("Schwein");
        addSpielfigur(schwein, "schwein.png");*/

        String imagePath = "C:\\Users\\freit\\eclipse-workspace\\Bionopoly\\src\\gui\\fisch.png";
        String imagePath1 = "C:\\Users\\freit\\eclipse-workspace\\Bionopoly\\src\\gui\\regenwurm.png";
        String imagePath2 = "C:\\Users\\freit\\eclipse-workspace\\Bionopoly\\src\\gui\\paramecium.png";
        String imagePath3 = "C:\\Users\\freit\\eclipse-workspace\\Bionopoly\\src\\gui\\schwein.png";
        String imagePath4 = "C:\\Users\\freit\\eclipse-workspace\\Bionopoly\\src\\gui\\heuschrecke.png";
        String imagePath5 = "C:\\Users\\freit\\eclipse-workspace\\Bionopoly\\src\\gui\\seestern.png";
        
        JButton würfelnButton = new JButton("Würfeln");
        würfelnButton.setBounds(350, 700, 100, 30);
        würfelnButton.addActionListener(new ActionListener() {
      
            public void actionPerformed(ActionEvent e) {
                Würfel würfel = new Würfel();
                würfel.würfel();
                int augensumme = würfel.getAugensumme();
                System.out.println("Gewürfelte Augensumme: " + augensumme);

                // Beispiel: Bewege die Spielfigur "Regenwurm"
                verschiebeSpielfigur("Regenwurm", augensumme * 10, augensumme * 10);
            }
        });

        spielfeld.add(würfelnButton);
        add(spielfeld);
    }

    private void addSpielfigur(Spielfigur spielfigur, String bildDatei) {
        // Überprüfe, ob die Ressource vorhanden ist
        java.net.URL bildURL = getClass().getResource("/" + bildDatei);
        if (bildURL == null) {
            System.out.println("Bilddatei nicht gefunden: " + bildDatei);
            return;
        }
        
        ImageIcon icon = new ImageIcon(bildURL);
        JLabel label = new JLabel(icon);
        label.setBounds(50, 50, icon.getIconWidth(), icon.getIconHeight());
        spielfeld.feldAmOrt(spielfigur.getPosition()).add(label); // Füge das Label dem richtigen Feld hinzu
        spielfeld.add(label);
        spielfigurenLabels.put(spielfigur.getName(), label);
    }

    private void verschiebeSpielfigur(String name, int x, int y) {
        JLabel label = spielfigurenLabels.get(name);
        if (label != null) {
            label.setLocation(x, y);
            //alternativ -> label.setBounds(x, y, label.getWidth(), label.getHeight());
        } else {
            System.out.println("Spielfigur mit Namen " + name + " nicht gefunden.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
           
            public void run() {
                new SpielfigurGui().setVisible(true);
            }
        });
    }
}
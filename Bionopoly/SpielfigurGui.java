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
        int startfeld = 0;

        
        spielfeld = new Spielfeld(50, 50, 612, 612);


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
            }
        });

        spielfeld.add(würfelnButton);
        add(spielfeld);
    }

    private void addSpielfigur(Spielfigur spielfigur, String bildDatei) {
        //Überprüfen, ob die Ressource vorhanden ist
        java.net.URL bildURL = getClass().getResource("/" + bildDatei);
        if (bildURL == null) {
            System.out.println("Bilddatei nicht gefunden: " + bildDatei);
            return;
        }
        
        ImageIcon icon = new ImageIcon(bildURL);
        JLabel label = new JLabel(icon);
        label.setBounds(50, 50, icon.getIconWidth(), icon.getIconHeight());
        spielfeld.feldAmOrt(spielfigur.getPosition()).add(label); //Füge das Label dem richtigen Feld hinzu
        spielfeld.add(label);
        spielfigurenLabels.put(spielfigur.getName(), label);
    }

    private void verschiebeSpielfigur(String name, int x, int y) {
        JLabel label = spielfigurenLabels.get(name);
        if (label != null) {
            label.setLocation(x, y);
        } 
        else {
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

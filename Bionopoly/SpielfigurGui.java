package gui;

import bionopoly.Spielfigur;
import bionopoly.Spielfeld;
import bionopoly.Feld;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpielfigurGui extends JFrame {
    private Map<String, JLabel> spielfigurenLabels;
    private JPanel spielfeldPanel;
    private Spielfeld spielfeld;

    public SpielfigurGui() {
        setTitle("Bionopoly");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        spielfeldPanel = new JPanel();
        spielfeldPanel.setLayout(null);
        add(spielfeldPanel);

        spielfigurenLabels = new HashMap<>();
    }

    public void initializeSpielfeld(Spielfeld spielfeld, List<Spielfigur> spielfiguren) {
        this.spielfeld = spielfeld;

        for (Spielfigur spielfigur : spielfiguren) {
            addSpielfigur(spielfigur);
        }
    }

    private void addSpielfigur(Spielfigur spielfigur) {
        String bildDatei = getBildDatei(spielfigur.getName());
        if (bildDatei == null) {
            System.out.println("Bilddatei nicht gefunden für: " + spielfigur.getName());
            return;
        }

        ImageIcon originalIcon = new ImageIcon(bildDatei); // Assuming image paths are correct
        ImageIcon scaledIcon = getScaledIcon(originalIcon, 100, 60); // Scale to 50x50 pixels
        JLabel label = new JLabel(scaledIcon);
        Feld aktuellesFeld = spielfigur.getAktuellesFeld();
        label.setBounds(aktuellesFeld.getX(), aktuellesFeld.getY(), scaledIcon.getIconWidth(), scaledIcon.getIconHeight());
        spielfeldPanel.add(label);
        spielfigurenLabels.put(spielfigur.getName(), label);
    }

    private ImageIcon getScaledIcon(ImageIcon originalIcon, int width, int height) {
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    private String getBildDatei(String name) {
        switch (name) {
            case "Paramecium":
                return "paramecium.png";
            case "Regenwurm":
                return "regenwurm.png";
            case "Heuschrecke":
                return "heuschrecke.png";
            case "Seestern":
                return "seestern.png";
            case "Fisch":
                return "fisch.png";
            case "Schwein":
                return "schwein.png";
            default:
                return null;
        }
    }

    public void verschiebeSpielfigur(String name, int x, int y) {
        JLabel label = spielfigurenLabels.get(name);
        if (label != null) {
            label.setLocation(x, y);
        } else {
            System.out.println("Spielfigur mit Namen " + name + " nicht gefunden.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SpielfigurGui gui = new SpielfigurGui();
                gui.setVisible(true);

                // Initialize the Spielfeld and Spielfiguren
                Spielfeld spielfeld = new Spielfeld(50, 50, 612, 612);
                List<Spielfigur> spielfiguren = Spielfigur.initSpielfiguren(spielfeld, gui);

                gui.initializeSpielfeld(spielfeld, spielfiguren);

                // Test movement for one Spielfigur
                Spielfigur figur = spielfiguren.get(0); // First Spielfigur
                spielfeld.würfelnUndBewegen(figur); // Assuming this method moves the figure and updates the GUI
            }
        });
    }
}

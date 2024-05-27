package gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
//import java.awt.Dimension;
import java.awt.FlowLayout;

public class SpielbrettGui {

	    public static void main(String[] args) {
	        // Größe für das Bild festlegen (quadratisch)
	        int imageSize = 780; // Quadratische Größe für das Bild (angepasst an die Bildschirmgröße)

	        JFrame frame = new JFrame("Bildanzeige");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        // Pfad zum PNG-Bild
	        String imagePath = "C:\\Users\\sina3\\OneDrive\\Dokumente\\HSB 2. Semester\\Informatik\\Softwareprojekt\\src\\bionopoly\\spielbrett2.0.png";

	        // ImageIcon erstellen und dem JLabel zuweisen
	        ImageIcon icon = new ImageIcon(imagePath);
	        // Bildgröße anpassen
	        ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(imageSize, imageSize, java.awt.Image.SCALE_SMOOTH));
	        JLabel label = new JLabel(scaledIcon);

	        // Rahmen hinzufügen
	        label.setBorder(new EmptyBorder(0, 0, 0, 0)); // 0 Pixel Abstand zum Rahmen

	        // Layout-Manager zuweisen
	        frame.setLayout(new FlowLayout());

	        // JLabel zum Frame hinzufügen
	        frame.add(label);

	        frame.pack();
	        frame.setVisible(true);
	    }
	}

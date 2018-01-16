package fr.eseo.jic.wifidetector.vue.ui;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanneauAccueil extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanneauAccueil() {
		super();
		this.setVisible(true);
		this.setBackground(Color.WHITE);

		JLabel label = new JLabel();
		// ImageIcon image = new ImageIcon("");
		// label.setIcon(image);
		this.add(label);

	}

}

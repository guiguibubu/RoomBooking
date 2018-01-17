package fr.eseo.jic.wifidetector.vue.forme;

import java.awt.Graphics;

import javax.swing.JPanel;

import fr.eseo.jic.wifidetector.modele.Salle;

public class VueSalle extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static final String PORTE = "Porte";

	public static final int LARGEUR_MAX = 591;
	public static final int HAUTEUR_MAX = 336;
	public static final int ABSCISSE_ORIGINE= 52;
	public static final int ORDONNE_ORIGINE = 141;

	private static final int LARGEUR_SALLE_DEFAUT = 500;
	private static final int HAUTEUR_SALLE_DEFAUT = 300;
	private static final int ABSCISSE_SALLE_DEFAUT= 30;
	private static final int ORDONNE_SALLE_DEFAUT = 10;

	private Salle salle;

	public VueSalle() {
		super();
		try {
			this.salle = new Salle();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		// Dessin du rectangle representant la salle
		g.drawRect(ABSCISSE_SALLE_DEFAUT, ORDONNE_SALLE_DEFAUT, HAUTEUR_SALLE_DEFAUT, LARGEUR_SALLE_DEFAUT);
		System.out.println("Salle faite");

		// Dessin du restangle représentant la porte
		g.drawRect(80, 295, 50, 10);
		g.drawString(PORTE, 80, 290);
		System.out.println("Porte faite");
	}

	public Salle getSalle() {return this.salle;}
	public void setSalle(Salle salle) {this.salle = salle;}
}

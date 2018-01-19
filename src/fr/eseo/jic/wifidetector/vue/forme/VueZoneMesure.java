package fr.eseo.jic.wifidetector.vue.forme;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import fr.eseo.jic.wifidetector.modele.ZoneMesure;

/**
 *	Classe représentant la représentation graphique d'une zone de mesure
 *
 */
public class VueZoneMesure extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * Zone de mesure représentée par la classe
	 */
	private ZoneMesure zoneMesure;
	/**
	 * Constructeur de la représentation graphique de la zone de mesure
	 * @param zoneMesure La zone de mesure à représenter
	 */
	public VueZoneMesure(ZoneMesure zoneMesure) {
		super();
		try {
			this.zoneMesure = zoneMesure;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Dessin du rectangle representant la zone
		//On dessine en pointillé
		float[] pointille = new float[]{10, 10};
		BasicStroke traitPointille = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 1, pointille, 0);
		Graphics2D g2D = (Graphics2D)g.create();
		g2D.setStroke(traitPointille);
		g2D.drawRect(this.zoneMesure.getX(), this.zoneMesure.getY(), this.zoneMesure.getLargeur(), this.zoneMesure.getHauteur());
	}
	/**
	 *
	 * @return La zone de mesure représentée
	 */
	public ZoneMesure getZoneMesure() {return this.zoneMesure;}
	/**
	 *
	 * @param zoneMesure La nouvelle zone de mesure à représenter
	 */
	public void setZoneMesure(ZoneMesure zoneMesure) {this.zoneMesure= zoneMesure;}
}

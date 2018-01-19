package fr.eseo.jic.wifidetector.vue.forme;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.eseo.jic.wifidetector.modele.Salle;
import fr.eseo.jic.wifidetector.modele.ZoneMesure;

/**
 *	Classe représentant la représentation graphique d'une salle
 *
 */
public class VueSalle extends JPanel {
	private static final long serialVersionUID = 1L;

	public static final String PORTE = "Porte";

	/**
	 * Largeur maximum en pixel de la représentation
	 */
	public static final int LARGEUR_MAX = 600;
	/**
	 * Hauteur maximum en pixel de la représentation
	 */
	public static final int HAUTEUR_MAX = 600;

	private static final int LARGEUR_SALLE_DEFAUT = 500;
	private static final int HAUTEUR_SALLE_DEFAUT = 600;

	private static final int DX_PORTE = 30;
	private static final int DY_PORTE = 15;
	private static final int LARGEUR_PORTE = 50;
	private static final int HAUTEUR_PORTE = 10;

	/**
	 * La salle à représenter
	 */
	private Salle salle;
	/**
	 * Liste des représentations des zones de mesure dans la salle
	 */
	private List<VueZoneMesure> listeVueZoneMesure;
	/**
	 * Construit la représentation graphiqua de la salle
	 */
	public VueSalle() {
		super();
		try {
			this.salle = new Salle(LARGEUR_SALLE_DEFAUT, HAUTEUR_SALLE_DEFAUT);
			this.listeVueZoneMesure = new ArrayList<VueZoneMesure>();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		//ratio dans le cas où la salle est plus grande que le panneau pour la tracer
		double ratioHauteur = this.salle.getHauteur()/(double)HAUTEUR_MAX;
		double ratioLargeur = this.salle.getLargeur()/(double)LARGEUR_MAX;

		double ratio = 0.0;
		if(ratioHauteur>1 || ratioLargeur>1){
			ratio = Math.max(ratioHauteur, ratioLargeur);
		}

		//ratio dans le cas où la salle est plus petite que le panneau pour la tracer
		ratioHauteur = (double)HAUTEUR_MAX/this.salle.getHauteur();
		ratioLargeur = (double)LARGEUR_MAX/this.salle.getLargeur();

		ratio = 0.0;
		if(ratioHauteur>1.5 || ratioLargeur>1.5){
			ratio = Math.min(ratioHauteur, ratioLargeur);
			ratio = 1/ratio;
		}

		if(ratio > 0.0){
			try {
				this.salle.setHauteur(Math.toIntExact(Math.round(this.salle.getHauteur()/ratio)));
				this.salle.setLargeur(Math.toIntExact(Math.round(this.salle.getLargeur()/ratio)));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}

		// Dessin du rectangle representant la salle
		int xSalle = Math.toIntExact(Math.round((LARGEUR_MAX-this.salle.getLargeur())/2.0));
		int ySalle = Math.toIntExact(Math.round((HAUTEUR_MAX-this.salle.getHauteur())/2.0));
		g.drawRect(xSalle, ySalle, this.salle.getLargeur(), this.salle.getHauteur());

		// Dessin du restangle représentant la porte
		int xPorte = xSalle + DX_PORTE;
		int yPorte = ySalle + this.salle.getHauteur() - DY_PORTE;
		g.drawRect(xPorte, yPorte, LARGEUR_PORTE, HAUTEUR_PORTE);
		g.drawString(PORTE, xPorte, yPorte-5);

		// Dessin des zones de mesure
		int nbZoneMesure = this.salle.getListeZoneMesure().size();
		if(nbZoneMesure > 0){
			this.listeVueZoneMesure.clear();
			for (int i = 0; i<nbZoneMesure; i++){
				ZoneMesure zoneMesure = this.salle.getListeZoneMesure().get(i);
				int largeurMesure = (ratio > 0.0) ? Math.toIntExact(Math.round(zoneMesure.getLargeur()/ratio)) : zoneMesure.getLargeur();
				int hauteurMesure = (ratio > 0.0) ? Math.toIntExact(Math.round(zoneMesure.getHauteur()/ratio)) : zoneMesure.getHauteur();
				int xZone = (ratio > 0.0) ? xSalle + Math.toIntExact(Math.round(zoneMesure.getX()/ratio)) : xSalle + zoneMesure.getX();
				int yZone = (ratio > 0.0) ? ySalle + Math.toIntExact(Math.round(zoneMesure.getY()/ratio)) : ySalle + zoneMesure.getY();
				try {
					zoneMesure.setLargeur(largeurMesure);
					zoneMesure.setHauteur(hauteurMesure);
					zoneMesure.setX(xZone);
					zoneMesure.setY(yZone);
					this.listeVueZoneMesure.add(new VueZoneMesure(zoneMesure));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}

			for(VueZoneMesure vueZoneMesure : this.listeVueZoneMesure){
				vueZoneMesure.paintComponent(g);
			}
		}
	}
	/**
	 *
	 * @return La salle représentée par la classe
	 */
	public Salle getSalle() {return this.salle;}
	/**
	 *
	 * @param salle La nouvelle salle à représenter
	 */
	public void setSalle(Salle salle) {this.salle = salle;}
	/**
	 *
	 * @return La liste des représentations des zones de mesure dans la salle
	 */
	public List<VueZoneMesure> getListeVueZoneMesure() {return this.listeVueZoneMesure;}
	/**
	 *
	 * @param listeVueZoneMesure La nouvelle liste des représentations des zones de mesure dans la salle
	 */
	public void setListeVueZoneMesure(List<VueZoneMesure> listeVueZoneMesure) {this.listeVueZoneMesure = listeVueZoneMesure;}
}

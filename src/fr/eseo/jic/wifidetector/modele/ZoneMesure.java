package fr.eseo.jic.wifidetector.modele;

import fr.eseo.jic.wifidetector.modele.math.SurfaceRectangulaire;

/**
 *	Classe représentant une zone de mesure de la qualité du Wifi
 *
 */
public class ZoneMesure extends SurfaceRectangulaire {


	/*CONSTRUCTEUR*/
	/**
	 * Construit une zone de mesure de la qualité du Wif
	 * @param x L'abscisse du point de référence de la zone
	 * @param y L'ordonnée du point de référence de la zone
	 * @param largeur Largeur de la zone de mesure
	 * @param hauteur Hauteur de la zone de mesure
	 * @throws Exception Exception levée si l'abscisse, l'ordonnée, la largeur ou la hauteur est négative
	 */
	public ZoneMesure(int x, int y, int largeur, int hauteur) throws Exception {
		super(x, y, largeur, hauteur);
	}

	@Override
	public String toString(){
		return "Zone Mesure =>"+" x:"+this.getX()+" y:"+this.getY()+" hauteur:"+this.getHauteur()+" largeur:"+this.getLargeur();
	}
}

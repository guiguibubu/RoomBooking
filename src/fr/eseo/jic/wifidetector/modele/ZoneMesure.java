package fr.eseo.jic.wifidetector.modele;

import fr.eseo.jic.wifidetector.modele.math.SurfaceRectangulaire;

public class ZoneMesure extends SurfaceRectangulaire {

	PointMesure pointMesure;

	/*CONSTRUCTEUR*/
	public ZoneMesure(int x, int y, int largeur, int hauteur) throws Exception {
		super(x, y, largeur, hauteur);
		int xCenter = x + largeur/2;
		int yCenter = y + hauteur/2;
		this.pointMesure = new PointMesure(xCenter, yCenter);
	}


	/*GETTER SETTER*/
	public PointMesure getPointMesure() {return this.pointMesure;}
	public void setPointMesure(PointMesure pointMesure) {this.pointMesure = pointMesure;}
}

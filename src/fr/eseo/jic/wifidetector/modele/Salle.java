package fr.eseo.jic.wifidetector.modele;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.jic.wifidetector.modele.math.SurfaceRectangulaire;

public class Salle extends SurfaceRectangulaire {

	private String nom;
	private List<ZoneMesure> listeZoneMesure;

	public static final String MSG_NON_ZERO = "La largeur et la hauteur doivent être positives";

	/**
	 * Diamètre pris en compte pour connaitre le nombre de points de mesure à faire (en mètre)
	 */
	public static final double DIAMETRE_MESURE = 5;

	/*CONSTRUCTEURS*/
	public Salle(int largeur, int hauteur) throws Exception{
		super(largeur, hauteur);
		this.listeZoneMesure = new ArrayList<ZoneMesure>();
		//		this.calculListePointMesure();
	}

	public Salle() throws Exception {
		this(0,0);
	}

	public void calculListePointMesure() throws Exception {
		int nbPointMesureLargeur = (int) Math.ceil(this.getLargeur() / DIAMETRE_MESURE);
		int nbPointMesureHauteur = (int) Math.ceil(this.getHauteur() / DIAMETRE_MESURE);

		if(nbPointMesureHauteur == 0 || nbPointMesureLargeur == 0){
			throw new Exception(MSG_NON_ZERO);
		}
		int largeurMesure = this.getLargeur() / nbPointMesureLargeur;
		int hauteurMesure = this.getHauteur() / nbPointMesureHauteur;

		for(int i = 0; i<nbPointMesureLargeur; i++){
			for(int j = 0; j<nbPointMesureHauteur; j++){
				int indexLigne = i;
				int indexColonne = j;
				int xZoneMesure = indexColonne*largeurMesure;
				int yZoneMesure = indexLigne*hauteurMesure;
				this.getListeZoneMesure().add(new ZoneMesure(xZoneMesure, yZoneMesure, largeurMesure, hauteurMesure));
			}
		}
	}

	public List<ZoneMesure> getListeZoneMesure() {return this.listeZoneMesure;}
	public void setListeZoneMesure(List<ZoneMesure> listeZoneMesure) {this.listeZoneMesure = listeZoneMesure;}

	public String getNom() {return this.nom;}
	public void setNom(String nom) {this.nom = nom;}
}

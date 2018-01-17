package fr.eseo.jic.wifidetector.modele;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.jic.wifidetector.modele.math.SurfaceRectangulaire;

public class Salle extends SurfaceRectangulaire {

	private String nom;
	private List<ZoneMesure> listeZoneMesure;

	/**
	 * Diam�tre pris en compte pour connaitre le nombre de points de mesure � faire (en m�tre)
	 */
	public static final int DIAMETRE_MESURE = 5;

	/*CONSTRUCTEURS*/
	public Salle(int largeur, int hauteur) throws Exception{
		super(largeur, hauteur);
		this.listeZoneMesure = new ArrayList<ZoneMesure>();
	}

	public Salle() throws Exception{
		this(0,0);
	}

	public void calculListePointMesure() throws Exception{
		int nbPointMesureLargeur = this.getLargeur() / DIAMETRE_MESURE;
		int nbPointMesureHauteur = this.getHauteur() / DIAMETRE_MESURE;
		int nbPointMesure = nbPointMesureLargeur*nbPointMesureHauteur;

		int largeurMesure = this.getLargeur() / nbPointMesureLargeur;
		int hauteurMesure = this.getHauteur() / nbPointMesureHauteur;

		for(int i = 0; i<nbPointMesure; i++){
			int indexLigne = i / nbPointMesureLargeur;
			int indexColonne = i / nbPointMesureHauteur;
			int xZoneMesure = indexColonne*largeurMesure;
			int yZoneMesure = indexLigne*hauteurMesure;
			this.getListeZoneMesure().add(new ZoneMesure(xZoneMesure, yZoneMesure, largeurMesure, hauteurMesure));
		}
	}

	/*GETTER SETTER*/

	public List<ZoneMesure> getListeZoneMesure() {return this.listeZoneMesure;}
	public void setListeZoneMesure(List<ZoneMesure> listeZoneMesure) {this.listeZoneMesure = listeZoneMesure;}

	public String getNom() {return this.nom;}
	public void setNom(String nom) {this.nom = nom;}
}

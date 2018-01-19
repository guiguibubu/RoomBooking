package fr.eseo.jic.wifidetector.modele;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.jic.wifidetector.modele.math.SurfaceRectangulaire;

/**
 *	Classe représentant une salle de classe
 *
 */
public class Salle extends SurfaceRectangulaire {

	/**
	 * Nom de la salle
	 */
	private String nom;
	/**
	 * Liste des zone de mesure dans la salle
	 */
	private List<ZoneMesure> listeZoneMesure;

	public static final String MSG_NON_ZERO = "La largeur et la hauteur doivent être positives";
	/**
	 * Diamètre pris en compte pour connaitre le nombre de points de mesure à faire (en mètre)
	 */
	public static final int DIAMETRE_MESURE = 5;

	/*CONSTRUCTEURS*/
	/**
	 * Construit une salle
	 * @param largeur Largeur de la salle
	 * @param profondeur Profondeur de la salle
	 * @throws Exception Exception levé en cas de saisie de largeur ou profondeur négative
	 */
	public Salle(int largeur, int profondeur) throws Exception{
		super(largeur, profondeur);
		this.listeZoneMesure = new ArrayList<ZoneMesure>();
	}
	/**
	 * Construit une salle de largeur et profondeur égales à 0
	 * @throws Exception Exception levé en cas de saisie de largeur ou profondeur négative
	 */
	public Salle() throws Exception {
		this(0,0);
	}

	/**
	 * Remplie la liste des zones de mesure avec le nombre de zone de mesure en adéquation avec la taille de la salle
	 * @throws Exception Exception levé en cas de salle de largeur et de profondeur égales à 0
	 */
	public void calculListePointMesure() throws Exception {
		int nbPointMesureLargeur = (int) Math.ceil(this.getLargeur() / (double)DIAMETRE_MESURE);
		int nbPointMesureHauteur = (int) Math.ceil(this.getHauteur() / (double)DIAMETRE_MESURE);

		if(nbPointMesureHauteur == 0 || nbPointMesureLargeur == 0){
			throw new Exception(MSG_NON_ZERO);
		}
		int largeurMesure = this.getLargeur() / nbPointMesureLargeur;
		int hauteurMesure = this.getHauteur() / nbPointMesureHauteur;

		this.listeZoneMesure.clear();
		for(int i = 0; i<nbPointMesureLargeur; i++){
			for(int j = 0; j<nbPointMesureHauteur; j++){
				int indexLigne = j;
				int indexColonne = i;
				int xZoneMesure = indexColonne*largeurMesure;
				int yZoneMesure = indexLigne*hauteurMesure;
				this.getListeZoneMesure().add(new ZoneMesure(xZoneMesure, yZoneMesure, largeurMesure, hauteurMesure));
			}
		}
	}
	/**
	 *
	 * @return La profondeur de la salle
	 */
	public int getProfondeur(){return super.getHauteur();}
	/**
	 *
	 * @param profondeur La nouvelle profondeur de la salle
	 * @throws Exception Exception levée si la nouvelle profondeur est négative
	 */
	public void setProfondeur(int profondeur) throws Exception{super.setHauteur(profondeur);}
	/**
	 *
	 * @return La liste des zones de mesure dans la salle
	 */
	public List<ZoneMesure> getListeZoneMesure() {return this.listeZoneMesure;}
	/**
	 *
	 * @param listeZoneMesure La nouvelle liste des zone de mesure dans la salle
	 */
	public void setListeZoneMesure(List<ZoneMesure> listeZoneMesure) {this.listeZoneMesure = listeZoneMesure;}
	/**
	 *
	 * @return Le nom de la salle
	 */
	public String getNom() {return this.nom;}
	/**
	 *
	 * @param nom Le nouveau nomde la salle
	 */
	public void setNom(String nom) {this.nom = nom;}
}

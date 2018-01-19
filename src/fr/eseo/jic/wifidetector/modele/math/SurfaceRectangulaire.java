package fr.eseo.jic.wifidetector.modele.math;

/**
 *	Classe représentant une surface rectangulaire d'un point de vue mathématique
 *
 */
public class SurfaceRectangulaire extends Surface{

	/**
	 * Largeur de la surface
	 */
	private int largeur;
	/**
	 * Hauteur de la surface
	 */
	private int hauteur;

	public static final String MSG_LARGEUR_NEGATIVE = "La largeur doit etre positive";
	public static final String MSG_HAUTEUR_NEGATIVE = "La hauteur doit etre positive";
	/**
	 * Construit une surface rectangulaire
	 * @param x Abscisse du point de référence
	 * @param y Ordonnée du point de référence
	 * @param largeur Largeur de la surface rectangulaire
	 * @param hauteur Hauteur de la surface rectangulaire
	 * @throws Exception Exception levée si l'abscisse, l'ordonnée, la largeur ou la hauteur est négative
	 */
	public SurfaceRectangulaire(int x , int y, int largeur, int hauteur) throws Exception {
		super(x, y);
		if(largeur < 0){
			throw new Exception(MSG_LARGEUR_NEGATIVE);
		}
		if(hauteur < 0){
			throw new Exception(MSG_HAUTEUR_NEGATIVE);
		}
		this.largeur = largeur;
		this.hauteur = hauteur;
	}
	/**
	 * Construit une surface avec (0,0) comme point de référence
	 * @param largeur Largeur de la surface rectangulaire
	 * @param hauteur Hauteur de la surface rectangulaire
	 * @throws Exception Exception levée si l'abscisse, l'ordonnée, la largeur ou la hauteur est négative
	 */
	public SurfaceRectangulaire(int largeur, int hauteur) throws Exception {
		this(0,0,largeur,hauteur);
	}
	/**
	 * Construit une surface avec(0,0) comme point de référence avec une largeur et une hauteur égales à 0
	 * @throws Exception Exception levée si l'abscisse, l'ordonnée, la largeur ou la hauteur est négative
	 */
	public SurfaceRectangulaire() throws Exception {
		this(0,0,0,0);
	}

	@Override
	public int getAire() {
		return this.getLargeur()*this.getHauteur();
	}

	/*GETTER SETTER*/
	/**
	 *
	 * @return La largeur de la surface
	 */
	public int getLargeur() {return this.largeur;}
	/**
	 *
	 * @param largeur La nouvelle largeur de la surface
	 * @throws Exception Exception levée si la largeur est négative
	 */
	public void setLargeur(int largeur) throws Exception {
		if(largeur < 0){
			throw new Exception(MSG_LARGEUR_NEGATIVE);
		}else{
			this.largeur = largeur;
		}
	}
	/**
	 *
	 * @return La hauteur de la surface
	 */
	public int getHauteur() {return this.hauteur;}
	/**
	 *
	 * @param hauteur La nouvelle hauteur de la surface
	 * @throws Exception Exception levée si la hauteur est négative
	 */
	public void setHauteur(int hauteur) throws Exception {
		if(hauteur < 0){
			throw new Exception(MSG_HAUTEUR_NEGATIVE);
		}else{
			this.hauteur = hauteur;
		}
	}
}

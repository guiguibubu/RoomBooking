package fr.eseo.jic.wifidetector.modele.math;

/**
 *	Classe représentant un point d'un point de vue mathématique
 *
 */
public class Point {

	public static final String MSG_ABSCISSE_NEGATIVE = "L'abscisse doit etre positive";
	public static final String MSG_ORDONNEE_NEGATIVE = "L'ordonnee doit etre positive";
	/**
	 * Abscisse du point
	 */
	int x;
	/**
	 * Ordonnée du point
	 */
	int y;
	/**
	 * Construit un point
	 * @param x Abscisse du point
	 * @param y Ordonnée du point
	 * @throws Exception Exception levée si l'abscisse ou l'ordonnée est négative
	 */
	public Point(int x, int y) throws Exception {
		if(x<0){
			throw new Exception(MSG_ABSCISSE_NEGATIVE);
		}
		if(y<0){
			throw new Exception(MSG_ORDONNEE_NEGATIVE);
		}
		this.x = x;
		this.y = y;
	}

	/**
	 * Construit un point en (0,0)
	 * @throws Exception Exception levée si l'abscisse ou l'ordonnée est négative
	 */
	public Point() throws Exception{
		this(0,0);
	}
	/**
	 *
	 * @return L'abscisse du point
	 */
	public int getX() {return this.x;}
	/**
	 *
	 * @param x La nouvelle abscisse du point
	 * @throws Exception Exception levée si l'abscisse ou l'ordonnée est négative
	 */
	public void setX(int x) throws Exception {
		if(x<0){
			throw new Exception(MSG_ABSCISSE_NEGATIVE);
		}else{
			this.x = x;
		}
	}
	/**
	 *
	 * @return L'ordonnée du point
	 */
	public int getY() {return this.y;}
	/**
	 *
	 * @param y La nouvelle ordonnée du point
	 * @throws Exception Exception levée si l'abscisse ou l'ordonnée est négative
	 */
	public void setY(int y) throws Exception {
		if(y<0){
			throw new Exception(MSG_ORDONNEE_NEGATIVE);
		}else{
			this.y = y;
		}
	}
}

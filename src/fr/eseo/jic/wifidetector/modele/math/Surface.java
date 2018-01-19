package fr.eseo.jic.wifidetector.modele.math;

/**
 * Classe représentant une surface d'un point de vue mathématique
 *
 */
public abstract class Surface extends Point {

	/**
	 * Constructeur d'une surface
	 * @param x Abscisse du point de référence de la surface
	 * @param y Ordonnée du point de référence de la surface
	 * @throws Exception Exception levée si l'abscisse ou l'ordonnée est négative
	 */
	public Surface(int x, int y) throws Exception {
		super(x, y);
	}
	/**
	 * Constructeur d'une surface avec(0,0) comme point de référence
	 * @throws Exception Exception levée si l'abscisse ou l'ordonnée est négative
	 */
	public Surface() throws Exception {
		this(0,0);
	}
	/**
	 *
	 * @return L'aire de la surface
	 */
	abstract public int getAire();
}

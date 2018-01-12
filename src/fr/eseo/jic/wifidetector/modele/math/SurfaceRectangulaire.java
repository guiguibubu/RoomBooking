package fr.eseo.jic.wifidetector.modele.math;

public class SurfaceRectangulaire extends Surface{

	private int largeur;
	private int hauteur;
	public static final String MSG_LARGEUR_NEGATIVE = "La largeur doit être positive";
	public static final String MSG_HAUTEUR_NEGATIVE = "La hauteur doit être positive";

	public SurfaceRectangulaire(int x	, int y, int largeur, int hauteur) throws Exception{
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

	public SurfaceRectangulaire(int largeur, int hauteur) throws Exception {
		this(0,0,largeur,hauteur);
	}

	public SurfaceRectangulaire() throws Exception {
		this(0,0,0,0);
	}

	@Override
	public int getAire() {
		return this.getLargeur()*this.getHauteur();
	}

	/*GETTER SETTER*/
	public int getLargeur() {return this.largeur;}
	public void setLargeur(int largeur) throws Exception {
		if(largeur < 0){
			throw new Exception(MSG_LARGEUR_NEGATIVE);
		}else{
			this.largeur = largeur;
		}
	}

	public int getHauteur() {return this.hauteur;}
	public void setHauteur(int hauteur) throws Exception {
		if(hauteur < 0){
			throw new Exception(MSG_HAUTEUR_NEGATIVE);
		}else{
			this.hauteur = hauteur;
		}
	}
}

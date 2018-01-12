package fr.eseo.jic.wifidetector.modele.math;

public class SurfaceCirculaire extends Surface {

	private int rayon;

	public static final String MSG_RAYON_NEGATIVE = "La rayon doit être positif";

	public SurfaceCirculaire(int x, int y, int rayon) throws Exception {
		super(x, y);
		if(rayon < 0){
			throw new Exception(MSG_RAYON_NEGATIVE);
		}
		this.rayon = rayon;
	}

	public SurfaceCirculaire() throws Exception {
		this(0, 0, 0);
	}

	@Override
	public int getAire() {
		return (int)Math.round(Math.PI*this.getRayon());
	}

	/*GETTER SETTER*/
	public int getRayon() {return this.rayon;}
	public void setRayon(int rayon) {this.rayon = rayon;}

}

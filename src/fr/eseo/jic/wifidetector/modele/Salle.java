package fr.eseo.jic.wifidetector.modele;

public class Salle {

	private int largeur;
	private int longueur;

	/*CONSTRUCTEURS*/
	public Salle(int largeur, int longueur){
		this.largeur = largeur;
		this.longueur = longueur;
	}

	public Salle(){
		this(0,0);
	}

	int getAire(){
		return this.largeur*this.longueur;
	}

	/*GETTER SETTER*/
	public int getLargeur() {return this.largeur;}
	public void setLargeur(int largeur) {this.largeur = largeur;}

	public int getLongueur() {return this.longueur;}
	public void setLongueur(int longueur) {this.longueur = longueur;}
}

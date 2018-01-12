package fr.eseo.jic.wifidetector.modele;

import fr.eseo.jic.wifidetector.modele.math.Point;

public class PointMesure extends Point{

	Mesure mesure;

	public PointMesure(int x, int y) throws Exception {
		super(x,y);
	}

	public PointMesure() throws Exception{
		this(0,0);
	}

	/*GETTER SETTER*/
	public Mesure getMesure() {return this.mesure;}
	public void setMesure(Mesure mesure) {this.mesure = mesure;}
}

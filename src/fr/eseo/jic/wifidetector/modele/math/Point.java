package fr.eseo.jic.wifidetector.modele.math;

public class Point {

	public static final String MSG_ABSCISSE_NEGATIVE = "L'abscisse doit etre positive";
	public static final String MSG_ORDONNEE_NEGATIVE = "L'ordonnee doit etre positive";

	int x;
	int y;

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

	public Point() throws Exception{
		this(0,0);
	}

	public int getX() {return this.x;}
	public void setX(int x) throws Exception {
		if(x<0){
			throw new Exception(MSG_ABSCISSE_NEGATIVE);
		}else{
			this.x = x;
		}
	}

	public int getY() {return this.y;}
	public void setY(int y) throws Exception {
		if(y<0){
			throw new Exception(MSG_ORDONNEE_NEGATIVE);
		}else{
			this.y = y;
		}
	}
}

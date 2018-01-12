package fr.eseo.jic.wifidetector.modele.math;

public abstract class Surface extends Point {

	public Surface(int x, int y) throws Exception {
		super(x, y);
	}

	public Surface() throws Exception {
		this(0,0);
	}

	abstract public int getAire();
}

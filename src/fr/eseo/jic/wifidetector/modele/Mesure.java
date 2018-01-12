package fr.eseo.jic.wifidetector.modele;

public abstract class Mesure {

	private int value;
	private String name;

	public Mesure(String name, int value){
		this.name = name;
		this.value = value;
	}

	public Mesure(String name){
		this(name, 0);
	}

	public Mesure(){
		this("");
	}

	/*GETTER SETTER*/
	public int getValue() {return this.value;}
	public void setValue(int value) {this.value = value;}
	public String getName() {return this.name;}
	public void setName(String name) {this.name = (name == null) ? "" : name;}
}

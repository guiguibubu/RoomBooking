package fr.eseo.jic.wifidetector.modele;

/**
 * Classe représentant une mesure à titre générale
 *
 */
public class Mesure {

	/**
	 * Valeur de la mesure
	 */
	private int value;
	/**
	 * Nom de la mesure
	 */
	private String name;

	/**
	 * Constructeur d'une mesure
	 * @param name Nom de la mesure
	 * @param value Valeur de la mesure
	 */
	public Mesure(String name, int value){
		this.name = name;
		this.value = value;
	}

	/**
	 * Construit une mesure de valeur 0
	 * @param name Nom de la mesure
	 */
	public Mesure(String name){
		this(name, 0);
	}

	/**
	 * Construit une mesure sans nom de valeur 0
	 */
	public Mesure(){
		this("");
	}

	/*GETTER SETTER*/
	/**
	 *
	 * @return La valeur de la mesure
	 */
	public int getValue() {return this.value;}
	/**
	 *
	 * @param value La nouvelle valeur de la mesure
	 */
	public void setValue(int value) {this.value = value;}
	/**
	 *
	 * @return Le nom de la mesure
	 */
	public String getName() {return this.name;}
	/**
	 *
	 * @param name Le nouveau nom de la mesure
	 */
	public void setName(String name) {this.name = (name == null) ? "" : name;}
}

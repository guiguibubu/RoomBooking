package fr.eseo.jic.wifidetector.modele;

/**
 * Classe représentant une mesure de la qualité du Wifi
 */
public class MesureWifiModel {

	public static final String NOM_PUISSANCE_SIGNAL = "PuissanceSignal";
	public static final String NOM_DEBIT_DESCENDANT = "DébitDescendant";

	/**
	 * Puissance du signal Wifi
	 */
	private Mesure puissanceSignal;
	/**
	 * Débit descendant du réseau
	 */
	private Mesure debitDescendant;

	/**
	 * Constructeur d'une mesure de la qualité du Wifi
	 * @param puissanceSignal La puissance du signal Wifi
	 * @param debitDescendant Débit descendant du réseau Wifi
	 */
	public MesureWifiModel(Mesure puissanceSignal, Mesure debitDescendant) {
		this.puissanceSignal = puissanceSignal;
		this.debitDescendant = debitDescendant;
	}
	/**
	 * Construit une mesure de la qualité du Wifi avec ses valeurs à 0
	 */
	public MesureWifiModel() {
		this(new Mesure(NOM_PUISSANCE_SIGNAL), new Mesure(NOM_DEBIT_DESCENDANT));
	}

	/**
	 * Construit une mesure de la qualité du Wifi
	 * @param puissanceSignal La puissance du signal Wifi
	 * @param debitDescendant Débitdescendant du réseau
	 */
	public MesureWifiModel(int puissanceSignal, int debitDescendant) {
		this(new Mesure(NOM_PUISSANCE_SIGNAL, puissanceSignal), new Mesure(NOM_DEBIT_DESCENDANT, debitDescendant));
	}

	/**
	 *
	 * @return La puissance du signal Wifi
	 */
	public Mesure getPuissanceSignal() {return this.puissanceSignal;}
	/**
	 *
	 * @param puissanceSignal La nuovelle puissance du signal Wifi
	 */
	public void setPuissanceSignal(Mesure puissanceSignal) {this.puissanceSignal = puissanceSignal;}
	/**
	 *
	 * @return Le débit descendant du réseau
	 */
	public Mesure getDebitDescendant() {return this.debitDescendant;}
	/**
	 *
	 * @param debitDescendant Le nouveau débit descendant du réseau
	 */
	public void setDebitDescendant(Mesure debitDescendant) {this.debitDescendant = debitDescendant;}
}

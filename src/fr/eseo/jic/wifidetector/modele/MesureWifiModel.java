package fr.eseo.jic.wifidetector.modele;

public class MesureWifiModel {

	public static final String NOM_PUISSANCE_SIGNAL = "PuissanceSignal";
	public static final String NOM_DEBIT_DESCENDANT = "DébitDescendant";

	private Mesure puissanceSignal;
	private Mesure debitDescendant;

	public MesureWifiModel(Mesure puissanceSignal, Mesure debitDescendant) {
		this.puissanceSignal = puissanceSignal;
		this.debitDescendant = debitDescendant;
	}

	public MesureWifiModel() {
		this(new Mesure(NOM_PUISSANCE_SIGNAL), new Mesure(NOM_DEBIT_DESCENDANT));
	}

	public MesureWifiModel(int puissanceSignal, int debitDescendant) {
		this(new Mesure(NOM_PUISSANCE_SIGNAL, puissanceSignal), new Mesure(NOM_DEBIT_DESCENDANT, debitDescendant));
	}

	public Mesure getPuissanceSignal() {return this.puissanceSignal;}
	public void setPuissanceSignal(Mesure puissanceSignal) {this.puissanceSignal = puissanceSignal;}

	public Mesure getDebitDescendant() {return this.debitDescendant;}
	public void setDebitDescendant(Mesure debitDescendant) {this.debitDescendant = debitDescendant;}
}

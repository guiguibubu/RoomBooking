package fr.eseo.jic.wifidetector.modele;

public class MesureWifiModel {

	public static final String NOM_PUISSANCE_SIGNAL = "PuissanceSignal";
	public static final String NOM_DEBIT_DESCENDANT = "DébitDescendant";
	public static final String NOM_DEBIT_DESCENDANT_MOYEN = "DébitDescendantMoyen";

	private Mesure puissanceSignal;
	private Mesure debitDescendant;
	private Mesure debitDescendantMoyen;

	public MesureWifiModel(Mesure puissanceSignal, Mesure debitDescendant, Mesure debitDescendantMoyen) {
		this.puissanceSignal = puissanceSignal;
		this.debitDescendant = debitDescendant;
		this.debitDescendantMoyen = debitDescendantMoyen;
	}

	public MesureWifiModel() {
		this(new Mesure(NOM_PUISSANCE_SIGNAL), new Mesure(NOM_DEBIT_DESCENDANT), new Mesure(NOM_DEBIT_DESCENDANT_MOYEN));
	}

	public MesureWifiModel(int puissanceSignal, int debitDescendant, int debitDescendantMoyen) {
		this(new Mesure(NOM_PUISSANCE_SIGNAL, puissanceSignal), new Mesure(NOM_DEBIT_DESCENDANT, debitDescendant), new Mesure(NOM_DEBIT_DESCENDANT_MOYEN, debitDescendantMoyen));
	}

	public Mesure getPuissanceSignal() {return this.puissanceSignal;}
	public void setPuissanceSignal(Mesure puissanceSignal) {this.puissanceSignal = puissanceSignal;}

	public Mesure getDebitDescendant() {return this.debitDescendant;}
	public void setDebitDescendant(Mesure debitDescendant) {this.debitDescendant = debitDescendant;}

	public Mesure getDebitDescendantMoyen() {return this.debitDescendantMoyen;}
	public void setDebitDescendantMoyen(Mesure debitDescendantMoyen) {this.debitDescendantMoyen = debitDescendantMoyen;}
}

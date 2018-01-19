package fr.eseo.jic.wifidetector.modele.math;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.jic.wifidetector.modele.Mesure;
import fr.eseo.jic.wifidetector.modele.MesureWifiModel;

public class CalculStatistique {

	/**
	 * Renvoie les minimums des mesures
	 * @param listeMesureWifiModel
	 * @return une liste de mesures représentant respectivement le minimum de chaque catégorie
	 */
	public static List<Mesure> minimumMesures(List<MesureWifiModel> listeMesureWifiModel){
		List<Mesure> listeMinimumMesures = new ArrayList<Mesure>();
		int minSignal = (listeMesureWifiModel.size()>0) ? listeMesureWifiModel.get(0).getPuissanceSignal().getValue() : 0;
		int minDebit = (listeMesureWifiModel.size()>0) ? listeMesureWifiModel.get(0).getDebitDescendant().getValue() : 0;
		int minDebitMoyen = (listeMesureWifiModel.size()>0) ? listeMesureWifiModel.get(0).getDebitDescendantMoyen().getValue() : 0;
		for(int i = 1; i<listeMesureWifiModel.size(); i++){
			MesureWifiModel mesureWifiModel = listeMesureWifiModel.get(i);
			minSignal = (minSignal > mesureWifiModel.getPuissanceSignal().getValue()) ? mesureWifiModel.getPuissanceSignal().getValue() : minSignal;
			minDebit = (minSignal > mesureWifiModel.getDebitDescendant().getValue()) ? mesureWifiModel.getDebitDescendant().getValue() : minDebit;
			minDebitMoyen = (minSignal > mesureWifiModel.getDebitDescendantMoyen().getValue()) ? mesureWifiModel.getDebitDescendantMoyen().getValue() : minDebitMoyen;
		}
		listeMinimumMesures.add(new Mesure(MesureWifiModel.NOM_PUISSANCE_SIGNAL, minSignal));
		listeMinimumMesures.add(new Mesure(MesureWifiModel.NOM_DEBIT_DESCENDANT, minDebit));
		listeMinimumMesures.add(new Mesure(MesureWifiModel.NOM_DEBIT_DESCENDANT_MOYEN, minDebitMoyen));
		return listeMinimumMesures;
	}

	/**
	 * Renvoie les maximums des mesures
	 * @param listeMesureWifiModel
	 * @return une liste de mesures représentant respectivement le maximum de chaque catégorie
	 */
	public static List<Mesure> maximumMesures(List<MesureWifiModel> listeMesureWifiModel){
		List<Mesure> listeMaximumMesures = new ArrayList<Mesure>();
		int maxSignal = (listeMesureWifiModel.size()>0) ? listeMesureWifiModel.get(0).getPuissanceSignal().getValue() : 0;
		int maxDebit = (listeMesureWifiModel.size()>0) ? listeMesureWifiModel.get(0).getDebitDescendant().getValue() : 0;
		int maxDebitMoyen = (listeMesureWifiModel.size()>0) ? listeMesureWifiModel.get(0).getDebitDescendantMoyen().getValue() : 0;
		for(int i = 1; i<listeMesureWifiModel.size(); i++){
			MesureWifiModel mesureWifiModel = listeMesureWifiModel.get(i);
			maxSignal = (maxSignal < mesureWifiModel.getPuissanceSignal().getValue()) ? mesureWifiModel.getPuissanceSignal().getValue() : maxSignal;
			maxDebit = (maxSignal < mesureWifiModel.getDebitDescendant().getValue()) ? mesureWifiModel.getDebitDescendant().getValue() : maxDebit;
			maxDebitMoyen = (maxSignal < mesureWifiModel.getDebitDescendantMoyen().getValue()) ? mesureWifiModel.getDebitDescendantMoyen().getValue() : maxDebitMoyen;
		}
		listeMaximumMesures.add(new Mesure(MesureWifiModel.NOM_PUISSANCE_SIGNAL, maxSignal));
		listeMaximumMesures.add(new Mesure(MesureWifiModel.NOM_DEBIT_DESCENDANT, maxDebit));
		listeMaximumMesures.add(new Mesure(MesureWifiModel.NOM_DEBIT_DESCENDANT_MOYEN, maxDebitMoyen));
		return listeMaximumMesures;
	}

	/**
	 * Renvoie les moyennes des mesures
	 * @param listeMesureWifiModel
	 * @return une liste de mesures représentant respectivement la moyenne de chaque catégorie
	 */
	public static List<Mesure> moyenneMesures(List<MesureWifiModel> listeMesureWifiModel){
		List<Mesure> listeMoyenneMesures = new ArrayList<Mesure>();
		int moySignal = (listeMesureWifiModel.size()>0) ? listeMesureWifiModel.get(0).getPuissanceSignal().getValue() : 0;
		int moyDebit = (listeMesureWifiModel.size()>0) ? listeMesureWifiModel.get(0).getDebitDescendant().getValue() : 0;
		int moyDebitMoyen = (listeMesureWifiModel.size()>0) ? listeMesureWifiModel.get(0).getDebitDescendantMoyen().getValue() : 0;
		for(int i = 1; i<listeMesureWifiModel.size(); i++){
			MesureWifiModel mesureWifiModel = listeMesureWifiModel.get(i);
			moySignal += mesureWifiModel.getPuissanceSignal().getValue();
			moyDebit += mesureWifiModel.getDebitDescendant().getValue();
			moyDebitMoyen += mesureWifiModel.getDebitDescendantMoyen().getValue();
		}

		if(listeMesureWifiModel.size() > 0){
			moySignal = Math.toIntExact(Math.round(moySignal/(double)listeMesureWifiModel.size()));
			moyDebit = Math.toIntExact(Math.round(moyDebit/(double)listeMesureWifiModel.size()));
			moyDebitMoyen = Math.toIntExact(Math.round(moyDebitMoyen/(double)listeMesureWifiModel.size()));
		}
		listeMoyenneMesures.add(new Mesure(MesureWifiModel.NOM_PUISSANCE_SIGNAL, moySignal));
		listeMoyenneMesures.add(new Mesure(MesureWifiModel.NOM_DEBIT_DESCENDANT, moyDebit));
		listeMoyenneMesures.add(new Mesure(MesureWifiModel.NOM_DEBIT_DESCENDANT_MOYEN, moyDebitMoyen));
		return listeMoyenneMesures;
	}
}

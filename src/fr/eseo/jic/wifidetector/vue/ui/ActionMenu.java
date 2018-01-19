package fr.eseo.jic.wifidetector.vue.ui;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import fr.eseo.jic.wifidetector.modele.MesureWifiModel;

public class ActionMenu extends AbstractAction {
	private static final long serialVersionUID = 1L;

	public static final String NOM_ACTION_INFO_RESEAU = "Info reseau";
	public static final String NOM_ACTION_CARTOGRAPHIER = "Cartographie du réseau wifi";
	public static final String NOM_ACTION_FENETRE_START = "Start";
	public static final String NOM_ACTION_RETOUR = "Retour au menu";
	public static final String NOM_ACTION_RETOUR_MENU = "Retour menu";
	public static final String NOM_ACTION_RETOUR_ACCUEIL = "Retour fenetre accueil";
	public static final String NOM_ACTION_FERMER = "Fermer fenetre";
	public static final String NOM_ACTION_CLOSE = "Fermer WifiDetector";
	public static final String NOM_ACTION_VALIDER_DIMENSION = "Valider dimensions";
	public static final String NOM_ACTION_AFFICHER_RESULTAT = "Afficher fenetre resultat";
	public static final String NOM_ACTION_ACQUERIR_INFO_RESEAUX = "Acquerir info sur le reseau";

	public ActionMenu() {
		super();
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		// Affichage du menu et désaffichage de la fenetre d'accueil
		if (event.getActionCommand() == NOM_ACTION_FENETRE_START) {
			System.out.println("Menu");
			FenetreMenu.getInstance().setVisible(true);
			FenetreAccueil.getInstance().setVisible(false);
		}
		// Affichage de la fenetre WifiDetector et désaffichage du menu
		if (event.getActionCommand() == NOM_ACTION_INFO_RESEAU) {
			System.out.println("Info reseau");
			FenetreWifiDetector.getInstance().setVisible(true);
			FenetreMenu.getInstance().setVisible(false);
		}
		// Affichage de la fenetre cartographie et desaffichage du menu
		if (event.getActionCommand() == NOM_ACTION_CARTOGRAPHIER) {
			System.out.println("Carto wifi");
			FenetreCartographieWifi.getInstance().setVisible(true);
			FenetreMenu.getInstance().setVisible(false);
			FenetreMesure.getInstance().dispose();
		}
		// Retour au menu après la fenetre wifi detector
		if (event.getActionCommand() == NOM_ACTION_RETOUR) {
			System.out.println("Retour menu apres info");
			FenetreWifiDetector.getInstance().setVisible(false);
			FenetreMenu.getInstance().setVisible(true);
		}
		// Retour au menu après le fenetre cartographie wifi
		if (event.getActionCommand() == NOM_ACTION_RETOUR_MENU) {
			System.out.println("Retour menu apres carto");
			FenetreCartographieWifi.getInstance().setVisible(false);
			FenetreAffichageAcquisitions.getInstance().setVisible(false);
			FenetreMenu.getInstance().setVisible(true);
		}
		// Fermeture du programme
		if (event.getActionCommand() == NOM_ACTION_CLOSE) {
			System.exit(0);
		}
		// Fermeture de la fenetre wifi detector et retour au menu
		if (event.getActionCommand() == NOM_ACTION_FERMER) {
			FenetreWifiDetector.getInstance().setVisible(false);
			FenetreMenu.getInstance().setVisible(true);
		}

		if (event.getActionCommand() == NOM_ACTION_RETOUR_ACCUEIL) {
			FenetreWarningConnexion.getInstance().setVisible(false);
			FenetreAccueil.getInstance().setVisible(true);
		}
		/**
		 * Instructions à ajouter ici pour le bouton valider
		 *
		 */
		if (event.getActionCommand() == NOM_ACTION_VALIDER_DIMENSION) {
			String largeurStr = FenetreCartographieWifi.getInstance().getTextFieldLargeur().getText();
			String hauteurStr = FenetreCartographieWifi.getInstance().getTextFieldHauteur().getText();
			try {
				int largeur = new Integer(largeurStr);
				int hauteur = new Integer(hauteurStr);
				FenetreCartographieWifi.getInstance().getPanelSalle().getSalle().setHauteur(hauteur);
				FenetreCartographieWifi.getInstance().getPanelSalle().getSalle().setLargeur(largeur);
				FenetreCartographieWifi.getInstance().getPanelSalle().getSalle().calculListePointMesure();
				FenetreCartographieWifi.getInstance().getPanelSalle().repaint();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "Merci de saisir des chiffres", "Erreur",
						JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
		/*
		 * Instruction à ajouter ici pour le bouton afficher résultat
		 */
		if (event.getActionCommand() == NOM_ACTION_AFFICHER_RESULTAT) {
			System.out.println("Dimension validées");
			FenetreAffichageAcquisitions.getInstance().setVisible(true);
			FenetreCartographieWifi.getInstance().setVisible(false);
			FenetreAffichageAcquisitions.getInstance().setListeMesureWifiModel(new ArrayList<MesureWifiModel>(FenetreCartographieWifi.getInstance().getListeMesureWifiModel()));
			FenetreCartographieWifi.getInstance().getListeMesureWifiModel().clear();
			FenetreAffichageAcquisitions.getInstance().calculeValeurAffiche();
		}
		/**
		 * Instructions à ajouter ici pour le bouton acquisition des info réseaux
		 *
		 */
		if (event.getActionCommand() == NOM_ACTION_ACQUERIR_INFO_RESEAUX) {
			System.out.println("Prendre info");
			FenetreMesure.getInstance().setVisible(true);
			if(FenetreMesure.getInstance().getProgressBar().getValue() == 100){
				FenetreMesure.getInstance().nouvelleMesure();
			}
		}

	}

}

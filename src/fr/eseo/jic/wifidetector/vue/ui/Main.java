package fr.eseo.jic.wifidetector.vue.ui;

import javax.swing.SwingUtilities;

import fr.eseo.jic.wifidetector.controller.MesureWifi;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Warning();
				
				
				// FenetreAccueil.getInstance().setVisible(true);
				// FenetreMenu.getInstance().setVisible(true);
				// FenetrePartition.getInstance().setVisible(true);
				// FenetreWifiDetection.getInstance().setVisible(true);
				// WarningCalibration.getInstance().setVisible(true);
				// FenetreCartographieWifi.getInstance().setVisible(true);

			}
		});
	}

	
	// Pop warning si SSID == None donc pas de réseau wifi
	public static void Warning() {
		System.out.println(MesureWifi.getCurrentSsid());
		//Mettre en commentaire pour passer la condition if pour passer le warning (pour Mac) 
		if (MesureWifi.getCurrentSsid() == "None") {
			System.out.println("Warning");
			FenetreWarningConnexion.getInstance().setVisible(true); //on affiche le pop up 
		} else {
			System.out.println(MesureWifi.getCurrentSsid());
			FenetreWarningConnexion.getInstance().setVisible(false);
			FenetreAccueil.getInstance().setVisible(true);
			
		}
	}

}

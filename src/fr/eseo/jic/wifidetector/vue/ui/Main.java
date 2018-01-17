package fr.eseo.jic.wifidetector.vue.ui;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				FenetreAccueil.getInstance().setVisible(true);
				// FenetreMenu.getInstance().setVisible(true);
				// FenetrePartition.getInstance().setVisible(true);
				// FenetreWifiDetection.getInstance().setVisible(true);
				// WarningCalibration.getInstance().setVisible(true);
				// FenetreCartographieWifi.getInstance().setVisible(true);

			}
		});
	}

}

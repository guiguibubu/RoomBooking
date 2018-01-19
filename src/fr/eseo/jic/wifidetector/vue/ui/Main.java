package fr.eseo.jic.wifidetector.vue.ui;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					Warning();
				} catch (IOException e) {
					System.out.println("Warning");
					// on affiche le pop up
					FenetreWarningConnexion.getInstance().setVisible(true);
				}
			}
		});
	}

	// Pop warning si SSID == None donc pas de réseau wifi
	public static void Warning() throws IOException {
		//		System.out.println(MesureWifi.getCurrentSsid());
		// Mettre en commentaire pour passer la condition if pour passer le
		// warning (pour Mac, car getSSID ne fonctionne pas pour Mac)
		/*
		 * if (MesureWifi.getCurrentSsid() == "None") {
		 * System.out.println("Warning");
		 * FenetreWarningConnexion.getInstance().setVisible(true); //on affiche
		 * le pop up } else {
		 */

		final String FILE_URL = "http://www.ovh.net/files/1Gb.dat";
		URL mUrl = new URL(FILE_URL);
		HttpURLConnection mCon = (HttpURLConnection) mUrl.openConnection();
		mCon.setChunkedStreamingMode(0);
		mCon.setDoOutput(true);
		System.out.println(mCon.getResponseCode());

		if (mCon.getResponseCode() != 200) {
			System.out.println("Warning");
			// on affiche le pop up
			FenetreWarningConnexion.getInstance().setVisible(true);
		} else {
			// on affiche la fenetre d'accueil
			FenetreAccueil.getInstance().setVisible(true);
		}
	}

}

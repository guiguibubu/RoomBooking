package fr.eseo.jic.wifidetector.vue.ui;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.SwingUtilities;

import fr.eseo.jic.wifidetector.controller.MesureWifi;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Warning();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
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
	public static void Warning() throws IOException {
		System.out.println(MesureWifi.getCurrentSsid());
		//Mettre en commentaire pour passer la condition if pour passer le warning (pour Mac) 
		/*
		if (MesureWifi.getCurrentSsid() == "None") {
			System.out.println("Warning");
			FenetreWarningConnexion.getInstance().setVisible(true); //on affiche le pop up 
		} else {
		*/
		
		final String FILE_URL = "https://www.google.fr";
		URL mUrl = new URL(FILE_URL);
		HttpURLConnection mCon = (HttpURLConnection)mUrl.openConnection();
		mCon.setChunkedStreamingMode(0);
		mCon.setDoOutput(true);

		if(mCon.getResponseCode() != 200)
		{
			System.out.println("Warning");
			FenetreWarningConnexion.getInstance().setVisible(true); //on affiche le pop up 
		} else {
			System.out.println(MesureWifi.getCurrentSsid());
			FenetreWarningConnexion.getInstance().setVisible(false);
			FenetreAccueil.getInstance().setVisible(true);
		}
	}
		
	

}

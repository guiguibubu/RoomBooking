package fr.eseo.jic.wifidetector.vue.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import fr.eseo.jic.wifidetector.controller.MesureWifi;

public class FenetreWarningConnexion extends JFrame {
	/**
	 * Pop up warning si connecté aucun réseau
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labelInfoReseau;
	private JLabel labelInfoWarning;
	private JLabel labelSSID;
	private JButton btnFermer;

	public static final String TITRE_PAR_DEFAUT = "WifiDetector";
	public static final Color COLOR_PAR_DEFAUT = Color.WHITE;
	private static final int LARGEUR_FENETRE_MIN = 650;
	private static final int HAUTEUR_FENETRE_MIN = 400;

	public static FenetreWarningConnexion instance;
	private JLabel labelSSIDGet;
	private JLabel labelMenuImage;

	private FenetreWarningConnexion() {

		// Création de la fenetre
		this.setResizable(true);
		this.getContentPane().setBackground(COLOR_PAR_DEFAUT);
		this.setMinimumSize(new Dimension(LARGEUR_FENETRE_MIN, HAUTEUR_FENETRE_MIN));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setTitle("WifiDetector - Warning");

		// Label texte
		this.labelInfoReseau = new JLabel("Attention");
		this.labelInfoReseau.setForeground(Color.BLUE);
		this.labelInfoReseau.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 50));
		this.labelInfoReseau.setBounds(225, 30, 208, 46);
		this.getContentPane().add(this.labelInfoReseau);
		// Label Info
		this.labelInfoWarning = new JLabel("<html>Vous n'etes pas connecté au réseau.</html>");
		this.labelInfoWarning.setForeground(Color.BLUE);
		this.labelInfoWarning.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 30));
		this.labelInfoWarning.setBounds(80, 70, 503, 46);
		this.getContentPane().add(this.labelInfoWarning);

		this.labelMenuImage = new JLabel("");
		this.labelMenuImage.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelMenuImage.setForeground(Color.BLACK);
		this.labelMenuImage.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 50));
		this.labelMenuImage.setIcon(new ImageIcon(this.getClass().getResource("/no-wifi.png")));
		this.labelMenuImage.setBounds(250, 87, 152, 169);
		this.getContentPane().add(this.labelMenuImage);

		// Label texte SSID
		this.labelSSID = new JLabel("Nom du réseau:");
		this.labelSSID.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 19));
		this.labelSSID.setBounds(80, 305, 152, 29);
		this.getContentPane().add(this.labelSSID);

		JLabel lblVeuillezVousConnecter = new JLabel(
				"<html>Veuillez vous connecter à un réseau et ensuite relancer le programme.</html>");
		lblVeuillezVousConnecter.setForeground(Color.BLACK);
		lblVeuillezVousConnecter.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 20));
		lblVeuillezVousConnecter.setBounds(80, 235, 503, 46);
		this.getContentPane().add(lblVeuillezVousConnecter);

		// Boutton fermer fenetre
		this.btnFermer = new JButton(new ActionMenu());
		this.btnFermer.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 17));
		this.btnFermer.setBounds(521, 298, 96, 44);
		this.btnFermer.setText("Fermer");
		this.btnFermer.setActionCommand(ActionMenu.NOM_ACTION_CLOSE);
		this.getContentPane().add(this.btnFermer);

		//		System.out.println("Signal : " + MesureWifi.getSignal(MesureWifi.WIFI_ETUDIANT));
		String nomSignal = MesureWifi.WIFI_ETUDIANT;
		this.labelSSIDGet = new JLabel(nomSignal);
		this.labelSSIDGet.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelSSIDGet.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 19));
		this.labelSSIDGet.setBounds(310, 305, 152, 29);
		this.getContentPane().add(this.labelSSIDGet);

		// on centre la fenetre
		Toolkit tool = this.getToolkit();
		int largeurEcran = (int) tool.getScreenSize().getWidth();
		int hauteurEcran = (int) tool.getScreenSize().getHeight();
		this.pack();
		this.setLocation((largeurEcran - this.getWidth()) / 2, (hauteurEcran - this.getHeight()) / 2);

	}

	public static FenetreWarningConnexion getInstance() {
		if (instance == null) {
			FenetreWarningConnexion.instance = new FenetreWarningConnexion();
		}
		return FenetreWarningConnexion.instance;
	}

}

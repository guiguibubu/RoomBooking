package fr.eseo.jic.wifidetector.vue.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import fr.eseo.jic.wifidetector.controller.MesureWifi;
import fr.eseo.jic.wifidetector.controller.NoConnectedWifi;
import fr.eseo.jic.wifidetector.modele.MesureWifiModel;

public class FenetreMesure extends JFrame {

	private static final long serialVersionUID = 1L;
	private static FenetreMesure instance;

	public static final String TITRE_PAR_DEFAUT = "WifiDetector - Mesure";
	public static final Color COLOR_PAR_DEFAUT = Color.WHITE;

	private static final int LARGEUR_FENETRE_MIN = 600;
	private static final int HAUTEUR_FENETRE_MIN = 400;

	JProgressBar progressBar;
	JButton returnButton;
	Timer timer = new Timer();

	JLabel labelMenuImage;
	private JLabel label;

	private MesureWifiModel mesureWifiModel;

	private FenetreMesure() {
		// Création de la fenetre
		this.setVisible(true);
		this.setResizable(true);
		this.setMinimumSize(new Dimension(LARGEUR_FENETRE_MIN, HAUTEUR_FENETRE_MIN));
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(COLOR_PAR_DEFAUT);
		this.setTitle(TITRE_PAR_DEFAUT);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		// Barre de chargement
		this.progressBar = new JProgressBar();
		this.progressBar.setStringPainted(true);
		this.progressBar.setForeground(new Color(0, 0, 0));
		this.progressBar.setMaximum(100);
		this.progressBar.setMinimum(0);
		this.progressBar.setValue(0);
		this.progressBar.setBounds(25, 300, 350, 50);
		this.progressBar.setBackground(new Color(255, 255, 255));

		Traitement traitementProgressBar = new Traitement(this.progressBar);
		traitementProgressBar.start();
		// Logo + texte WifiDetection

		this.labelMenuImage = new JLabel("");
		this.labelMenuImage.setForeground(Color.BLACK);
		this.labelMenuImage.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 50));
		// this.labelMenu.setIcon(new
		// ImageIcon("/fr/eseo/jic/wifidetector/res/FenetreAccueil/wifi-2.png"));
		this.labelMenuImage.setIcon(new ImageIcon(this.getClass().getResource("/wifi-2.png")));
		this.labelMenuImage.setBounds(25, 25, 300, 200);
		this.getContentPane().add(this.labelMenuImage);

		// Boutton returnButton pour aller sur la fenetre suivante
		this.returnButton = new JButton(new ActionMenu());
		this.returnButton.setOpaque(false);
		this.returnButton.setVisible(false);
		this.returnButton.setHorizontalAlignment(SwingConstants.CENTER);
		this.returnButton.setActionCommand(ActionMenu.NOM_ACTION_CARTOGRAPHIER);
		this.returnButton.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 20));
		this.returnButton.setText("Retour");
		this.returnButton.setBounds(440, 300, 125, 50);
		// Titre fenetre
		this.label = new JLabel("Mesure ...");
		this.label.setForeground(Color.BLUE);
		this.label.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 50));
		this.label.setBounds(325, 25, 400, 200);
		this.getContentPane().add(this.label);

		this.getContentPane().add((this.progressBar));
		this.getContentPane().add(this.returnButton);
		this.getContentPane().add(this.labelMenuImage);

		// on centre la fenetre
		Toolkit tool = this.getToolkit();
		int largeurEcran = (int) tool.getScreenSize().getWidth();
		int hauteurEcran = (int) tool.getScreenSize().getHeight();
		this.pack();
		this.setLocation((largeurEcran - this.getWidth()) / 2, (hauteurEcran - this.getHeight()) / 2);
	}

	public static FenetreMesure getInstance() {
		if (instance == null) {
			FenetreMesure.instance = new FenetreMesure();
		}
		return FenetreMesure.instance;
	}

	public void nouvelleMesure(){
		if(this.progressBar.getValue() == 100){
			Traitement traitementProgressBar = new Traitement(this.progressBar);
			traitementProgressBar.start();
		}
	}

	class Traitement implements Runnable {

		boolean running = false;
		private Thread thread;
		JProgressBar progressBar;
		TraitementWifi traitementWifi;

		public Traitement(JProgressBar progressBar) {
			super();
			this.progressBar = progressBar;
			this.traitementWifi = new TraitementWifi();
		}

		@Override
		public void run() {
			if(!this.traitementWifi.running){
				this.traitementWifi.start();
			}
			for (int val = 0; val <= 100; val++) {

				this.progressBar.setValue(val);
				if (val == 100) {
					FenetreMesure.this.returnButton.setVisible(true);
					this.stop();
				}
				try {
					Thread.sleep(200); // rapidité de remplissage de la barre
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		public synchronized void start() {
			if (this.running) {
				return;
			}
			this.running = true;
			this.thread = new Thread(this);
			this.thread.start();// va chercher le run() car Runnable
		}

		public synchronized void stop() {
			if (!this.running) {
				return;
			}
			this.running = false;
			try {
				this.thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	class TraitementWifi implements Runnable {

		private boolean running = false;
		private Thread thread;

		@Override
		public void run() {
			int qualiteSignal = MesureWifi.getSignal(MesureWifi.WIFI_ETUDIANT);
			int debitEntrant = 0;
			try {
				debitEntrant = MesureWifi.getSpeedDownFixedTime();
			} catch (NoConnectedWifi e) {
				e.printStackTrace();
			}
			int moyenneDebitDescendantGet = 0;
			try {
				moyenneDebitDescendantGet = MesureWifi.getAverageSpeed(1);
			} catch (NoConnectedWifi e) {
				e.printStackTrace();
			}
			FenetreMesure.getInstance().setMesureWifiModel(new MesureWifiModel(qualiteSignal, debitEntrant, moyenneDebitDescendantGet));
			FenetreCartographieWifi.getInstance().getListeMesureWifiModel().add(FenetreMesure.getInstance().getMesureWifiModel());
			this.stop();
		}

		public synchronized void start() {
			if (this.running) {
				return;
			}
			this.running = true;
			this.thread = new Thread(this);
			this.thread.start();// va chercher le run() car Runnable
		}

		public synchronized void stop() {
			if (!this.running) {
				return;
			}
			this.running = false;
			try {
				this.thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public MesureWifiModel getMesureWifiModel() {return this.mesureWifiModel;}
	public void setMesureWifiModel(MesureWifiModel mesureWifiModel) {this.mesureWifiModel = mesureWifiModel;}

	public JProgressBar getProgressBar() {return this.progressBar;}
	public void setProgressBar(JProgressBar progressBar) {this.progressBar = progressBar;}
}

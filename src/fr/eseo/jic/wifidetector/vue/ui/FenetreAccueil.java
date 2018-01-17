package fr.eseo.jic.wifidetector.vue.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class FenetreAccueil extends JFrame {

	private static final long serialVersionUID = 1L;
	private static FenetreAccueil instance;

	public static final String TITRE_PAR_DEFAUT = "WifiDetector";

	public PanneauAccueil paneAccueil = new PanneauAccueil();

	private Thread t;
	JProgressBar progressBar;
	JButton startButton;
	Timer timer = new Timer();

	JLabel labelMenu;

	private FenetreAccueil() {

		this.setVisible(true);
		this.setMinimumSize(new Dimension(550, 300));
		this.setLocationRelativeTo(null);
		this.setTitle(TITRE_PAR_DEFAUT);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		// timer.schedule(versEcranCalibration(), (long)10000);

		// Barre de chargement
		this.t = new Thread(new Traitement());
		this.progressBar = new JProgressBar();
		this.progressBar.setForeground(new Color(0, 0, 0));
		this.progressBar.setMaximum(100);
		this.progressBar.setMinimum(0);
		this.progressBar.setValue(0);
		this.progressBar.setBounds(127, 227, 301, 45);
		this.progressBar.setStringPainted(true);
		this.progressBar.setBackground(new Color(255, 255, 255));
		this.progressBar.setOpaque(false);

		this.t.start();
		// Logo + texte WifiDetection

		this.labelMenu = new JLabel("WifiDetector");
		this.labelMenu.setForeground(Color.BLACK);
		this.labelMenu.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 38));
		this.labelMenu.setIcon(new ImageIcon("/fr/eseo/jic/wifidetector/res/FenetreAccueil/wifi-2.png"));
		this.labelMenu.setBounds(6, 6, 500, 202);
		this.getContentPane().add(this.labelMenu);

		// Boutton startButton pour aller sur la fenetre suivante
		this.startButton = new JButton(new ActionMenu());
		this.startButton.setOpaque(false);
		this.startButton.setVisible(false);
		this.startButton.setHorizontalAlignment(SwingConstants.CENTER);
		this.startButton.setActionCommand(ActionMenu.NOM_ACTION_FENETRE_START);
		this.startButton.setFont(new Font("Helvetica", Font.PLAIN, 20));
		this.startButton.setText("Start");
		this.startButton.setHorizontalAlignment(SwingConstants.LEFT);
		this.startButton.setBounds(300, 150, 87, 50);

		this.getContentPane().add(this.progressBar);
		this.getContentPane().add(this.startButton);
		this.getContentPane().add(this.labelMenu);
	}

	public static FenetreAccueil getInstance() {
		if (instance == null) {
			FenetreAccueil.instance = new FenetreAccueil();
		}
		return FenetreAccueil.instance;
	}

	public PanneauAccueil getPanneauAccueil() {
		return this.paneAccueil;
	}

	class Traitement implements Runnable {
		@Override
		public void run() {
			for (int val = 0; val <= 100; val++) {
				
				FenetreAccueil.this.progressBar.setValue(val);
				FenetreWifiDetector.getInstance().setEnabled(true);
				if (val == 100) {
					FenetreAccueil.this.startButton.setVisible(true);
				}
				try {
					Thread.sleep(15); // rapidité de remplissage de la barre
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}

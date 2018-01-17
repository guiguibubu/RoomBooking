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

public class FenetreAccueil extends JFrame {

	private static final long serialVersionUID = 1L;
	private static FenetreAccueil instance;

	public static final String TITRE_PAR_DEFAUT = "WifiDetector";
	public static final Color COLOR_PAR_DEFAUT = Color.WHITE;

	private static final int LARGEUR_FENETRE_MIN = 650;
	private static final int HAUTEUR_FENETRE_MIN = 400;

	public PanneauAccueil paneAccueil = new PanneauAccueil();

	private Thread t;
	JProgressBar progressBar;
	JButton startButton;
	Timer timer = new Timer();

	JLabel labelMenuImage;
	private JLabel label;

	private FenetreAccueil() {
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
		this.t = new Thread(new Traitement());
		this.progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		this.progressBar.setForeground(new Color(0, 0, 0));
		this.progressBar.setMaximum(100);
		this.progressBar.setMinimum(0);
		this.progressBar.setValue(0);
		this.progressBar.setBounds(25, 300, 350, 50);
		this.progressBar.setBackground(new Color(255, 255, 255));

		this.t.start();
		// Logo + texte WifiDetection

		this.labelMenuImage = new JLabel("");
		this.labelMenuImage.setForeground(Color.BLACK);
		this.labelMenuImage.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 50));
		// this.labelMenu.setIcon(new
		// ImageIcon("/fr/eseo/jic/wifidetector/res/FenetreAccueil/wifi-2.png"));
		this.labelMenuImage.setIcon(new ImageIcon(getClass().getResource("/wifi-2.png")));
		this.labelMenuImage.setBounds(25, 25, 300, 200);
		this.getContentPane().add(this.labelMenuImage);

		// Boutton startButton pour aller sur la fenetre suivante
		this.startButton = new JButton(new ActionMenu());
		this.startButton.setOpaque(false);
		this.startButton.setVisible(false);
		this.startButton.setHorizontalAlignment(SwingConstants.CENTER);
		this.startButton.setActionCommand(ActionMenu.NOM_ACTION_FENETRE_START);
		this.startButton.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 20));
		this.startButton.setText("Start");
		this.startButton.setBounds(485, 300, 125, 50);
		// Titre fenetre
		label = new JLabel("WifiDetector");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 50));
		label.setBounds(325, 25, 300, 200);
		getContentPane().add(label);

		this.getContentPane().add((this.progressBar));
		this.getContentPane().add(this.startButton);
		this.getContentPane().add(this.labelMenuImage);

		// on centre la fenetre
		Toolkit tool = this.getToolkit();
		int largeurEcran = (int) tool.getScreenSize().getWidth();
		int hauteurEcran = (int) tool.getScreenSize().getHeight();
		this.pack();
		this.setLocation((largeurEcran - this.getWidth()) / 2, (hauteurEcran - this.getHeight()) / 2);
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

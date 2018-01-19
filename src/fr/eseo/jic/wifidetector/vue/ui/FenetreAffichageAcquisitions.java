package fr.eseo.jic.wifidetector.vue.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import fr.eseo.jic.wifidetector.controller.MesureWifi;
import fr.eseo.jic.wifidetector.modele.Mesure;
import fr.eseo.jic.wifidetector.modele.MesureWifiModel;
import fr.eseo.jic.wifidetector.modele.math.CalculStatistique;

public class FenetreAffichageAcquisitions extends JFrame {
	/**
	 * Fenetre affichage résultat cartographie
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labelInfoReseau;
	private JLabel labelSSID;
	private JButton btnFermer;

	public static final String TITRE_PAR_DEFAUT = "WifiDetector";
	public static final Color COLOR_PAR_DEFAUT = Color.WHITE;
	private static final int LARGEUR_FENETRE_MIN = 650;
	private static final int HAUTEUR_FENETRE_MIN = 750;

	public static FenetreAffichageAcquisitions instance;
	private JLabel labelSSIDGet;
	private JLabel lblVersion;

	private JLabel lblQualiteSignalMin;
	private JLabel lblDebitEntrantMin;
	private JLabel lblQualiteSignalMax;
	private JLabel lblDebitEntrantMax;
	private JLabel lblQualiteSignalMoy;
	private JLabel lblDebitEntrantMoy;

	private JLabel lblQualiteSignalMinInfo;
	private JLabel lblDebitEntrantMinInfo;
	private JLabel lblQualiteSignalMaxInfo;
	private JLabel lblDebitEntrantMaxInfo;
	private JLabel lblQualiteSignalMoyInfo;
	private JLabel lblDebitEntrantMoyInfo;

	private List<MesureWifiModel> listeMesureWifiModel;

	private List<Mesure> listeMesureMinimum;
	private List<Mesure> listeMesureMaximum;
	private List<Mesure> listeMesureMoyenne;

	private List<JLabel> listeJLabelAffichageMesure;

	private FenetreAffichageAcquisitions() {

		this.initListeAffichage();

		// Création de la fenetre
		this.setResizable(true);
		this.getContentPane().setBackground(COLOR_PAR_DEFAUT);
		this.setMinimumSize(new Dimension(LARGEUR_FENETRE_MIN, HAUTEUR_FENETRE_MIN));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setTitle("WifiDetector - Informations");

		// Label texte
		this.labelInfoReseau.setText("<html>Informations sur les "+this.listeMesureWifiModel.size()+" acquisitions</html>");
		this.labelInfoReseau.setForeground(Color.BLUE);
		this.labelInfoReseau.setFont(new Font("Helvetica", Font.PLAIN, 30));
		this.labelInfoReseau.setBounds(80, 6, 579, 74);
		this.getContentPane().add(this.labelInfoReseau);

		//Moyenne des mesures
		JLabel labelMoyenne = new JLabel("Moyenne des mesures:");
		labelMoyenne.setHorizontalAlignment(SwingConstants.LEFT);
		labelMoyenne.setFont(new Font("Helvetica", Font.BOLD, 19));
		labelMoyenne.setBounds(80, 140, 222, 30);
		this.getContentPane().add(labelMoyenne);
		// Label texte Qualite signal
		this.lblQualiteSignalMoy = new JLabel("Qualité du signal (en %):");
		this.lblQualiteSignalMoy.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblQualiteSignalMoy.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 19));
		this.lblQualiteSignalMoy.setBounds(80, 180, 222, 30);
		this.getContentPane().add(this.lblQualiteSignalMoy);
		// Label texte Debit entrant
		this.lblDebitEntrantMoy = new JLabel("Débit entrant (en kb):");
		this.lblDebitEntrantMoy.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblDebitEntrantMoy.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 19));
		this.lblDebitEntrantMoy.setBounds(80, 220, 222, 30);
		this.getContentPane().add(this.lblDebitEntrantMoy);
		JLabel labelMinimum = new JLabel("Minimum des mesures:");
		labelMinimum.setHorizontalAlignment(SwingConstants.LEFT);
		labelMinimum.setFont(new Font("Helvetica", Font.BOLD, 19));
		labelMinimum.setBounds(80, 300, 222, 30);
		this.getContentPane().add(labelMinimum);
		// Label texte Qualite signal
		this.lblQualiteSignalMin = new JLabel("Qualité du signal (en %):");
		this.lblQualiteSignalMin.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblQualiteSignalMin.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 19));
		this.lblQualiteSignalMin.setBounds(80, 340, 222, 30);
		this.getContentPane().add(this.lblQualiteSignalMin);
		// Label texte Debit entrant
		this.lblDebitEntrantMin = new JLabel("Débit entrant (en kb):");
		this.lblDebitEntrantMin.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblDebitEntrantMin.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 19));
		this.lblDebitEntrantMin.setBounds(80, 380, 222, 30);
		this.getContentPane().add(this.lblDebitEntrantMin);
		//Maximum des mesures
		JLabel labelMaximium = new JLabel("Maximum des mesures:");
		labelMaximium.setHorizontalAlignment(SwingConstants.LEFT);
		labelMaximium.setFont(new Font("Helvetica", Font.BOLD, 19));
		labelMaximium.setBounds(80, 460, 222, 30);
		this.getContentPane().add(labelMaximium);
		// Label texte Qualite signal
		this.lblQualiteSignalMax = new JLabel("Qualité du signal (en %):");
		this.lblQualiteSignalMax.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblQualiteSignalMax.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 19));
		this.lblQualiteSignalMax.setBounds(80, 500, 222, 30);
		this.getContentPane().add(this.lblQualiteSignalMax);
		// Label texte Debit entrant
		this.lblDebitEntrantMax = new JLabel("Débit entrant (en kb):");
		this.lblDebitEntrantMax.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblDebitEntrantMax.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 19));
		this.lblDebitEntrantMax.setBounds(80, 540, 222, 30);
		this.getContentPane().add(this.lblDebitEntrantMax);

		// Label texte SSID
		this.labelSSID = new JLabel("Nom du réseau:");
		this.labelSSID.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 19));
		this.labelSSID.setBounds(80, 100, 152, 29);
		this.getContentPane().add(this.labelSSID);

		// Boutton fermer fenetre
		this.btnFermer = new JButton(new ActionMenu());
		this.btnFermer.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 17));
		this.btnFermer.setBounds(515, 640, 96, 44);
		this.btnFermer.setText("Fermer");
		this.btnFermer.setActionCommand(ActionMenu.NOM_ACTION_RETOUR_MENU);
		this.getContentPane().add(this.btnFermer);

		String nomSignal = MesureWifi.WIFI_ETUDIANT;
		this.labelSSIDGet = new JLabel(nomSignal);
		this.labelSSIDGet.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelSSIDGet.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 19));
		this.labelSSIDGet.setBounds(457, 100, 152, 29);
		this.getContentPane().add(this.labelSSIDGet);

		/**
		 * Mettre à partir d'ici les info que l'on veut afficher dans les
		 * différents label
		 */
		//Moyenne
		this.lblQualiteSignalMoyInfo.setText(new Integer(this.listeMesureMoyenne.get(0).getValue()).toString());
		this.lblQualiteSignalMoyInfo.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblQualiteSignalMoyInfo.setFont(new Font("Helvetica", Font.PLAIN, 19));
		this.lblQualiteSignalMoyInfo.setBounds(457, 181, 152, 29);
		this.getContentPane().add(this.lblQualiteSignalMoyInfo);

		this.lblDebitEntrantMoyInfo.setText(new Integer(this.listeMesureMoyenne.get(1).getValue()).toString());
		this.lblDebitEntrantMoyInfo.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblDebitEntrantMoyInfo.setFont(new Font("Helvetica", Font.PLAIN, 19));
		this.lblDebitEntrantMoyInfo.setBounds(457, 221, 152, 29);
		this.getContentPane().add(this.lblDebitEntrantMoyInfo);

		//Minimum
		this.lblQualiteSignalMinInfo.setText(new Integer(this.listeMesureMinimum.get(0).getValue()).toString());
		this.lblQualiteSignalMinInfo.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblQualiteSignalMinInfo.setFont(new Font("Helvetica", Font.PLAIN, 19));
		this.lblQualiteSignalMinInfo.setBounds(457, 341, 152, 29);
		this.getContentPane().add(this.lblQualiteSignalMinInfo);

		this.lblDebitEntrantMinInfo.setText(new Integer(this.listeMesureMinimum.get(1).getValue()).toString());
		this.lblDebitEntrantMinInfo.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblDebitEntrantMinInfo.setFont(new Font("Helvetica", Font.PLAIN, 19));
		this.lblDebitEntrantMinInfo.setBounds(457, 381, 152, 29);
		this.getContentPane().add(this.lblDebitEntrantMinInfo);

		//Maximum
		this.lblQualiteSignalMaxInfo.setText(new Integer(this.listeMesureMaximum.get(0).getValue()).toString());
		this.lblQualiteSignalMaxInfo.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblQualiteSignalMaxInfo.setFont(new Font("Helvetica", Font.PLAIN, 19));
		this.lblQualiteSignalMaxInfo.setBounds(457, 501, 152, 29);
		this.getContentPane().add(this.lblQualiteSignalMaxInfo);

		this.lblDebitEntrantMaxInfo.setText(new Integer(this.listeMesureMaximum.get(1).getValue()).toString());
		this.lblDebitEntrantMaxInfo.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblDebitEntrantMaxInfo.setFont(new Font("Helvetica", Font.PLAIN, 19));
		this.lblDebitEntrantMaxInfo.setBounds(457, 541, 152, 29);
		this.getContentPane().add(this.lblDebitEntrantMaxInfo);

		/**
		 * Fin des info que l'on veut afficher
		 */

		// Label version en bas
		this.lblVersion = new JLabel("Version 1.0 2018. Tous droits réservés.");
		this.lblVersion.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblVersion.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 13));
		this.lblVersion.setBounds(223, 650, 229, 22);
		this.getContentPane().add(this.lblVersion);

		// on centre la fenetre
		this.centerFenetre();
	}

	/**
	 * Centre la fenêtre au centre de l'écran
	 */
	private void centerFenetre() {
		Toolkit tool = this.getToolkit();
		int largeurEcran = (int) tool.getScreenSize().getWidth();
		int hauteurEcran = (int) tool.getScreenSize().getHeight();
		this.pack();
		this.setLocation((largeurEcran - this.getWidth()) / 2, (hauteurEcran - this.getHeight()) / 2);
	}

	public static FenetreAffichageAcquisitions getInstance() {
		if (instance == null) {
			FenetreAffichageAcquisitions.instance = new FenetreAffichageAcquisitions();
		}
		return FenetreAffichageAcquisitions.instance;
	}

	private void initListeAffichage(){
		this.labelInfoReseau = new JLabel();

		this.listeMesureWifiModel = new ArrayList<MesureWifiModel>();

		this.listeMesureMinimum = new ArrayList<Mesure>();
		this.listeMesureMaximum = new ArrayList<Mesure>();
		this.listeMesureMoyenne = new ArrayList<Mesure>();

		this.listeJLabelAffichageMesure = new ArrayList<JLabel>();

		this.lblQualiteSignalMinInfo = new JLabel();
		this.lblDebitEntrantMinInfo = new JLabel();
		this.lblQualiteSignalMaxInfo = new JLabel();
		this.lblDebitEntrantMaxInfo = new JLabel();
		this.lblQualiteSignalMoyInfo = new JLabel();
		this.lblDebitEntrantMoyInfo = new JLabel();

		this.listeJLabelAffichageMesure.add(this.labelInfoReseau);
		this.listeJLabelAffichageMesure.add(this.lblQualiteSignalMinInfo);
		this.listeJLabelAffichageMesure.add(this.lblDebitEntrantMinInfo);
		this.listeJLabelAffichageMesure.add(this.lblQualiteSignalMaxInfo);
		this.listeJLabelAffichageMesure.add(this.lblDebitEntrantMaxInfo);
		this.listeJLabelAffichageMesure.add(this.lblQualiteSignalMoyInfo);
		this.listeJLabelAffichageMesure.add(this.lblDebitEntrantMoyInfo);

		this.calculeValeurAffiche();
	}

	public void calculeValeurAffiche(){
		this.labelInfoReseau.setText("<html>Informations sur les "+this.listeMesureWifiModel.size()+" acquisitions</html>");

		this.listeMesureMinimum = CalculStatistique.minimumMesures(this.listeMesureWifiModel);
		this.listeMesureMaximum = CalculStatistique.maximumMesures(this.listeMesureWifiModel);
		this.listeMesureMoyenne = CalculStatistique.moyenneMesures(this.listeMesureWifiModel);

		if(this.lblQualiteSignalMinInfo != null){
			this.lblQualiteSignalMinInfo.setText(new Integer(this.listeMesureMinimum.get(0).getValue()).toString());
			this.lblDebitEntrantMinInfo.setText(new Integer(this.listeMesureMinimum.get(1).getValue()).toString());
			this.lblQualiteSignalMaxInfo.setText(new Integer(this.listeMesureMaximum.get(0).getValue()).toString());
			this.lblDebitEntrantMaxInfo.setText(new Integer(this.listeMesureMaximum.get(1).getValue()).toString());
			this.lblQualiteSignalMoyInfo.setText(new Integer(this.listeMesureMoyenne.get(0).getValue()).toString());
			this.lblDebitEntrantMoyInfo.setText(new Integer(this.listeMesureMoyenne.get(1).getValue()).toString());

			for(JLabel label : this.listeJLabelAffichageMesure){
				label.repaint();
			}
		}
	}

	public List<MesureWifiModel> getListeMesureWifiModel() {return this.listeMesureWifiModel;}
	public void setListeMesureWifiModel(List<MesureWifiModel> listeMesureWifi) {this.listeMesureWifiModel = listeMesureWifi;}

	public List<JLabel> getListeJLabelAffichageMesure() {return this.listeJLabelAffichageMesure;}
	public void setListeJLabelAffichageMesure(List<JLabel> listeJLabelAffichageMesure) {this.listeJLabelAffichageMesure = listeJLabelAffichageMesure;}
}

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

public class FenetreAccueil extends JFrame{

	private static final long serialVersionUID = 1L;
	private static FenetreAccueil instance;
	
	public static final String TITRE_PAR_DEFAUT = "WifiDetector";
	
	public PanneauAccueil paneAccueil = new PanneauAccueil();
	
	private Thread t;
	JProgressBar progressBar;
	JButton startButton;
	Timer timer = new Timer();
	
	JLabel labelMenu;
	
	private FenetreAccueil(){
		
		this.setVisible(true);
		this.setMinimumSize(new Dimension(550, 300));
		this.setLocationRelativeTo(null);
		this.setTitle(TITRE_PAR_DEFAUT);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
//		timer.schedule(versEcranCalibration(), (long)10000);
		
		
		//Barre de chargement
		t = new Thread(new Traitement());
		progressBar = new JProgressBar();
		progressBar.setForeground(new Color(0, 0, 0));
		progressBar.setMaximum(100);
		progressBar.setMinimum(0);
		progressBar.setValue(0);
		progressBar.setBounds(127, 227, 301, 45);
		progressBar.setStringPainted(true);
		progressBar.setBackground(new Color(255, 255, 255));
		progressBar.setOpaque(false);

		t.start(); 
		//Logo + texte WifiDetection
		
		labelMenu = new JLabel("WifiDetector");
		labelMenu.setForeground(Color.BLACK);
		labelMenu.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 38));
		labelMenu.setIcon(new ImageIcon("/Users/dimitrijarneau/workspace/WifiDetection/img/FenetreAccueil/wifi-2.png"));
		labelMenu.setBounds(6, 6, 500, 202);
		this.getContentPane().add(labelMenu);
		
		//Boutton startButton pour aller sur la fenetre suivante
		startButton = new JButton(new ActionMenu());
		startButton.setOpaque(false);
		startButton.setVisible(false);
		startButton.setHorizontalAlignment(SwingConstants.CENTER);
		startButton.setActionCommand(ActionMenu.NOM_ACTION_FENETRE_START);
		startButton.setFont(new Font("Helvetica", Font.PLAIN, 20));
		startButton.setText("Start");
		startButton.setHorizontalAlignment(SwingConstants.LEFT);
		startButton.setBounds(300, 150, 87, 50);
		
		
		this.getContentPane().add(progressBar);
		this.getContentPane().add(startButton);
		this.getContentPane().add(labelMenu);
	}
	
	
	
	
	public static FenetreAccueil getInstance(){
		if (instance == null){
			FenetreAccueil.instance = new FenetreAccueil();
		}
		return FenetreAccueil.instance;
	}

	
	
	public PanneauAccueil getPanneauAccueil(){
		return this.paneAccueil;
	}
	
	

	
	class Traitement implements Runnable{   
	    public void run(){
	      for(int val = 0; val <= 100; val++){
	    	progressBar.setValue(val);
	    	if(val == 100){
	    		startButton.setVisible(true);
	    	}
	        try {
	          Thread.sleep(100);
	        } catch (InterruptedException e) {
	          // TODO Auto-generated catch block
	        	e.printStackTrace();
	        }
	      }
	    }
	}
	
	


}

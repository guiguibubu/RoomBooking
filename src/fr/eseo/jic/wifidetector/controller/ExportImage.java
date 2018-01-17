package fr.eseo.jic.wifidetector.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ExportImage {
	
	public static void exportToPng(JPanel panel, String path) throws Exception {
	    try 
	    {
		  BufferedImage bi = new BufferedImage((int) panel.getSize().getWidth(), (int) panel.getSize().getHeight(), BufferedImage.TYPE_INT_ARGB);
		  Graphics2D ig2 = bi.createGraphics();		
		  panel.paint(ig2);
		  ig2.dispose();
		  ImageIO.write(bi, "PNG", new File(path));	      
	    } 
	    catch (IOException ie) 
	    {
	      ie.printStackTrace();
	    }

	  }
}

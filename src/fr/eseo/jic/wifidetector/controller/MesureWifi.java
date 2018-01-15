package fr.eseo.jic.wifidetector.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class MesureWifi {
	
	public static final String SIGNAL_FR = "Signal";
	
	public static void main(String[] args) 
	{
		System.out.println("Signal : " + MesureWifi.getSignal("Livebox-9962"));	
		System.out.println(MesureWifi.getAverageSpeed(10));
		System.out.println(MesureWifi.getCurrentSsid());
	}
	
	/**
	 * Télécharge un fichier de 1Mb (http://speedtest.ftp.otenet.gr/files/test1Mb.db) pour mesurer le débit entrant du réseau
	 * @return Vitesse en download (en kb)
	 */
	public static int getSpeedDown()
	{ 
		try
		{
			final String FILE_URL = "http://speedtest.ftp.otenet.gr/files/test1Mb.db";
			final long FILE_SIZE = 1024 * 8; // 5MB in Kilobits
			byte[] buffer = "bonjour".getBytes();
	
			long mStart, mEnd;
			URL mUrl = new URL(FILE_URL);
			HttpURLConnection mCon = (HttpURLConnection)mUrl.openConnection();
			mCon.setChunkedStreamingMode(0);
			mCon.setDoOutput(true);
	
			if(mCon.getResponseCode() == 200) 
			{
				mStart = new Date().getTime();
	
				InputStream input = mCon.getInputStream();
				File f = new File("file.bin");
				FileOutputStream fo = new FileOutputStream(f);
				int read_len = 0;
	
				while((read_len = input.read(buffer)) > 0) 
				{
					fo.write(buffer, 0, read_len);
				}
				fo.close();
				mEnd = new Date().getTime();
				mCon.disconnect();
	
				return (int) (FILE_SIZE / ((mEnd - mStart) / 1000));
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return 0;
		}
		return 0;
	}
	
	/**
	 * 
	 * @param samplesNumber Nombre d'échantillons à réaliser
	 * @return Moyenne du débit descendant
	 */
	public static int getAverageSpeed(int samplesNumber)
	{
		int sum = 0;
		for(int n = 0; n < samplesNumber; n++)
		{
			sum += getSpeedDown();
		}
		return sum/samplesNumber;
	}
	
	/**
	 * 
	 * @param wifiName Nom du réseau Wifi (Example : Livebox-6589)
	 * @return Signal wifi du réseau sélectionné en pourcentage (Max : 99 %)
	 */
	public static int getSignal(String wifiName)
	{	
		String signal = "0";	
        try 
        {        	
			String commandWlan = "netsh wlan show network mode=bssid";
        	Process cmd;
        	cmd = Runtime.getRuntime().exec("cmd /c " + commandWlan);
    		cmd.waitFor();
            
    		BufferedReader reader = new BufferedReader(new InputStreamReader(cmd.getInputStream()));
            String line = reader.readLine();  
            
            boolean rightSsid = false, signalFound = false; 
            
            while(line != null || signalFound == false)
    		{		
            	line = reader.readLine();
    			if(rightSsid == true)
    			{
    				if(line.contains(SIGNAL_FR))
    				{
    					signal = line.substring(line.indexOf(": ") + 2, line.indexOf("%"));
    					rightSsid = false;
    					signalFound = true;
    				}	
    			} 
    			else 
    			{
    				rightSsid = line.contains(wifiName);
    			}
    		}             
        } 
        catch (Exception e)
        {
            return Integer.valueOf(signal);
        }		
		return Integer.valueOf(signal);
	}
	
	/**
	 * 
	 * @return Renvoie le SSID actuellement utilisé
	 */
	public static String getCurrentSsid()
	{
        String ssidList = "None";
        try 
        {        
			String commandWlan = "netsh wlan show network";
        	Process cmd;
        	cmd = Runtime.getRuntime().exec("cmd /c " + commandWlan);
    		cmd.waitFor();
            
    		BufferedReader reader = new BufferedReader(new InputStreamReader(cmd.getInputStream()));
            String line = reader.readLine();
            while(line != null)
    		{		
            	line = reader.readLine();
				if(line.contains("SSID "))
				{					
					ssidList = line.replace("SSID " + 1 + "�: ", "");
				}				
    		}             
        } 
        catch (Exception e)
        {
            return ssidList;
        }		
		return ssidList;
	}
}





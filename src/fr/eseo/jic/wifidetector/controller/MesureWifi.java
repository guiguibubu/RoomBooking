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
	
	/**
	 * Télécharge un fichier de 1Gib (http://www.ovh.net/files/1Gb.dat) pour mesurer le débit entrant du réseau en 10 secondes
	 * @return Vitesse en download (en kb)
	 * @throws NoConnectedWifi Problème de connexion, voir le message retourné
	 */
	public static int getSpeedDownFixedTime() throws NoConnectedWifi
	{
		try
		{
			final String FILE_URL = "http://www.ovh.net/files/1Gb.dat";
			byte[] buffer = "Signals that an error occurred while attempting to connect a socket to a remote address and port.".getBytes();
	
			URL mUrl = new URL(FILE_URL);
			HttpURLConnection mCon = (HttpURLConnection)mUrl.openConnection();
			mCon.setChunkedStreamingMode(0);
			mCon.setDoOutput(true);
			
			if(mCon.getResponseCode() == 200) 
			{								
				InputStream input = mCon.getInputStream();					
				int numberLoop = 0;
				long mStart = new Date().getTime();
				int lengthBuffer = buffer.length; // en Byte
				
				while(input.read(buffer) > 0 && (new Date().getTime())-mStart < 10000) 
				{
					numberLoop++;
				}
				long mEnd = new Date().getTime();
				mCon.disconnect();
				return (int) ((lengthBuffer*numberLoop*8) / ((mEnd - mStart) / 1000))/1000;
			} 
			else
			{
				throw new NoConnectedWifi(mCon.getResponseMessage());
			}
		}
		catch (NoConnectedWifi e)
		{
			throw e;
		}
		catch(Exception e)
		{
			return -1;
		}
	}
	
	/**
	 * 
	 * @param samplesNumber Nombre d'échantillons à réaliser
	 * @return Moyenne du débit descendant utilisant getSpeedDownFixedTime()
	 * @throws NoConnectedWifi Problème de connexion dans getSpeedDown(), voir le message retourné
	 */
	public static int getAverageSpeed(int samplesNumber) throws NoConnectedWifi 
	{
		int sum = 0;
		for(int n = 0; n < samplesNumber; n++)
		{
			sum += getSpeedDownFixedTime();
		}
		return sum/samplesNumber;
	}
	
	/**
	 * Voir les fonctions getSignal*() pour plus d'informations pour chaque OS
	 * Fonctionne sur Linux et Windows
	 * @param wifiName Nom du Wifi
	 * @return Force du signal en pourcentage 
	 */
	public static int getSignal(String wifiName)
	{
		int signal = 0;
		if(OsValidator.isWindows())
		{
			signal = getSignalWin(wifiName);
		}
		else if (OsValidator.isUnix())
		{
			signal = getSignalLinux(wifiName);
		}
		return signal;
	}
	
	/**
	 * Utilise la commande iwconfig et convertit le résultat de dBm en % (dbm = (pourcentage/2)-100)
	 * Fonctionne seulement sur Linux
	 * @param wifiName
	 * @return Force du signal Wifi en pourcentage de 0 à 100 pour Linux
	 */
	public static int getSignalLinux(String wifiName)
	{
		String signalDbm = "0";	
        try 
        {        	
			String commandWlan = "iwconfig";
        	Process cmd;
        	cmd = Runtime.getRuntime().exec("cmd /c " + commandWlan);
    		cmd.waitFor();
            
    		BufferedReader reader = new BufferedReader(new InputStreamReader(cmd.getInputStream()));
            String line = reader.readLine();  
            
            boolean rightSsid = false, signalFound = false; 
            
            while(line != null && signalFound == false)
    		{		
            	line = reader.readLine();
    			if(rightSsid == true)
    			{
    				if(line.contains(SIGNAL_FR))
    				{
    					signalDbm = line.substring(line.indexOf("Signal level=") + 13, line.indexOf(" dBm"));
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
            return dbmToPercentage(Integer.valueOf(signalDbm));
        }		
		return dbmToPercentage(Integer.valueOf(signalDbm)); 
	}
	
	/**
	 * Utilise le cmd avec la commande "netsh wlan show network mode=bssid"
	 * Fonctionne seulement sur Windows
	 * @param wifiName Nom du réseau Wifi (Example : Livebox-6589)
	 * @return Signal wifi du réseau sélectionné en pourcentage (Max : 99 %)
	 */
	public static int getSignalWin(String wifiName)
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
	 * Ne fonctionne pas en filaire
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
	
	private static int dbmToPercentage(int signalDbm)
	{
		int signalPercentage = 0;
		if(signalDbm < 50)
		{
			signalPercentage = 100;
		} 
		else if (signalDbm > 100)
		{
			signalPercentage = 0;
		}
		else 
		{
			signalPercentage = (signalDbm+100)*2;
		}
		return signalPercentage;
	}
}





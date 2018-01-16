package fr.eseo.jic.wifidetector.controller;

public class NoConnectedWifi extends Exception 
{
	public NoConnectedWifi() 
	{ 
		super("Aucun réseau Wi-Fi n'est connecté."); 
		}
	public NoConnectedWifi(String message) 
	{ 
		super(message); 
	}
	public NoConnectedWifi(String message, Throwable cause) 
	{ 
		super(message, cause); 
	}
	public NoConnectedWifi(Throwable cause) 
	{ 
		super(cause); 
	}
}

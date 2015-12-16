package bot.controller;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

import bot.model.EV3Bot;

public class BotController
{
	private String message;
	private int xPosition, yPosition;
	private long waitTime;
	
	public BotController() 
	{		
		this.xPosition = 50;
		this.yPosition = 100;
		
	}
	
	public void start() 
	{
		
	}
}

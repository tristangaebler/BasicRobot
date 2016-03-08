package bot.model;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

public class EV3Bot
{
	private String botMessage;
	private int xPosition;
	private int yPosition;
	private long waitTime;
	
	private MovePilot botPilot;
	private EV3UltrasonicSensor distanceSensor;
	private EV3TouchSensor backTouch;
	private float [] touchSamples;
	private float[] ultrasonicSamples;
	
	public EV3Bot()
	{
		this.botMessage = "This is tristanBot";
		this.xPosition = 50;
		this.yPosition = 50;
		this.waitTime = 4_000;
		
		distanceSensor = new EV3UltrasonicSensor(LocalEV3.get().getPort("S1"));
		backTouch = new EV3TouchSensor(LocalEV3.get().getPort("S2"));
	
		setUpPilot();
		displayMessage("Hello");
		
	}
	
	private void setUpPilot()
	{
		Wheel leftWheel = WheeledChassis.modelWheel(Motor.A, 43.3).offset(-72);
		Wheel rightWheel = WheeledChassis.modelWheel(Motor.B, 43.3).offset(-72);
		WheeledChassis chassis= new WheeledChassis(new Wheel[]{leftWheel, rightWheel}, WheeledChassis.TYPE_DIFFERENTIAL);
		botPilot = new MovePilot(chassis);
	}
	
	/*
	 * These will be the measurements for my robot. 16x12x2 = how many of my feet I measured.
	 * From the first door to the long way: 176 inches = 447.04 Centimeters
	 * The long distance: 231 inches = 586.74 Centimeters
	 * The short distance door: 24 inches : 60.96 Centimeters
	 */
	public void driveRoom()
	{
		
		//call private helper method here
/*		ultrasonicSamples = new float[distanceSensor.sampleSize()];
		distanceSensor.fetchSample(ultrasonicSamples, 0);
		if(ultrasonicSamples[0] < 60.96)
		{
			botPilot.travel(447.04);
			botPilot.rotate(-50);
		}
		else
		{
			botPilot.travel(586.74);
			botPilot.rotate(50);
		}*/
		
		ultrasonicSamples = new float [distanceSensor.sampleSize()];
		distanceSensor.fetchSample(ultrasonicSamples, 0);
		
		shortRobotTravel();
		longRobotTravel();
		
	}
	
	private void displayMessage()
	{
		LCD.drawString(botMessage, xPosition, yPosition);
		Delay.msDelay(waitTime);
	}
	
	private void displayMessage(String message)
	{
		LCD.drawString(message, xPosition, yPosition);
		Delay.msDelay(waitTime);
	}
	
	private void shortRobotTravel()
	{
		botPilot.travel(760.2);
		botPilot.rotate(66);
		botPilot.travel(3300.2);
		botPilot.rotate(-66);
		botPilot.travel(5565.8);
		botPilot.rotate(65);
		botPilot.travel(3350.2);
	}
	
	private void longRobotTravel()
	{
		botPilot.travel(3350.2);
		botPilot.rotate(-66);
		botPilot.travel(5565.8);
		botPilot.rotate(65);
		botPilot.travel(3300.2);
		botPilot.rotate(-66);
		botPilot.travel(760);
	}

}

import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
public class MIIO {
	
	static DifferentialPilot pilot = new DifferentialPilot(3.5f,13f, Motor.A, Motor.B,false);
	
	static NXTMotor leftMotor = new NXTMotor(MotorPort.A);
	static NXTMotor rightMotor = new NXTMotor(MotorPort.B);
	
	static TouchSensor frontBumper = new TouchSensor(SensorPort.S1);
	static TouchSensor rightBumper = new TouchSensor(SensorPort.S2);
	
	static LightSensor light = new LightSensor(SensorPort.S3);
	static UltrasonicSensor uss = new UltrasonicSensor(SensorPort.S4);
	
	static int MIN_DIST=5;
	
	public static void main(String[] args) {
		System.out.println("Hi I'm MIIO");
		
		//to avoid useless self picked constants
		MIN_DIST=uss.getDistance();
		
		Button.ENTER.waitForPressAndRelease();
		while(Button.ENTER.isUp())	{
			forward();
		}
	}
	
	public static void forward() {
		
		if(frontBumper.isPressed()) { goBack(); return; }
		
		if(uss.getDistance()<MIN_DIST) {turnRight(); return;}
		
		if(rightBumper.isPressed()) { turnLeft(); return; }
		
		pilot.forward();
	}
	
	public static void turnLeft(){
		pilot.rotateLeft();
		//forward();
	}
	
	public static void turnRight() {
		pilot.rotateRight();
	}
	
	public static void goBack(){
		pilot.backward();
		if(uss.getDistance()>MIN_DIST)	turnRight();
		
		else turnLeft();
		pilot.backward();
	}
}

import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;
public class MIIO {
	static NXTMotor leftMotor = new NXTMotor(MotorPort.A);
	static NXTMotor rightMotor = new NXTMotor(MotorPort.B);
	
	static DifferentialPilot pilot = new DifferentialPilot(3.5f,13f, Motor.A, Motor.B,false);
	
	static TouchSensor leftBumper = new TouchSensor(SensorPort.S1);
	static TouchSensor rightBumper = new TouchSensor(SensorPort.S2);
	
	static LightSensor light = new LightSensor(SensorPort.S3);
	
	public static void main(String[] args) {
		System.out.println("Hi I'm MIIO");
		
		Button.ENTER.waitForPressAndRelease();
		while(Button.ENTER.isUp())	{
			forward();
		}
		
	}
	
	public static void forward() {
		
		//System.out.println(light.getLightValue());
		
		if(leftBumper.isPressed()&&rightBumper.isPressed()) { goBack(); return; }
		
		if(leftBumper.isPressed()) { turnRight(); return; }
		
		if(rightBumper.isPressed()) { turnLeft(); return; }
		
		pilot.forward();
	}
	
	public static void turnLeft(){
		pilot.rotateLeft();
		//forward();
	}
	
	public static void turnRight() {
		
		pilot.backward();

		Delay.msDelay(500);
		
		pilot.rotateRight();
	}
	
	public static void goBack(){
		pilot.backward();
		double rndNum = Math.random();
		if(rndNum<0.5) turnLeft();
		else turnRight();
	}
}

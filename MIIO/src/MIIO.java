

import lejos.nxt.*;
import lejos.util.Delay;
public class MIIO {
	
	/*		?
	 * o.o		 ?
	 * 
	 * (x)		(x)
	 * 
	 * Left		Right
	 */
	
	static NXTMotor leftMotor = new NXTMotor(MotorPort.A);
	static NXTMotor rightMotor = new NXTMotor(MotorPort.B);
	static MiioPilot pilot = new MiioPilot(leftMotor, rightMotor);
	
	static TouchSensor frontTouchSensor = new TouchSensor(SensorPort.S1);
	static TouchSensor rightTouchSensor = new TouchSensor(SensorPort.S2);
	
	static UltrasonicSensor leftUltrasonicSensor = new UltrasonicSensor(SensorPort.S4);
	static BufferedDistanceSensor distanceSensor = new BufferedDistanceSensor(leftUltrasonicSensor);
	
	public static void main(String[] args) {
		System.out.println("Hi I'm MIIO!");
		System.out.println("Press the orange button to start or stop.");
		
		Button.ENTER.waitForPressAndRelease();
		while(Button.ENTER.isUp()) {
			
			if(frontTouchSensor.isPressed()) {
				pilot.backward();
				Delay.msDelay(400);
				pilot.turnRight();
				Delay.msDelay(50);
				
				continue;
			}
			
			if(rightTouchSensor.isPressed()) {
				pilot.turnLeft();
				Delay.msDelay(50);
				
				continue;
			}
			
			if(distanceSensor.hasNewValue()) {
				int distance = distanceSensor.getNewValue();
				
				if(distance < 20) { pilot.turnRight(); }
				if(distance > 60) { pilot.turnLeft(); }
				
				continue;
			}
			
			pilot.forward();
		}
		
	}
	
}

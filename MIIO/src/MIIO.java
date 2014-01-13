

import lejos.nxt.*;
import lejos.util.Delay;
public class MIIO {
	
	/*
	 * Schema der Sensoren
	 *   o.o - Distanz-Sensor
	 *   ?   - Druck-Sensor
	 *   (x) - Motor
	 * 
	 * +----------------+
	 * |		?		|
	 * | o.o		 ?	|
	 * |				|
	 * | (x)		(x)	|
	 * +----------------+
	 * 
	 *  Left		Right
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
		
		//Der eigentliche Programmablauf wird erst nach druecken des orangen Knopfes gestartet.
		Button.ENTER.waitForPressAndRelease();
		
		//Das Wegfindungsprogramm laeuft bis man wiederum den orangen Knopf drueckt
		while(Button.ENTER.isUp()) {
			
			//Wenn der vordere Druck-Sensor gedrueckt wird:
			// - setzt der Roboter 400ms zurueck
			// - dreht sich der Roboter 50ms nach rechts weg
			if(frontTouchSensor.isPressed()) {
				pilot.backward();
				Delay.msDelay(400);
				pilot.turnRight();
				Delay.msDelay(50);
				
				continue;
			}
			
			//Wenn der linke Druck-Sensor gedreuckt wird:
			// - dreht sich der Roboter 50ms nach rechts weg
			if(rightTouchSensor.isPressed()) {
				pilot.turnRight();
				Delay.msDelay(50);
				
				continue;
			}
			
			//Wenn der gepufferte Distanzsensor einen neuen Distanz-Wert errechnet hat:
			// - Wird abhaengig von der Distanz, 
			// - unter 20mm: vom Hindernis weg gedreht
			// - ueber 60mm: zum Hindernis zu gedreht
			if(distanceSensor.hasNewValue()) {
				int distance = distanceSensor.getNewValue();
				
				if(distance < 20) { pilot.turnRight(); }
				if(distance > 60) { pilot.turnLeft(); }
				
				continue;
			}
			
			//Ansonsten bewegt sich der Roboter einfach nach vorne
			pilot.forward();
		}
		
	}
	
}

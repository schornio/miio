import lejos.nxt.NXTMotor;


public class MiioPilot {
	//Der Roboter faehrt mit Maximalgeschwindigkeit
	private static final int MOTOR_POWER = 100;
	
	private NXTMotor leftMotor;
	private NXTMotor rightMotor;
	
	public MiioPilot(NXTMotor leftMotor, NXTMotor rightMotor) {
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;

		
	}
	
	public void start() {
		this.leftMotor.setPower(MOTOR_POWER);
		this.rightMotor.setPower(MOTOR_POWER);
	}
	
	/**
	 * Der Roboter faehrt vorwaerz
	 */
	public void forward() {
		leftMotor.forward();
		rightMotor.forward();
	}
	
	/**
	 * Der Roboter faehrt rueckwaerz
	 */
	public void backward() {
		leftMotor.backward();
		rightMotor.backward();
	}

	/**
	 * Der Roboter dreht am Stand nach links
	 */
	public void turnLeft() {
		leftMotor.backward();
		rightMotor.forward();
	}
	
	/**
	 * Der Roboter dreht am Stand nach rechts
	 */
	public void turnRight() {
		rightMotor.backward();
		leftMotor.forward();
	}
}

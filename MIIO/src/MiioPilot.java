import lejos.nxt.NXTMotor;


public class MiioPilot {
	private static final int MOTOR_POWER = 100;
	
	private NXTMotor leftMotor;
	private NXTMotor rightMotor;
	
	public MiioPilot(NXTMotor leftMotor, NXTMotor rightMotor) {
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;

		this.leftMotor.setPower(MOTOR_POWER);
		this.rightMotor.setPower(MOTOR_POWER);
	}
	
	public void forward() {
		leftMotor.forward();
		rightMotor.forward();
	}
	
	public void backward() {
		leftMotor.backward();
		rightMotor.backward();
	}

	public void turnLeft() {
		leftMotor.backward();
		rightMotor.forward();
	}
	
	public void turnRight() {
		rightMotor.backward();
		leftMotor.forward();
	}
}

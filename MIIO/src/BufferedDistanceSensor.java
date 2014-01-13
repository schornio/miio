import lejos.nxt.UltrasonicSensor;


public class BufferedDistanceSensor {
	private static final int BUFFER_SIZE = 5;
	private static final int INVALID_SENSOR_VALUE = 255;
	
	private int[] buffer;
	private int bufferPosition;
	
	private UltrasonicSensor ultrasonicSensor;
	
	public BufferedDistanceSensor(UltrasonicSensor ultrasonicSensor) {
		buffer = new int[BUFFER_SIZE];
		bufferPosition = 0;
		
		this.ultrasonicSensor = ultrasonicSensor;
		this.ultrasonicSensor.continuous();
	}
	
	public int getNewValue() {
		bufferPosition = 0;
		return average(buffer);
	}
	
	public Boolean hasNewValue() {
		if(bufferPosition == BUFFER_SIZE) return true;
		
		int sensorValue = ultrasonicSensor.getDistance();
		if(sensorValue == INVALID_SENSOR_VALUE) return false; 
		
		buffer[bufferPosition] = sensorValue;
		bufferPosition += 1;
		
		return bufferPosition == BUFFER_SIZE;
	}
	
	private int average(int[] values) {
		int sum = 0;
		for(int i = 0; i < values.length; i++)
			sum += values[i];
		return sum / values.length;
	}
}

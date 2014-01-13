import lejos.nxt.UltrasonicSensor;

/* 
 * Da Distanzen die der Sensor misst oft sehr unterschiedlich sind, 
 * werden die Messungen in einem Puffer abgelegt,
 * und danach ein Mittelwert aus den Messungen genommen.
 */
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
	
	/**
	 * Der Buffer wird geloescht und der Mittelwert der 
	 * gepufferten Ergebnise wird zurueckgegeben.
	 * @return Mittelwert der Distanzen
	 */
	public int getNewValue() {
		bufferPosition = 0;
		return average(buffer);
	}
	
	/**
	 * Eine neue Messung wird vorgenommen und das Ergebnis
	 * in den Puffer geschrieben. Ist der Buffer voll,
	 * so wird TRUE zurueckgeliefert.
	 * @return Wenn Puffer voll, dann TRUE. Ansonsten FALSE
	 */
	public Boolean hasNewValue() {
		if(bufferPosition == BUFFER_SIZE) return true;
		
		int sensorValue = ultrasonicSensor.getDistance();
		if(sensorValue == INVALID_SENSOR_VALUE) return false; 
		
		buffer[bufferPosition] = sensorValue;
		bufferPosition += 1;
		
		return bufferPosition == BUFFER_SIZE;
	}
	
	/**
	 * Hilfsmethode zum Mittelwert berechnen
	 * @param values Die Zahlen aus dem der Mittelwert berechnet werden soll
	 * @return Mittelwert
	 */
	private int average(int[] values) {
		int sum = 0;
		for(int i = 0; i < values.length; i++)
			sum += values[i];
		return sum / values.length;
	}
}

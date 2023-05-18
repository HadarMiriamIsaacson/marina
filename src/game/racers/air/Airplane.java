/*
 * Dor Yehoshua 315619098
 * Hadar Isaacson 209831262
 */
package game.racers.air;


//											***	IMPORTS ***
import game.racers.Racer;
import utilities.EnumContainer.Color;
import game.racers.Wheeled;

/**
 * The Airplane class represents a racer that travels through the air.
 * It extends the Racer class and implements the AerialRacer interface.
 * It also contains a field for the number of wheels that it has.
 */
public class Airplane extends Racer implements AerialRacer   
{
	
	
	//										*** FIELDS ***
	static final String CLASS_NAME = "AirPlane";
	static final int DEFAULT_WHEELS = 3;
	static final double DEFAULT_MAX_SPEED = 885, DEFAULT_ACCELERATION = 100;
	static final Color DEFAULT_color = Color.BLACK;
	private Wheeled wheeled;
	
	
	
	
	//										*** CONSTRUCTORS ***
	
	/**
     * Constructs a new Airplane with default values for its fields.
     * Uses the Airplane constructor that takes parameters with the default values.
     */
	public Airplane() {this("Airplane #" + getSerial(), Airplane.DEFAULT_MAX_SPEED, Airplane.DEFAULT_ACCELERATION, Airplane.DEFAULT_color, Airplane.DEFAULT_WHEELS);}
	
	/**
     * Constructs a new Airplane with the given values for its fields.
     * @param name the name of the airplane
     * @param maxSpeed the maximum speed of the airplane
     * @param acceleration the acceleration of the airplane
     * @param color the color of the airplane
     * @param numOfWheels the number of wheels that the airplane has
     */
	public Airplane(String name, double maxSpeed, double acceleration, Color color, int numOfWheels)
	{
		super(name, maxSpeed, acceleration, color);
		this.wheeled = new Wheeled(numOfWheels);
	}

	
	
	
	//										 *** OVVERRIDE ABSTRACT METHODS FROM RACER CLASS ***
	
	/**
     * Returns the name of this class.
     * @return the name of this class
     */
	@Override
	public String className() { return Airplane.CLASS_NAME; }

	/**
     * Returns a String that describes the specific properties of this airplane.
     * @return a String that describes the specific properties of this airplane
     */
	@Override
	public String describeSpecific() { return ", Number Of Wheels: " + this.wheeled.getNumOfWheels(); }

	@Override
	public void run() {

	}
}
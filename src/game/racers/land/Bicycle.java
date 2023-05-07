/*
 * Dor Yehoshua 315619098
 * Hadar Isaacson 209831262
 */
package game.racers.land;

//													***	IMPORTS ***
import game.racers.Racer;
import game.racers.Wheeled;
import utilities.EnumContainer.BicycleType;
import utilities.EnumContainer.Color;

/**
 * Represents a bicycle racer that races on land.
 */
public class Bicycle extends Racer implements LandRacer
{
	
	//												***	FIELDS ***
	static final String CLASS_NAME = "Bicycle";
	static final int DEFAULT_WHEELS = 2;
	static final double DEFAULT_MAX_SPEED = 270, DEFAULT_ACCELERATION = 10;
	static final Color DEFAULT_color = Color.GREEN;
	private Wheeled wheeled;
	private BicycleType type = BicycleType.MOUNTAIN;
	
	
	
	//												***	CONSTRUCTORS ***

	/**
     * Constructs a bicycle racer with default parameters.
     */
	public Bicycle() {this("Bicycle #" + Racer.getSerial(), Bicycle.DEFAULT_MAX_SPEED, Bicycle.DEFAULT_ACCELERATION, Bicycle.DEFAULT_color, Bicycle.DEFAULT_WHEELS);}

	
	/**
     * Constructs a bicycle racer with the specified parameters.
     *
     * @param name the name of the racer
     * @param maxSpeed the maximum speed of the racer
     * @param acceleration the acceleration of the racer
     * @param color the color of the racer
     * @param numOfWheels the number of wheels of the bicycle
     */
	public Bicycle(String name, double maxSpeed, double acceleration, Color color, int numOfWheels)
	{
		super(name, maxSpeed, acceleration, color);
		this.wheeled = new Wheeled(numOfWheels);
	}


	//										***	OVVERRIDE ABSTRACT METHODS FROM RACER CLASS ***
	
	/**
     * Returns the name of this class.
     *
     * @return the name of this class
     */
	@Override
	public String className() { return Bicycle.CLASS_NAME; }

	/**
     * Returns a string that describes this bicycle racer, including its number of wheels and type.
     *
     * @return a string that describes this bicycle racer
     */
	@Override
	public String describeSpecific() { return ", Number Of Wheels: " + this.wheeled.getNumOfWheels() + ", Bicycle Type: " + this.getType(); }

	
	//													SETTERS & GETTERS
	
	/**
     * Returns the type of this bicycle.
     *
     * @return the type of this bicycle
     */
	public BicycleType getType() { return this.type; }
	
	/**
     * Returns a string that describes this bicycle racer, including its number of wheels and type.
     *
     * @return a string that describes this bicycle racer
     */
	public boolean setType(BicycleType type) { this.type = type; return this.type.equals(type); }
}

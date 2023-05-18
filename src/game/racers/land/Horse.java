/*
 * Dor Yehoshua 315619098
 * Hadar Isaacson 209831262
 */
package game.racers.land;
//													***	IMPORTS ***
import game.racers.Racer;
import utilities.EnumContainer.Breed;
import utilities.EnumContainer.Color;

/**
*The Horse class is a type of Racer that represents a horse that participates in land races.
*It extends the Racer class and implements the LandRacer interface.
*/
public class Horse extends Racer implements LandRacer
{
	//												***	FIELDS ***
	static final String CLASS_NAME = "Horse";
	static final double DEFAULT_MAX_SPEED = 50, DEFAULT_ACCELERATION = 3;
	static final Color DEFAULT_color = Color.BLACK;
	private Breed breed = Breed.THOROUGHBRED;
	
	
	
	
	//												***	CONSTRUCTORS ***
	
	/**
	 * Constructs a new instance of the Horse class with default values for its fields.
	 * Uses a default name ("Horse #" + Racer.getSerial()), maximum speed of 50,
	 * acceleration of 3, and black color.
	 */
	public Horse() {this("Horse #" + Racer.getSerial(), Horse.DEFAULT_MAX_SPEED, Horse.DEFAULT_ACCELERATION, Horse.DEFAULT_color);}

	
	/**
	 * Constructs a new instance of the Horse class with the specified values for its fields.
	 *
	 * @param name         the name of the horse.
	 * @param maxSpeed     the maximum speed of the horse.
	 * @param acceleration the acceleration of the horse.
	 * @param color        the color of the horse.
	 */
	public Horse(String name, double maxSpeed, double acceleration, Color color)
	{
		super(name, maxSpeed, acceleration, color);
	}


	//										***	OVVERRIDE ABSTRACT METHODS FROM RACER CLASS ***
	

	/**
 	* Returns the name of the class as a String.
 	*
 	* @return the name of the class as a String.
 	*/
	@Override
	public String className() { return Horse.CLASS_NAME; }

	
	/**
	 * Returns a String representation of the Horse object's specific attributes.
	 *
	 * @return a String representation of the Horse object's specific attributes.
	 */
	@Override
	public String describeSpecific() { return ", Breed: " + getBreed(); }

	
	//													SETTERS & GETTERS
	

	/**
 	* Returns the breed of the Horse object.
 	*
 	* @return the breed of the Horse object.
 	*/
	public Breed getBreed() { return this.breed; }
	
	
	/**
	 * Sets the breed of the Horse object.
	 *
	 * @param breed the breed of the Horse object.
	 * @return true if the breed was set successfully, false otherwise.
	 */
	public boolean setEngine(Breed breed) { this.breed = breed; return this.breed.equals(breed); }

	@Override
	public void run() {

	}
}

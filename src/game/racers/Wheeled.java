/*
 * Dor Yehoshua 315619098
 * Hadar Isaacson 209831262
 */
package game.racers;

/**
*The Wheeled class represents a racer that moves on wheels. 
*It contains information about the number of wheels
*
*the racer has and provides methods for accessing and modifying this information.
*/
public class Wheeled
{
	
	
	//														*** FIELDS ***
	private int numOfWheels;
	
	
	
	//														*** CONSTRUCTORS ***
	
	/**
	*Constructs a Wheeled object with a default number of wheels (3).
	*/
	public Wheeled() {this(3);} 
	
	
	/**
	*Constructs a Wheeled object with the specified number of wheels.
	*@param numOfWheels the number of wheels the racer should have
	*/
	public Wheeled(int numOfWheels) {this.setNumOfWheels(numOfWheels);}
	
	
	
	//														*** METHODS ***	
	
	/**
	*Returns a string describing the specific information about the wheels of the racer.
	*@return a string describing the number of wheels the racer has
	*/
	public String describeSpecific() {return "Num of wheels: " + this.getNumOfWheels();}
	
	
	
	
	//														*** SETTERS & GETTERS ***
	
	/**
	Returns the number of wheels the racer has.
	@return the number of wheels the racer has
	*/
	public int getNumOfWheels() {return this.numOfWheels;}
	
	
	/**
	*Sets the number of wheels the racer has to the specified value.
	*@param numOfWheels the number of wheels the racer should have
	*@return true if the number of wheels was successfully set to the specified value, false otherwise
	*/
	public boolean setNumOfWheels(int numOfWheels) {this.numOfWheels = numOfWheels; return this.getNumOfWheels() == numOfWheels;}
}
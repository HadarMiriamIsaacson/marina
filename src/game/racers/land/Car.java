/*
 * Dor Yehoshua 315619098
 * Hadar Isaacson 209831262
 */
package game.racers.land;
//													***	IMPORTS ***
import game.racers.*;
import game.racers.Wheeled;
import utilities.EnumContainer.Engine;
import utilities.EnumContainer.Color;

/**
*The Car class represents a type of racer that moves on land using four or more wheels and an engine.
*It extends the Racer class and implements the LandRacer interface.
*It contains information about the number of wheels, engine type and provides methods for accessing and 
*modifying this information.
*/
public class Car extends Racer implements LandRacer
{
	
	
	//												***	FIELDS ***
	static final String CLASS_NAME = "Car";
	static final int DEFAULT_WHEELS = 4;
	static final double DEFAULT_MAX_SPEED = 400, DEFAULT_ACCELERATION = 20;
	static final Color DEFAULT_color = Color.RED;
	private Wheeled wheeled;
	private Engine engine = Engine.FOURSTROKE;
	
	
	
	//												***	CONSTRUCTORS ***
	
	/**
	*Constructs a Car object with a default name, maximum speed, acceleration, color, and number of wheels.
	*/
	public Car() {this("Car #" + Racer.getSerial(), Car.DEFAULT_MAX_SPEED, Car.DEFAULT_ACCELERATION, Car.DEFAULT_color, Car.DEFAULT_WHEELS);}

	
	/**
	*Constructs a Car object with the specified name, maximum speed, acceleration, color, and number of wheels.
	*@param name the name of the car
	*@param maxSpeed the maximum speed of the car
	*@param acceleration the acceleration of the car
	*@param color the color of the car
	*@param numOfWheels the number of wheels the car has
	*/
	public Car(String name, double maxSpeed, double acceleration, Color color, int numOfWheels)
	{
		super(name, maxSpeed, acceleration, color);
		this.wheeled = new Wheeled(numOfWheels);
	}


	//										***	OVVERRIDE ABSTRACT METHODS FROM RACER CLASS ***
	
	/**
	*Returns the name of the class.
	*@return the name of the class
	*/
	@Override
	public String className() { return Car.CLASS_NAME; }

	
	/**
	*Returns a string describing the specific information about the car, including the number of wheels and engine type.
	*@return a string describing the specific information about the car
	*/
	@Override
	public String describeSpecific() { return ", Number Of Wheels: "+ this.wheeled.getNumOfWheels() + ", Engine Type: " + this.getEngine(); }

	
	//													SETTERS & GETTERS
	
	/**
	*Returns the type of engine the car has.
	*@return the type of engine the car has
	*/
	public Engine getEngine() { return this.engine; }
	
	
	/**
	*Sets the type of engine the car has to the specified value.
	*@param engine the type of engine the car should have
	*@return true if the type of engine was successfully set to the specified value, false otherwise
	*/
	public boolean setEngine(Engine engine) { this.engine = engine; return this.engine.equals(engine); }

	@Override
	public void run() {

	}
}
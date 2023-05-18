/*
 * Dor Yehoshua 315619098
 * Hadar Isaacson 209831262
 */
package game.racers;
//											***	IMPORTS	***
import game.arenas.Arena;
import utilities.EnumContainer.Color;
import utilities.Fate;
import utilities.Mishap;
import utilities.Myobservable;
import utilities.Point;


/**
*This abstract class represents a Racer, which has a name, current and finish locations, an arena, a color, 
*maximum speed, acceleration, current speed and failure probability.
*This class includes methods that allow for the initialization of the race, 
*movement of the racer, introduction of the racer, describing the racer, and determining if the racer has a mishap.
*It also includes abstract methods that will be implemented by each specific racer class.
*/
public abstract class Racer extends Myobservable implements Runnable
{
	
	
	//										***	FIELDS	***
	private static int serial = 1;
	private int serialNumber;
	private String name;
	private Point currentLocation;
	private Point finish;
	private Arena arena;
	private double maxSpeed, acceleration, currentSpeed, failureProbability;
	private Color color;
	private Mishap mishap; 
	
	
	
	
	
	
	//										***	CONSTRUCTORS ***
	
	
	/**
	 * Constructs a racer object with a serial number, name, maximum speed, acceleration, 
	 * color, current speed, failure probability and a null mishap.
	 * 
	 * @param name				The racer's name.
	 * @param maxSpeed			The racer's maximum speed.
	 * @param acceleration		The racer's acceleration.
	 * @param color				The racer's color.
	 */
	public Racer(String name, double maxSpeed, double acceleration, Color color)
	{
		this.setSerialNumber(Racer.getSerial());
		this.setName(name);
		this.setMaxSpeed(maxSpeed);
		this.setAcceleration(acceleration);
		this.setColor(color);
		this.setCurrentSpeed(0);
		this.setFailureProbability(0);
		Racer.increasSerial();
	}
	
	
	
	
	
	//										***	METHODS	***
	
	/**
	 * Initializes the racer's race with the given arena, starting location and finish location.
	 * 
	 * @param arena				The arena to be used for the race.
	 * @param start				The starting location of the racer.
	 * @param finish			The finish location of the racer.
	 */
	public void initRace(Arena arena, Point start, Point finish)
	{
		this.setArena(arena);
		this.setCurrentLocation(start);
		this.setFinish(finish);
	}
	
	
	/**
	 * Moves the racer a distance based on its current speed, acceleration and friction. 
	 * Also determines if the racer has a mishap, and if so, generates and processes it. 
	 * 
	 * @param friction			The friction of the arena.
	 * @return					The current location of the racer.
	 */
	public Point move(double friction)
	{
		if( this.mishap == null ||(this.mishap.getFixable() && this.mishap.getTurnsToFix() == 0) )
		{
			this.mishap = null;
			if(Fate.breakDown()) 
			{
				this.mishap = Fate.generateMishap();
				System.out.println( this.getName() + " Has a new mishap! " + this.mishap );
				this.mishap.nextTurn();
				return this.check_move( friction, this.mishap.getReductionFactor());
			}
			else return this.check_move( friction, 1 );		
		}
		
		else 
		{
			if ( this.mishap.getFixable())
			{
				this.mishap.nextTurn();
				return this.check_move( friction, this.mishap.getReductionFactor());
			}
			else return this.check_move( friction, this.mishap.getReductionFactor());
		}
	}
	
	
	/**
	 * A helper method to avoid code duplication. Updates the racer's speed and location based on its current state.
	 * @param friction the friction factor to apply
	 * @param m a multiplier to adjust the acceleration
	 * @return the new location of the racer
	 */
	private Point check_move(double friction,double m)
	{
		if (this.currentSpeed < this.maxSpeed) this.setCurrentSpeed(this.currentSpeed + this.acceleration * friction*m);
		if (this.currentSpeed > this.maxSpeed) this.setCurrentSpeed(this.maxSpeed);
		this.setCurrentLocation(new Point((this.currentLocation.getX() + (1 * this.currentSpeed)), this.currentLocation.getY()));
		return this.getCurrentLocation();
	}
	
	
	/**
	 * Introduces the racer by printing its description to the console.
	 */
	public void introduce() { System.out.println("[" + this.className() + "] " + this.describeRacer()); }
	
	
	/**
	 * Returns a string describing the racer's characteristics.
	 * @return a string describing the racer
	 */
	public String describeRacer() 
	{
		return "name: " + this.getName() + ", SerialNumber: " + this.getSerialNumber() + ", maxSpeed: " + this.getMaxSpeed() + ", acceleration: " + this.getAcceleration() + ", color: " + this.getColor() + this.describeSpecific();
	}
	
	
	/**
	 * Determines if the racer is currently experiencing a mishap.
	 * @return true if the racer has a mishap and it has not been fixed, false otherwise
	 */
	public boolean hasMishap() { return this.getMishap() != null && this.getMishap().getTurnsToFix() > 0; }
	
	
	
	
	
	//										***	ABSTRACT METHODS	***
	
	/**
	 * Returns the class name of the racer.
	 * @return the class name of the racer
	 */
	public abstract String className();
	
	
	/**
	 * Returns a string containing any additional information specific to the racer's subclass.
	 * @return a string containing additional information about the racer
	 */
	public abstract String describeSpecific();	
	
	
	
	
	
	//										***	SETTERS & GETTERS	***
	
	/**
	 * Returns the static serial number of the racer class.
	 * @return the static serial number of the racer class
	 */
	public static int getSerial() { return Racer.serial; }
	
	
	/**
	 * Returns the serial number of the racer.
	 * @return the serial number of the racer
	 */
	public int getSerialNumber() { return this.serialNumber; }
	
	
	/**
	 * Returns the name of the racer.
	 * @return the name of the racer
	 */
	public String getName() { return this.name; }
	
	
	/**
	 * Returns the current location of the racer.
	 * @return the current location of the racer
	 */
	public Point getCurrentLocation() { return this.currentLocation; }
	
	
	/**
	 * Returns the finish line of the race.
	 * @return the finish line of the race
	 */
	public Point getFinish() { return this.finish; }
	
	
	/**
	 * Returns the arena where the race takes place.
	 * @return the arena where the race takes place
	 */
	public Arena getArena() { return this.arena; }
	
	
	/**
	 * Returns the maximum speed of the racer.
	 * @return the maximum speed of the racer
	 */
	public double getMaxSpeed() { return this.maxSpeed; }
	
	
	/**
	 * Returns the acceleration of the racer.
	 * @return the acceleration of the racer
	 */
	public double getAcceleration() { return this.acceleration; }
	
	

	/**
 	* Returns the current speed of the racer.
 	* @return the current speed of the racer
 	*/
	public double getCurrentSpeed() { return this.currentSpeed; }
	
	
	/**
	 * Returns the failure probability of the racer.
	 * @return the failure probability of the racer
	 */
	public double getFalureProbabilithy() { return this.failureProbability; }
	
	
	/**
	*Returns the mishap of the racer.
	*@return The mishap of the racer.
	*/
	public Mishap getMishap() { return this.mishap; }
	
	
	/**
	*Returns the color of the racer.
	*@return The color of the racer.
	*/
	public Color getColor() { return this.color; }
	
	
	/**
	Sets the name of the racer.
	@param name The name to set.
	@return True if the name was set successfully, false otherwise.
	*/
	public boolean setName(String name) { this.name = name; return this.name.equals(name); } 
	
	
	/**
	*Sets the current location of the racer.
	*@param currentLocation The new current location of the racer
	*@return true if the new current location is equal to the input location, false otherwise
	*/
	public boolean setCurrentLocation(Point currentLocation) 
	{
		this.currentLocation = currentLocation;
		return this.currentLocation.equals(currentLocation);
	}
	
	
	/**
	*Sets the finish point of the racer.
	*@param finish The new finish point for the racer
	*@return true if the new finish point is equal to the input finish point, false otherwise
	*/
	public boolean setFinish(Point finish)
	{
		this.finish = finish;
		return this.finish.equals(finish);
	}
	
	
	/**
	*Sets the arena of the racer.
	*@param arena The new arena for the racer
	*@return true if the new arena is equal to the input arena, false otherwise
	*/
	public boolean setArena(Arena arena)
	{
		this.arena = arena;
		return this.arena == arena;
	}
	
	
	/**
	*Sets the maximum speed of the racer.
	*@param maxSpeed The new maximum speed for the racer
	*@return true if the new maximum speed is equal to the input maximum speed, false otherwise
	*/
	public boolean setMaxSpeed(double maxSpeed) { this.maxSpeed = maxSpeed; return this.maxSpeed == maxSpeed; }
	
	
	/**
	*Sets the acceleration of the racer.
	*@param acceleration The new acceleration for the racer
	*@return true if the new acceleration is equal to the input acceleration, false otherwise
	*/
	public boolean setAcceleration(double acceleration) { this.acceleration = acceleration; return this.acceleration == acceleration; }
	
	
	/**
	*Sets the current speed of the racer.
	*@param currentSpeed The new current speed for the racer
	*@return true if the new current speed is equal to the input current speed, false otherwise
	*/
	public boolean setCurrentSpeed(double currentSpeed) { this.currentSpeed = currentSpeed; return this.currentSpeed == currentSpeed; }
	
	
	/**
	*Sets the failure probability of the racer.
	*@param failureProbability The new failure probability for the racer
	*@return true if the new failure probability is equal to the input failure probability, false otherwise
	*/
	public boolean setFailureProbability(double failureProbability) { this.failureProbability = failureProbability; return this.failureProbability == failureProbability; }
	
	
	/**
	*Sets the color of the racer.
	*@param color The new color for the racer.
	*@return true if the color was set successfully, false otherwise.
	*/
	public boolean setColor(Color color) { this.color = color; return this.color == color; }
	
	
	/**
	*Sets the mishap of the racer.
	*@param mishap The new mishap for the racer.
	*@return true if the mishap was set successfully, false otherwise.
	*/
	public boolean setMishap(Mishap mishap) 
	{
		this.mishap = new Mishap(mishap.getFixable(),mishap.getTurnsToFix(),mishap.getReductionFactor());
		return this.mishap.equals(mishap);
	}
	
	
	/**
	*Sets the serial number of the racer.
	*@param serialNumber The new serial number for the racer.
	*@return true if the serial number was set successfully, false otherwise.
	*/
	public boolean setSerialNumber(int serialNumber) { this.serialNumber = serialNumber; return this.getSerialNumber() == serialNumber; }
	
	
	/**
	Increases the serial number of the racer by 1.
	*/
	public static void increasSerial() { Racer.serial++; }
}
	

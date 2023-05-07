/*
 * Dor Yehoshua 315619098
 * Hadar Isaacson 209831262
 */
package game.arenas.naval;




//															*** IMPORTS ***
import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.naval.NavalRacer;
import utilities.EnumContainer.Body;
import utilities.EnumContainer.Water;
import utilities.EnumContainer.WaterSurface;


/**
*The {@code NavalArena} class represents a naval arena, extending the {@link Arena} class
*and implementing the {@link NavalRacer} interface.
*This class includes the arena length, maximum number of racers, friction, water, water surface, and body fields.
*It provides constructors for creating a new naval arena, and methods for adding racers, getting and setting
*the water, water surface, and body of the arena.
*/
public class NavalArena extends Arena implements NavalRacer
{
	
	
	
	//														*** FIELDS ***
	private final static double DEFAULT_FRICTION = 0.7;
	private final static int DEFAULT_MAX_RACERS = 5, DEFAULT_LENGTH = 1000;
	private Water water = Water.SWEET;
	private WaterSurface surface = WaterSurface.FLAT;
	private Body body = Body.LAKE;
	
	
	
	
	
	//														*** CONSTRUCTORS ***
	
	/**
	 * Creates a new object with default length and maximum number of racers.
	 */
	public NavalArena() { this(NavalArena.DEFAULT_LENGTH, NavalArena.DEFAULT_MAX_RACERS); }
	
	
	/**
	 * Creates a new object with the specified length and maximum number of racers.
	 * @param length the length of the arena
	 * @param maxRacers the maximum number of racers allowed in the arena
	 */
	public NavalArena(double length, int maxRacers) { super(length, maxRacers, NavalArena.DEFAULT_FRICTION); }
	
	
	
	
	
	//														*** OVERRIDE METHODS ***
	
	/**
	 * Adds a new racer to the arena.
	 * 
	 * @param newRacer the racer to add to the arena
	 * @throws RacerLimitException if the maximum number of racers has been reached
	 * @throws RacerTypeException if the racer is not of the correct type for this arena
	 */
	public void addRacer(Racer newRacer) throws RacerLimitException, RacerTypeException
	{
		if (newRacer instanceof NavalRacer) super.addRacer(newRacer);
		else throw new RacerTypeException(newRacer.className(),"Naval Arena");
	}
	
	
	
	
	
	//														*** SETTERS & GETTERS ***
	
	/**
	 * Returns the water of the naval arena.
	 * 
	 * @return the water of the arena
	 */
	public Water getWater() { return this.water; }
	
	
	/**
	 * Returns the surface of the water in the naval arena.
	 * 
	 * @return the surface of the water in the arena
	 */
	public WaterSurface getSurface() { return this.surface; }
	
	

	/**
 	* Returns the body of water in the naval arena.
 	* 
 	* @return the body of water in the arena
 	*/
	public Body getBody() { return this.body; }
	
	
	/**
	*Sets the water type for the naval arena.
	*@param water the type of water for the arena
	*@return true if the water type was successfully set, false otherwise
	*/
	public boolean setWater(Water water) { this.water = water; return this.water.equals(water); }
	
	
	/**
	*Sets the water surface for the naval arena.
	*@param surface the type of water surface for the arena
	*@return true if the water surface was successfully set, false otherwise
	*/
	public boolean setSurface(WaterSurface surface) { this.surface = surface; return this.surface.equals(surface); }
	
	
	/**
	*Sets the body of water for the naval arena.
	*@param body the type of body of water for the arena
	*@return true if the body of water was successfully set, false otherwise
	*/
	public boolean setBody(Body body) { this.body = body; return this.body.equals(body); }
}

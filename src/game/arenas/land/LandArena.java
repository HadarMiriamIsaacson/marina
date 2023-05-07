/*
 * Dor Yehoshua 315619098
 * Hadar Isaacson 209831262
 */
package game.arenas.land;



//															*** IMPORTS ***
import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.land.LandRacer;
import utilities.EnumContainer.Coverage;
import utilities.EnumContainer.LandSurface;


/**
*The LandArena class represents a land arena where land racers can participate in a race.
*This class extends the Arena class and implements the LandRacer interface.
*/
public class LandArena extends Arena implements LandRacer
{
	
	
	//														*** FIELDS ***
	private final static double DEFAULT_FRICTION = 0.5;
	private final static int DEFAULT_MAX_RACERS = 8, DEFAULT_LENGTH = 800;
	private Coverage coverage = Coverage.GRASS;
	private LandSurface surface = LandSurface.FLAT;
	
	
	
	
	//														*** CONSTRUCTORS ***
	
	/**
	 * Constructs a new LandArena object with the default length and maximum number of racers.
	 */
	public LandArena() {this(LandArena.DEFAULT_LENGTH, LandArena.DEFAULT_MAX_RACERS);}
	
	
	
	/**
	 * Constructs a new LandArena object with the given length and maximum number of racers.
	 *
	 * @param length the length of the arena
	 * @param maxRacers the maximum number of racers that can participate in the arena
	 */
	public LandArena(double length, int maxRacers) {super(length, maxRacers, LandArena.DEFAULT_FRICTION);}
	
	
	
	
	
	//														*** OVERRIDE METHODS ***
	
	
	/**
	 * Adds a new racer to the land arena.
	 * Throws RacerLimitException if the maximum number of racers has been reached.
	 * Throws RacerTypeException if the racer being added is not a land racer.
	 *
	 * @param newRacer the racer to be added to the arena
	 * @throws RacerLimitException if the maximum number of racers has been reached
	 * @throws RacerTypeException if the racer being added is not a land racer
	 */
	public void addRacer(Racer newRacer) throws RacerLimitException, RacerTypeException
	{
		if (newRacer instanceof LandRacer) super.addRacer(newRacer);
		else throw new RacerTypeException(newRacer.className(),"Land Arena");
	}
	
	
	
	
	//														*** SETTERS & GETTERS ***
	
	
	/**
	 * Returns the coverage of the land arena.
	 *
	 * @return the coverage of the land arena
	 */
	public Coverage getCoverage() { return this.coverage; }
	
	
	/**
	 * Returns the surface of the land arena.
	 *
	 * @return the surface of the land arena
	 */
	public LandSurface getSurface() { return this.surface; }
	
	
	/**
	 * Sets the coverage of the land arena.
	 *
	 * @param coverage the new coverage of the land arena
	 * @return true if the coverage was set successfully, false otherwise
	 */
	public boolean setCoverage(Coverage coverage) { this.coverage = coverage; return this.coverage.equals(coverage); }
	
	
	/**
	 * Sets the surface of the land arena.
	 *
	 * @param surface the new surface of the land arena
	 * @return true if the surface was set successfully, false otherwise
	 */
	public boolean setSurface(LandSurface surface) {this.surface = surface; return this.surface.equals(surface);}
}
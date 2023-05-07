/*
 * Dor Yehoshua 315619098
 * Hadar Isaacson 209831262
 */
package game.arenas.air;



//															*** IMPORTS ***
import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.air.AerialRacer;
import utilities.EnumContainer.Vision;
import utilities.EnumContainer.Weather;
import utilities.EnumContainer.Height;
import utilities.EnumContainer.Wind;


/**
*AerialArena is an arena for aerial racers. It extends Arena and implements the AerialRacer interface.
*It contains fields for vision, weather, height and wind, and default values for friction, maximum racers and length.
*It also has constructors to create instances of AerialArena with specific parameters.
*/
public class AerialArena extends Arena implements AerialRacer
{
	
	
	
	//														*** FIELDS ***
	private final static double DEFAULT_FRICTION = 0.4;
	private final static int DEFAULT_MAX_RACERS = 6, DEFAULT_LENGTH = 1500;
	private Vision vision = Vision.SUNNY;
	private Weather weather = Weather.DRY;
	private Height height = Height.HIGH;
	private Wind wind = Wind.HIGH;
	
	
	
	
	
	//														*** CONSTRUCTORS ***
	
	/**
	 * Default constructor for AerialArena.
	 * Calls the parameterized constructor with default values for length and maximum racers.
	 */
	public AerialArena() {this(AerialArena.DEFAULT_LENGTH,AerialArena.DEFAULT_MAX_RACERS);}
	
	
	/**
	 * Parameterized constructor for AerialArena.
	 * Calls the constructor of the superclass Arena with the given length, maximum racers and default friction.
	 * @param length The length of the arena.
	 * @param maxRacers The maximum number of racers in the arena.
	 */
	public AerialArena(double length, int maxRacers) {super(length, maxRacers, AerialArena.DEFAULT_FRICTION);}
	
	
	
	
	
	
	//														*** OVERRIDE METHODS ***
	
	/**
	 * Method to add a racer to the arena.
	 * Overrides the addRacer method in the Arena class.
	 * Throws a RacerLimitException if the maximum number of racers has been reached.
	 * Throws a RacerTypeException if the racer being added is not an instance of AerialRacer.
	 * @param newRacer The racer to be added to the arena.
	 * @throws RacerLimitException If the maximum number of racers has been reached.
	 * @throws RacerTypeException If the racer being added is not an instance of AerialRacer.
	 */
	public void addRacer(Racer newRacer) throws RacerLimitException, RacerTypeException
	{
		if (newRacer instanceof AerialRacer) super.addRacer(newRacer);
		else throw new RacerTypeException(newRacer.className(),"Aerial Arena");
	}
	
	
	
	
	//														*** SETTERS & GETTERS ***
	
	/**
	 * Getter for the wind field.
	 * @return The current wind in the arena.
	 */
	public Wind getWind() { return this.wind; }
	
	
	/**
	 * Getter for the vision field.
	 * @return The current vision in the arena.
	 */
	public Vision getVision() { return this.vision; }
	
	
	/**
	 * Getter for the height field.
	 * @return The current height in the arena.
	 */
	public Height getHeight() { return this.height; }
	
	
	/**
	 * Getter for the weather field.
	 * @return The current weather in the arena.
	 */
	public Weather getWeather() { return this.weather; }
	
	
	/**
	 * Sets the wind parameter of the aerial arena to the specified value.
	 * @param wind The wind value to set.
	 * @return True if the wind parameter was successfully set, false otherwise.
	 */
	public boolean setWind(Wind wind) {this.wind = wind; return this.wind.equals(wind);}
	
	
	/**
	 * Sets the vision parameter of the aerial arena to the specified value.
	 * @param vision The vision value to set.
	 * @return True if the vision parameter was successfully set, false otherwise.
	 */
	public boolean setVision(Vision vision) {this.vision = vision; return this.vision.equals(vision);}
	
	
	/**
	 * Sets the height parameter of the aerial arena to the specified value.
	 * @param height The height value to set.
	 * @return True if the height parameter was successfully set, false otherwise.
	 */
	public boolean setHeight(Height height) {this.height = height; return this.height.equals(height);}
	
	
	/**
	 * Sets the weather parameter of the aerial arena to the specified value.
	 * @param weather The weather value to set.
	 * @return True if the weather parameter was successfully set, false otherwise.
	 */
	public boolean setWeather(Weather weather) {this.weather = weather; return this.weather.equals(weather);}
}

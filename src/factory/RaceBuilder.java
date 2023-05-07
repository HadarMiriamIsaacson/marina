/*
 * Dor Yehoshua 315619098
 * Hadar Isaacson 209831262
 */
package factory;


//													*** IMPORTS ***
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import game.arenas.Arena;
import game.racers.Racer;
import utilities.EnumContainer.Color;


/**
*The RaceBuilder class is a singleton class that provides methods for building arenas and racers.
*The buildArena method takes in the arenaType, length, and maxRacers as parameters and returns an instance of the Arena class.
*The buildRacer method takes in the racerType, name, maxSpeed, acceleration, and color as parameters and returns an instance of the Racer class.
*The buildWheeledRacer method takes in the racerType, name, maxSpeed, acceleration, color, and wheels as parameters and returns an instance of the WheeledRacer class.
*This class uses reflection to dynamically load the required classes and create instances using the constructors.
*/
public class RaceBuilder
{
	
	
	
	//												***	FIELDS ***
	private static RaceBuilder instance;
	private ClassLoader classLoader;
	private Class<?> classObject;
	private Constructor<?> constructor;
	
	
	
	
	
	//												***	METHODS ***
	
	
	/**
	 * Builds an Arena object using the given arenaType, length, and maxRacers parameters.
	 * @param arenaType - the type of arena
	 * @param length - the length of the arena
	 * @param maxRacers - the maximum number of racers allowed in the arena
	 * @return Arena object
	 * @throws ClassNotFoundException - if the class cannot be found
	 * @throws NoSuchMethodException - if the method cannot be found
	 * @throws SecurityException - if there is a security violation
	 * @throws InstantiationException - if an instance of the class cannot be created
	 * @throws IllegalAccessException - if access to the class or method is denied
	 * @throws IllegalArgumentException - if the method arguments are invalid
	 * @throws InvocationTargetException - if the method invocation fails
	 */
	public Arena buildArena(String arenaType, double length, int maxRacers) 
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, 
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		this.classLoader = ClassLoader.getSystemClassLoader();
		this.classObject = this.classLoader.loadClass(arenaType);
		this.constructor = this.classObject.getConstructor(double.class, int.class);
		return (Arena) this.constructor.newInstance(length, maxRacers);
	
	}
	
	
	/**
	 * Builds a Racer object using the given racerType, name, maxSpeed, acceleration, and color parameters.
	 * @param racerType - the type of racer
	 * @param name - the name of the racer
	 * @param maxSpeed - the maximum speed of the racer
	 * @param acceleration - the acceleration of the racer
	 * @param color - the color of the racer
	 * @return Racer object
	 * @throws ClassNotFoundException - if the class cannot be found
	 * @throws NoSuchMethodException - if the method cannot be found
	 * @throws SecurityException - if there is a security violation
	 * @throws InstantiationException - if an instance of the class cannot be created
	 * @throws IllegalAccessException - if access to the class or method is denied
	 * @throws IllegalArgumentException - if the method arguments are invalid
	 * @throws InvocationTargetException - if the method invocation fails
	 */
	public Racer buildRacer(String racerType, String name, double maxSpeed, double acceleration, Color color) 
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, 
			IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		this.classLoader = ClassLoader.getSystemClassLoader();
		this.classObject = this.classLoader.loadClass(racerType);
		this.constructor = this.classObject.getConstructor(String.class,double.class,double.class,Color.class);
		return (Racer) this.constructor.newInstance(name, maxSpeed, acceleration, color);
	}
	
	
	/**
	*Builds and returns a new instance of a wheeled racer object based on the given parameters.
	*@param racerType The fully qualified name of the class of the racer to be built.
	*@param name The name of the racer.
	*@param maxSpeed The maximum speed of the racer.
	*@param acceleration The acceleration of the racer.
	*@param color The color of the wheels.
	*@param wheels The number of wheels of the racer.
	*@return A new instance of the wheeled racer object built based on the given parameters.
	*@throws ClassNotFoundException If the specified class for the racerType cannot be found.
	*@throws NoSuchMethodException If the constructor for the specified racerType class cannot be found.
	*@throws SecurityException If the security manager denies access to the constructor for the specified racerType class.
	*@throws InstantiationException If an object cannot be created for the specified racerType class.
	*@throws IllegalAccessException If the constructor for the specified racerType class is not accessible.
	*@throws IllegalArgumentException If an illegal or inappropriate argument is passed to the constructor for the specified racerType class.
	*@throws InvocationTargetException If the constructor for the specified racerType class throws an exception.
	*/
	public Racer buildWheeledRacer(String racerType, String name, double maxSpeed, double acceleration, Color color, int wheels) 
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		this.classLoader = ClassLoader.getSystemClassLoader();
		this.classObject = this.classLoader.loadClass(racerType);
		this.constructor = this.classObject.getConstructor(String.class,double.class, double.class, Color.class, int.class);
		return (Racer) this.constructor.newInstance(name, maxSpeed, acceleration, color, wheels);
	}
	
	
	
	
	
	//											*** SETTERS & GETTERS ***
	
	/**
	 * Returns an instance of the RaceBuilder class.
	 * If the instance is null, creates a new instance and returns it.
	 * @return RaceBuilder instance
	 */
	public static RaceBuilder getInstance()
	{
		if (RaceBuilder.instance == null)
			RaceBuilder.instance = new RaceBuilder();
		return RaceBuilder.instance;
	}
}
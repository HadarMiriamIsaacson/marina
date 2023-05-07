/*
 * Dor Yehoshua 315619098
 * Hadar Isaacson 209831262
 */

/**
*The RacerTypeException class represents an exception that is thrown when an invalid racer type is encountered.
*This exception extends the Exception class.
*/

package game.arenas.exceptions;
@SuppressWarnings("serial")
public class RacerTypeException extends Exception
{
	
	//												*** CONSTRUCTOR ***
	
	
	
	/**
	 * Constructs a new RacerTypeException with a detail message that includes the class name of the invalid racer type and the name of the arena.
	 *
	 * @param className the class name of the invalid racer type
	 * @param arenaName the name of the arena where the invalid racer type was encountered
	 */
	public RacerTypeException(String className, String name_of_arena) {super("Invalid Racer of type " + className + " for " + name_of_arena );}
}
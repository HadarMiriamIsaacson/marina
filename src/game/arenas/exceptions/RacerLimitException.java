/*
 * Dor Yehoshua 315619098
 * Hadar Isaacson 209831262
 */
package game.arenas.exceptions;

/**
*The RacerLimitException class represents an exception that is thrown when the maximum number of 
*racers is reached in the arena.
*This exception extends the Exception class.
*/

@SuppressWarnings("serial")
public class RacerLimitException extends Exception
{
	//												*** CONSTRUCTOR ***
	
	
	/**
	 * Constructs a new RacerLimitException with a detail message that includes the maximum number 
	 * of racers and the serial number of the racer that was not added.
	 *
	 * @param maxRacers the maximum number of racers allowed in the arena
	 * @param serialNumber the serial number of the racer that was not added
	 */
	public RacerLimitException(int maxRacers,int serialNumber){super("Arena is full! (" + maxRacers + " active racers exist). racer #" + serialNumber + " was not added");}
}
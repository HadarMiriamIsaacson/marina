/*
 * Dor Yehoshua 315619098
 * Hadar Isaacson 209831262
 */
package game.arenas;


//											*** IMPORTS ***
import java.util.ArrayList;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import utilities.Point;


/**
*Arena is an abstract class that represents a racing arena where racers compete against each other.
*It contains fields for the maximum number of racers, the length of the arena, the friction factor,
*and lists for active racers and completed racers. It also provides methods for adding a racer to the arena,
*initializing the race, playing turns for all the racers, crossing the finish line, and showing the results.
*/
public abstract class Arena 
{
	
	
	//										***	FIELDS	***
	private ArrayList<Racer> activeRacers, completedRacers;
	private final double FRICTION;
	private final int MAX_RACERS;
	private final static int MIN_Y_GAP = 10;
	private double length;
	
	
	
	
	//										***	CONSTRUSTOR	***
	
	/**
	 * Constructs a new Arena with the given length, maximum number of racers, and friction factor.
	 *
	 * @param length the length of the arena
	 * @param maxRacers the maximum number of racers allowed in the arena
	 * @param friction the friction factor of the arena
	 */
	public Arena(double length, int maxRacers, double friction)
	{
		this.FRICTION = friction;
		this.length = length;
		this.MAX_RACERS = maxRacers;
		this.activeRacers = new ArrayList<>();
        this.completedRacers = new ArrayList<>();
	}
	
	
	
	
	//										***	METHODS	***
	
	/**
	 * Adds a new racer to the arena.
	 *
	 * @param newRacer the racer to add to the arena
	 * @throws RacerLimitException if the maximum number of racers allowed in the arena has been reached
	 * @throws RacerTypeException if the racer's type is not compatible with the arena
	 */
	public void addRacer(Racer newRacer) throws RacerLimitException, RacerTypeException
	{
		//check if the arena is full
		
		if(this.activeRacers.size() == getMaxRacers())
			throw new RacerLimitException(getMaxRacers(), newRacer.getSerialNumber());
		
		// if not then add a new racer
		
		this.addActiveRacer(newRacer);
	}
	
	/**
	 * Initializes the race by setting a starting Y_GAP for every player.
	 */
	public void initRace()
	{
		int STARTING_Y_GAP = 0;
		for(Racer active_racer: this.getActiveRacers())
		{
			active_racer.initRace(this, new Point(0,STARTING_Y_GAP), new Point(this.getLength(),STARTING_Y_GAP));
			STARTING_Y_GAP += Arena.getMin_Y_Gap();
		}
	}
	
	
	/**
	 * Returns whether there are any active racers currently in the arena.
	 *
	 * @return true if there are active racers, false otherwise
	 */
	public boolean hasActiveRacers() {return this.getActiveRacers().size() > 0;}
	
	
	/**
	 * Plays a turn for all the racers in the arena, checking if each racer has crossed the finish line.
	 * If a racer crosses the finish line, they are removed from the active racers list and added 
	 * to the completed racers list.
	 */
	public void playTurn()
	{
		for(int racer_lim = 0; racer_lim < this.getActiveRacers().size(); racer_lim++)
		{
			this.getActiveRacers().get(racer_lim).move(this.getFriction());
			if (this.getActiveRacers().get(racer_lim).getCurrentLocation().getX() >= this.getLength())
			{
				this.crossFinishLine(this.getActiveRacers().get(racer_lim));
				racer_lim--;
			}
		}
	}
	
	
	/**
	*Removes the racer from the active racers list and adds it to the completed racers list.
	*@param racer the racer that crossed the finish line
	*/
	public void crossFinishLine(Racer racer)
	{
		this.removeActiveRacer(racer);
		this.addCompletedRacers(racer);
	}
	
	
	/**
	*Prints a description of the results of the race.
	*/
	public void showResults()
	{
		int i = 0;
		for (Racer racer: this.getCompletedRacers())
		{
			System.out.println("#" + i + " -> " + racer.describeRacer());
			i++;
		}
	}
	
	
	
	//											*** SETTERS & GETTERS ***	
	
	/**
	*Returns the friction factor of the arena.
	*@return the friction factor of the arena
	*/
	public final double getFriction() {return this.FRICTION;}
	
	
	/**
	*Returns the maximum number of racers allowed in the arena.
	*@return the maximum number of racers allowed in the arena
	*/
	public final int getMaxRacers() {return this.MAX_RACERS;}
	
	
	/**
	*Returns the minimum Y_GAP between racers in the arena.
	*@return the minimum Y_GAP between racers in the arena
	*/
	public final static int getMin_Y_Gap() {return MIN_Y_GAP;}
	
	
	/**
	*Returns the length of the arena.
	*@return the length of the arena
	*/
	public double getLength() {return this.length;}
	
	
	/**
	 * Return the active racers list
	 * @return the active racers list 
	 */
	public ArrayList<Racer> getActiveRacers() {return this.activeRacers;}
	

	/**
	 * Return the completed racers list
	 * @return the completed racers list
	 */
	public ArrayList<Racer> getCompletedRacers() {return this.completedRacers;}
	
	
	/**
	*Sets the length of the arena.
	*@param length the new length of the arena
	*@return true if the length was set successfully, false otherwise
	*/
	public boolean setlength(double length) {this.length = length; return this.length == length;}
	
	
	/**
	*Sets the list of active racers.
	*@param activeRacers the new list of active racers
	*@return true if the list of active racers was set successfully, false otherwise
	*/
	public boolean setActiveRacers(ArrayList<Racer> activeRacers) {this.activeRacers = activeRacers; return this.getActiveRacers().equals(activeRacers);}
	
	
	/**
	*Sets the list of completed racers.
	*@param completedRacers the new list of completed racers
	*@return true if the list of completed racers was set successfully, false otherwise
	*/
	public boolean setCompletedRacers(ArrayList<Racer> completedRacers) {this.completedRacers = completedRacers; return this.getCompletedRacers().equals(completedRacers);}
	
	
	/**
	*Helper method for adding a racer to the active racers list.
	*@param newRacer the racer to add to the active racers list
	*/
	public void addActiveRacer(Racer newRacer) {this.activeRacers.add(newRacer);}//help function
	
	
	/**
	*Helper method for removing a racer from the active racers list.
	*@param racer the racer to remove from the active racers list
	*@return true if the racer was removed successfully, false otherwise
	*/
	public void removeActiveRacer(Racer racer) {this.activeRacers.remove(racer);}//help function
	
	
	/**
	*Helper method for adding a racer to the completed racers list.
	*@param racer the racer to add to the completed racers list
	*/
	public void addCompletedRacers(Racer newRacer) {this.completedRacers.add(newRacer);}//help function
	
	
	/**
	*Removes the completed racers from the given list of racers.
	*@param racers a list of Racer objects to remove completed racers from
	*/
	public void removeCompletedRacers(Racer racer) {this.completedRacers.remove(racer);}//help function
}
/*
 * Dor Yehoshua 315619098
 * Hadar Isaacson 209831262
 */
package game.racers.naval;
//													***	IMPORTS ***
import game.racers.Racer;
import game.racers.naval.NavalRacer;
import utilities.EnumContainer.BoatType;
import utilities.EnumContainer.Color;
import utilities.EnumContainer.Team;

/**
*The RowBoat class represents a rowing boat that participates in a naval race.
*It extends the Racer class and implements the NavalRacer interface.
*It has fields for boat type and team, and methods for getting and setting them.
*/
public class RowBoat extends Racer implements NavalRacer
{
	
	
	//												***	FIELDS ***
	static final String CLASS_NAME = "RowBoat";
	static final double DEFAULT_MAX_SPEED = 75, DEFAULT_ACCELERATION = 10;
	static final Color DEFAULT_color = Color.RED;
	private BoatType type = BoatType.SKULLING;
	private Team team = Team.DOUBLE;
	
	
	
	//												***	CONSTRUCTORS ***
	
	/**
	 * Constructs a new RowBoat object with a default name, speed, acceleration, and color.
	 */
	public RowBoat() {this("RowBoat #" + Racer.getSerial(), RowBoat.DEFAULT_MAX_SPEED, RowBoat.DEFAULT_ACCELERATION, RowBoat.DEFAULT_color);}

	
	/**
	 * Constructs a new RowBoat object with the specified name, maximum speed, acceleration, and color.
	 * 
	 * @param name the name of the row boat
	 * @param maxSpeed the maximum speed of the row boat
	 * @param acceleration the acceleration of the row boat
	 * @param color the color of the row boat
	 */
	public RowBoat(String name, double maxSpeed, double acceleration, Color color)
	{
		super(name, maxSpeed, acceleration, color);
	}


	//										***	OVVERRIDE ABSTRACT METHODS FROM RACER CLASS ***
	
	

	/**
 	* Returns the class name of the row boat.
 	* 
 	* @return the class name of the row boat
 	*/
	@Override
	public String className() { return RowBoat.CLASS_NAME; }

	
	/**
	 * Returns a string containing the specific description of the row boat,
	 * including its type and team.
	 * 
	 * @return a string containing the specific description of the row boat
	 */
	@Override
	public String describeSpecific() { return ", Type: " + getType() + ", Team: " + getTeam(); }

	
	//												***	SETTERS & GETTERS ***
	
	/**
	 * Returns the type of the row boat, as defined in the BoatType enum.
	 * 
	 * @return the type of the row boat
	 */
	public BoatType getType() { return this.type; }
	
	
	/**
	 * Returns the team that the row boat is racing for, as defined in the Team enum.
	 * 
	 * @return the team that the row boat is racing for
	 */
	public Team getTeam() { return this.team; }
	
	

	/**
 	* Sets the type of the row boat to the specified type.
 	* 
 	* @param type the type of the row boat
 	* @return true if the type was set successfully, false otherwise
 	*/
	public boolean setType(BoatType type) { this.type = type; return this.type.equals(type); }
	
	
	/**
	*Sets the team of the row boat to the given team.
	*@param team the team to set for the row boat
	*@return true if the team was successfully set, false otherwise
	*/
	public boolean setTeam(Team team) { this.team = team; return this.team.equals(team); }
}
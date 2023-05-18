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
*The SpeedBoat class represents a Racer that is a type of naval racer.
*It extends the Racer class and implements the NavalRacer interface.
*It has a default maximum speed, acceleration, and color.
*It also has a BoatType and Team fields that can be set and retrieved.
*/
public class SpeedBoat extends Racer implements NavalRacer
{
	
	
	//												***	FIELDS ***
	static final String CLASS_NAME = "SpeedBoat";
	static final double DEFAULT_MAX_SPEED = 170, DEFAULT_ACCELERATION = 5;
	static final Color DEFAULT_color = Color.RED;
	private BoatType type = BoatType.SKULLING;
	private Team team = Team.DOUBLE;
	
	
	
	
	//												***	CONSTRUCTORS ***
	
	/**
	*Constructs a new SpeedBoat instance with a default name, maximum speed,
	*acceleration, and color.
	*/
	public SpeedBoat() {this("SpeedBoat #" + Racer.getSerial(), SpeedBoat.DEFAULT_MAX_SPEED, SpeedBoat.DEFAULT_ACCELERATION, SpeedBoat.DEFAULT_color);}

	
	/**
	*Constructs a new SpeedBoat instance with the given name, maximum speed,acceleration, and color.
	*@param name the name of the speed boat.
	*@param maxSpeed the maximum speed of the speed boat.
	*@param acceleration the acceleration of the speed boat.
	*@param color the color of the speed boat.
	*/
	public SpeedBoat(String name, double maxSpeed, double acceleration, Color color)
	{
		super(name, maxSpeed, acceleration, color);
	}


	//										***	OVVERRIDE ABSTRACT METHODS FROM RACER CLASS ***
	
	
	/**
	*Returns the class name of the SpeedBoat.
	*@return the class name of the SpeedBoat.
	*/
	@Override
	public String className() { return SpeedBoat.CLASS_NAME; }

	
	/**
	*Returns a string that describes the specific attributes of the SpeedBoat,
	*including its BoatType and Team.
	*@return a string describing the specific attributes of the SpeedBoat.
	*/
	@Override
	public String describeSpecific() { return ", Type: " + getType() + ", Team: " + getTeam(); }

	
	//												***	SETTERS & GETTERS ***
	
	/**
	*Returns the BoatType of the SpeedBoat.
	*@return the BoatType of the SpeedBoat.
	*/
	public BoatType getType() { return this.type; }
	
	
	/**
	*Returns the Team of the SpeedBoat.
	*@return the Team of the SpeedBoat.
	*/
	public Team getTeam() { return this.team; }
	
	
	/**
	*Sets the BoatType of the SpeedBoat.
	*@param type the BoatType to set.
	*@return true if the BoatType was successfully set, false otherwise.
	*/
	public boolean setType(BoatType type) { this.type = type; return this.type.equals(type); }
	
	
	/**
	*Sets the Team of the SpeedBoat.
	*@param team the Team to set.
	*@return true if the Team was successfully set, false otherwise.
	 */
	public boolean setTeam(Team team) { this.team = team; return this.team.equals(team); }

	@Override
	public void run() {

	}
}
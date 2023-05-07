/*
 * Dor Yehoshua 315619098
 * Hadar Isaacson 209831262
 */
package utilities;


/**
*A class representing a point in 2D space.
*/
public class Point 
{
	
	
	//											*** FIELDS ***
	private static final int MAX_X = 1000000, MIN_X = 0, MAX_Y = 800, MIN_Y = 0;
	private double x, y;
	
	
	
	//											*** CONSTRUCTORS ***
	
	/**
	*Constructs a point at (0,0).
	*/
	public Point() { this(0, 0); }
	
	
	/**
	*Constructs a point at (x, y).
	*If x or y are out of range, sets them to 0.
	*@param x the x coordinate of the point
	*@param y the y coordinate of the point
	*/
	public Point(double x, double y)
	{
		if(!(this.setX(x))) this.setX(0);
		if(!(this.setY(y))) this.setY(0);
	}
	
	
	/**
	*Constructs a copy of a given point.
	*@param obj the point to be copied
	*/
	public Point(Point obj) {this(obj.x,obj.y);}
	
	

	
	//											*** METHODS ***
	
	/**
	*Returns a string representation of the point in the format "(x, y)".
	*@return a string representation of the point
	*/
	@Override 
	public String toString() {return String.format("(%.2f, %.2f)", this.x, this.y);}
	
	
	/**
	*Returns the x coordinate of the point.
	*@return the x coordinate of the point
	*/
	public double getX() { return this.x; }
	
	
	/**
	*Returns the y coordinate of the point.
	*@return the y coordinate of the point
	*/
	public double getY() {return this.y;}
	
	
	/**
	*Sets the x coordinate of the point.
	*If x is out of range, sets it to 0.
	*@param x the x coordinate to set
	*@return true if x was successfully set, false otherwise
	*/
	public boolean setX(double x) 
	{
		this.x = x;
		return this.x == x;
	}
	
	
	/**
	*Sets the y coordinate of the point.
	*If y is out of range, sets it to 0.
	*@param y the y coordinate to set
	*@return true if y was successfully set, false otherwise
	*/
	public boolean setY(double y) 
	{
		this.y = y;
		return this.y == y;
	}
}
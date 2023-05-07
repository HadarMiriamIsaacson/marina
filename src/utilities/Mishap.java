/*
 * Dor Yehoshua 315619098
 * Hadar Isaacson 209831262
 */
package utilities;

/** 
 *A class that represents a mishap that can occur in the game.
 *A mishap has a fixable status, a reduction factor, and a number of turns required to fix it.
 */
public class Mishap 
{
	
	//											*** FIELDS ***
	private boolean fixable;
	private double reductionFactor;
	private int turnsToFix;
	
	
	
	//											*** CONSTRUCTOR ***
	
	
	/**
	 * Constructor for a Mishap object.
	 * 
	 * @param fixable the fixable status of the mishap
	 * @param turnsToFix the number of turns required to fix the mishap
	 * @param reductionFactor the reduction factor of the mishap
	 */
	public Mishap(boolean fixable, int turnsToFix, double reductionFactor)
	{
		this.setFixable(fixable);
		this.setReductionFactor(reductionFactor);
		this.setTurnsToFix(turnsToFix);
	}
	//											*** SETTERS & GETTERS ***
	
	/**
	 * Gets the fixable status of the mishap.
	 * 
	 * @return the fixable status of the mishap
	 */
	public boolean getFixable() {return this.fixable;}
	
	

	/**
 	* Gets the reduction factor of the mishap.
 	* 
 	* @return the reduction factor of the mishap
 	*/
	public double getReductionFactor() {return this.reductionFactor;}
	
	
	/**
	 * Gets the number of turns required to fix the mishap.
	 * 
	 * @return the number of turns required to fix the mishap
	 */
	public int getTurnsToFix() {return this.turnsToFix;}
	
	
	/**
	 * Sets the fixable status of the mishap.
	 * 
	 * @param fixable the fixable status of the mishap
	 * @return true if the fixable status was successfully set, false otherwise
	 */
	public boolean setFixable(boolean fixable) {this.fixable = fixable; return this.fixable == fixable;}
	
	
	/**
	 * Sets the reduction factor of the mishap.
	 * 
	 * @param reductionFactor the reduction factor of the mishap
	 * @return true if the reduction factor was successfully set, false otherwise
	 */
	public boolean setReductionFactor(double reductionFactor) {this.reductionFactor = reductionFactor; return this.reductionFactor == reductionFactor;}
	
	
	/**
	 * Sets the number of turns required to fix the mishap.
	 * 
	 * @param turnsToFix the number of turns required to fix the mishap
	 * @return true if the number of turns was successfully set, false otherwise
	 */
	public boolean setTurnsToFix(int turnsToFix) {this.turnsToFix = turnsToFix; return this.turnsToFix == turnsToFix;}
	
	
	
	//												*** METHODS & OVERRIDES ***
	

	/**
 	* Decrements the number of turns required to fix the mishap if it is fixable.
 	*/
	public void nextTurn(){if (this.getFixable()) this.setTurnsToFix(this.getTurnsToFix() - 1);}
	
	
	/**
	 * Returns a string representation of the mishap object.
	 * 
	 * @return a string representation of the mishap object
	 */
	@Override
	public String toString() {return String.format("(%b, %d, %.2f)", this.getFixable(), this.getTurnsToFix(), this.getReductionFactor());}
}
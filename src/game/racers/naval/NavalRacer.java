/*
 * Dor Yehoshua 315619098
 * Hadar Isaacson 209831262
 */
package game.racers.naval;


/**
*The interface is a marker interface that indicates that a racer is capable of racing on water.
*This interface defines no methods of its own, but rather serves as a type for all naval racers.
*Any class that implements this interface must be able to participate in water-based races and must implement all the methods
*declared by its parent class or classes, as well as any additional methods specific to the implementing class.
*Implementing the interface allows a racer to be classified as a naval racer, which can be used to filter and
*distinguish it from other types of racers.
*/
public interface NavalRacer {}
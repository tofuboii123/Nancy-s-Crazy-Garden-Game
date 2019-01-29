//-----------------------------------------
// Assignment 4
// Written by: Weiliang Xie (40100475)
// For COMP 248 Section P - Fall 2018
//-----------------------------------------

// Class for Dice objects.
// This will generate the dice rolls randomly for each player.
// Each player will get 2 dice rolls and their overall roll is the sum of each roll.
// Methods will permit the user to know what each die's value was.
// These values will be used to evaluate who goes first and which event happens in other classes.

import java.util.Random;

public class Dice {
	
	private int value1; // Value for the first dice.
	private int value2; // Value for the second dice.
	public static Random randomRoll = new Random(); // Used to assign random values to the dice.
	
	// Default constructor.
	public Dice() {
		value1 = 0;
		value2 = 0;
	}
	
	// Accessor for the value of the first dice.
	public int getFirstRoll() {
		return value1;
	}
	
	// Accessor for the value of the second dice.
	public int getSecondRoll() {
		return value2;
	}

	// Rolls both dice and returns the sum.
	public int rollDice() {
		
		int sum = 0;
		
		value1 = randomRoll.nextInt(6) + 1;
		value2 = randomRoll.nextInt(6) + 1;
		
		sum = value1 + value2;
		
		return sum;
	}
	
	// Prints the value of each of the dice.
	public String toString() {
		return (" (First die: " + getFirstRoll() + " ; Second die: " + getSecondRoll()) + ")";
	}

}

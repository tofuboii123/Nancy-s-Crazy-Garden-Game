//-----------------------------------------
// Assignment 4
// Written by: Weiliang Xie (40100475)
// For COMP 248 Section P - Fall 2018
//-----------------------------------------

// Class for the Player objects.
// Players have a name and a garden.
// The player has multiple methods that access their own garden.
// That way it is less tedious to go through the gardens, all we need to do is pass by the player without having to directly access his garden.
// Depending on their rolls, different events happen (diceEvent()).
// Each player will roll until there is a final winner.

public class Player {
	
	private String name = "";
	private Garden garden;
	
	// Constructor which takes the name of the player and the size of his garden.
	public Player(String name, int size) {
		this.name = name;
		garden = new Garden(size);
	}
	
	// Accessor for the player's name.
	public String getName() {
		return(name);
	}
	
	// Returns the number of possible places to plant flowers.
	public int howManyFlowersPossible() {
		return(garden.countPossibleFlowers());
	}
	
	// Same idea, but this time with trees.
	public int howManyTreesPossible() {
		return(garden.countPossibleTrees());
	}
	
	// Returns what is planted.
	public char whatIsPlanted(int r, int c) {
		return(garden.getInLocation(r, c));
	}
	
	// Allows the player to plant a flower.
	public void plantFlowerInGarden(int r, int c) {
		garden.plantFlower(r, c);
	}
	
	// Allows the player to plant a tree.
	public void plantTreeInGarden(int r, int c) {
		garden.plantTree(r, c);
	}
	
	// Removes whatever was in that tile.
	public void eatHere(int r, int c) {
		garden.removeFlower(r, c);
	}
	
	// Tells the player whether his garden is full or not.
	public boolean isGardenFull() {
		return(garden.gardenFull());
	}
	
	// Prints out the player's garden.
	public void showGarden() {
		System.out.println(garden.toString());
	}
	
	// Returns whether the garden is empty or not.
	public boolean isGardenEmpty() {
		return (garden.gardenEmpty());
	}
	
	// This evaluates what each player's next move is depending on their dice roll.
	public void diceEvent(int eventNum) {
		
		int row = 0; // Used to determine which tile the event will take place on.
		int column = 0;
		//boolean valid = garden.validTile(row, column);
		
		switch(eventNum) {
		
		// If the user rolls a 3, they plant a tree AND a flower.
		case 3:
			System.out.println("\nYou have to plant a flower and a tree!");
			
			showGarden();
			
			// Tells the player how many possibilities they have to plant a flower.
			System.out.println("\nYou have " + howManyFlowersPossible() + " available spots to plant a flower.");
			
			// If there are no more available spots after the last flower, that means the player has won.
			if(howManyFlowersPossible() == 0)
				break;
			
			// Loops if the coordinates are out of bounds or if the tile isn't empty.
			do {
				System.out.print("Please input the coordinates for your flower (row followed by column): ");
				
				LetsPlay.checkInt(); // Checks if the input is an integer.
				row = LetsPlay.keyboard.nextInt();
				
				LetsPlay.checkInt(); // Checks if the input is an integer.
				column = LetsPlay.keyboard.nextInt();
			
			}while(!garden.inBounds(row, column) || !garden.validFlowerTile(row, column));
			
			plantFlowerInGarden(row, column); // Plants a flower.
			
			showGarden(); // Shows the user where he chose to plant.
			
			// If there are no more available spots, the player's turn is skipped.
			if(howManyTreesPossible() == 0)
			{
				// If they win, the game ends directly.
				if(isGardenFull())
					break;
				else
				{
					System.out.println("Your garden is too full to plant a tree! Too bad... On to the next player!");
					break;
				}
			}
			
			// Tells the user how many possibilities they have to plant a tree.
			System.out.println("\nYou have " + howManyTreesPossible() + " available spot(s) to plant a tree.");
			
			// Loops if the coordinates are out of bounds or if the tile and adjacent ones aren't empty.
			do {
				System.out.print("Please input the coordinates for your tree (top left row followed by column): ");
				
				LetsPlay.checkInt(); // Checks if the input is an integer.
				row = LetsPlay.keyboard.nextInt();
				
				LetsPlay.checkInt(); // Checks if the input is an integer.
				column = LetsPlay.keyboard.nextInt();
				
			}while(!garden.inBounds(row + 1, column + 1) || !garden.validTreeTile(row, column));
			
			plantTreeInGarden(row, column); // Plants a tree.
				
			showGarden();
			break;
			
		// If the user rolls a 6, they have to plant 2 flowers.
		case 6:
			System.out.println("\nYou have to plant 2 flowers!");
			
			showGarden();
			
			// Asks the user to plant flowers twice.
			for(int i = 1; i <= 2; i++) {
				
				// If there are no more available spots after the last flower, that means the player has won.
				if(howManyFlowersPossible() == 0)
					break;
				
				// Prints the number of available tiles.
				System.out.println("You have " + howManyFlowersPossible() + " available spot(s) to plant a flower.");
				
				
				// Loops if the coordinates are out of bounds or if the tile isn't empty.
				do {
					System.out.print("Please input the coordinates for flower number " + i + " (row followed by column): ");
					
					LetsPlay.checkInt(); // Checks if the input is an integer.
					row = LetsPlay.keyboard.nextInt();
					
					LetsPlay.checkInt(); // Checks if the input is an integer.
					column = LetsPlay.keyboard.nextInt();
				
				}while(!garden.inBounds(row, column) || !garden.validFlowerTile(row, column));
				
				plantFlowerInGarden(row, column); // Plants a flower.
					
				showGarden();
			}
			break;
		
		// If the user rolls a 12, they have to plant 2 trees.
		case 12:
			System.out.println("\nYou have to plant 2 trees!");
			
			showGarden();
			
			// Asks the user to plant trees twice.
			for(int i = 1; i <= 2; i++) {
				
				// If there are no more available spots, the player's turn is skipped.
				if(howManyTreesPossible() == 0)
				{
					// If they win, the game ends directly.
					if(isGardenFull())
						break;
					else
					{
						System.out.println("Your garden is too full to plant a tree! Too bad... On to the next player!");
						break;
					}
				}
				
				// Prints the number of available tree planting spots.
				System.out.println("\nYou have " + howManyTreesPossible() + " available spots to plant a tree.");
				
				// Loops if the chosen tile is out of bounds or if the chosen tile and its adjacent tiles are occupied.
				do {
					System.out.print("Please input the coordinates for tree number " + i + " (top left row followed by column): ");
					
					LetsPlay.checkInt(); // Checks if the input is an integer.
					row = LetsPlay.keyboard.nextInt();
					
					LetsPlay.checkInt(); // Checks if the input is an integer.
					column = LetsPlay.keyboard.nextInt();
					
				}while(!garden.inBounds(row + 1, column + 1) || !garden.validTreeTile(row, column));
				
				plantTreeInGarden(row, column);
					
				showGarden();
			}
			break;
			
		// If the user rolls a 5 or a 10, the rabbit removes a filled tile.
		case 5:
		case 10:
			boolean edible = false; // Indicates whether the tile can be eaten or not.
			
			showGarden();
			
			// Loops until there is a valid, edible tile.
			do {
				row = Dice.randomRoll.nextInt(garden.getGardenSize()); // Random row.
				column = Dice.randomRoll.nextInt(garden.getGardenSize()); // Random column.
				
				if(isGardenEmpty() == true) // If the garden is empty, nothing can be eaten, so exit.
				{
					System.out.println("The rabbit came, but your garden was empty! It leaves sad and hungry...");
					edible = true;
				}
				else if(whatIsPlanted(row, column) == '-') // If the random tile is empty, loop again.
					edible = false;
				else // Eat whatever is in the tile and stop the loop!
				{
					System.out.println("A hungry rabbit ate whatever was planted at (" + row + ", " + column +")");
					eatHere(row, column);
					edible = true;
				}
				
			}while(edible == false);
			
			showGarden();
			break;
		
		// If the user rolls any other even number, they plant a tree.
		case 2:
		case 4:
		case 8:
			System.out.println("\nYou have to plant a tree!");
			
			showGarden();
			
			// If there are no more available spots, the player's turn is skipped.
			if(howManyTreesPossible() == 0)
			{
				// If they win, the game ends directly.
				if(isGardenFull())
					break;
				else
				{
					System.out.println("Your garden is too full to plant a tree! Too bad... On to the next player!");
					break;
				}
			}
			
			// Prints the number of available tree planting spots.
			System.out.println("\nYou have " + howManyTreesPossible() + " available spot(s) to plant a tree.");
			
			// Loops if the coordinates are out of bounds or if the tile and its adjacent ones aren't empty.
			do {
				System.out.print("Please input the coordinates for your tree (top left row followed by column): ");
				
				LetsPlay.checkInt(); // Checks if the input is an integer.
				row = LetsPlay.keyboard.nextInt();
				
				LetsPlay.checkInt(); // Checks if the input is an integer.
				column = LetsPlay.keyboard.nextInt();
				
			}while(!garden.inBounds(row + 1, column + 1) || !garden.validTreeTile(row, column));
				
			plantTreeInGarden(row, column); // Plants a tree.
			
			showGarden();
			break;
		
		// Everything else (all other odd numbers) means the player plants a flower.
		default:
			System.out.println("\nYou have to plant a flower!");
			
			showGarden();
			
			// Prints the number of available flower planting spots.
			System.out.println("\nYou have " + howManyFlowersPossible() + " available spot(s) to plant a flower.");
			
			// Loops if the coordinates are out of bounds or if the tile isn't empty.
			do {
				System.out.print("Please input the coordinates for your flower (row followed by column): ");
				
				LetsPlay.checkInt(); // Checks if the input is an integer.
				row = LetsPlay.keyboard.nextInt();
				
				LetsPlay.checkInt(); // Checks if the input is an integer.
				column = LetsPlay.keyboard.nextInt();
			
			}while(!garden.inBounds(row, column) || !garden.validFlowerTile(row, column));
			
			plantFlowerInGarden(row, column); // Plants a flower.
			
			showGarden();
			break;
		}
		
	}
}

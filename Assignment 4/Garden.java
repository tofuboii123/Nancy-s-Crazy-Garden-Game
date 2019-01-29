//-----------------------------------------
// Assignment 4
// Written by: Weiliang Xie (40100475)
// For COMP 248 Section P - Fall 2018
//-----------------------------------------

// Class for the Grid (Garden) of the game.
// This will take care of initializing each garden with the appropriate size and the appropriate characters.
// There are also methods to count the number of possibilities left to plant flowers and trees.
// There is also a method which removes 1 square tile (the rabbit).
// Methods for bound-checking and tile-checking are also included as anti-break measures.
// There is an extra method to check if the garden is empty or not.

public class Garden {
	
	private char[][] garden; // To draw the grid.
	
	private void initializeGarden() {
		
		// Fills the garden with '-'.
		for(int i = 0; i < garden.length; i++) {
			for(int j = 0; j < garden[i].length; j++) {
				garden[j][i] = '-';
			}
		}
	}
	
	// Default constructor for the grid.
	public Garden() {
		garden = new char[3][3];
		
		initializeGarden();
	}
	
	// Constructor with a custom size.
	public Garden(int size) {
		garden = new char[size][size];
		
		initializeGarden();
	}
	
	// Gets the row and column chosen.
	public char getInLocation(int r, int c) {
		return (garden[r][c]);
	}
	
	
	// Plants a flower at the chosen row and column.
	public void plantFlower(int r, int c) {
		garden[r][c] = 'f';
	}
	
	// Plants a tree at the chosen row and columns (and the adjacent ones because trees are 2x2).
	public void plantTree(int r, int c) {
		garden[r][c] = 't';
		garden[r + 1][c] = 't';
		garden[r][c + 1] = 't';
		garden[r + 1][c + 1] = 't';
	}
	
	// Removes a flower from the chosen row and column.
	public void removeFlower(int r, int c) {
		garden[r][c] = '-';
	}
	
	// Outputs the number of possible ways to plant a tree.
	public int countPossibleTrees() {
		int treePossibilities = 0;
		
		// Checks the possibility for each row and column.
		for(int i = 0; i < (garden.length - 1); i++) {
			for(int j = 0; j < (garden[i].length - 1); j++) {
				
				// Checks to see if every character in a 2x2 space is '-' (i.e. free to plant a tree).
				if((garden[i][j] == '-') && (garden[i + 1][j] == '-') && (garden[i][j + 1] == '-') && (garden[i + 1][j + 1] == '-'))
					treePossibilities++;
			}
		}
		
		return treePossibilities;
	}
	
	public int countPossibleFlowers() {
		int flowerPossibilities = 0;
		
		// Checks every row and column for a possibility.
		for(int i = 0; i < garden.length; i++) {
			for(int j = 0; j < garden[i].length; j++) {
				
				// Checks if a tile is empty. If so, there is a possibility to plant a flower.
				if(garden[i][j] == '-')
					flowerPossibilities++;
			}
		}
		
		return flowerPossibilities;
	}
	
	public boolean gardenFull() {
		int filledTile = 0;
		
		// Checks every tile of every row and every column to see if it's filled.
		for(int i = 0; i < garden.length; i++) {
			for(int j = 0; j < garden[i].length; j++) {
				
				// Every time a filled tile is encountered, the number of filled tiles increments.
				if(garden[i][j] != '-')
					filledTile++;
			}
		}
		
		// If the number of filled tiles is equal to the number of overall tiles, then the grid is filled.
		return (filledTile == (garden.length*garden[0].length));
	}
	
	public String toString() {
		
		// New array to include the grid and the garden.
		String[][] grid = new String[garden.length + 1][garden[0].length + 1];
		
		// Fills the first row of the grid with the correct index.
		for(int j = 0; j < grid[0].length; j++) {
			if(j == 0)
				grid[0][j] = "  |";
			else
				grid[0][j] = (" " + (j - 1));
			}
		
		// Fills the first column with the right index and fills in the rest of the empty spaces with the garden grid.
		for(int i = 1; i <= garden.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				if(j == 0)
					grid[i][j] = ((i - 1) + " |");
				else if(j > 0)
					grid[i][j] = (" " + garden[i - 1][j - 1]);
			}
		}
		
		// The method returns a String.
		String printGrid = "";
		
		// Make our array into a String that can be returned.
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				printGrid += grid[i][j];
			}
			
			printGrid += "\n";
		}
		
		return ("\n" + printGrid);
	}
	
	// Determines whether the chosen tile is valid or not.
	public boolean inBounds(int r, int c) {
		
		// If the chosen row and column are out of bounds, it is invalid.
		if(r >= garden[0].length || c >= garden.length)
		{
			System.out.println("That spot is outside of your garden! Please try again.");
			return false;
		}
		// Other than that, it is a valid input.
		else
			return true;
		
	}
	
	// Checks whether it is possible to plant a flower.
	public boolean validFlowerTile(int r, int c) {
		
		// If the chosen tile is already occupied, it is an invalid tile.
		if(garden[r][c] != '-')
		{
			System.out.println("There is already something planted there! Choose another spot.");
			return false;	
		}
		else
			return true;
	}
	
	// Checks whether it is possible to plant a tree.
	public boolean validTreeTile(int r, int c) {
		if(garden[r][c] != '-' || garden[r + 1][c] != '-' || garden[r][c + 1] != '-' || garden[r + 1][c + 1] != '-')
		{
			System.out.println("There is already something planted there! Choose another spot.");
			return false;
		}
		else
			return true;
	}
	
	// Makes the garden size accessible.
	public int getGardenSize() {
		return (garden.length);
	}
	
	// This returns true if the garden is completely empty.
	// It will be useful for the rabbit event.
	public boolean gardenEmpty() {
		int emptyTiles = 0;
		
		// Loop through each character in the garden. Increment each time the tile is empty.
		for(int i = 0; i < garden.length; i++) {
			for(int j = 0; j < garden[0].length; j++) {
				if(garden[i][j] == '-')
					emptyTiles++;
			}
		}
		
		return (emptyTiles == (garden.length*garden[0].length));
	}
	
}

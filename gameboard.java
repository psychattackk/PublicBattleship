/*This file will contain code to build the gameboard, placement of pieces,  
 * tracking score, and other code for the game.*/
import java.util.Random; 
import java.util.Scanner;

public class SheldonMcClish_gameboard
{
	Random rand = new Random();
	Scanner input = new Scanner(System.in);
	
	// Strings symbols for the game board.
	String EMPTY = "~";
	String MISS = "o";
	String OCCUPIED = "X";
	
	//Array of ships
	initializeShips[] SHIPS = new initializeShips[5];
	
	// Array of Difficulty Levels with board's Size and Missiles
	private int[] BEGINNER = {6, 30};
	private int[] STANDARD = {9, 50};
	private int[] ADVANCED = {12, 75};

	// Public Variables
	public int[][] BOARD;
	public int difficulty, size, missiles = 0, shipsLeft = 5;
	
	
	// Method for receiving player difficulty.
	public void getDifficulty()
	{
		difficulty = input.nextInt();
		
		// Check for Valid input.
		while (difficulty != 1 && difficulty != 2 && difficulty != 3)
		{
			System.out.print("\nWhy would you enter something other than 1-3?\n\nChoose your difficulty(1-3): ");
			difficulty = input.nextInt();
		}
		
		//Set Difficulty from user's choice. 
		switch(difficulty)
		{
		case 1: // easy
			System.out.println("\nDIFFICULTY: JEDI PADAWAN\n\nYou have much to learn...\n");
			size = BEGINNER[0];
			missiles = BEGINNER[1];
			break;
		case 2: // standard
			System.out.println("\nDIFFICULTY: JEDI KNIGHT\n\nPerhaps you are not as strong as the Emporer thought...\n");
			size = STANDARD[0];
			missiles = STANDARD[1];
			break;
		case 3: // advanced
			System.out.println("\nDIFFICULTY: JEDI MASTER\n\nThe Force is strong with this one!\n");
			size = ADVANCED[0];
			missiles = ADVANCED[1];
			break;
		}
		
		setBoard();
		initializeBoard(); 
	} // end getDifficulty()
	
	// Class to initialize setting ships
	public class initializeShips 
	{
		String name = "";
		String letter = "";
		int shipLength = 0;
		int value = 0;
		
	} // end class initializeShips
	
	// Set all SHIPS using initializeShips class, respectively (inspired by Nick) 
	public void setShips()
	{
		SHIPS[0] = new initializeShips();
		SHIPS[1] = new initializeShips();
		SHIPS[2] = new initializeShips();
		SHIPS[3] = new initializeShips();
		SHIPS[4] = new initializeShips();
		
		SHIPS[0].name = "DEATH STAR";       // Aircraft Carrier name
		SHIPS[1].name = "MILLINEUM FALCON"; // Battleship name
		SHIPS[2].name = "STAR DESTROYER";   // Destroyer name
		SHIPS[3].name = "SITH INFILTRATOR"; // Submarine name
		SHIPS[4].name = "TIE FIGHTER";      // Patrol Boat name
		
		// Length of each ship.
		SHIPS[0].shipLength = 5;
		SHIPS[1].shipLength = 4;
		SHIPS[2].shipLength = 4;
		SHIPS[3].shipLength = 3;
		SHIPS[4].shipLength = 2;
		
		SHIPS[0].letter = "A"; // Aircraft Carrier symbol ("DEATH STAR")
		SHIPS[1].letter = "B"; // Battleship symbol ("MILLINEUM FALCON")
		SHIPS[2].letter = "D"; // Destroyer symbol ("STAR DESTROYER")
		SHIPS[3].letter = "S"; // Submarine symbol ("SITH INFILTRATOR")
		SHIPS[4].letter = "P"; // Patrol boat symbol ("TIE FIGHTER")
		
		SHIPS[0].value = 1;
		SHIPS[1].value = 2;
		SHIPS[2].value = 3;
		SHIPS[3].value = 4;
		SHIPS[4].value = 5;
		
	} 
	
	// Get size of board _ x _.
	public int getSize()
	{
		return this.size;
	}
	
	// Set board size.
	public void setBoard()
	{
		this.BOARD = new int [size][size];
	}
	
	// Get missile count.
	public int getMissiles()
	{
		return this.missiles;
	}
	
	// Missiles Shot
	public int missilesShot()
	{
		return this.missiles--;
	}
	
	// Print Row Letters by converting to ASCII characters.
	public char printRowLetters(int i)
    {
	  return (char) (i + 64);
	}
	
	// Initialize the board to empty cell values.
	public void initializeBoard()
	{
		for (int row = 0; row < getSize(); row++)
		{
			for (int col = 0; col < getSize(); col++)
				BOARD[row][col] = -1;
		}
	}
	
	// Print board size, missiles left, and ships left.
	public void scoreBoard()
	{
		System.out.printf("BOARD SIZE = %dx%d%nMISSILES LEFT = %d%nSHIPS LEFT = %d%n", 
				getSize(), getSize(),
				getMissiles(), this.shipsLeft;
	}
	
	// Print Battleship board.
	public void printBoard()
	{
		scoreBoard(); 
        System.out.println();
        
     		System.out.print("\n  ");
     		
     		// Print Column Numbers
     		for (int i = 0; i < getSize(); i++) 
     		{
     			System.out.printf("%4d", i + 1);	
     		}
     		
     		for (int row = 0; row < getSize(); row++) 
     		{
     			System.out.print("\n");
     			
     			// Print Row Letters
     			System.out.printf("%3c", printRowLetters(row + 1));
     			
     			for (int col = 0; col < getSize(); col++) 
     			{
     				if (BOARD[row][col] == -1)
     					System.out.printf("%3s", EMPTY); // ~
     				if (BOARD[row][col] == 0)
     					System.out.printf("%3s", MISS); // o
     				if (BOARD[row][col] == 6)
     					System.out.printf("%3s", OCCUPIED); // X
     					
     				// Hide ships from player by making ship cells appear to be empty.
        			if (BOARD[row][col] == 1)
        					System.out.printf("%3s", EMPTY);
        			if (BOARD[row][col] == 2)
        					System.out.printf("%3s", EMPTY);
        			if (BOARD[row][col] == 3)
        					System.out.printf("%3s", EMPTY);
        			if (BOARD[row][col] == 4)
        					System.out.printf("%3s", EMPTY);
        			if (BOARD[row][col] == 5)
        					System.out.printf("%3s", EMPTY);
    			
     				System.out.print("|");
     			}
     			System.out.println();
        }
	}

	
	// Method to randomly place ships
	public void addShips() 
	{
		setShips();
		int size = getSize();
		int ship = 1; // Start with first ship (Aircraft Carrier)
			
		for (int i = 0; i < SHIPS.length; i++) 
		{
			boolean placed = false;
				
			while (!placed) 
			{
				//true = horizontal, false = vertical
			boolean horizontal = rand.nextBoolean();
				
			int row = rand.nextInt(size);
			int col = rand.nextInt(size);
				
			// If space is not empty, restart the while-loop
			if (BOARD[row][col] != -1)
				continue;

			// Horizontal placement
			if (horizontal) 
			{ 
			// If random direction is OUT OF BOUNDS, restart while-loop.
			   if (row + SHIPS[i].shipLength > size) 
				{
					continue;
				}
				// Else if ship is IN BOUNDS
				else 
				{
					// Prevent overlapping ships
					boolean freeSpace = true;
					
					for (int j = 0; j < SHIPS[i].shipLength; j++) 
						{
							if (BOARD[row + j][col] != -1) 
							{
								freeSpace = false;
							}
						}
					
				    if (!freeSpace)
				      continue; 
				    
				 // Once ship is IN BOUNDS AND no OVERLAPPING, place ship on board.
				    for (int j = 0; j < SHIPS[i].shipLength; j++) 
				    {
						BOARD[row + j][col] = ship;
					}
				    // Once ship is placed, change placed to true. Go forward with for-loop
				    placed = true;
				  } 
				} // end Horizontal placement
				
				// If ship can't place horizontally, then place vertically.
				else 
				{ // Vertical placement	   
					if (col + SHIPS[i].shipLength > size) 
					{
						continue;
					}

					else 
					{
						boolean freeSpace = true;

						for (int j = 0; j < SHIPS[i].shipLength; j++) 
						{
							if (BOARD[row][col + j] != -1)
								freeSpace = false; 
						}

						if (!freeSpace)
							continue; 

						for (int j = 0; j < SHIPS[i].shipLength; j++) 
						{	
							BOARD[row][col + j] = ship;
						}	
				  
						placed = true;
					}
				  } //end Vertical placement
			} // end while-loop
			ship++; // Next ship 
		} // end nested for-loop
	} // end addShips
		
	// Cheat board (cheat sheet)
	public void cheatSheet()
	{
		scoreBoard();
		
        System.out.println();
        
     		System.out.print("\n  ");
     		
     		// Print Column Numbers
     		for (int i = 0; i < getSize(); i++) 
     		{
     			System.out.printf("%4d", i + 1);	
     		}
     		
     		for (int row = 0; row < getSize(); row++) 
     		{
     			System.out.print("\n");
     			
     			// Print Row Letters
     			System.out.printf("%3c", printRowLetters(row + 1));
     			
     			for (int col = 0; col < getSize(); col++) 
     			{
     				if (BOARD[row][col] == -1)
     					System.out.printf("%3s", EMPTY);
     				if (BOARD[row][col] == 0)
     					System.out.printf("%3s", MISS);
     				if (BOARD[row][col] == 6)
     					System.out.printf("%3s", OCCUPIED);
     					
     				// Shows ship locations.
        			if (BOARD[row][col] == 1)
        					System.out.printf("%3s", SHIPS[0].letter);
        			if (BOARD[row][col] == 2)
        					System.out.printf("%3s", SHIPS[1].letter);
        			if (BOARD[row][col] == 3)
        					System.out.printf("%3s", SHIPS[2].letter);
        			if (BOARD[row][col] == 4)
        					System.out.printf("%3s", SHIPS[3].letter);
        			if (BOARD[row][col] == 5)
        					System.out.printf("%3s", SHIPS[4].letter);
    				
     				System.out.print("|");
     			}
     			System.out.println();
        }
	} // end cheatSheet()
	
} // end class gameboard.java


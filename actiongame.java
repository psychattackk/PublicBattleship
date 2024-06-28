/*This file will be running the game.*/

import java.util.Scanner;

public class SheldonMcClish_actiongame
{	

	SheldonMcClish_gameboard board = new SheldonMcClish_gameboard();
	Scanner input = new Scanner(System.in);

	// Variables
	private boolean win;
	private String name;
	private String[] rowLetters = { "","A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};
	private double hits = 0, moves = 0, accuracy;
	
	public int missilesShot = board.getMissiles();
	
	// Run Game
	public void runGame()
	{
		menuIntro();
		board.getDifficulty();
		board.addShips();
		board.printBoard();
		guessShips();
	}
	
	// Get player name, just for fun.
	public String getName()
	{
		return name;
	}
	
	
	// Intro to the Game.
	public void menuIntro()
	{	
		System.out.println("  **********************************");
		System.out.println("  *      ********************      *");
		System.out.println("  *      STAR WARS BATTLESHIP!     *");
		System.out.println("  *      ********************      *");
		System.out.println("  **********************************");
		System.out.print("ENTER YOUR FIRST OR LAST NAME, JEDI: ");
		name = input.next();
		name = getName().toUpperCase();
		System.out.println("\nDIFFICULTY LEVELS TO CHOOSE FROM, YOUNG " + name + "\n");
		System.out.println("*************************************************************");
		System.out.println("* 1 - Jedi PADAWAN " + name.toUpperCase() + " (easy) - 6x6 - 30 Missiles     *");
		System.out.println("* 2 - Jedi KNIGHT " + name.toUpperCase() + " (standard) - 9x9 - 50 Missiles  *");
		System.out.println("* 3 - Jedi MASTER " + name.toUpperCase() + " (advanced) - 12x12 - 75 Missiles*");
		System.out.println("*************************************************************");
		System.out.printf("\nNow, YOUNG " + name + ", Enter your difficulty (1-3): ");
	} // end menuIntro()
	
	
	// Get Row guess from player.
	public int getRowGuess()
	{ 
		int rowGuess = 0;
		System.out.print("\nEnter the Row LETTER in which to fire: ");
	    String letter = input.next();         
	    letter = letter.toUpperCase();
	    for (int i = 1; i <= 12; i++)
			{if (rowLetters[i].equals(letter)) // Convert to String letter from rowLetters array.
				rowGuess = i - 1; //subtract 1 for accurate letter.
			}
	    return rowGuess;
	} // end getRowGuess()

	// Get Column guess from player.
	public int getColumnGuess()
	{
		int columnGuess = 0;
		System.out.print("Enter the Column NUMBER in which to fire: ");
		columnGuess = input.nextInt() - 1;
		
		// Test for valid input
		while(columnGuess > board.getSize())
		{
			System.out.print("Do you see that Column number anywhere on the grid? Didn't think so try again: ");
			columnGuess = input.nextInt();
		}
		return columnGuess;
	} // end getColumnGuess()
	
	// Guess coordinates on BOARD to hit and miss ships.
	public void guessShips()
	{
		do {
			win = false;
			moves++;
			int row = getRowGuess();
			int col = getColumnGuess();
			hitOrMiss(row, col);
			
			board.missilesShot(); // Decrement missiles for each move.
			
			System.out.println();
			System.out.printf("YOUR ACCURACY = %.2f%%%n", getAccuracy());
			board.printBoard();
			//board.cheatSheet(); // Uncomment this for cheat sheet. 
			
			if (board.shipsLeft == 0)
			{
				System.out.printf("%nYou defeated ALL of their ships! "
						+ "%nYOU WON!%n%nYOUNG %s is the NEW LEADER OF THE REBEL ALLIANCE!%n%n%n", name);
				win = true;
			}
			else if (board.getMissiles() == 10) // Give player a warning when they are down to 10 missiles left.
			{
				System.out.println("\n\nWARNING: 10 MISSILES LEFT... USE THEM WISELY WITH THE FORCE..");
			}
			//If missiles run out, win = true, but player lost.
			else if (board.getMissiles() == 0)
			{
				System.out.println("\n\nYou ran out of missiles! YOU LOST!!!!\n\n");
				win = true;
			}
		
		}while(!win); // Loop until game ends.
		
	} // end guessShips()
	
	// Check player's guess for ship hit/sunk or miss.
	public void hitOrMiss(int row, int column)
	{
		// If Aircraft Carrier is hit:
		if (board.BOARD[row][column] == 1)
		{
			int destroyed = board.SHIPS[0].shipLength--; // Decrement shipLength for each hit until ship is destroyed.
			hits++;
			System.out.printf("%nYou've hit the %s! Keep it up!%n", board.SHIPS[0].name); // Let the player know the ship name they've hit.
			
			board.BOARD[row][column] = 6; //OCCUPIED = 6
			
			if (destroyed == 1) // If shipLength - 1 = 0, destroyed. 
			{
			System.out.printf("%nYOU HAVE DESTROYED THE %s (Aircraft Carrier)!! HOW DO YOU LIKE %s'S SKILLS, VADER!",
					board.SHIPS[0].name, getName());
			board.shipsLeft--; //Aircraft Carrier destroyed. 
			}
		}
		// If Battleship is hit:
		else if (board.BOARD[row][column] == 2)
		{
			int destroyed = board.SHIPS[1].shipLength--;
			hits++;
			System.out.printf("%nYou've hit the %s! Keep it up!%n", board.SHIPS[1].name);
			
			board.BOARD[row][column] = 6; // occupied
					if(destroyed == 1)
					{
						System.out.printf("%nYOU HAVE DESTROYED THE %s (Battleship)!!%n Wait isn't that our ship? Oh well it still counts!%n",
								board.SHIPS[1].name);
						board.shipsLeft--; //Battleship destroyed
					}
		}
		// If Destroyer is hit:
		else if (board.BOARD[row][column] == 3)
		{
			int destroyed = board.SHIPS[2].shipLength--;
			hits++;
			System.out.printf("%nYou've hit the %s! Keep it up!%n", board.SHIPS[2].name);
			board.BOARD[row][column] = 6; //occupied
			
			if (destroyed == 1)
			{
				System.out.printf("%nYOU HAVE DESTROYED THE %s (Destroyer)!!%n", board.SHIPS[2].name);
				board.shipsLeft--; //Destroyer destroyed
			}
		}
		// If Submarine is hit: 
		else if (board.BOARD[row][column] == 4)
		{
			int destroyed = board.SHIPS[3].shipLength--;
			hits++;
			System.out.printf("%nYou've hit the %s! Keep it up!%n", board.SHIPS[3].name);
			board.BOARD[row][column] = 6; //occupied
			if(destroyed == 1)
			{
				System.out.printf("%nYOU HAVE DESTROYED THE %s (Submarine)!!%n", board.SHIPS[3].name);
				board.shipsLeft--; //Submarine destroyed
			}
		}
		// If Patrol is hit:
		else if (board.BOARD[row][column] == 5)
		{
			int destroyed = board.SHIPS[4].shipLength--;
			hits++;
			System.out.printf("%nYou've hit the %s! Keep it up!%n", board.SHIPS[4].name);
			board.BOARD[row][column] = 6; //occupied
			if (destroyed == 1)
			{
				System.out.printf("%nYOU HAVE DESTROYED THE %s (Patrol)!!%n", board.SHIPS[4].name);
				board.shipsLeft--; //Patrol destroyed
			}
			
		}
		else if (board.BOARD[row][column] == -1)
			{
				System.out.printf("%nYou missed! WTF?! USE THE FORCE, %s%n", name);
				board.BOARD[row][column] = 0;
			}
		// If same space is hit.
				else if (board.BOARD[row][column] == 0 || board.BOARD[row][column] == 6) 
					{
						System.out.print("\nYou already tried that spot! What's wrong with you?\n");
					}
	} // end hitOrMiss
	
	// Get player accuracy. (hits / missiles fired)
	public double getAccuracy() 
	{
		if (moves == 0)
			accuracy = 0.00;
		else
			accuracy = ((hits / moves) * 100);
		return this.accuracy;
	}

}// end class actiongame.java
	
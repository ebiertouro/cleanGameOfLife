package gameOfLife;
import java.util.*;


public class Main {
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		int stage = 1;
		System.out.println("Welcome to the Game of Life simulation.");
		System.out.println("Enter a board size between 20 and 30: ");
		
		int size = input.nextInt();
		while (size < 20 || size > 30) {
			System.out.println("Invalid size.");
			System.out.println("Enter a board size between 20 and 30: ");
			size = input.nextInt();
		}
		GameOfLife game = new GameOfLife(size);
		game.displayBoard();
		
		game.generateAliveStarters();
		System.out.println("Our simulation will randomly generate which " + game.getAliveStarters() 
		+" cells start out as 'alive.' I will then display the first 20 generations (if our cells"
		+ "live that long!)");
		game.generateAlive();
		game.displayBoard();
		

		while (game.keepPlaying() && stage < 21) {
			System.out.println("Now I'll show you stage #" + stage);
		game.keepPlaying();
		game.displayBoard();
		stage++;
		}
		System.out.println("Game over! Thanks for playing");
		input.close();
	}

}

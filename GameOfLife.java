package gameOfLife;

import java.util.Random;

public class GameOfLife {
	
	public int size;
	Cell[][] board;
	int aliveStarters;
	CircularQ<Cell> queue = new CircularQ<Cell>();
	public Cell cell;
	
	//instantiate a board of the given size and 
	//fill it up with cells. the cells are all instantiated as dead
	public GameOfLife(int size) {
		this.size = size;
		board = new Cell[size][size];
		
		for(int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++){
				cell = new Cell(i, j);
				board[i][j] = cell;
			}}
	}

	//loop through the board to print
	public void displayBoard() {
		String str = "";
		for (int k = 0; k < size; k++)
			str += "--";
		
		System.out.println("\n"+ str);
		for(int i = 0; i < size; i++) {
			System.out.print("|");
			for (int j = 0; j < size; j++){
				System.out.print(board[i][j] + "|");
		}
			System.out.print("\n" + str + "\n");
			}
		System.out.println();
	}
	
	public void generateAliveStarters() {
		Random random = new Random();
		aliveStarters = random.nextInt((size*5), (size*10));
	}
	
	public int getAliveStarters() {
		return aliveStarters;
	}
	
	//given the number of cells which will be alive, we randomly place them in the board
	public void generateAlive() {
		
	for (int i = 0; i < aliveStarters; i++){
			Random random = new Random();
			board[random.nextInt(size)][random.nextInt(size)].setAlive(true);
		}
			}
	
	public boolean keepPlaying() {
		boolean keepPlaying = true;
		checkAll();
		//we stop the game when there are no more cells to change 
		//(also an option in main to stop after 20 rounds
		if (queue.isEmpty()) {
			keepPlaying = false;
		}
		for (int k = 0; k < queue.size(); k++) {
			Cell d = queue.dequeue();
			if (d.getAlive()) {
				d.setAlive(false);
			}
			else
				d.setAlive(true);
		}
		return keepPlaying;
		
	}
	
	//we check if there are cells that must be updated by looping through the
		//board and passing in each cell with its neighbors
		//we wrap the board by going back to "size" if we hit zero and modding if we get past the edge
	public void checkAll() {
		int goingBacki;
		int goingBackj;
		for(int i = 0; i < size; i++) {
			if (i == 0) {
				goingBacki = (size-1);
			}
			else
				goingBacki = (i-1);
			for (int j = 0; j < size; j++){
				if (j == 0) {
					goingBackj = (size-1);
				}
				else {
					goingBackj = (j -1);
				}
				GenericStack<Cell> cellArray = createStack(board[goingBacki][goingBackj], 
				board[i][goingBackj],board[(i+1) % size][goingBackj], 
				board[goingBacki][j], board[i][j],
				board[(i+1) % size][j], board[(i+1) % size][goingBackj], 
				board[i][(j+1) % size], board[(i+1) % size][(j+1) % size]);
				
				checkIfChange(cellArray, board[i][j]);
			}
		}}
	//i push each neighbor onto a stack, then, as i pop my stack,

	public GenericStack<Cell> createStack(Cell topLeft, Cell top, Cell topRight,
			Cell left, Cell checkingThis, Cell right, Cell bottomLeft, Cell bottom, Cell bottomRight) {
			
		GenericStack<Cell> cellArray = new GenericStack<Cell>();
		cellArray.push(topLeft);
		cellArray.push(top);
		cellArray.push(topRight);
		cellArray.push(left);
		cellArray.push(right);
		cellArray.push(bottomLeft);
		cellArray.push(bottom);
		cellArray.push(bottomRight);
		
		return cellArray;
	}

	//if the cell status must change, i enqueue it	
	public void checkIfChange(GenericStack<Cell> cellArray, Cell checkingThis) {
		boolean mustChange = false;
		
		int liveNeighbors = checkForLiveNeighbors(cellArray);
		
		if (checkingThis.getAlive() && liveNeighbors < 2) {
			mustChange = true;
		}
		if (checkingThis.getAlive() && liveNeighbors > 3) {
			mustChange = true;
		}
		if (!checkingThis.getAlive() && liveNeighbors == 3) {
			mustChange = true;
		}
		
		if(mustChange)
			queue.enqueue(checkingThis);
	}
	
	//i increment a counter. then i check my counter - how many
	//living neighbors does my cell have?
	public int checkForLiveNeighbors(GenericStack<Cell> cellArray) {
		
		int liveNeighbors = 0;
		for (int i = 0; i < cellArray.size(); i++) {
			Cell neighbor = cellArray.pop();
			if (neighbor.getAlive()) {
				liveNeighbors++;
			}
		}
		return liveNeighbors;
	}
			
}	
	


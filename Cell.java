package gameOfLife;

public class Cell {

	boolean alive;
	int row, col;
	
	public Cell(boolean isAlive, int rw, int column){
		alive = isAlive;
		row = rw;
		col = column;
	}
	public Cell(int rw, int column) {
		alive = false;
		row = rw;
		col = column;
	}
	
	public void setAlive(boolean isAlive) {
		alive = isAlive;
	}
	
	public boolean getAlive() {
		return alive;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	@Override
	public String toString() {
		String displayLife = "o";
		if (getAlive()) {
			displayLife = "x";
		}
		return displayLife;
	}

}

 import java.util.Random;
public class Minesweeper {
	
	int[][] mines;
	int[][] tiles;
	
	public Minesweeper(int size) {
		mines = new int[size][size];
		tiles = new int[size][size];
	}
	
	public void fillMines(int num) {
		int count = 0;
		while (count < num)  {
			int j = (int)((mines.length)*Math.random());
			int k = (int)((mines.length)*Math.random());
			if(mines[j][k] != 9) {
				mines[j][k] = 9;
				count++;
			}
		}
	}
	
	public int getNumMines() {
		int count = 0;
		for(int i = 0; i < mines.length; i++) {
			for(int j = 0; j < mines.length; j++) {
				if(mines[i][j] == 9) {
					count++;
				}
			}
		}
		return count;
	}

	public void CalculateClues() {
		int clues = 0;
		for(int i = 0; i < mines.length; i++) {
			for(int j = 0; j < mines.length; j++) {
				if(mines[i][j] != 9) {
					mines[i][j] = MinesAround(i,j);
				}
			}
		}
	}
	
	public int MinesAround(int y, int x) {
		int numOfMines = 0;
		numOfMines += mineIn(y - 1, x - 1);
		numOfMines += mineIn(y - 1, x) ;
		numOfMines += mineIn(y - 1, x + 1);
		numOfMines += mineIn(y, x - 1);
		numOfMines += mineIn(y, x + 1);
		numOfMines += mineIn(y + 1, x - 1);
		numOfMines += mineIn(y + 1, x);
		numOfMines += mineIn(y + 1, x + 1);
		return numOfMines;
		
	}
	
	public int mineIn(int i,int j) {
		if(i >= 0 && i < mines.length && j >= 0 && j < mines.length && mines[i][j] == 9) {
			return 1;
		}else {
			return 0;
		}
	}
	
	public void MarkTiles(int row, int col, int status) {
		tiles[row][col] = status;
	}
	
	public int getTileStatus(int row, int col) {
		int closed = 0;
		int open = 1;
		int flagged = 2;
		
		if(tiles[row][col] == closed) {
			return 0;
		}
		else if(tiles[row][col] == open){
			return 1;
		}
		else {
			return 2;
		}
	}
	
	public void displayBoard() {
		for (int i = 0; i < tiles.length; i++) {
			System.out.println();
			for(int j = 0; j < tiles.length; j++) {
				if(getTileStatus(i,j) == 0) {
					System.out.print("[" + " " + "]");
				}
				else if(getTileStatus(i,j) == 1) {
					System.out.print("[" + mines[i][j] + "]");
				}
				else {
					System.out.print("[" + '#' + "]");
				}
			}
		}
	}
	

	
	public boolean gameWon() {
		int open = 1;
		int numOpenTiles = 0;
		for(int i = 0; i < mines.length; i++) {
			for(int j = 0; j < tiles.length; j++) {
				if(mines[i][j] == 9) {
					if (tiles[i][j] == open) {
						System.out.println("You Lose!");
						return true;
					}
				}
				else {
					if(tiles[i][j] == open) {
						numOpenTiles++;
					}
				}
			}
		}
		System.out.println("Open:" + numOpenTiles);
		if ((numOpenTiles)== mines.length + mines[0].length - getNumMines() ) {
			System.out.println("You Win!");
			return true;
		}
		else {
		return false;
		}
	}
	

}

import java.util.Scanner;
public class MinesweeperApp {
	
	public static void main(String[] args) {
		
		int row;
		int col;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the size of the mine:");
		int scan = sc.nextInt();
		
		Minesweeper play = new Minesweeper(scan);
		System.out.print("Enter the number of mines:");
		int num = sc.nextInt();
		play.fillMines(num);
		play.CalculateClues();
		
		play.displayBoard();
		System.out.println();
		boolean chooseS = true;
		while (chooseS) {
			System.out.println("");
			System.out.println("Enter the row:");
			row = sc.nextInt();
			
			System.out.println("Enter the col:");
			col = sc.nextInt();
			
			
			play.MarkTiles(row,col,1);
			play.displayBoard();
			
			if(play.gameWon() == true) {
				chooseS = false;
			};
			
		}
	

	}

}

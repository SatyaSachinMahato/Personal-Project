import java.util.Random;
import java.util.Scanner;

class TicTacToe
{
	static char[][] board;
	
	public TicTacToe()
	{
		board = new char[3][3];
		initBoard();  // after initialization 
		
	}
	
	void initBoard()
	{
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board[i].length;j++)
			{
				board[i][j]=' ';
			}
		}
	}
	
	static void dispBoard()
	{
		System.out.println("-------------");
		for(int i=0;i<board.length;i++)
		{
			System.out.print("| ");
			for(int j=0;j<board[i].length;j++)
			{
				System.out.print(board[i][j]+ " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}
	
	
	static void placeMark(int row, int col, char mark)
	{
		if(row>=0 && row<=3 && col>=0 && col<=2)
		{
			board[row][col]=mark;
		}
		else
		{
			System.out.println("Invalid Position");
		}
	}
	
	static boolean checkColWin()  // column wise win
	{
		for(int j=0;j<=2;j++)
		{
			if(board[0][j]!=' '&& board[0][j] ==board[1][j] && board[1][j] ==board[2][j])
			{
				return true;
			}
		}
		return false;
		   
	}
	
	static boolean checkRowWin()
	{
		for(int k=0;k<=2;k++)
		{
			if(board[k][0]!=' ' && board[k][0]==board[k][1] && board[k][1]==board[k][2])
			{
				return true;
			}
		}
		return false;
	}
	
	static boolean checkDiagonalWin()
	{
		for(int l=0;l<=2;l++)
		{
			if(board[0][0]!=' ' && board[1][1]!=' ' && board[l][l]==board[l+1][l+1] && board[l+1][l+1]==board[l+2][l+2])
			{
				return true;
			}
		}
		return false;
	}
	
	
	static boolean checkDraw()
	{
		for(int i=0;i<=2;i++) {
			for(int j=0;j<=2;j++)
			{
				if(board[i][j]==' ')
				{
					return false;
				}
			}
		}
		return true;
	}
	
}

// part 2 for Human & AI inputs

abstract class Player
{
	String name;
	char mark;
	abstract void makeMove();
	
	boolean isValidMove(int row, int col) {
		if((row>=0 && row<=2)&& (col>=0 && col<=2))
		{
			if(TicTacToe.board[row][col]==' ')
			{
				return true;
			}
		}
		return false;
	}
}



class HumanPlayer extends Player{

	HumanPlayer(String name, char mark)
	{
		this.name=name;
		this.mark=mark;
	}
	
	void makeMove() {
		Scanner scan = new Scanner(System.in);
		int row,col;
		do {
			System.out.println("Enter the row and Column : ");
			row = scan.nextInt();
			col = scan.nextInt();
		}
		while(!isValidMove(row,col));
		
		TicTacToe.placeMark(row, col, mark);
	}
}


class AIPlayer extends Player{
	
	AIPlayer(String name, char mark)
	{
		this.name=name;
		this.mark=mark;
	}
	
	void makeMove() {
		Scanner scan = new Scanner(System.in);
		int row;
		int col;
		do{
			Random r = new Random();
			row = r.nextInt(3);
			col = r.nextInt(3);
		}
		while(!isValidMove(row,col));
		
		TicTacToe.placeMark(row, col, mark);
	}
}



public class LaunchGame {

	public static void main(String[] args) {

		TicTacToe t = new TicTacToe();

		HumanPlayer p1 = new HumanPlayer("Mukul",'X');
		//HumanPlayer p2 = new HumanPlayer("Satya",'O');
		
		AIPlayer p2 = new AIPlayer("TAI",'O');
		
		Player cp;
		cp=p1;
		
		while(true)
		{
			System.out.println(cp.name+"'s turn");
			cp.makeMove();
			
			TicTacToe.dispBoard();
			
			if(TicTacToe.checkColWin()|| TicTacToe.checkRowWin() ||TicTacToe.checkDiagonalWin())
			{
				System.out.println(cp.name+" Has Won");
				break;
			}
			else if(TicTacToe.checkDraw())
			{
				System.out.println("Game is a Draw");
				break;
			}
			else
			{
				if(cp==p1)
				{
					cp=p2;
				}
				else
				{
					cp=p1;
				}
			}
			
		}
		
		//	//	t.dispBoard();
////		t.placeMark(0,0,'X');
////		t.placeMark(1,1,'X');
////		t.placeMark(2,2,'X');
////		t.placeMark(0,2,'O');
////		t.placeMark(1,0,'X');
////		t.placeMark(1,2,'X');
//		t.dispBoard();
//		
//	//  System.out.println(t.checkColWin());
//	//	System.out.println(t.checkColWin());
//			System.out.println(t.checkDiagonalWin());
	}

}

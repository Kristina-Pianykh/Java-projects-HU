import java.util.Scanner;

/*
/ use testOktadoku.java to test the Oktadoku class with the provided cofigs in /Oktadoku_config
/ "-x" argument specifies the type of class (in this case, mitDiagonalen; normal, otherwise)
*/

public class Oktadoku {

	public enum Variante { normal, mitDiagonalen };
	  private Variante v;
	  private int [][] board;
	  
	  public Oktadoku(Variante var) {
		  this.v = var;
		  this.board = new int[8][8];
	  }
	  
	  //check if the the given number occurs elsewhere in the corresponding row
	  public boolean isRowOk(int row, int num, boolean whichTest) {
		  if (whichTest) { //True if used for solveOktadoku, False for check()
		  for (int i = 0; i < this.board.length; i++) {
			  if (this.board[row][i] == num)
				  return false;
		}
		  return true;
		  }
		  else {
			  int count = 0;
			  for (int i = 0; i < this.board.length; i++) {
				  if (this.board[row][i] == num)
					  count++;
			}
			  if (count == 2) return false;
			  else return true;
		  }
	  }
	  
	  //check if the the given number occurs elsewhere in the corresponding column
	  public boolean isColOk(int col, int num, boolean whichTest) {
		  if (whichTest) {
		  for (int i = 0; i < this.board.length; i++) {
			  if (this.board[i][col] == num)
				  return false;
		}
		  return true;
		  }
		  else {
			  int count = 0;
			  for (int i = 0; i < this.board.length; i++) {
				  if (this.board[i][col] == num)
					  count++;
			}
			  if (count == 2) return false;
			  else return true;
		  }
	  }
	  
	  //check if the the given number occurs elsewhere in the corresponding subgrid
	  public boolean isSubGridOk(int row, int col, int num, boolean whichTest) {
		  int baseRow = row - row % 4;
		  int baseCol = col - col % 2;
		  
		  if (whichTest) {
		  for (int i = baseRow; i < baseRow + 4; i++) {
			  for (int j = baseCol; j < baseCol + 2; j++) {
			  if (this.board[i][j] == num)
				  return false;
			  }
		  }
		  return true;
		  }
		  else {
			  int count = 0;
			  for (int i = baseRow; i < baseRow + 4; i++) {
				  for (int j = baseCol; j < baseCol + 2; j++) {
				  if (this.board[i][j] == num)
					  count++;
				  }
			  }
			  if (count == 2) return false;
			  else return true;
		  }
	  }
	  
	  public boolean isDiagonalOk(int row, int col, int num, boolean whichTest) {
		  if (!whichTest) { //diagonals for check()
			  int count = 0;
			  int k;
			  for (int i = 0; i < this.board.length; i++) {
				  k = i;
				  if (this.board[i][k] == num)
					  count++;
			  }
			  if (count == 2) return false;
			  else return true;
		  }
		  else { //diagonals for solveOktadoku()
			  if (row == col) {
				  for (int i = 0; i < this.board.length; i++) {
					  if (this.board[i][i] == num)
						  return false;
				  }
			  }
			  else if (row == 7 - col) {
				  for (int i = 0; i < this.board.length; i++) {
					  if (this.board[i][7-i] == num)
						  return false;
				  }
			  }
			  return true;
		  }
	}
	  
	  public void read() {
		  Scanner sc = new Scanner(System.in);
		  int[] ascii = {0, 1, 2, 3, 4, 5, 6, 7, 8};
		  int lines = 0;
		  while (lines < 8) {
			  String row = sc.nextLine();
			  for (int i = 0; i < this.board.length; i++) {
				  char c = row.charAt(i);
				  if (c >= 48 && c <= 56) {
					  int ascii_idx = c - 48;
					  this.board[lines][i] = ascii[ascii_idx];
				  }
				  else
					  this.board[lines][i] = 0;
			  }
					lines++;   
			}
		  sc.close();
	  }
	  
	  public boolean check() {
		  for (int row = 0; row < this.board.length; row++) {
			  for (int col = 0; col < this.board.length; col++) {
				  if (this.board[row][col] != 0) {
					  int num = this.board[row][col];
					  if (this.v == Variante.normal) {
						  if (!isRowOk(row, num, false) || !isColOk(col, num, false) || !isSubGridOk(row, col, num, false))
							  return false;
					  }
					  else if (this.v == Variante.mitDiagonalen) {
						  if (!isRowOk(row, num, false) || !isColOk(col, num, false) || !isSubGridOk(row, col, num, false) || !isDiagonalOk(row, col, num, false)) {
							  return false;  
						  }
						  }
					  }
				  }
			  }
		  return true;
	  }
	  
	  public boolean canWeGo(int row, int col, int num) {
		  if (this.v == Variante.normal) {
			  if (!isRowOk(row, num, true) || !isColOk(col, num, true) || !isSubGridOk(row, col, num, true))
				  return false;
		  }
		  else if (this.v == Variante.mitDiagonalen) {
			  if (!isRowOk(row, num, true) || !isColOk(col, num, true) || !isSubGridOk(row, col, num, true) || !isDiagonalOk(row, col, num, true)) {
				  return false; 
			  }
		  }
		  return true;
	  }
	  
	  public boolean solveOktadoku() {
		  for (int row = 0; row < board.length; row++) {
			  for (int col = 0; col < board.length; col++) {
				  if (board[row][col] == 0) {
					  for (int digit = 1; digit < 10; digit++) {
						  if (canWeGo(row, col, digit)) {
							  board[row][col] = digit;
						  
					  		if (solveOktadoku())
					  			return true;
					  		else board[row][col] = 0;
						  }
					  }
				  return false;
				  }
			  }
		  }
		  return true;
	  }
	  
	  public void solve() {
		  if (solveOktadoku())
			  write();
		  else
			  System.out.println("nicht loesbar :-(");
	  }
	  
	  public void write() {
		  if (this.v == Variante.normal)
			  System.out.println("Oktadoku");
		  else
			  System.out.println("Oktadoku mit Diagonalen");
		  System.out.println("+-----+-----+-----+-----+");
		  int row = 0;
		  while (row < this.board.length) {
				for (int col = 0; col < this.board.length; col++) {
					if (col % 2 == 0) {
						System.out.print("| " + (board[row][col] == 0 ? " " : board[row][col]) + " ");
					}
					else if (col == 7)
						System.out.print((board[row][col] == 0 ? " " : board[row][col]) + " |");
					else System.out.print((board[row][col] == 0 ? " " : board[row][col]) + " ");
				}
				System.out.println();
				row++;
				if (row == 4)
					System.out.println("+-----+-----+-----+-----+");
			}
			System.out.println("+-----+-----+-----+-----+");
	  }
}

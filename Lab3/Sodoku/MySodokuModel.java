import java.util.*;
import java.lang.*;
import java.awt.*;


public class MySodokuModel {
	static final int ROWS = 9;
	static final int COLS = 9;
	static String testsud  = "003020600\n900305001\n001806400\n008102900\n700000008\n006708200\n002609500\n800203009\n005010300";
	static String testsud2 = "043080250\n600000000\n000001094\n900004070\n000608000\n010200003\n820500000\n000000005\n034090710";
	static String testsud3 = "7.....4...2..7..8...3..8.799..5..3...6..2..9...1.97..6...3..9...3..4..6...9..1.35";
	private int[][] sud;
	
	@Override public String toString() {
		return getBoard();
	}
	//Constructor classes.
	MySodokuModel() {
		sud = new int[9][9];
	}
	
	MySodokuModel(MySodokuModel s) {
		sud = s.getSud();
	}
	
	//Main method for testing.
	public static void main (String[] args) {
		MySodokuModel test = new MySodokuModel();
		test.setBoard(testsud3, true);
		System.out.println(test.solve());
		System.out.println(test);
	}
	
	//Clear board method
	public void clear(){
		int i, j;
		for (i=0; i < ROWS; i++) {
			for (j=0; j < COLS; j++) {
				sud[i][j] = 0;
			}
		}
	}
	//Get sudoku array (for testing)
	public int[][] getSud() {
		return sud;
	}
	
	//Set board method 1
	public void setBoard(int row, int col, int val) {
		if (isLegal(row, col, val)) 
			sud[row][col] = val;
		else
			throw new IllegalArgumentException("Illegal value");
	}
	
	//Set board method from string input.
	public void setBoard(String input) {
		int i, j, val;
		String[] tokens = input.split("[\\n]");
		if (true) {
			for (i=0; i < ROWS; i++){
				for (j=0; j < COLS; j++) {
					val = Character.getNumericValue(tokens[i].charAt(j));
					if (isLegal(i, j, val))
						sud[i][j] = val;
					else
						throw new IllegalArgumentException("Illegal value");
				}
			}
		}
		else
			throw new NumberFormatException("Incorrect formating");
		
	}
	//For parsing differnt kinds of string inputs
	public void setBoard(String input, boolean p) {
		int i = 0;
		String str = "";
		if (p == true) {
			String zero_input = input.replaceAll("\\.", "0");
			while (i < 81) {
				str = str + zero_input.substring(i, (i+9)) + "\n";
				i = i + 9;
			}
			setBoard(str);	
		}
	}
	
	//Get board value of row/col
	public int getBoard(int row, int col) {
		return sud[row][col];
	}
	
	//String getboard
	public String getBoard() {
		int i, j;
		String sodoku = "";
		for (i=0; i < ROWS; i++){
				for (j=0; j < COLS; j++) {
					sodoku = sodoku + getBoard(i, j);
				}
			sodoku = sodoku + "\n";
		}
		return sodoku;
	}
	
	//Checks if it's a legal value at location.
	boolean isLegal(int row, int col, int val) {
		boolean inRow = arrayCheck(getRow(row), val);
		boolean inCol = arrayCheck(getCol(col), val);
		boolean inBlock = arrayCheck(getBlock(row, col), val);
		if (val == 0)
			return true;
		else if (inRow || inCol || inBlock)
			return false;
		else
			return true;
	}
	
	//Helper function for checking arrays.
	boolean arrayCheck(int[] r, int val) {
		int i;
		for (i=0; i < COLS; i++) {
			if (r[i] == val) {
				return true;
			}
		}
		return false;
	}
	
	int arrayCount(int[] r, int val) {
		int i;
		int match = 0;
		for (i=0; i < r.length; i++) {
			if (r[i] == val) {
				match++;
			}
		}
		return match;
	}	
	
	//Board values
	int[] getRow(int row) {
		return sud[row];
	}
	

	int[] getCol(int col) {
		int i;
		int[] colc = new int[9];
		for (i=0; i < ROWS; i++) {
			colc[i] = sud[i][col];
		}
		return colc;
	}
	
	int[] getBlock(int row, int col) {
		int i, j;
		int index = 0;
		int lower_row = (row-(row % 3));
		int lower_col = (col-(col % 3));
		int[] block = new int[9];
		for (i=lower_row; i < (lower_row + 3); i++) {
			for (j=lower_col; j < (lower_col + 3); j++) {
				block[index] = sud[i][j];
				index++;
			}
		}
		return block;
	}
	
	//Solver part
	
	//Checks if sudoku contains zeroes aka is it solved.
	public boolean isSolved() {
		int i;
		for (i=0; i < ROWS; i++) {
			if (arrayCheck(sud[i], 0))
				return false;
		}
		return true;
	}
	int zeroCounter(int row, int col) {
		int row_zero, col_zero, block_zero;
		row_zero = arrayCount(getRow(row), 0);
		col_zero = arrayCount(getCol(col), 0);
		block_zero = arrayCount(getBlock(row, col), 0);
		return row_zero + col_zero + block_zero;
	}

	Point zeroFinder() {
		int i, j, zeroes;
		int first = 0;
		Point bp = new Point();
		for (i=0; i < ROWS; i++) {
			for (j=0; j < COLS; j++) {
				if (sud[i][j] == 0) {
					zeroes = zeroCounter(i, j);
					if (first == 0) {
						bp.setLocation(i, j);
						first++;
					}		
					else if (zeroes < zeroCounter(bp.x, bp.y)) {
						bp.setLocation(i, j);
					}
				}
			}
		}
		return bp;	
	}
	
	public boolean solve() {
		int i, row, col;
		Point bp2;
		if (isSolved()) {
			return true;
		}
		else {
			bp2 = zeroFinder();
			for (i=1; i < 10; i++) {
				if (isLegal(bp2.x, bp2.y, i)) {
					setBoard(bp2.x, bp2.y, i);
					if (solve())
						return true;
					else
						setBoard(bp2.x, bp2.y, 0);
					
				}
			}
		return false;
		}
	}
	
	public boolean solve2() {
		int i, j, k;
		if (isSolved()) {
			return true;
		}
		else {
			for (i=0; i < ROWS; i++) {
				for (j=0; i < COLS; j++) {
					if (sud[i][j] == 0) {
						for (k=1; k < 10; k++) {
							if (isLegal(i, j, k)) {
								setBoard(i, j, k);
								if (solve())
									return true;
								else
									setBoard(i, j, 0);
								
							}
						}
					}
				}
			}
		return false;
		}
	}
	
}

















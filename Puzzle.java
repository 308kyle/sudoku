import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

public class Puzzle {
	public static boolean safePlace(int[][] board, int row, int col, int num) {
		for(int c=0;c<9;c++) {
			if(board[row][c] == num)
				return false;
		}
		for(int r=0;r<9;r++) {
			if(board[r][col] == num)
				return false;
		}
		int rStart = (int)(row/3)*3;
		int cStart = (int)(col/3)*3;
		for(int r=rStart;r<rStart+3;r++) {
			for(int c=cStart;c<cStart+3;c++) {		
				if(board[r][c] == num) 
					return false;
			}
		}
		return true;
	}
	public static boolean solveBoard(int[][] board) {		
		boolean full = true;
		int row = 0;
		int col = 0;
		for(int r=8;r>=0;r--) {
			for(int c=8;c>=0;c--) {
				if(board[r][c] == 0) {
					full = false;
					row = r;
					col = c;
				}
			}
		}
		if(full) {
			return true;
		}
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for(int i=1;i<=9;i++) {
			nums.add(i);
		}
		Collections.shuffle(nums);
		for(int i=0;i<=8;i++) {
			int val = nums.get(i);
			if(safePlace(board, row, col, val)) {
				board[row][col] = val;
				if(solveBoard(board)) {
					return true;
				}
				board[row][col] = 0;
			}
		}
		return false;
	}
	private static boolean allSolutions(int[][] board, int[] count) {
		boolean full = true;
		int row = 0;
		int col = 0;
		for(int r=8;r>=0;r--) {
			for(int c=8;c>=0;c--) {
				if(board[r][c] == 0) {
					full = false;
					row = r;
					col = c;
				}
			}
		}
		if(full) {
			count[0]++;
			return true;		
		}
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for(int i=1;i<=9;i++) {
			nums.add(i);
		}
		Collections.shuffle(nums);
		for(int i=0;i<=8;i++) {
			int val = nums.get(i);
			if(safePlace(board, row, col, val)) {
				board[row][col] = val;
				allSolutions(board, count);
				board[row][col] = 0;
			}
		}
		return false;
	}
	public static int[][] createPuzzle() {
		ArrayList<Point> pos = new ArrayList<Point>();
		for(int r=0;r<9;r++) {
			for(int c=0;c<9;c++) {
				pos.add(new Point(r,c));
			}
		}
		Collections.shuffle(pos);
		int[][] board = new int[9][9];
		solveBoard(board);
		for(Point p : pos) {
			int row = (int) p.getX();
			int col = (int) p.getY();

			int[][] copy = copyArray(board);
			copy[row][col] = 0;
			int[] count = new int[1];
			allSolutions(copy, count);
			if(count[0]==1) {
				board[row][col] = 0;
			}
		}
		return board;
	}
	private static int[][] copyArray(int[][] array) {
		int[][] copy = new int[array.length][];
		for(int i=0;i<array.length;i++) {
			copy[i] = array[i].clone();
		}
		return copy;
	}
}





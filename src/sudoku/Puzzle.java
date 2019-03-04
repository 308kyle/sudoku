import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

public class Puzzle {
	public static void printBoard(int[][] board) {
		for(int r=0;r<9;r++) {
			for(int c=0;c<9;c++) {
				System.out.print(board[r][c]);
			}
			System.out.println();
		}
	}
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
	public static boolean allSolutions(int[][] board, int[] count) {
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
	public static int[][] copyArray(int[][] array) {
		int[][] copy = new int[array.length][];
		for(int i=0;i<array.length;i++) {
			copy[i] = array[i].clone();
		}
		return copy;
	}
	public static void testSolver() {
		int[][] board = {
				{0,0,0,2,6,0,7,0,1},
				{6,8,0,0,7,0,0,9,0},
				{1,9,0,0,0,4,5,0,0},
				{8,2,0,1,0,0,0,4,0},
				{0,0,4,6,0,2,9,0,0},
				{0,5,0,0,0,3,0,2,8},
				{0,0,9,3,0,0,0,7,4},
				{0,4,0,0,5,0,0,3,6},
				{7,0,3,0,1,8,0,0,0}
		};
		if(solveBoard(board)) {
			printBoard(board);
		}
	}
	public static void testAllSolutions() {
		int[][] board = {
				{2,9,5,7,4,3,8,6,1},
				{4,3,1,8,6,5,9,0,0},
				{8,7,6,1,9,2,5,4,3},
				{3,8,7,4,5,9,2,1,6},
				{6,1,2,3,8,7,4,9,5},
				{5,4,9,2,1,6,7,3,8},
				{7,6,3,5,3,4,1,8,9},
				{9,2,8,6,7,1,3,5,4},
				{1,5,4,9,3,8,6,0,0}
		};
		int[] count = new int[1];
		allSolutions(board, count);
		System.out.println(count[0]);
		int[][] board2 = {
				{0,0,0,0,0,0,0,0,0},
				{0,5,2,0,0,0,6,3,0},
				{0,4,6,8,0,3,1,5,0},
				{0,0,7,4,0,9,8,0,0},
				{0,0,0,0,1,0,0,0,0},
				{0,0,9,7,0,6,2,0,0},
				{0,2,8,5,0,4,9,6,0},
				{0,1,3,0,0,0,7,8,0},
				{0,0,0,0,0,0,0,0,0}
		};
		count = new int[1];
		allSolutions(board2, count);
		System.out.println(count[0]);
	}
	public static void testGenerator() {
		int[][] puzzle = createPuzzle();
		printBoard(puzzle);
		int[] count = new int[1];
		allSolutions(puzzle, count);
		System.out.println(count[0]);
	}
	public static void main(String[] str) {
		//testSolver();
		//testAllSolutions();
		testGenerator();
	}

}





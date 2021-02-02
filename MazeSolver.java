import gdp.stdlib.StdDraw;

public class MazeSolver {

	public static boolean solve(int[][] maze, int row, int col) {
			//false if out of bounds
			if ((row < 0) || (row >= maze.length) || (col < 0) || (col >= maze.length)) {
				return false;
			}
			//false if dead end or visited cell
			if ((maze[row][col] == 0) || (maze[row][col] == 4)) {
					return false;
			}
			
			//true if the destination is reached
			if (maze[row][col] == 3) {
				maze[row][col] = 4;
				return true;
			}
			
			//mark cell as visited
			maze[row][col] = 4;
			
			//iterate through the moves
			for (int move = 0; move < moveRow.length; move++ ) {
				if (solve(maze, row+moveRow[move], col+moveCol[move]))
						return true;
			}
			
			//unmark visite cell
			maze[row][col] = 1;
			return false;
		}
	
	//moves: up, down, right, left
    static int[] moveRow = {1, -1, 0, 0};
    static int[] moveCol = {0, 0, 1, -1};

	//rotate the maze by 90 degrees clockwise for drawing
	public static int[][] rotate90Degrees(int[][] m) {
		 int[][] newM = new int[m.length][m.length];

		 for (int i = 0; i < m.length; ++i) 
		  for (int j = 0; j < m.length; ++j) 
			  newM[i][j] = m[m.length - j - 1][i];
		 
		 return newM;
	}
	
	public static void draw(int[][] maze) {
		int maze_side = maze.length;
		StdDraw.setXscale(-1, maze_side + 1);
        StdDraw.setYscale(-1, maze_side + 1);
        
        //draw the frame
        StdDraw.line(0, 0, 0, maze_side);
        StdDraw.line(0, maze_side, maze_side, maze_side);
        StdDraw.line(maze_side, maze_side, maze_side, 0);
        StdDraw.line(0, maze_side, 0, 0);
        
        int [][]rotatedMaze = rotate90Degrees(maze);
        
        for (int i = 0; i < rotatedMaze.length; i++) {
        	for (int j = 0; j < rotatedMaze.length; j++) {
        		if (rotatedMaze[i][j] == 0) {
        			StdDraw.setPenColor(StdDraw.GRAY);
        		}
        		else if (rotatedMaze[i][j] == 1) {
        			StdDraw.setPenColor(StdDraw.WHITE);
        		}
        		else if (rotatedMaze[i][j] == 2 || rotatedMaze[i][j] == 4) {
        			StdDraw.setPenColor(StdDraw.GREEN);
        		}
        		else if (rotatedMaze[i][j] == 3) {
        			StdDraw.setPenColor(StdDraw.BLUE);
        		}
        		StdDraw.filledSquare(i+0.5, j+0.5, 0.5);
        	}
        }
        //draw lines
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < maze_side; i++) {
            StdDraw.line(0, i, maze_side, i);
            StdDraw.line(i, 0, i, maze_side);
        }
	}
}

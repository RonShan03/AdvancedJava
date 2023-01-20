import java.io.*;

public class MazeSolver {
    static char[][] maze;
    static int startX, startY, endX, endY;
    
    public static void main(String[] args) throws IOException {
        // Read the .dat file
        BufferedReader br = new BufferedReader(new FileReader("maze.dat"));
        String line;
        int rows = 0;
        int cols = 0;
        while ((line = br.readLine()) != null) {
            cols = Math.max(cols, line.length());
            rows++;
        }
        br.close();

        // Initialize the maze array
        maze = new char[rows][cols];
        br = new BufferedReader(new FileReader("maze.dat"));
        int row = 0;
        while ((line = br.readLine()) != null) {
            for (int col = 0; col < line.length(); col++) {
                maze[row][col] = line.charAt(col);
                if (maze[row][col] == 'S') {
                    startX = col;
                    startY = row;
                } else if (maze[row][col] == 'E') {
                    endX = col;
                    endY = row;
                }
            }
            row++;
        }
        br.close();
        
        // Solve the maze
        solveMaze(startX, startY);
    }
    
    public static boolean solveMaze(int x, int y) {
        // Check if we've reached the end of the maze
        if (maze[y][x] == 'E') {
            return true;
        }
        
        // Check if we've hit a wall or an already visited spot
        if (maze[y][x] == '+' || maze[y][x] == '.') {
            return false;
        }
        
        // Mark the current spot as visited
        maze[y][x] = '.';
        
        // Try moving in all four directions
        if (solveMaze(x+1, y)) { // move right
            return true;
        } else if (solveMaze(x-1, y)) { // move left
            return true;
        } else if (solveMaze(x, y+1)) { // move down
            return true;
        } else if (solveMaze(x, y-1)) { // move up
            return true;
        }
        
        // If none of the directions work, backtrack
        maze[y][x] = ' ';
        return false;
    }
}

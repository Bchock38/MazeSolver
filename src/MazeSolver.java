/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */
//Benjamin Chock

import java.util.ArrayList;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;


public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        // Should be from start to end cells
        ArrayList<MazeCell> solution = new ArrayList<MazeCell>();
        Stack<MazeCell> holder = new Stack<MazeCell>();
        //create temp mazecell to keep track of the current cell where are on in the path
        MazeCell temp = maze.getEndCell();
        //while current cell isn't the beginning cell add to path
        while(temp != maze.getStartCell()){
            holder.add(temp);
            temp = temp.getParent();
        }
        //add start cell to path
        holder.add(maze.getStartCell());
        int size = holder.size();
        //reverse order from end to start, to start to end
        for (int i = 0; i < size; i++){
            solution.add(holder.pop());
        }
        //return solution
        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        //temp cell to keep track of position in maze
        MazeCell currentCell = maze.getStartCell();
        //first cell set to explored
        currentCell.setExplored(true);
        //Stack to hold all possible cells to vist
        Stack<MazeCell> cellsToVisit = new Stack<MazeCell>();
        while (currentCell != maze.getEndCell()){
            //if North is valid add to tovisit Stack
            if(maze.isValidCell(currentCell.getRow()-1, currentCell.getCol())){
                //set explore value to true and record parent cell
                maze.getCell(currentCell.getRow()-1, currentCell.getCol()).setExplored(true);
                maze.getCell(currentCell.getRow()-1, currentCell.getCol()).setParent(currentCell);
                cellsToVisit.push(maze.getCell(currentCell.getRow()-1, currentCell.getCol()));
            }
            //if East is valid add to tovisit Stack
            if(maze.isValidCell(currentCell.getRow(), currentCell.getCol()+1)){
                //set explore value to true and record parent cell
                maze.getCell(currentCell.getRow(), currentCell.getCol()+1).setParent(currentCell);
                maze.getCell(currentCell.getRow(), currentCell.getCol()+1).setExplored(true);
                cellsToVisit.push(maze.getCell(currentCell.getRow(), currentCell.getCol()+1));
            }
            //if South is valid add to tovisit Stack
            if (maze.isValidCell(currentCell.getRow()+1, currentCell.getCol())){
                //set explore value to true and record parent cell
                maze.getCell(currentCell.getRow()+1, currentCell.getCol()).setParent(currentCell);
                maze.getCell(currentCell.getRow()+1, currentCell.getCol()).setExplored(true);
                cellsToVisit.push(maze.getCell(currentCell.getRow()+1, currentCell.getCol()));
            }
            //if West is valid add to tovisit Stack
            if(maze.isValidCell(currentCell.getRow(), currentCell.getCol()-1)){
                //set explore value to true and record parent cell
                maze.getCell(currentCell.getRow(), currentCell.getCol()-1).setExplored(true);
                maze.getCell(currentCell.getRow(), currentCell.getCol()-1).setParent(currentCell);
                cellsToVisit.push(maze.getCell(currentCell.getRow(), currentCell.getCol()-1));
            }
            //move current cell to the top cell in the Stack
            currentCell = cellsToVisit.pop();
        }
        //return solution path
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        //create queue to hold all the cells to visit
        Queue<MazeCell> cellsToVisit = new LinkedList<MazeCell>();
        //create temp cell to keep track of position in maze
        MazeCell currentCell = maze.getStartCell();
        //set current cell/first cell to explored
        currentCell.setExplored(true);
        while (currentCell != maze.getEndCell()) {
            //if North is valid add to tovisit list
            if (maze.isValidCell(currentCell.getRow() - 1, currentCell.getCol())) {
                //set explore value to true and record parent cell
                maze.getCell(currentCell.getRow() - 1, currentCell.getCol()).setExplored(true);
                maze.getCell(currentCell.getRow() - 1, currentCell.getCol()).setParent(currentCell);
                cellsToVisit.add(maze.getCell(currentCell.getRow() - 1, currentCell.getCol()));
            }
            //if East is valid add to tovisit list
            if (maze.isValidCell(currentCell.getRow(), currentCell.getCol() + 1)) {
                //set explore value to true and record parent cell
                maze.getCell(currentCell.getRow(), currentCell.getCol() + 1).setParent(currentCell);
                maze.getCell(currentCell.getRow(), currentCell.getCol() + 1).setExplored(true);
                cellsToVisit.add(maze.getCell(currentCell.getRow(), currentCell.getCol() + 1));
            }
            //if South is valid add to tovisit list
            if (maze.isValidCell(currentCell.getRow() + 1, currentCell.getCol())) {
                //set explore value to true and record parent cell
                maze.getCell(currentCell.getRow() + 1, currentCell.getCol()).setParent(currentCell);
                maze.getCell(currentCell.getRow() + 1, currentCell.getCol()).setExplored(true);
                cellsToVisit.add(maze.getCell(currentCell.getRow() + 1, currentCell.getCol()));
            }
            //if West is valid add to tovisit list
            if (maze.isValidCell(currentCell.getRow(), currentCell.getCol() - 1)) {
                //set explore value to true and record parent cell
                maze.getCell(currentCell.getRow(), currentCell.getCol() - 1).setExplored(true);
                maze.getCell(currentCell.getRow(), currentCell.getCol() - 1).setParent(currentCell);
                cellsToVisit.add(maze.getCell(currentCell.getRow(), currentCell.getCol() - 1));
            }
            //change currentcells position to the first element in the queue
            currentCell = cellsToVisit.remove();
        }
        //return solution path
        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}

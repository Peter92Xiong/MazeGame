package solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A maze game.
 * 
 * @author
 * @version
 *
 */
public class MazeGame
{
    /**
     * The size of each side of the game map.
     */
    private final static int HEIGHT = 19;
    private final static int WIDTH = 39;

    /**
     * The game map, as a 2D array of ints.
     */
    private boolean[][] blocked;
    
    /**
     * The current location of the player vertically.
     */
    // TODO: add field here.
    private int userCol; 
    /**
     * The current location of the player horizontally.
     */
    // TODO: add field here.
    private int userRow;
    /**
     * The scanner from which each move is read.
     */
    // TODO: add field here.
    Scanner moveScanner; //= new Scanner(System.in);
    /**
     * The row and column of the goal.
     */
    // TODO: add fields here.
    private int goalRow;
    private int goalCol;
    /**
     * The row and column of the start.
     */
    // TODO: add fields here.
    private int startRow;
    private int startCol;
    /**
     * Constructor initializes the maze with the data in 'mazeFile'.
     * @param mazeFile the input file for the maze
     */
    public MazeGame(String mazeFile)
    {
        // TODO
        //Peter's code Start:
        loadMaze(mazeFile);
        
        moveScanner = new Scanner(System.in);
        
        
        /*        while (moveScanner.hasNextInt())
              {
        moveScanner.nextInt();
              }*/
        
        
        //:Peter's code Ends.
    }

    /**
     * Constructor initializes the maze with the 'mazeFile' and the move 
     * scanner with 'moveScanner'.
     * @param mazeFile the input file for the maze
     * @param moveScanner the scanner object from which to read user moves
     */
    public MazeGame(String mazeFile, Scanner moveScanner)
    {
    	// TODO
        loadMaze(mazeFile);
        this.moveScanner = moveScanner;
        

    }

    /**
     * getMaze returns a copy of the current maze for testing purposes.
     * 
     * @return the grid
     */
    public boolean[][] getMaze()
    {
        if (blocked == null)
        {
            return null;
        }
        boolean[][] copy = new boolean[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++)
        {
            for (int j = 0; j < WIDTH; j++)
            {
                copy[i][j] = blocked[i][j];
            }
        }
        return copy;
    }

    /**
     * setMaze sets the current map for testing purposes.
     * 
     * @param maze
     *            another maze.
     */
    public void setMaze(boolean[][] maze)
    {
        this.blocked = maze;
    }
    
    /**
     * Function loads the data from the maze file and creates the 'blocked' 
     * 2D array.
     *  
     * @param mazeFile the input maze file.
     */
    // TODO: private void loadMaze(String mazeFile)
    
    
    /**
     * Actually plays the game.
     */
    public void playGame()
    {
        printMaze();
      System.out.println("Enter up, down, left, or right. Enter quit to exit:");
      
      String userInput = moveScanner.next(); 
      int count = 0; 
      
      while(!userInput.equalsIgnoreCase("quit")) {
          makeMove(userInput); 
          printMaze();
          System.out.println("Enter up, down, left, or right. Enter quit to exit:");
          userInput = moveScanner.next();
          count ++; 
      }
        System.out.println("It took you " + count + " moves.");
        
    }

    /**
     * Checks to see if the player has won the game.
     * @return true if the player has won.
     */
    // TODO: public boolean playerAtGoal()
    //Peter code start
    public boolean playerAtGoal()
    {
        if (getUserRow() == getGoalRow() && getUserCol() == getGoalCol())
        {
            return true;
        } else return false;
    }
    //P's end
    /**
     * Makes a move based on the String.
     * 
     * @param move
     *            the direction to make a move in.
     * @return whether the move was valid.
     */
    public boolean makeMove(String move)
    {
        // TODO
        
        boolean isMoved = false;
        
        if(move.equalsIgnoreCase("up")) {
            isMoved = moveUp();
        }else if(move.equalsIgnoreCase("down")) {
            isMoved = moveDown();
        }else if(move.equalsIgnoreCase("left")) {
            isMoved = moveLeft(); 
        }else if(move.equalsIgnoreCase("right")) {
            isMoved = moveRight();
        }else if(move.equalsIgnoreCase("quit"))
            isMoved = true;  
        
        return isMoved;
    }

    private boolean moveRight()
    {
        if(getUserCol() != 38 && blocked[getUserRow()][getUserCol() +1] != true) {
            setUserCol(getUserCol() + 1);
            return true; 
        }else  return false;
    }

    private boolean moveLeft()
    {
        if(getUserCol() != 0 && blocked[getUserRow()][getUserCol()-1] != true) {
            setUserCol(getUserCol() -1 );
            return true; 
        }else return false;
    }

    private boolean moveDown()
    {
        if(getUserRow() != 18 && blocked[getUserRow() + 1][getUserCol()] != true) {
            setUserRow(getUserRow() + 1);
            return true; 
        }else return false;
    }

    private boolean moveUp()
    {
        if(getUserRow() != 0 && blocked[getUserRow()-1][getUserCol()] != true) {
            setUserRow(getUserRow() - 1);
            return true; 
        }else return false; 
    }

    /**
     * Prints the map of the maze.
     */
    public void printMaze()
    {
        // TODO
        //Peter's code Start
        System.out.println("*---------------------------------------*");
        for (int i = 0; i < blocked.length; i++)
        {   
            System.out.print("|");
            for (int j = 0; j < blocked[i].length; j++)
            {
                if (i == startRow && j == startCol && i != getUserRow() && j != getUserCol() )
                {
                    System.out.print("S");
                }else if(i == getUserRow() && j == getUserCol()) {
                    System.out.print("@");
                }else if (i == getGoalRow() && j == getGoalCol())
                {
                    System.out.print("G");
                }

                else if (blocked[i][j] == true)
                {
                    System.out.print("X");                   
                }
                else if (blocked[i][j] == false)
                {
                    System.out.print(" ");
                }
                
                
            }
            System.out.println("|");            
        }
        System.out.println("*---------------------------------------*");
    }
    //Peter's code Ends.

    /**
     * Creates a new game, using a command line argument file name, if one is
     * provided.
     * 
     * @param args the command line arguments
     */
    //Peter's code Starts
    //setters
    public void setGoalRow (int goalRow)
    {
        this.goalRow = goalRow;
    }
    public void setGoalCol (int goalCol)
    {
        this.goalCol = goalCol;
    }
    public void setStartRow (int startRow)
    {
        this.startRow = startRow;
    }
    public void setStartCol (int startCol)
    {
        this.startCol = startCol;
    }
    
    public void setUserCol(int userCol)
    {
        this.userCol = userCol;
        
    }
    
    public void setUserRow(int userRow)
    {
        this.userRow = userRow;
        
    }
    
    public void setMoveScanner(Scanner moveScanner)
    {
        this.moveScanner = moveScanner;
        
    }
    
    public void setBlock(boolean [] [] blocked)
    {
        this.blocked = blocked;
    }
    //getters
    public int getGoalRow()
    {
        return goalRow;
    }
    public int getGoalCol()
    {
        return goalCol;
    }
    public int getStartRow()
    {
        return startRow;
        
    }
    public int getStartCol()
    {
        return startCol;
        
    }
    public int getUserCol()
    {
        return userCol;
        
    }
    
    public int getUserRow()
    {
        return userRow;
            
    }
    
    public boolean [] [] getBlock()
    {
        
        return blocked;
    }
    
    public Scanner getMoveScanner()
    {
        return moveScanner;
        
        
    }
    
    public void loadMaze(String mazeFile)
    {
    Scanner scanner = null;
    
    try 
    {
        String var;
        scanner = new Scanner(new File(mazeFile));
        blocked = new boolean[HEIGHT] [WIDTH];
        for (int i = 0; i < HEIGHT; i++)
        {
            for (int j = 0; j < WIDTH; j++)
            {
                if (scanner.hasNext())
                {
                    var = scanner.next();
                    if (var.equals ("1"))
                    {
                        blocked[i] [j] = true;
                        
                    }else if (var.equals("0"))
                    {
                        blocked[i][j] = false;
                    }else if (var.equals("S"))
                    {
                        blocked[i] [j] = false;
                        setStartCol(j);
                        setStartRow(i);
                    }
                    else if (var.equals("G"))
                    {
                        blocked [i] [j] = false;
                        setGoalCol(j);
                        setGoalRow(i);
                        }
                    System.out.print(blocked[i][j] + ", ");
                }
                
            }
            System.out.println();
        }
        scanner.close();
    }
    catch (FileNotFoundException e) {
        // TODO: handle exception
        e.printStackTrace();
    }
    }
    
    //peter's code ends.
    /*blocked = new boolean[HEIGHT][WIDTH];
    try 
    {
        File file = new File(mazeFile);
        Scanner fileScanner = new Scanner(file);           
        int c = 0;
        while (fileScanner.hasNext()) 
        {
            String character = fileScanner.next();
            switch (character) 
            {
                case "S":
                    startRow = c / WIDTH;
                    startCol = c % WIDTH;
                    blocked[c / WIDTH][c % WIDTH] = false;
                    break;
                case "G":
                    goalRow = c / WIDTH;
                    goalCol = c % WIDTH;
                    blocked[c / WIDTH][c % WIDTH] = false;
                    break;
                default:
                    blocked[c / WIDTH][c % WIDTH] = 
                        Boolean.valueOf(character);
            }
            c++;
        }
        fileScanner.close();                                
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }       
    }
    */
    //Peter's code ends
    
    public static void main(String[] args)
    {
        String mapFile = "data/easy.txt";
        Scanner scan = new Scanner(System.in);
        MazeGame game = new MazeGame(mapFile, scan);
        game.playGame();
    }
}

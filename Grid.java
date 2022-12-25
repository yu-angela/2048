/*  Name: Angela Yu
*  Execution: N/A (no main method)
*
*  A class that represents the creation of the game board (grid), empty and then
*  containing tiles within a 4x4 space. Alongside generating the board, contains
*  methods dictating the movements possible within the game (moving tiles, accessing
*  specific values, recording score, recording number of moves, etc.). Essentially,
*  sets up everything needed for game play.
*/
public class Grid {
    private Tile[][] board;
    private int scoreCounter;
    private int moveCounter;
    
    /* constructor for new empty board for the game with empty tiles,
    * then generates two random tiles with either a value of 2 or 4
    */
    public Grid() {
        board = new Tile[4][4];
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = new Tile();
            }
        }
        
        int randomRowFirstTile = (int) (Math.random() * 4);
        int randomRowSecondTile = (int) (Math.random() * 4);
        int randomColumnFirstTile = (int) (Math.random() * 4);
        int randomColumnSecondTile = (int) (Math.random() * 4);
        
        // generate the two tiles only if they are in different locations
        while (randomColumnFirstTile == randomColumnSecondTile) {
            randomColumnFirstTile = (int) (Math.random() * 4);
        }
        
        if ((randomRowFirstTile != randomRowSecondTile) || (randomColumnFirstTile !=
        randomColumnSecondTile)) {
            double probTwoOrFourFirstTile = Math.random();
            
            if (probTwoOrFourFirstTile < 0.3) {
                board[randomRowFirstTile][randomColumnFirstTile] = new Tile(4);
                } else {
                board[randomRowFirstTile][randomColumnFirstTile] = new Tile(2);
            }
            
            double probTwoOrFourSecondTile = Math.random();
            
            if (probTwoOrFourSecondTile < 0.3) {
                board[randomRowSecondTile][randomColumnSecondTile] = new Tile(4);
                } else {
                board[randomRowSecondTile][randomColumnSecondTile] = new Tile(2);
            }
        }
    }
    
    // construct a board that can hold values of an input board
    public Grid(Tile[][] inputBoard) {
        board = inputBoard;
    }
    
    /**
    * Inputs: None
    * Outputs: integer representing the score of the game
    * Description: allows for access to the score of the game as it continues
    */
    public int getScore() {
        return scoreCounter;
    }
    
    /**
    * Inputs: None
    * Outputs: integer representing the number of moves made during the game
    * Description: allows for access to the number of moves made during a game
    */
    public int getMoveNumber() {
        return moveCounter;
    }
    
    /**
    * Inputs: None
    * Outputs: 2D integer array holding all of the values within the board
    * Description: takes the board and stores it as a 2D integer array for
    *              later access (because board is private)
    */
    public int[][] getBoard() {
        int[][] storageBoard = new int[4][4];
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                storageBoard[i][j] = board[i][j].getTileValue();
            }
        }
        
        return storageBoard;
    }
    
    /**
    * Inputs: None
    * Outputs: None
    * Description: spawns a tile in an empty space with a lesser probability of the
    *              tile value being 4 instead of 2
    */
    public void spawnTile() {
        int i = (int) (Math.random() * 4);
        int j = (int) (Math.random() * 4);
        
        while (true) {
            if (board[i][j].getTileValue() == 0) {
                double probTwoOrFourTile = Math.random();
                
                if (probTwoOrFourTile < 0.3) {
                    board[i][j] = new Tile(4);
                    } else {
                    board[i][j] = new Tile(2);
                }
                
                return;
            } else {
                i = (int) (Math.random() * 4);
                j = (int) (Math.random() * 4);
            }
        }
    }
    
    /**
    * Inputs: None
    * Outputs: None
    * Description: moves all tiles in the board to the leftmost position by
    *              iterating through rows to find all non-zero values, creating a
    *              new row with those new values in the left-most place and
    *              inserting that row in place of the previous one
    */
    public void shiftLeft() {
        int[] tempStore = new int[4];
        
        // iterate through every row
        for (int i = 0; i < 4; i++) {
            int tracker = 0;
            // takes in one row and captures each non-zero value in array as new row
            for (int j = 0; j < 4; j++) {
                if (board[i][j].getTileValue() != 0) {
                    tempStore[tracker] = board[i][j].getTileValue();
                    tracker++;
                }
            }
            
            // insert new shifted row to replace row in new board
            for (int k = 0; k < 4; k++) {
                board[i][k].setTileValue(tempStore[k]);
            }
            
            // reset tempStore for new row
            tempStore = new int[4];
        }
        
        // updates number of moves
        moveCounter++;
    }
    
    /**
    * Inputs: None
    * Outputs: None
    * Description: moves all tiles in the board to the rightmost position by
    *              iterating through rows to find all non-zero values, creating a
    *              new row with those new values in the right-most place and
    *              inserting that row in place of the previous one
    */
    public void shiftRight() {
        int[] tempStore = new int[4];
        
        // iterate through every row
        for (int i = 0; i < 4; i++) {
            int tracker = 3;
            // takes in one row and captures each non-zero value in array as new row
            for (int j = 3; j >= 0; j--) {
                if (board[i][j].getTileValue() != 0) {
                    tempStore[tracker] = board[i][j].getTileValue();
                    tracker--;
                }
            }
            
            // insert new shifted row to replace row in new board
            for (int k = 0; k < 4; k++) {
                board[i][k].setTileValue(tempStore[k]);
            }
            
            // reset tempStore for new row
            tempStore = new int[4];
        }
        
        // updates number of moves
        moveCounter++;
    }
    
    /**
    * Inputs: None
    * Outputs: None
    * Description: moves all tiles in the board to the upmost position by
    *              iterating through rows to find all non-zero values, creating a
    *              new row with those new values in the up-most place and
    *              inserting that row in place of the previous one
    */
    public void shiftUp() {
        int[] tempStore = new int[4];

        // iterate through every column
        for (int i = 0; i < 4; i++) {
            int tracker = 3;
            /* takes in one column and captures each non-zero value in array as
            * as a new column
            */
            for (int j = 3; j >= 0; j--) {
                if (board[j][i].getTileValue() != 0) {
                    tempStore[tracker] = board[j][i].getTileValue();
                    tracker--;
                }
            }
            
            // insert new shifted column to replace column in new board
            for (int k = 0; k < 4; k++) {
                board[k][i].setTileValue(tempStore[k]);
            }
            
            // reset tempStore for new column
            tempStore = new int[4];
        }
        
        // updates number of moves
        moveCounter++;
    }
    
    /**
    * Inputs: None
    * Outputs: None
    * Description: moves all tiles in the board to the bottommost position by
    *              iterating through rows to find all non-zero values, creating a
    *              new row with those new values in the bottom-most place and
    *              inserting that row in place of the previous one
    */
    public void shiftDown() {
        int[] tempStore = new int[4];
        
        // iterate through every row and column
        for (int i = 0; i < 4; i++) {
            int tracker = 0;
            
            /* takes in one column and captures each non-zero value in array as
            * as a new column
            */
            for (int j = 0; j < 4; j++) {
                if (board[j][i].getTileValue() != 0) {
                    tempStore[tracker] = board[j][i].getTileValue();
                    tracker++;
                }
            }
            
            // insert new shifted column to replace column in new board
            for (int k = 0; k < 4; k++) {
                board[k][i].setTileValue(tempStore[k]);
            }
            
            // reset tempStore for new column
            tempStore = new int[4];
        }
        
        // updates number of moves
        moveCounter++;
    }
    
    /**
    * Inputs: None
    * Outputs: None
    * Description: combines all like tiles in row when moving left by taking tiles
    *              tiles with the same value and adding them together toward the
    *              left, then shifting everything left so that there are no gaps
    *              in between numbers
    */
    public void combineLeft() {
        for (int i = 0; i < 4; i++) {
            int first = 0;
            
            while (first < 4 && board[i][first].getTileValue() == 0) {
                first++;
            }
            
            while (first < 4) {
                int second = first + 1;

                while (second < 4 && board[i][second].getTileValue() == 0) {
                    second++;
                }

                if (second < 4 && board[i][first].getTileValue() ==
                board[i][second].getTileValue()) {
                    board[i][first].setTileValue(board[i][first].getTileValue() * 2);
                    board[i][second].setTileValue(0);
                    scoreCounter += board[i][first].getTileValue();
                }

                first = second;
            }
        }
        
        shiftLeft();
    }
    
    /**
    * Inputs: None
    * Outputs: None
    * Description: combines all like tiles in row when moving right by taking tiles
    *              with the same value and adding them together toward the
    *              right, then shifting everything right so that there are no gaps
    *              in between numbers
    */
    public void combineRight() {
        for (int i = 0; i < 4; i++) {
            int first = 3;
            
            while (first >= 0 && board[i][first].getTileValue() == 0) {
                first--;
            }
            
            while (first >= 0) {
                int second = first - 1;
                
                while (second >= 0 && board[i][second].getTileValue() == 0) {
                    second--;
                }

                if (second >= 0 && board[i][first].getTileValue() ==
                board[i][second].getTileValue()) {
                    board[i][first].setTileValue(board[i][first].getTileValue() * 2);
                    board[i][second].setTileValue(0);
                    scoreCounter += board[i][first].getTileValue();
                }

                first = second;
            }
        }
        
        shiftRight();
    }
    
    /**
    * Inputs: None
    * Outputs: None
    * Description: combines all like tiles in row when moving down by taking tiles
    *              with the same value and adding them together toward the
    *              bottom, then shifting everything down so that there are no gaps
    *              in between numbers
    */
    public void combineDown() {
        for (int i = 0; i < 4; i++) {
            int first = 0;
            
            while (first < 4 && board[first][i].getTileValue() == 0) {
                first++;
            }
            
            while (first < 4) {
                int second = first + 1;
                
                while (second < 4 && board[second][i].getTileValue() == 0) {
                    second++;
                }
                
                if (second < 4 && board[first][i].getTileValue() ==
                board[second][i].getTileValue()) {
                    board[first][i].setTileValue(board[first][i].getTileValue() * 2);
                    board[second][i].setTileValue(0);
                    scoreCounter += board[first][i].getTileValue();
                }
                
                first = second;
            }
        }
        
        shiftDown();
    }
    
    /**
    * Inputs: None
    * Outputs: None
    * Description: combines all like tiles in row when moving up by taking tiles
    *              with the same value and adding them together toward the
    *              top, then shifting everything up so that there are no gaps
    *              in between numbers
    */
    public void combineUp() {
        for (int i = 0; i < 4; i++) {
            int first = 3;
            
            while (first >= 0 && board[first][i].getTileValue() == 0) {
                first--;
            }
            
            while (first >= 0) {
                int second = first - 1;
                
                while (second >= 0 && board[second][i].getTileValue() == 0) {
                    second--;
                }
                
                if (second >= 0 && board[first][i].getTileValue() ==
                board[second][i].getTileValue()) {
                    board[first][i].setTileValue(board[first][i].getTileValue() * 2);
                    board[second][i].setTileValue(0);
                    scoreCounter += board[first][i].getTileValue();
                }
                
                first = second;
            }
        }
        
        shiftUp();
    }
    
    /**
    * Inputs: None
    * Outputs: integer representing the value of the largest tile in the board,
    *          eventually allowing for one to determine if 2048 has been reached
    * Description: assists in accessing and returning the value of the largest tile
    *              in the board
    */
    public int getHighestValue() {
        int tempHighValue = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].getTileValue() > tempHighValue) {
                    tempHighValue = board[i][j].getTileValue();
                }
            }
        }
        
        return tempHighValue;
    }
    
    /**
    * Inputs: None
    * Outputs: boolean to determine whether or not the game is finished
    * Description: checks if the game is over by iterating through each tile and
    *              seeing if there are any tiles with the same value within the
    *              surrounding perimter; if there are none, then the game is over
    */
    public boolean isGameOver() {
        boolean gameIsOver = false;
        int tileChecker = 0;
        
        // test top left corner
        if (board[0][0].getTileValue() != board[0][1].getTileValue() &&
        board[0][0].getTileValue() != board[1][0].getTileValue()) {
            tileChecker++;
        }
        
        // test top right corner
        if (board[0][3].getTileValue() != board[0][2].getTileValue() &&
        board[0][3].getTileValue() != board[1][3].getTileValue()) {
            tileChecker++;
        }
        
        // test bottom left corner
        if (board[3][0].getTileValue() != board[2][0].getTileValue() &&
        board[3][0].getTileValue() != board[3][1].getTileValue()) {
            tileChecker++;
        }
        
        // test bottom right corner
        if (board[3][3].getTileValue() != board[3][2].getTileValue() &&
        board[3][3].getTileValue() != board[2][3].getTileValue()) {
            tileChecker++;
        }
        
        // test the left border tiles that are not corners
        for (int i = 1; i < 3; i++) {
            if (board[i][0].getTileValue() != board[i][1].getTileValue() &&
            board[i][0].getTileValue() != board[i - 1][0].getTileValue() &&
            board[i][0].getTileValue() != board [i + 1][0].getTileValue()) {
                tileChecker++;
            }
        }
        
        // test top border tiles that are not corners
        for (int i = 1; i < 3; i++) {
            if (board[0][i].getTileValue() != board[0][i - 1].getTileValue() &&
            board[0][i].getTileValue() != board[0][i + 1].getTileValue() &&
            board[0][i].getTileValue() != board[1][i].getTileValue()) {
                tileChecker++;
            }
        }
        
        // test right border tiles that are not corners
        for (int i = 1; i < 3; i++) {
            if (board[i][3].getTileValue() != board[i - 1][3].getTileValue() &&
            board[i][3].getTileValue() != board[i + 1][3].getTileValue() &&
            board[i][3].getTileValue() != board[i][2].getTileValue()) {
                tileChecker++;
            }
        }
        
        // test bottom border tiles that are not corners
        for (int i = 1; i < 3; i++) {
            if (board[3][i].getTileValue() != board[3][i - 1].getTileValue() && 
            board[3][i].getTileValue() != board[3][i + 1].getTileValue() &&
            board[3][i].getTileValue() != board[2][i].getTileValue()) {
                tileChecker++;
            }
        }
        
        // test the center tiles of board's second row
        for (int i = 1; i < 3; i++) {
            if (board[1][i].getTileValue() != board[1][i - 1].getTileValue() &&
            board[1][i].getTileValue() != board[0][i].getTileValue() &&
            board[1][i].getTileValue() != board[2][i].getTileValue() &&
            board[1][i].getTileValue() != board[1][i + 1].getTileValue()) {
                tileChecker++;
            }
        }
        
        // test the center tiles of board's third row
        for (int i = 1; i < 3; i++) {
            if (board[2][i].getTileValue() != board[2][i - 1].getTileValue() &&
            board[2][i].getTileValue() != board[1][i].getTileValue() &&
            board[2][i].getTileValue() != board[3][i].getTileValue() &&
            board[2][i].getTileValue() != board[2][i + 1].getTileValue()) {
                tileChecker++;
            }
        }
        
        // determines if game is still playable
        if (tileChecker == 16) {
            return true;
            } else {
            return false;
        }
    }
}

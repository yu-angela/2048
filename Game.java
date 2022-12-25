/*  Name: Angela Yu
*  PennKey: yuangel
*  Recitation: 206
*  Execution: java Game
*
*  A class that represents and enables actual gameplay, allowing for 2048 to run
*  until there are no further possible moves (displays lose message with the number
*  of moves) or a correct creation of 2048 (displays victory message with the
*  number of moves). Moves can only be made if tiles are shifting and will only
*  generate a new tile when tiles are being shifted around the board.
*
*  The game counts the number of moves made and includes this in the victory or
*  defeat message. The game also counts the points being earned during gameplay.
*/
public class Game {
    public static void main(String[] args) {
        Grid gameBoard = new Grid();
        
        // set up gameboard
        PennDraw.setCanvasSize(700, 700);
        PennDraw.enableAnimation(45);
        
        // initialize variables for score keeping and number of moves
        int scoreKeeper = 0;
        int moveKeeper = 0;
        
        // initialize variable to track game active or not
        boolean isGameActive = true;
        
        // while game is active, track tile movements when certain keys are hit
        while (isGameActive) {
            if (PennDraw.hasNextKeyTyped()) {
                char pressed = PennDraw.nextKeyTyped();
                
                /* must check if the move being entered does anything before
                *  spawning a new tile by iterating through gameboard array
                */
                if (pressed == 'w') {
                    int[][] comparisonBoard = gameBoard.getBoard();
                    boolean isEqual = true;
                    
                    gameBoard.combineUp();
                    
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (comparisonBoard[i][j] !=
                            gameBoard.getBoard()[i][j]) {
                                isEqual = false;
                            }
                        }
                    }
                    
                    if (!isEqual) {
                        gameBoard.spawnTile();
                    }
                    
                    PennDraw.clear();
                } else if (pressed == 'a') {
                    int[][] comparisonBoard = gameBoard.getBoard();
                    boolean isEqual = true;
                    
                    gameBoard.combineLeft();
                    
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (comparisonBoard[i][j] !=
                            gameBoard.getBoard()[i][j]) {
                                isEqual = false;
                            }
                        }
                    }
                    
                    if (!isEqual) {
                        gameBoard.spawnTile();
                    }
                    
                    PennDraw.clear();
                } else if (pressed == 's') {
                    int[][] comparisonBoard = gameBoard.getBoard();
                    boolean isEqual = true;
                    
                    gameBoard.combineDown();
                    
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (comparisonBoard[i][j] !=
                            gameBoard.getBoard()[i][j]) {
                                isEqual = false;
                            }
                        }
                    }
                    
                    if (!isEqual) {
                        gameBoard.spawnTile();
                    }
                    
                    PennDraw.clear();
                } else if (pressed == 'd') {
                    int[][] comparisonBoard = gameBoard.getBoard();
                    boolean isEqual = true;
                    
                    gameBoard.combineRight();
                    
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (comparisonBoard[i][j] !=
                            gameBoard.getBoard()[i][j]) {
                                isEqual = false;
                            }
                        }
                    }
                    
                    if (!isEqual) {
                        gameBoard.spawnTile();
                    }
                    
                    PennDraw.clear();
                }
            } 
            
            // draws background of board in black
            PennDraw.setPenColor(0, 0, 0);
            PennDraw.filledSquare(0.5, 0.45, 0.4);
            
            // writes name of game (2048) in top left corner
            PennDraw.setFontBold();
            PennDraw.setFontSize(75);
            PennDraw.setPenColor(22, 26, 238);
            PennDraw.text(0.18, 0.92, "2048");
            
            // draw score and number of moves in top right corner
            scoreKeeper = gameBoard.getScore();
            PennDraw.setFontSize(30);
            PennDraw.text(0.65, 0.90, "" + scoreKeeper);
            PennDraw.setFontSize(15);
            PennDraw.setPenColor(101, 144, 199);
            PennDraw.text(0.65, 0.96, "Your Score:");
            
            moveKeeper = gameBoard.getMoveNumber();
            PennDraw.setFontSize(30);
            PennDraw.setPenColor(22, 26, 238);
            PennDraw.text(0.85, 0.90, "" + moveKeeper);
            PennDraw.setFontSize(15);
            PennDraw.setPenColor(101, 144, 199);
            PennDraw.text(0.85, 0.96, "Number of Moves:");
            
            /* loop through tile grids to draw the tiles on the board with no value 
            *  or their numerical value that is some exponent of 2 (color changes
            *  depending on value)
            */
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    String numberShown = "";
                    if (gameBoard.getBoard()[i][j] == 0) {
                        numberShown = "";
                        } else {
                        numberShown = "" + gameBoard.getBoard()[i][j];
                    }
                    
                    // change color per tile value "type"
                    if (gameBoard.getBoard()[i][j] == 0) {
                            PennDraw.setPenColor(255, 255, 255);
                        } else if (gameBoard.getBoard()[i][j] == 2) {
                            PennDraw.setPenColor(193, 219, 245);
                        } else if (gameBoard.getBoard()[i][j] == 4) {
                            PennDraw.setPenColor(173, 214, 255);
                        } else if (gameBoard.getBoard()[i][j] == 8) {
                            PennDraw.setPenColor(138, 196, 255);
                        } else if (gameBoard.getBoard()[i][j] == 16) {
                            PennDraw.setPenColor(128, 168, 255);
                        } else if (gameBoard.getBoard()[i][j] == 32) {
                            PennDraw.setPenColor(128, 142, 255);
                        } else if (gameBoard.getBoard()[i][j] == 64) {
                            PennDraw.setPenColor(130, 128, 255);
                        } else if (gameBoard.getBoard()[i][j] == 128) {
                            PennDraw.setPenColor(147, 128, 255);
                        } else if (gameBoard.getBoard()[i][j] == 256) {
                            PennDraw.setPenColor(166, 128, 255);
                        } else if (gameBoard.getBoard()[i][j] == 512) {
                            PennDraw.setPenColor(183, 128, 255);
                        } else if (gameBoard.getBoard()[i][j] == 1024) {
                            PennDraw.setPenColor(208, 128, 233);
                        } else if (gameBoard.getBoard()[i][j] == 2048) {
                            PennDraw.setPenColor(255, 128, 204);
                    }
                    
                    // generate location of each new tile by determining coordinates
                    double tileY = 0.15 + i * 0.2;
                    double tileX = 0.2 + j * 0.2;
                    PennDraw.filledSquare(tileX, tileY, 0.075);
                    
                    // generate text on each tile showing the value of the tile
                    PennDraw.setPenColor(0, 0, 0);
                    PennDraw.setFontSize(35);
                    PennDraw.text(tileX, tileY, numberShown);
                }
            }
            
            // declares if the game is won (if 2048 is reached)
            if (gameBoard.getHighestValue() == 2048) {
                PennDraw.setPenColor(255, 229, 176);
                PennDraw.filledRectangle(0.5, 0.5, 0.4, 0.2);
                PennDraw.setPenColor(0, 0, 0);
                PennDraw.text(0.5, 0.5, "You win! " + moveKeeper + " moves made.");
            }
            
            // test to see if game is over
            boolean isFull = true;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (gameBoard.getBoard()[i][j] == 0) {
                        isFull = false;
                    }
                }
            }
            
            if (isFull) {
                isGameActive = !gameBoard.isGameOver();
            }
            
            PennDraw.advance();
        }
        
        // declares if game is lost (no more possible moves) and stops animation
        PennDraw.disableAnimation();
        PennDraw.setPenColor(191, 190, 187);
        PennDraw.filledRectangle(0.5, 0.5, 0.4, 0.2);
        PennDraw.setPenColor(0, 0, 0);
        PennDraw.text(0.5, 0.5, "You lose! " + moveKeeper + " moves made.");
    }
}

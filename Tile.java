/*  Name: Angela Yu
*  PennKey: yuangel
*  Recitation: 206
*  Execution: N/A (no main method)
*
*  A class that allows for Tiles to be generated with unique values that represent
*  exponential increases of 2. Tiles are accessed later for gameplay.
*/
public class Tile {
    private int tileValue;
    
    // constructs empty tile
    public Tile() {
        tileValue = 0;
    }
    
    // constructs tile with a specific tile value
    public Tile(int tileValue) {
        this.tileValue = tileValue;
    }
    
    /**
    * Inputs: None
    * Outputs: integer value of the chosen tile
    * Description: allows access to private tileValue variable
    */
    public int getTileValue() {
        return tileValue;
    }
    
    /**
    * Inputs: integer representing the value the tile should hold after a move
    * Outputs: None (void)
    * Description: gives access to change a tile's value
    */
    public void setTileValue(int newTileValue) {
        tileValue = newTileValue;
    }
}

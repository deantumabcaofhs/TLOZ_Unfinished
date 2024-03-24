//Ver 6.2.7:42PM (mth.day.hr:min)




public class GameBoard {
    private int[][] grid;                 // the grid that stores the pieces


    public GameBoard(int width, int height) {
        grid = new int[height][width];
    }


    public void nextLevel(int level){
        if (level == 0) {
            for(int h = 0; h < grid.length; h++){
                for(int w = 0; w < grid[h].length; w++){
                    grid[h][w] = 1;
                }
            }




            for(int w = 0; w < 2; w++) { //LEFT SIDE
                for (int h = 0; h < grid.length; h++) {
                    grid[h][w] = 2;
                }
            }




            for(int w = 8; w < 10; w++) { //RIGHT SIDE
                for (int h = 0; h < grid.length; h++) {
                    grid[h][w] = 2;
                }
            }
            for(int h = 8; h < 10; h++) { //FULL SIDE
                for (int w = 1; w < grid.length; w++) {
                    grid[h][w] = 2;
                }
            }








            for(int h = 0; h < 2; h++) { //GAP
                for (int w = 1; w < 4; w++) {
                    grid[h][w] = 2;
                }for(int w = 6; w < 8; w++){
                    grid[h][w] = 2;
                }
            }
        }
        if (level == 1) {
            for(int h = 0; h < grid.length; h++){
                for(int w = 0; w < grid[h].length; w++){
                    grid[h][w] = 1;
                }
            }
            for(int w = 0; w < 4; w++) { //LEFT SIDE
                for (int h = 0; h < grid.length; h++) {
                    grid[h][w] = 2;
                }
            }
            for(int w = 6; w < 10; w++) { //RIGHT SIDE
                for (int h = 0; h < grid.length; h++) {
                    grid[h][w] = 2;
                }
            }
        }if (level == 2) {
            for(int h = 0; h < grid.length; h++){
                for(int w = 0; w < grid[h].length; w++){
                    grid[h][w] = 1;
                }
            }
            grid[0][4] = 3;
            grid[0][5] = 3;


        }if (level == 3) {
        }
    }


    public boolean move(int row, int col) {
        System.out.println("[DEBUGGING INFO] You clicked in row " + row + " and column " + col);
        // check if move is not valid.  If so, return false.
        return true; // if move was valid, return true
    }


    public boolean isGameOver() {
        /*** YOU COMPLETE THIS METHOD ***/
        return false;
    }


    public int[][] getGrid() {
        return grid;
    }


    // Return true if the row and column in location loc are in bounds for the grid
    public boolean isInGrid(int row, int col) {
        return false;
    }
}
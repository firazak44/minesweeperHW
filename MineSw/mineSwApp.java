package MineSw;

public class mineSwApp{
    private int rows;
    private int columns;
    private int mines;
    private int board[][];
    private int visibleBoard[][];

    public mineSwApp(int inputRows, int inputColumns, int inputMines) { 
        rows = inputRows;
        columns = inputColumns;
        mines = inputMines;
        visibleBoard = new int[rows][columns];
        board = this.createBoard();
        board = populateBoard(board);
        visibleBoard();
    }

    private int[][] populateBoard(int inputBoard[][]) { //Mines randomizer
        while (mines != 0) {
            int a = (int)(Math.random()*rows);
            int b = (int)(Math.random()*columns);
            if (inputBoard[a][b] == 0) {
                inputBoard[a][b] = 9;
                mines -= 1;
            }
        }

    private void visibleBoard() { // Generates a blank "visible board"
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < columns; j++) {
            visibleBoard[i][j] = -1;
        }
    }
    printBoard(visibleBoard);
}
}
package MineSw;

import java.util.*;

public class mineSw {
    //Declare layout by Initializing two arrays
    private int[][] hiddenPlot = new int[10][10];
    private int[][] visiblePlot = new int[10][10];
    public static void main(String[] args){
        //Generate the game with the fixed settings
        mineSw game = new mineSw();
        game.startGame();
    }

    public void startGame(){
        //Initialize Stage
        System.out.println("\nInitializing Minesweeper...\n");
        setStage(1);

        //Loop until win/lose cond
        boolean run = true;
        while(run){
            dispVisiblePlot();
            run = playMove();
            if(checkWin()){
                dispHiddenPlot();
                System.out.println("\nYOU LIVE!");
                break;
            }
        }
    }

    public void setStage(int diff){
        //Randomise & set position for 'n'th-number of mines
        int mines=0;
        while(mines!=10){
            Random rnd = new Random();
            int i = rnd.nextInt(10);
            int j = rnd.nextInt(10);
            hiddenPlot[i][j] = 100;
            mines++;
        }
        buildHidden(); 
    }

    //proximity detection around neighbouring cells
    public void buildHidden(){
        for(int i=0; i < 10; i++){
            for (int j=0; j < 10; j++){
                if(hiddenPlot[i][j] !=100){
                    int cnt =0;
                    if(i!=0)
                    {
                        if(hiddenPlot[i-1][j]==100) cnt++;
                        if(j!=0)
                        {
                            if(hiddenPlot[i-1][j-1]==100) cnt++;
                        }

                    }
                    if(i!=9)
                    {
                        if(hiddenPlot[i+1][j]==100) cnt++;
                        if(j!=9)
                        {
                            if(hiddenPlot[i+1][j+1]==100) cnt++;
                        }
                    }
                    if(j!=0)
                    {
                        if(hiddenPlot[i][j-1]==100) cnt++;
                        if(i!=9)
                        {
                            if(hiddenPlot[i+1][j-1]==100) cnt++;
                        }
                    }
                    if(j!=9)
                    {
                        if(hiddenPlot[i][j+1]==100) cnt++;
                        if(i!=0)
                        {
                            if(hiddenPlot[i-1][j+1]==100) cnt++;
                        }
                    }

                    hiddenPlot[i][j] = cnt;
                }
            }
        }
    }
    
    //Display progress
    public void dispVisiblePlot() {
        System.out.print("\t ");
        for(int i=0; i<10; i++)
        {
            System.out.print(" " + i + "  ");
        }System.out.print("\n");
        for (int i = 0; i < 10; i++) {System.out.print(i + "\t| ");
            for (int j = 0; j < 10; j++) {
                if(visiblePlot[i][j] ==0){
                    System.out.print(" ");
                }else if(visiblePlot[i][j]==50){
                    System.out.print("0");
                }else{
                    System.out.print(visiblePlot[i][j]);
                }System.out.print(" | ");
            }System.out.print("\n");
        }
    }

    //Implement Input
    public boolean playMove(){
        Scanner kb= new Scanner(System.in);
        System.out.print("\nDig where? Input as row & col");
        System.out.print("\nRow: ");
        int i= kb.nextInt();
        System.out.print("Col: ");
        int j= kb.nextInt();

        if(i<0 || i>9 || j<0 || j>9 || visiblePlot[i][j]!=0)
        {
            System.out.print("INPUT ERROR\n");
            return true;
        }

        if(hiddenPlot[i][j] ==100){
            dispHiddenPlot();
            System.out.println("YOU DIED");
            return false;
        }else if(hiddenPlot[i][j]==0){
            //fixVisible(i,j);
        }else{
            //fixNeighbours(i,j);
            System.out.print(visiblePlot[i][j]);
        }

        return true;
    }

    //TODO fixNeighbours and fixVisible//

    // Check for win requirements
    public boolean checkWin()
    {
        for(int i=0; i<10; i++)
        {
            for(int j=0; j<10; j++)
            {
                if(visiblePlot[i][j]==0)
                {
                    if(hiddenPlot[i][j]!=100)
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //Show all plots
    public void dispHiddenPlot(){
        System.out.print("\t ");
        for(int i=0; i<10; i++)
        {
            System.out.print(" " + i + "  ");
        }
        System.out.print("\n");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + "\t| ");
            for (int j = 0; j < 10; j++) {
                if(hiddenPlot[i][j] ==0){
                    System.out.print(" ");
                }else if(hiddenPlot[i][j]==100){
                    System.out.print("X");
                }else{
                    System.out.print(visiblePlot[i][j]);
                }System.out.print(" | ");
            }System.out.print("\n");
        }
    }
}   

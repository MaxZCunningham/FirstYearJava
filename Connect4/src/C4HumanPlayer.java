import java.util.Scanner;

public class C4HumanPlayer extends ConnectPlayer{

    C4HumanPlayer(String name, int value){
        super(name,value);
    }

    @Override
    public void pickColumn(Connect4Grid2DArray grid) {
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.print("Please pick a column between 0 and 6 that isn't full in which to put your piece. ");
            if(input.hasNextInt()){
                int tmpValue = input.nextInt();
                if(tmpValue>=0 && tmpValue<=6 && !grid.isColumnFull(tmpValue)){
                    grid.dropPiece(this,tmpValue);
                    break;
                }
            }
            else{
                String value = input.next();
                System.err.println("ERROR: Invalid Input");
            }
        }
    }

    @Override
    public boolean isWinner(Connect4Grid2DArray grid) {
        if(winner || (grid.getLastPieceValue()==super.value && grid.didLastPieceConnect4())){
            winner=true;
        }
        return winner;
    }
}

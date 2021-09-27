import java.util.Random;
public class C4RandomAIPlayer extends ConnectPlayer {
    public C4RandomAIPlayer(String name, int value) {
        super(name, value);
    }

    @Override
    public void pickColumn(Connect4Grid2DArray grid) {
        Random rand = new Random();
        while(true) {
            int tmpValue = rand.nextInt(6);
            if (!grid.isColumnFull(tmpValue)) {
                grid.dropPiece(this,tmpValue);
                break;
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

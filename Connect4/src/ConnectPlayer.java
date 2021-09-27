public abstract class ConnectPlayer {
    private final String name;
    protected int value;
    boolean winner;

    ConnectPlayer(String name, int value){
        this.name=name;
        this.winner=false;
        if(value==1 || value==2) {
            this.value = value;
        }
        else{
            System.err.println("INVALID INPUT");
            System.exit(-111);
        }

    }
    public int getValue(){
        return value;
    }

    public String getName(){
        return name;
    }

    public abstract void pickColumn(Connect4Grid2DArray grid);

    public abstract boolean isWinner(Connect4Grid2DArray grid);

}

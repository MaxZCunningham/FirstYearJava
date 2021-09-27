public class Connect4Grid2DArray implements Connect4Grid {
    private int[][] grid;
    private final int numberOfColumns,numberOfRows;
    private int lastColumn,lastRow, lastPieceValue;

    Connect4Grid2DArray(int numberOfColumns, int numberOfRows){
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;
        this.lastPieceValue = -1;
        lastColumn=0;
        lastRow=0;
        this.emptyGrid();
    }

    @Override
    public void emptyGrid() {
        grid = new int[numberOfColumns][numberOfRows];
    }

    public int getLastPieceValue(){
        return lastPieceValue;
    }

    @Override
    public String toString(){
        String tmpString="     [0] [1] [2] [3] [4] [5] [6] \n";
        for(int i=0;i<grid[1].length;i++) { // rows
          boolean first = true;
          for(int a=-1;a< grid.length;a++){ // columns
              if(first){
                  tmpString += "["+i+"] | ";
                  first=false;
              }
              else{
                  tmpString += grid[a][i]+" | ";
              }
          }
          tmpString += "\n";
        }
        tmpString += "\n Last Piece Dropped: ["+lastColumn+"]["+lastRow+"]";
        return tmpString;
    }

    @Override
    public boolean isValidColumn(int column) {
        return (!isColumnFull(column) && (column >= 0) && (column <= numberOfColumns));
    }

    @Override
    public boolean isColumnFull(int column) {
        for(int i=0;i<grid[column].length;i++){
            if(grid[column][i]==0){
                return false;
            }
        }
        return true;
    }

    @Override
    public void dropPiece(ConnectPlayer player, int column) {
        if(isValidColumn(column)) {
            for (int i = numberOfRows - 1; i >= 0; i--) {
                if (grid[column][i] == 0) {
                    grid[column][i] = player.getValue();
                    lastColumn = column;
                    lastRow=i;
                    lastPieceValue = player.getValue();
                    break;
                }
            }
        }
    }

    @Override
    public boolean didLastPieceConnect4() { // i dont check the last place because that obviously has the last piece in it
        int count=0;
        for(int i=0;i<numberOfRows;i++){ // check vertical
            if(grid[lastColumn][i]==lastPieceValue){
                count++;
            }
            else{
                count=0;
            }

            if(count>=4){
                return true;
            }
        }
        count=0;
        for(int i=0;i<numberOfColumns;i++){ // check horizontal
            if(grid[i][lastRow]==lastPieceValue){
                count++;
            }
            else{
                count=0;
            }

            if(count>=4){
                return true;
            }
        }
        count=0;
        for(int i=-3;i<4;i++){// check diagonal left to right up to down
            if((lastRow-i)>=0 && (lastRow-i)<numberOfRows && lastColumn+i>=0 && lastColumn+i<numberOfColumns){
                if(grid[lastColumn+i][lastRow-i]==lastPieceValue) {
                    count++;
                }
                else{
                    count=0;
                }
            }
            else{
                count=0;
            }

            if(count>=4){
                return true;
            }
        }
        count=0;
        for(int i=-3;i<4;i++){// check diagonal left to right down to up
            if(lastRow+i>=0 && lastRow+i<numberOfRows && lastColumn+i>=0 && lastColumn+i<numberOfColumns){
                if(grid[lastColumn+i][lastRow+i]==lastPieceValue) {
                    count++;
                }
                else{
                    count=0;
                }
            }
            else{
                count=0;
            }
            if(count>=4){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isGridFull() {
        for(int i=0;i< grid.length;i++){
            if(!isColumnFull(i)){
                return false;
            }
        }
        return true;
    }
}

/* SELF ASSESSMENT

Connect4Game class (35 marks)
My class creates references to the Connect 4 Grid and two Connect 4 Players.
 It asks the user whether he/she would like to play/quit inside a loop.
  If the user decides to play then:
  1 . Connect4Grid2DArray is created using the Connect4Grid interface,
  2. the two players are initialised - must specify the type to be ConnectPlayer,
  3. the game starts. In the game, I ask the user where he/she would like to drop the piece.
   I perform checks by calling methods in the Connect4Grid interface.
   Finally a check is performed to determine a win.
Comment:My code does all of the above specifications and uses all defined methods while adding in some static
methods to clean up the main

Connect4Grid interface (10 marks)
I define all 7 methods within this interface.
Comment:I define all 7 methods as set out in the brief

Connect4Grid2DArray class (25 marks)
My class implements the Connect4Grid interface.
It creates a grid using a 2D array Implementation of the method to check whether the column
to drop the piece is valid.
 It provides as implementation of the method to check whether the column to drop the piece is full.
  It provides as implementation of the method to drop the piece.
  It provides as implementation of the method to check whether there is a win.
Comment:It creates the 2d array it checks if the column is valid i.e  0-6 and that the column isnt full
it checks if there is a win state after each move and implements how the piece is dropped by moving backwards through the array

ConnectPlayer abstract class (10 marks)
My class provides at lest one non-abstract method and at least one abstract method.
Comment:yes my class provides two getter-setter methods and two abstract classes for isWinner
and pickColumn

C4HumanPlayer class (10 marks)
My class extends the ConnectPlayer claas and overrides the abstract method(s).
It provides the Human player functionality.
Comment: yes my class overrides my abstract methods and replaces them with apropriate functionality
i.e asks the human player for which column they would like to drop their piece into ect

C4RandomAIPlayer class (10 marks)
My class extends the ConnectPlayer claas and overrides the abstract method(s).
It provides AI player functionality.
Comment:yes my class overrides my abstract methods and replaces them with apropriate functionality

Total Marks out of 100:100

*/
import java.util.Scanner;
public class Connect4Game {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        boolean playing=false;

        System.out.println("\n   Connect 4 Game");
         if(askToPlay()){
             playing=true;
         }

        Connect4Grid2DArray game ;
        ConnectPlayer player1 ,player2 ;

        while(playing) {
             game = new Connect4Grid2DArray(7, 6);
             player1 = null;
             player2 = null;

            while(player1==null || player2 == null) {
                player1 = choosePlayer(1);
                player2 = choosePlayer(2);
                if(player1==null || player2 == null){
                    System.err.println("ERROR: TRY AGAIN");
                }
            }

            System.out.println("");
            boolean exit = false;
            boolean turnP1 = true;
            int roundNumber = 1;
            while (!exit) {
                if (turnP1) {
                    printBoard(game, player1, roundNumber);
                    player1.pickColumn(game);
                    turnP1 = false;
                } else {
                    printBoard(game, player2, roundNumber);
                    player2.pickColumn(game);
                    turnP1 = true;

                }
                if (game.isGridFull() || player1.isWinner(game) ||  player2.isWinner(game)) {
                    exit = true;
                }
                roundNumber++;
            }
            printBoard(game, player1, player2);
            System.out.print("Good game, now I have to ask again. ");
            if(!askToPlay()){
                playing=false;
            }
        }
    }

    public static boolean askToPlay(){
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.print("Would you like to play? [yes/no] ");
            while (!input.hasNext()) {}
            String tmpString = input.next();
            if(tmpString.equalsIgnoreCase("yes")){
                System.out.println("Lets begin");
                return true;
            } else if(tmpString.equalsIgnoreCase("no")){
                System.out.println("Ok Goodbye");
                return false;
            } else{
                System.out.println("Invalid input please answer yes or no.");
            }
        }
    }

    public static ConnectPlayer choosePlayer(int value){
        if(value==1 || value == 2) {
            Scanner input = new Scanner(System.in);
            while (true) {
                    System.out.print("\nWould you like player" + value + " to be Human or AI operated? [Human/AI] ");
                while(!input.hasNext()){}
                if(input.hasNextLine()) {
                    String userInput = input.next();
                    if (userInput.equalsIgnoreCase("Human")) {
                        while (true) {
                            System.out.print("What is the name of the Player? ");
                            if (input.hasNext()) {
                                String playerName = input.next();
                                System.out.println("Player"+value+" is "+playerName+" and is human controlled\n");
                                return new C4HumanPlayer(playerName, value);
                            }
                        }
                    } else if (userInput.equalsIgnoreCase("AI")) {
                        System.out.println("Player"+value+" is AI"+value+" and is computer controlled\n");
                        return new C4RandomAIPlayer("AI" + value, value);
                    }
                }
            }
        }
        else{
            return null;
        }
    }

    public static void printBoard(Connect4Grid2DArray grid,ConnectPlayer player, int roundNumber){
        String tmpString = "   Round: "+roundNumber+"  Turn: "+player.getName()+"\n";
        tmpString += grid.toString();
        if(roundNumber>=2){tmpString += "   Last Piece Value: " + (player.getValue()==1?"2":"1");}

        System.out.println(tmpString+"\n");
    }

    public static void printBoard(Connect4Grid2DArray grid,ConnectPlayer player1,ConnectPlayer player2){
        String tmpString = "\n    GAME OVER:\n";
        tmpString += grid.toString()+"\n";
        if(player1.isWinner(grid)){
            tmpString += "   The winner is "+player1.getName();
        }
        else if(player2.isWinner(grid)){
            tmpString += "   The winner is "+player2.getName();
        }
        else{
            tmpString += "The game is a draw.";
        }

        System.out.println(tmpString);
    }

}

/* SELF ASSESSMENT

1. ResolveBet

I have correctly defined ResolveBet which takes the bet type (String) and the Wallet object, and a void return type [Mark out of 7:7 ].
Comment:yes it takes in the bet and wallet
My program presents the amount of cash in the wallet and asks the user how much he/she would like to bet [Mark out of 8: 8].
Comment: yes it says the money left in their wallet
My program ensures the bet amount is not greater than the cash in the wallet [Mark out of 5:5 ].
Comment:yes it checks that
My program creates three Dice objects, rolls them and creates a total variable with a summation of the roll values returned [Mark out of 15: 15]..
Comment: when the dice are created the roll occurs in the constructor
My program determines the winnings by comparing the bet type with the total and comparing the bet type with the dice faces for the triple bet [Mark out of 20: 20].
Comment: yes it does all of the above
My program outputs the results (win or loss) and adds the winnings to the wallet if user wins or removes the bet amount from the wallet if the user loses [Mark out of 10: 10].
Comment:yes

2. Main

I ask the user for the amount of cash he/she has, create a Wallet object and put this cash into it [Mark out of 15: 15]
Comment:yes
My program loops continuously until the user either enters quit or the cash in the wallet is 0 [Mark out of 5:5 ]
Comment:yes using an exit boolean
I ask the user to enter any of the four bet types or quit [Mark out of 5: 5].
Comment:yes
My program calls resolveBet for each bet type entered [Mark out of 5:5 ].
Comment:yes
At the end of the game my program presents a summary message regarding winnings and losses [Mark out of 5:5 ]
Comment:yes

 Total Mark out of 100 (Add all the previous marks):100
*/
import java.util.Scanner;
public class Main {
    public static  void main(String[] args){
        Scanner input = new Scanner(System.in);
        boolean exitClause=false;
        boolean walletSet=false;
        Wallet yourWallet= new Wallet();
        while(walletSet==false && exitClause == false){
            System.out.print("How much money are you gambling with: ");
            if(input.hasNextInt()){
                int userInt= input.nextInt();
                if(userInt>0 && userInt<Integer.MAX_VALUE) {
                    yourWallet.setInitialamount(userInt);
                    walletSet = true;
                    System.out.println("Thank you, lets begin.");
                    System.out.println("");
                }
                else{
                    System.out.println("Please enter a valid integer (>0) or type EXIT");
                }
            }
            else{
                String userInput= input.next();
                if(userInput.equalsIgnoreCase("EXIT")){
                    System.out.println("Goodbye");
                    exitClause=true;
                }
                else {
                    System.out.println("Please enter a valid integer (>0) or type EXIT");
                }
            }
        }

        boolean Title = true;
        while(exitClause==false && yourWallet.getMoney()>0){
            if (Title==true) {
                System.out.println("Game " + (yourWallet.getNumberOfBets() + 1));
            }
            System.out.print("Place your bet (Triple,Field,High,Low) or type EXIT:");
            String userInput = input.next();
            if(userInput.equalsIgnoreCase("exit")||yourWallet.getMoney()<=0){
                exitClause=true;
            }
            else if(userInput.equalsIgnoreCase("triple")||userInput.equalsIgnoreCase("field")
            ||userInput.equalsIgnoreCase("high")||userInput.equalsIgnoreCase("low")){
                ResolveBet(userInput,yourWallet);
                Title = true;
            }
            else{
                Title = false;
                System.out.println("Please enter a valid bet or exit");
            }
        }
        Summary(yourWallet);
    }

    public static void ResolveBet(String bet, Wallet wallet) {
        Scanner input = new Scanner(System.in);
        boolean betPlaced = false;
        int betAmount = 0;

        while (betPlaced==false) {
            System.out.print("You have "+wallet.getMoney()+" in your wallet, how much would you like to bet: ");
            if (input.hasNextInt()) {
                int userInt= input.nextInt();
                if (userInt > 0 && userInt <= wallet.getMoney()) {
                    betAmount = userInt;
                    betPlaced = true;
                } else {
                    System.out.println("You only have " + wallet.getMoney() + " in your wallet please place an integer " +
                            "bet less then this and greater then 0");
                }
            } else {
                String userString = input.next();
                System.out.println("You only have " + wallet.getMoney() + " in your wallet please place an integer " +
                        "bet less then this and greater then 0");
            }
        }

        Dice d1 = new Dice();
        int d1Roll = d1.getRoll();
        Dice d2 = new Dice();
        int d2Roll = d2.getRoll();
        Dice d3 = new Dice();
        int d3Roll = d3.getRoll();
        int rollTotal = d1Roll + d2Roll + d3Roll;

        if (bet.equalsIgnoreCase("Triple")) {
            if (d1Roll == d2Roll && d2Roll == d3Roll && d1Roll != 1 && d1Roll != 6) {
                System.out.println("The dice rolled a " + d1Roll + ", " + d2Roll + " and " + d3Roll + ". You Wost "
                        + (betAmount * 30) + ".");
                wallet.changeMoney(betAmount * 30);
                wallet.increaseBetsWon();
            } else {
                System.out.println("The dice rolled a " + d1Roll + ", " + d2Roll + " and " + d3Roll + ".You Lost "
                        + betAmount + ".");
                wallet.changeMoney(-betAmount);
            }
        } else if (bet.equalsIgnoreCase("Field")) {
            if (rollTotal < 8 || rollTotal > 12) {
                System.out.println("The dice rolled a " + d1Roll + ", " + d2Roll + " and " + d3Roll
                        + "with a total of " + rollTotal + ". You won " + betAmount + ".");
                wallet.changeMoney(betAmount);
                wallet.increaseBetsWon();
            } else {
                System.out.println("The dice rolled a " + d1Roll + ", " + d2Roll + " and " + d3Roll + " with a total of "
                        + rollTotal + ". You Lost " + betAmount);
                wallet.changeMoney(-betAmount);
            }

        } else if (bet.equalsIgnoreCase("High")) {
            if (rollTotal > 10 && (d1Roll != d2Roll || d2Roll != d3Roll)) {
                System.out.println("The dice rolled a " + d1Roll + ", " + d2Roll + " and " + d3Roll + " with a total of "
                        + rollTotal + ". You Won " + betAmount);
                wallet.changeMoney(betAmount);
                wallet.increaseBetsWon();
            } else {
                System.out.println("The dice rolled a " + d1Roll + ", " + d2Roll + " and " + d3Roll + " with a total of "
                        + rollTotal + ". You Lost " + betAmount);
                wallet.changeMoney(-betAmount);
            }

        } else if (bet.equalsIgnoreCase("Low")) {
            if (rollTotal < 11 && (d1Roll != d2Roll || d2Roll != d3Roll)) {
                System.out.println("The dice rolled a " + d1Roll + ", " + d2Roll + " and " + d3Roll + " with a total of "
                        + rollTotal + ". You Won " + betAmount);
                wallet.changeMoney(betAmount);
                wallet.increaseBetsWon();
            } else {
                System.out.println("The dice rolled a " + d1Roll + ", " + d2Roll + " and " + d3Roll + " with a total of "
                        + rollTotal + ". You Lost " + betAmount);
                wallet.changeMoney(-betAmount);
            }
        } else {
            System.out.println("That is an invalid bet please place another one");
        }

        System.out.println("Your current balance is: "+wallet.getMoney());
        System.out.println("");
    }

    public static void  Summary(Wallet wallet) {
        int numberOfBets= wallet.getNumberOfBets();
        int numberOfWins = wallet.getBetsWon();
        double winPercentage = (numberOfWins*100 / numberOfBets);
        System.out.println(winPercentage);
        double lossPercentage=100-winPercentage;
        System.out.println("");
        System.out.println("You started gambling with " + wallet.getInitialamount() + ". You finished playing with "
                + wallet.getMoney() + ".");
        System.out.println("You placed a total of " + numberOfBets + (numberOfBets==1?" bet":" bets")
                +" and won " + numberOfWins + " of them.");
        System.out.println("Your stats are laid out below.");
        if (wallet.getNumberOfBets() > 0) {
            System.out.println("     Win percentage: " + winPercentage+ "%");
            System.out.println("     Loss percentage: "+lossPercentage+"%");
        } else {
            System.out.println("     Win percentage: 0%");
            System.out.println("     Loss percentage: 0%");
        }
        System.out.println("     Amount Won/Lost: " + (wallet.getMoney() - wallet.getInitialamount()));
        System.out.println("Thank You For Playing.");
    }
}

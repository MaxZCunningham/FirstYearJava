public class Wallet {
    private int money=0;
    private int initialAmount=0;
    private int numberOfBets=0;
    private int betsWon=0;



    public Wallet(){}

    public void setMoney(int amount){
        this.money=amount;
    }
    public void setInitialamount(int money){
        this.money=money;
        this.initialAmount=money;
    }

    public int getMoney(){
        return money;
    }

    public int getInitialamount(){
        return initialAmount;
    }

    public void changeMoney(int x){
        this.money = this.money + x;
        this.numberOfBets++;
    }

    public void increaseBetsWon(){
        betsWon++;
    }

    public int getNumberOfBets(){
            return numberOfBets;
    }

    public int getBetsWon(){
        return  betsWon;
    }
}

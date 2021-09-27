import java.util.Random;
public class Dice {
    int diceRoll;
    Random rd = new Random();

    public Dice() {
        this.diceRoll = rd.nextInt(6);
    }
    public int getRoll(){
        return diceRoll;
    }
}

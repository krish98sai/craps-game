import java.util.Random;
import java.util.Scanner;

/**
 * Created by krish98sai on 11/3/2016.
 */
public class PlayCraps {

    //Initialize variables.
    //Variables need to be static so they can be accessed within the methods.
    private static Scanner in = new Scanner(System.in);
    private static Random rand = new Random(setSeed());
    private static boolean firstRoll = true;
    private static int balance = 0;
    private static int bet = 0;
    private static int tempBet = 0;
    private static int sum = 0;
    private static int point = 0;
    private static boolean correctBet = false;

    public static void main(String[] args){

        System.out.println("How many chips do you want?");
        setChips(in.nextInt());

        while(true){

            if(balance == 0 && bet == 0)
                break;

            //Loop needed in case bet entered is invalid.
            while(!correctBet) {
                System.out.println("Enter bet.");
                tempBet = in.nextInt();
                in.nextLine();
                firstRoll = true;
                sum = 0;
                if (tempBet == 0)
                    break;
                else if (tempBet > balance)
                    System.out.println("That is not a valid bet.");
                else {
                    setCurrentBet(tempBet);
                    correctBet = true;
                }
            }

            if(tempBet == 0)
                break;

            rollDice();
            checkRoll();
        }


    }

    //Sets the seed for the Random generator.
    public static int setSeed(){
        //Ask the user for the random seed.
        System.out.println("Enter the seed.");
        //This variable is final because its value is set in stone.
        final int SEED = in.nextInt();
        return SEED;
    }

    //Sets the amount of chips for the player.
    public static void setChips(int newBalance){
        balance += newBalance;
    }

    //Sets the current bet for the roll.
    public static void setCurrentBet(int tempBet){
        bet = tempBet;
        balance -= bet;
    }

    //Roll 2 dice and return the values of each and the sum.
    public static void rollDice(){
        System.out.println("Press enter to roll.");
        in.nextLine();
        int die1 = rand.nextInt(6) + 1;
        int die2 = rand.nextInt(6) + 1;
        sum = die1 + die2;
        System.out.println("You rolled a " + die1 + " and " + die2 + ".");
        System.out.println("The total is " + sum + ".");
    }

    //Checks if the roll is a win, lose, or point.
    public static void checkRoll(){
        if(firstRoll){
            if(sum == 7 || sum == 11){
                setChips(bet*2);
                setCurrentBet(0);
                correctBet = false;
                System.out.println("You win! New balance is " + balance + ".");
            }
            else if (sum == 2 || sum == 3 || sum == 12){
                System.out.println("You lose! Balance is " + balance + ".");
                correctBet = false;
                setCurrentBet(0);
            }
            else{
                setPoint(sum);
                firstRoll = false;
            }
        }
        else{
            if(sum == 7) {
                System.out.println("You lose! Balance is " + balance + ".");
                correctBet = false;
                setCurrentBet(0);
            }
            else if(sum == point) {
                setChips(bet*2);
                setCurrentBet(0);
                correctBet = false;
                System.out.println("You win! New balance is " + balance + ".");
            }
            else
                System.out.println("Roll again. Point is " + point + ".");
        }
    }

    //Sets the point for the roll
    public static void setPoint(int roll){
        point = roll;
        System.out.println("The point is set to " + point + ".");
    }
}

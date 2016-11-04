import java.util.Random;

/**
 * Created by krish98sai on 11/4/2016.
 */
public class SimCraps{

    //Initialize variables.
    //Variables need to be static so they can be accessed within the methods.
    private static Random rand = new Random();
    private static double wins = 0;
    private static boolean firstRoll = true;
    private static boolean gameOver = false;

    public static void main(String[] args){

        //Initialize variables.
        double total = Integer.parseInt(args[0]);
        int count = 0;
        int sum;
        int point = 0;
        double probability;

        //In a while loop so it can run multiple trials
        while(count < total) {
            while(!gameOver) {
                sum = rollDice();
                point = checkRoll(sum, point);
            }
            count++;
            gameOver = false;
        }

        //Calculate the probability of winning for total number of runs.
        probability = (wins/total);

        //Return probability
        System.out.println("Probability of winning = " + probability);

    }

    //Roll 2 dice using Random and return sum.
    public static int rollDice(){
        int die1 = rand.nextInt(6) + 1;
        int die2 = rand.nextInt(6) + 1;
        int sum = die1 + die2;
        return sum;
    }

    //Checks if the roll is a win, lose, or point
    public static int checkRoll(int sum, int point){

        if(firstRoll){
            if(sum == 7 || sum == 11) {
                wins++;
                gameOver = true;
            }
            else if(sum == 2 || sum == 3 || sum == 12){
                gameOver = true;
                firstRoll = true;
            }
            else {
                point = setPoint(sum);
                firstRoll = false;
            }
        }
        else{
            if(sum == point) {
                wins++;
                firstRoll = true;
                gameOver = true;
            }
            else if(sum == 7) {
                firstRoll = true;
                gameOver = true;
            }
        }

        return point;
    }

    //Sets the point for the roll
    public static int setPoint(int point){
        return point;
    }
}

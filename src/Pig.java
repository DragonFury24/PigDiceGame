import java.util.Scanner;

public class Pig {
    static int turn = 1; //Keeps track of current turn
    static final short player1Turn = 1;
    static final short player2Turn = 2;
    static NumberCube numberCube1 = new NumberCube();
    static NumberCube numberCube2 = new NumberCube();
    static int currentTurnScore;

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        int player1Score = 0, player2Score = 0;
        String input;

        while (player1Score < 100 && player2Score < 100) {
            int thisTurn = turn;
            System.out.println();
            System.out.println("Player " + turn + " turn."); //current turn
            printOptions(); //available options
            input = keyboard.nextLine();
            if (input.equalsIgnoreCase("R") || input.equalsIgnoreCase("roll")) {
                playerRoll(numberCube1, numberCube2);//
                if (thisTurn != turn) {
                    //add score earned this turn to the total player score
                    player1Score = thisTurn == player1Turn ? player1Score + currentTurnScore : player1Score;
                    player2Score = thisTurn == player2Turn ? player2Score + currentTurnScore : player2Score;
                    currentTurnScore = 0;//resets score earned this turn because turn has changed
                }
            } else if (input.equalsIgnoreCase("E") || input.equalsIgnoreCase("end")) {
                //add score earned this turn to total player score
                player1Score = thisTurn == player1Turn ? player1Score + currentTurnScore : player1Score;
                player2Score = thisTurn == player2Turn ? player2Score + currentTurnScore : player2Score;
                changeTurn(); //Changes turn
                currentTurnScore = 0;
            } else if (input.equalsIgnoreCase("S") || input.equalsIgnoreCase("score")) { //Print score
                System.out.println("Player 1: " + player1Score);
                System.out.println("Player 2: " + player2Score);
            } else
                System.out.println("Please choose a valid option."); //Please choose a valid option
        }

        //Display winner
        if (player1Score > player2Score) {
            System.out.println("Player 1 wins!"); //Player 1 wins!
        } else {
            System.out.print("Player 2 wins!"); //Player 2 wins!
        }

        System.out.println();
        //Display scores of both players
        System.out.println("Scores"); //Scores
        System.out.println("Player 1: " + player1Score);
        System.out.println("Player 2: " + player2Score);
    }


    static void playerRoll(NumberCube numberCube1, NumberCube numberCube2) {
        //rolls both dice
        numberCube1.roll();
        numberCube2.roll();
        System.out.println("Rolls: " + numberCube1.getRoll() + " - " + numberCube2.getRoll()); //dice values
        //double roll score calculation
        if (numberCube1.getRoll() == numberCube2.getRoll()) {
            currentTurnScore += doubleRollScore(numberCube1, numberCube2);
            System.out.println("You've earned " + currentTurnScore + " points this turn!");
            return;
        }

        /*
        Single 1 roll
        resets score earned this turn
        changes turn
         */
        if (numberCube1.getRoll() == 1 || numberCube2.getRoll() == 1) {
            System.out.println("Oh no! You rolled a single 1! You earn no points."); //Oh no! You rolled a single 1! You earn no points.
            currentTurnScore = 0;
            changeTurn();
            return;
        }
        currentTurnScore = currentTurnScore + numberCube1.getRoll() + numberCube2.getRoll(); //add points earned from roll to the current score
        System.out.println("You've earned " + currentTurnScore + " points this turn!");
    }

    //calculate bonus points for rolling doubles
    static int doubleRollScore(NumberCube numberCube1, NumberCube numberCube2) {
        if (numberCube1.getRoll() == numberCube2.getRoll()) {
            switch (numberCube1.getRoll()) {
                case 1:
                    return 25; //Double 1 - 25 points
                case 2:
                    return 8; //Double 2 - 8 points
                case 3:
                    return 12; //Double 3 - 12 points
                case 4:
                    return 16; //Double 4 - 16 points
                case 5:
                    return 20; //Double 5 - 20 points
                case 6:
                    return 24; //Double 6 - 24 points
                default:
                    return 0;
            }
        }
        return 0;
    }

    //alternate turn
    static void changeTurn() {
        turn = turn == player1Turn ? player2Turn : player1Turn;
    }

    static void printOptions() {
        System.out.println();
        System.out.println("Type R to roll the dice."); //Type R to roll the dice.
        System.out.println("Type E or end to end your turn."); //Type E or end to end your turn
        System.out.println("Type S or score to see the total scores of both players."); //Type S or score to see the total scores of both players
    }
}

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
            System.out.println("Player " + turn + " turn.");
            printOptions();
            input = keyboard.nextLine();
            if (input.equalsIgnoreCase("R") || input.equalsIgnoreCase("roll")) {
                playerRoll(numberCube1, numberCube2);
                if (thisTurn != turn) {
                    player1Score = thisTurn == player1Turn ? player1Score + currentTurnScore : player1Score;
                    player2Score = thisTurn == player2Turn ? player2Score + currentTurnScore : player2Score;
                    currentTurnScore = 0;
                }
            } else if (input.equalsIgnoreCase("E") || input.equalsIgnoreCase("end")) {
                player1Score = thisTurn == player1Turn ? player1Score + currentTurnScore : player1Score;
                player2Score = thisTurn == player2Turn ? player2Score + currentTurnScore : player2Score;
                changeTurn();
                currentTurnScore = 0;
            } else if (input.equalsIgnoreCase("S") || input.equalsIgnoreCase("score")) {
                System.out.println("Player 1: " + player1Score);
                System.out.println("Player 2: " + player2Score);
            }
        }

        if (player1Score > player2Score) {
            System.out.println("Player 1 wins!");
        } else {
            System.out.print("Player 2 wins!");
        }

        System.out.println();
        System.out.println("Scores");
        System.out.println("Player 1: " + player1Score);
        System.out.println("Player 2: " + player2Score);
    }


    static void playerRoll(NumberCube numberCube1, NumberCube numberCube2) {
        numberCube1.roll();
        numberCube2.roll();
        System.out.println("Rolls: " + numberCube1.getRoll() + " - " + numberCube2.getRoll());
        if (numberCube1.getRoll() == numberCube2.getRoll()) {
            currentTurnScore += doubleRollScore(numberCube1, numberCube2);
            System.out.println("You've earned " + currentTurnScore + " points this turn!");
            return;
        }
        if (numberCube1.getRoll() == 1 || numberCube2.getRoll() == 1) {
            System.out.println("Oh no! You rolled a single 1! You earn no points.");
            currentTurnScore = 0;
            changeTurn();
            return;
        }
        currentTurnScore = currentTurnScore + numberCube1.getRoll() + numberCube2.getRoll();
        System.out.println("You've earned " + currentTurnScore + " points this turn!");
    }

    static int doubleRollScore(NumberCube numberCube1, NumberCube numberCube2) {
        if (numberCube1.getRoll() == numberCube2.getRoll()) {
            switch (numberCube1.getRoll()) {
                case 1:
                    return 25;
                case 2:
                    return 8;
                case 3:
                    return 12;
                case 4:
                    return 16;
                case 5:
                    return 20;
                case 6:
                    return 24;
                default:
                    return 0;
            }
        }
        return 0;
    }

    static void changeTurn() {
        if (turn == player1Turn)
            turn = player2Turn;
        else if (turn == player2Turn)
            turn = player1Turn;
    }

    static void printOptions() {
        System.out.println();
        System.out.println("Type R to roll the dice.");
        System.out.println("Type E or end to end your turn.");
        System.out.println("Type S or score to see the total scores of both players.");
    }
}

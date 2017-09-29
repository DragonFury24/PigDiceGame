import java.util.Scanner;

public class Pig {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        NumberCube numberCube = new NumberCube();
        int player1Score = 0, player2Score = 0;
        String input;

        //Values that identify the player turn
        final short player1Turn = 0;
        final short player2Turn = 1;

        int turn = 0; //Keeps track of current turn

        System.out.println("Player 1 turn.");
        System.out.println("Type R to roll the dice.");
        input = keyboard.nextLine();
        while (true) {
            if (input.equalsIgnoreCase("R")) {
                numberCube.roll();
                if (numberCube.getRoll() != 1) {
                    player1Score += numberCube.getRoll();
                    System.out.println("You rolled " + numberCube.getRoll() + "!");
                    System.out.println("Scores: " + "Player 1 - " + player1Score + " " + "Player 2 - " + player2Score + ".");
                    break;
                } else {
                    System.out.println("You rolled " + numberCube.getRoll() + "!");
                    turn = player2Turn;
                    System.out.println("Player 2 turn.");
                    break;
                }
            } else {
                System.out.println("Please type in R.");
            }
        }

        while (player1Score < 100 && player2Score < 100) {
            while (turn == player1Turn) {
                System.out.println("Type R to roll the dice or E to end your turn.");
                input = keyboard.nextLine();
                if (input.equalsIgnoreCase("R")) {
                    numberCube.roll();
                    if (numberCube.getRoll() != 1) {
                        player1Score = numberCube.isDoubleRoll() ? player1Score + numberCube.doubleRollScore() :
                                player1Score + numberCube.getRoll();
                        System.out.println("You rolled " + numberCube.getRoll() + "!");
                        System.out.println("Scores: " + "Player 1 - " + player1Score + " " + "Player 2 - " + player2Score + ".");
                        turn = numberCube.getRoll() == 1 ? player2Turn : turn;
                    }else {
                        System.out.println("You rolled " + numberCube.getRoll() + "!");
                        turn = player2Turn;
                        System.out.println("Player 2 turn.");
                    }
                } else if (input.equalsIgnoreCase("E")) {
                    turn = player2Turn;
                    System.out.println("Player 2 turn.");
                }
                if (player1Score >= 100)
                    break;
            }
            if (player1Score >= 100)
                break;

            while (turn == player2Turn) {
                System.out.println("Type R to roll the dice or E to end your turn.");
                input = keyboard.nextLine();
                if (input.equalsIgnoreCase("R")) {
                    player2Score += numberCube.roll();
                    System.out.println("You rolled " + numberCube.getRoll() + "!");
                    System.out.println("Scores: " + "Player 1 - " + player1Score + " " + "Player 2 - " + player2Score + ".");
                    turn = numberCube.getRoll() == 1 ? player1Turn : turn;
                    System.out.println("Player 1 turn.");
                } else if (input.equalsIgnoreCase("E")) {
                    turn = player1Turn;
                    System.out.println("Player 1 turn.");
                }
                if (player2Score >= 100)
                    break;
            }
        }
        if (player1Score > player2Score) //Output winner of the game
            System.out.println("Player 1 wins!");
        else System.out.print("Player 2 wins!");

    }
}

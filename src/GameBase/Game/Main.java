package GameBase.Game;

import GameBase.Base.Coordinate;
import GameBase.Chess.ChessBoard;
import GameBase.Chess.ChessGame;
import GameBase.Chess.Figures.King;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static private ChessGame chessGame;
    static private boolean isEndGame;
    static public int stepCounter;
    static private Coordinate[] coor;

    static {
        isEndGame = false;
        stepCounter = 0;
        coor = new Coordinate[2];
    }

    public static void main(String[] args) {
        newGame();
        while (!isEndGame) {
            showMessage();
            checkAnswer(readAnswer());
            makeStep();
        }
    }

    static private void newGame() {
        chessGame = ChessGame.getInstance();
        chessGame.newGame();
    }

    static private void showMessage() {
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_RESET = "\u001B[0m";

        System.out.println("Press \"exit\" to stop game!");
        System.out.println("Or input your next step, " + ANSI_YELLOW + (((stepCounter + 1) % 2 == 1) ? "mr.White" : "mr.Black") + ANSI_RESET);
        System.out.println("In type by example: 'A1-A2' Or in type by example: 'A1A2'");
        System.out.println("Step counter : " + stepCounter);
    }

    static private String readAnswer() {
        return (new Scanner(System.in)).nextLine().toLowerCase();
    }

    static private void checkAnswer(String st) {
        System.out.println("st = " + st);
        if (st.equals("exit")) {
            endGame();
        } else {
            isInputCorrectStep(st);
            showStepMessage(st);
        }
    }

    static private void makeStep() {
        chessGame.makeStep(coor);
    }


    static private void endGame() {
        System.out.println("Thank you for game! Bye!!! Bye!!!");
        isEndGame = true;
    }
    static public void end_Game(){
        endGame();
    }

    static private void isInputCorrectStep(String st) {
        coor = new Coordinate[2];
        char[] data = st.toCharArray();
        System.out.println(Arrays.toString(data));
        if (!(data.length == 4 || data.length == 5)) {
            System.out.println("Incorrect input data");
            stepCounter--;
        } else {
            int t = 0;
            for (int i = 0; i < 2; i++) {
                if ((data[i * 2 + t] >= 'a' && data[i * 2 + t] <= 'h')
                        && (data[i * 2 + 1 + t] >= '1' && data[i * 2 + 1 + t] <= '8'))
                    coor[i] = new Coordinate(data[i * 2 + t] - 'a', data[i * 2 + 1 + t] - '1');
                if (data.length == 5) t++;
            }
        }
    }

    static private void showStepMessage(String st) {
        System.out.println((((stepCounter) % 2 == 1) ? "Mr.White" : "Mr.Black") + ", your turn is: " + st);
        System.out.println("And coordinate on field are:" + Arrays.toString(coor));
    }
}
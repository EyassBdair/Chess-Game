package Game;

import Enums.Color;
import Piecees.Position;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ChessGame {
    static Scanner scanner = new Scanner(System.in);
    private static String nameWhitePlayer;
    private static String nameBlackPlayer;
    private boolean isWhiteTurn;
    private final SingletonBoard boardChess;

    private static ChessGame startGame;


    public ChessGame(String nameWhitePlayer, String nameBlackPlayer) {
        boardChess = SingletonBoard.getInstance(nameWhitePlayer, nameBlackPlayer);
        isWhiteTurn = true;
    }

    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }

    public static void start() {
        System.out.print("Enter White Player Name : ");
        nameWhitePlayer = scanner.nextLine();
        System.out.print("\nEnter Black Player Name : ");
        nameBlackPlayer = scanner.nextLine();

        startGame = new ChessGame(nameWhitePlayer, nameBlackPlayer);
        try {
            while (!startGame.endGame()) {
                SingletonBoard.printBoard();
                String move = startGame.readMove();
                if (startGame.isWhiteTurn())
                    startGame.whitePlaying(move, Color.WHITE);
                else
                    startGame.blackPlaying(move, Color.BLACK);

            }
            startGame.printWinnerName();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Enter 0 to end game \nEnter 1 to continue game ");
            int x = scanner.nextInt();
            if (x == 1)
                ChessGame.start();
            else
                System.exit(0);
        }
    }

    public boolean endGame() {
        return boardChess.isEndGame();
    }

    public String readMove() {
        System.out.println("Enter valid move for example : 'move a2 to a3':");
        if (isWhiteTurn())
            System.out.print("Move " + nameWhitePlayer + " : ");
        else System.out.print("Move " + nameBlackPlayer + " : ");

        String move = scanner.nextLine();
        if (isValidMove(move))
            return move;
        else {
            System.out.println("Invalid '" + move + "' , try again :");
            return readMove();
        }
    }

    public boolean isValidMove(String move) {
        return Pattern.matches("move ([a-hA-H]\\d to [a-hA-H]\\d)", move);
    }

    public boolean isValidMove(String move, Color color) {
        String oldPos = move.split(" ")[1];
        String newPos = move.split(" ")[3];

        Position oldPosition = new Position(oldPos.toUpperCase());
        Position newPosition = new Position(newPos.toUpperCase());


        if (SingletonBoard.pieceAt(oldPosition) == null) {
            System.out.println("No piece in point ");
            return false;
        }
        if (SingletonBoard.pieceAt(oldPosition).getColor() != color) return false;

        return boardChess.moving(oldPosition, newPosition);

    }

    public void whitePlaying(String move, Color color) {
        if (isValidMove(move, color))
            isWhiteTurn = false;
        else
            System.out.println("invalid move");
    }

    public void blackPlaying(String move, Color color) {
        if (isValidMove(move, color))
            isWhiteTurn = true;
        else
            System.out.println("invalid move");
    }

    public void printWinnerName() {
        System.out.println("the winner is " + boardChess.getWinnerPlayer());
    }

}

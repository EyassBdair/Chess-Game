package Game;

import Enums.Color;
import Enums.PieceType;
import Piecees.*;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class SingletonBoard {
    private static volatile SingletonBoard instance;
    private static final Piece[][] boardChess = new Piece[8][8];
    private final Player whitePlayer;
    private final Player blackPlayer;
    private final PieceFactory pieceFactory;
    private Piece kingWhite, kingBlack;
    private String winnerPlayer;

    private SingletonBoard(String playerWhite, String playerBlack) {
        this.whitePlayer = new Player(playerWhite, Color.WHITE);
        this.blackPlayer = new Player(playerBlack, Color.BLACK);
        pieceFactory = new PieceFactory();
        createBlackPieces(blackPlayer.getColor());
        createWhitePieces(whitePlayer.getColor());
    }

    public static SingletonBoard getInstance(String playerWhite, String playerBlack) {
        if (instance == null) {
            synchronized (SingletonBoard.class) {
                if (instance == null)
                    instance = new SingletonBoard(playerWhite, playerBlack);
            }
        }
        return instance;
    }

    private void createWhitePieces(Color color) {
        for (int i = 0; i < 8; i++)
            boardChess[6][i] = pieceFactory.createPiece(PieceType.PAWN, color, new Position(6, i));

        boardChess[7][0] = pieceFactory.createPiece(PieceType.ROOK, color, new Position(7, 0));
        boardChess[7][1] = pieceFactory.createPiece(PieceType.KNIGHT, color, new Position(7, 1));
        boardChess[7][2] = pieceFactory.createPiece(PieceType.BISHOP, color, new Position(7, 2));
        boardChess[7][3] = pieceFactory.createPiece(PieceType.QUEEN, color, new Position(7, 3));
        kingWhite = pieceFactory.createPiece(PieceType.KING, color, new Position(7, 4));
        boardChess[7][4] = kingWhite;
        boardChess[7][5] = pieceFactory.createPiece(PieceType.BISHOP, color, new Position(7, 5));
        boardChess[7][6] = pieceFactory.createPiece(PieceType.KNIGHT, color, new Position(7, 6));
        boardChess[7][7] = pieceFactory.createPiece(PieceType.ROOK, color, new Position(7, 7));
    }

    private void createBlackPieces(Color color) {
        for (int i = 0; i < 8; i++)
            boardChess[1][i] = pieceFactory.createPiece(PieceType.PAWN, color, new Position(1, i));

        boardChess[0][0] = pieceFactory.createPiece(PieceType.ROOK, color, new Position(0, 0));
        boardChess[0][1] = pieceFactory.createPiece(PieceType.KNIGHT, color, new Position(0, 1));
        boardChess[0][2] = pieceFactory.createPiece(PieceType.BISHOP, color, new Position(0, 2));
        boardChess[0][3] = pieceFactory.createPiece(PieceType.QUEEN, color, new Position(0, 3));
        kingBlack = pieceFactory.createPiece(PieceType.KING, color, new Position(0, 4));
        boardChess[0][4] = kingBlack;
        boardChess[0][5] = pieceFactory.createPiece(PieceType.BISHOP, color, new Position(0, 5));
        boardChess[0][6] = pieceFactory.createPiece(PieceType.KNIGHT, color, new Position(0, 6));
        boardChess[0][7] = pieceFactory.createPiece(PieceType.ROOK, color, new Position(0, 7));

    }

    static void printBoard() {
        PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        System.out.println();
        System.out.println("    a   b   c   d   e   f   g   h");
        System.out.println("  ---------------------------------");
        int count = 8;
        for (int i = 0; i < 8; i++) {
            System.out.print(count + " ");
            System.out.print("| ");
            for (int j = 0; j < 8; j++) {
                if (boardChess[i][j] == null) {
                    out.print("  | ");
                } else {
                    out.print(boardChess[i][j] + "\u200A| ");
                }
            }
            System.out.print(count);
            count--;
            System.out.println();
            System.out.println("  ---------------------------------");
        }
        System.out.println("    a   b   c   d   e   f   g   h");
        System.out.println();
    }

    public String getWinnerPlayer() {
        return winnerPlayer;
    }

    public static Piece pieceAt(Position position) {
        return boardChess[position.getX()][position.getY()];
    }

    public boolean isEndGame() {
        if (kingWhite.isKill() && !kingBlack.isKill()) {
            System.out.println(blackPlayer.getNamePlayer() + " Wins , congratulations");
            winnerPlayer = blackPlayer.getNamePlayer();
            SingletonBoard.printBoard();
            return true;
        } else if (kingBlack.isKill() && !kingWhite.isKill()) {
            System.out.println(whitePlayer.getNamePlayer() + " Wins , congratulations");
            winnerPlayer = whitePlayer.getNamePlayer();
            SingletonBoard.printBoard();
            return true;
        }
        return false;
    }

    public boolean moving(Position oldPos, Position newPos) {
        if (pieceAt(oldPos).isValidMove(oldPos, newPos) && !pieceAt(oldPos).isSameColor(pieceAt(newPos))) {
            if (pieceAt(newPos) != null)
                pieceAt(newPos).setKill(true);
            Checkmate(oldPos);
            boardChess[newPos.getX()][newPos.getY()] = pieceAt(oldPos);
            boardChess[oldPos.getX()][oldPos.getY()] = null;
            pieceAt(newPos).setPosition(newPos);
            return true;
        }
        System.out.println(" the piece can't move to this Position !!");
        return false;
    }

    public void Checkmate(Position currentLocation) {
        if (pieceAt(currentLocation).getColor() == Color.WHITE) {
            if (pieceAt(currentLocation).isValidMove(currentLocation, kingBlack.getPosition())) {
                if (isKingCanMoving(currentLocation, kingBlack.getPosition())) {
                    System.out.println("Checkmate!!! " + blackPlayer.getNamePlayer() + " your king is under attack by " + whitePlayer.getNamePlayer());
                } else {
                    kingBlack.setKill(true);
                }
            }
        } else {
            if (pieceAt(currentLocation).isValidMove(currentLocation, kingWhite.getPosition())) {
                if (isKingCanMoving(currentLocation, kingWhite.getPosition())) {
                    System.out.println("Checkmate!!! " + whitePlayer.getNamePlayer() + " your king is under attack by " + blackPlayer.getNamePlayer());
                } else {
                    kingWhite.setKill(true);
                }
            }
        }
    }

    private boolean isKingCanMoving(Position currentLocation, Position newLocation) {
        for (int i = newLocation.getX() - 1; i <= newLocation.getX() + 1; i++)
            for (int j = newLocation.getY() - 1; j <= newLocation.getY() + 1; j++) {
                if (i < 0 || i > 7 || j < 0 || j > 8) continue;
                if (boardChess[i][j] == null && !pieceAt(currentLocation).isValidMove(currentLocation, new Position(i, j)))
                    return true;

            }
        return false;
    }

}


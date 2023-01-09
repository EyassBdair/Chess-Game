package Piecees;

import Enums.Color;
import Enums.PieceType;
import Game.SingletonBoard;

import static java.lang.Math.abs;

public class Pawn extends Piece {
    boolean isFirstMoved = true;

    public Pawn(Color color, Position position) {
        super(color, position, PieceType.PAWN);
    }

    @Override
    public boolean isValidMove(Position oldPosition, Position newPosition) {
        int rowDiff = oldPosition.getX() - newPosition.getX();
        int colDiff = oldPosition.getY() - newPosition.getY();

        if (this.getColor() == Color.WHITE && rowDiff < 0 || this.getColor() == Color.BLACK && rowDiff > 0) {
            return false;
        }
        if (this.isFirstMoved && colDiff == 0 && abs(rowDiff) == 2 && SingletonBoard.pieceAt(newPosition) == null) {
            this.isFirstMoved = false;
            return true;
        }
        if (colDiff == 0 && abs(rowDiff) == 1 && SingletonBoard.pieceAt(newPosition) == null) {
            return true;
        }
        return abs(colDiff) == 1 && abs(rowDiff) == 1 && SingletonBoard.pieceAt(newPosition) != null && !this.isSameColor(SingletonBoard.pieceAt(newPosition));

    }

    @Override
    public String toString() {

        //return "Pawn  : " + super.toString();
        if (this.getColor() == Color.WHITE)
            return "♙";
        return "♟";
    }
}

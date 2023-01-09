package Piecees;

import Enums.Color;
import Enums.PieceType;
import Game.SingletonBoard;

public class King extends Piece {
    public King(Color color, Position position) {
        super(color, position, PieceType.KING);
    }

    @Override
    public boolean isValidMove(Position oldPosition, Position newPosition) {
        if (this.isSameColor(SingletonBoard.pieceAt(newPosition))) {
            return false;
        }

        int rowDiff = Math.abs(newPosition.getX() - oldPosition.getX());
        int colDiff = Math.abs(newPosition.getY() - oldPosition.getY());
        return rowDiff <= 1 && colDiff <= 1;
    }

    @Override
    public String toString() {
        // return " King  : " + super.toString();
        if (this.getColor() == Color.WHITE)
            return "♔";
        return "♚";
    }
}

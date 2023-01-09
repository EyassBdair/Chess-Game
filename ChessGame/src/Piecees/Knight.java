package Piecees;

import Enums.Color;
import Enums.PieceType;

public class Knight extends Piece {
    public Knight(Color color, Position position) {
        super(color, position, PieceType.KNIGHT);
    }

    @Override
    public boolean isValidMove(Position oldPosition, Position newPosition) {

        int rowDiff = Math.abs(newPosition.getX() - oldPosition.getX());
        int colDiff = Math.abs(newPosition.getY() - oldPosition.getY());
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }

    @Override
    public String toString() {
        if (this.getColor() == Color.WHITE)
            return "♘";
        return "♞";
    }
}

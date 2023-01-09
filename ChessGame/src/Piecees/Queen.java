package Piecees;

import Enums.Color;
import Enums.PieceType;

public class Queen extends Piece {

    public Queen(Color color, Position position) {
        super(color, position, PieceType.QUEEN);
    }

    @Override
    public boolean isValidMove(Position oldPosition, Position newPosition) {

        return oldPosition.getX() == newPosition.getX() || oldPosition.getY() == newPosition.getY() ||
                Math.abs(oldPosition.getX() - oldPosition.getY()) == Math.abs(newPosition.getX() - newPosition.getY());
    }

    @Override
    public String toString() {
        if (this.getColor() == Color.WHITE)
            return "♕";
        return "♛";
    }
}



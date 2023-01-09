package Piecees;

import Enums.Color;
import Enums.PieceType;


public class Bishop extends Piece {


    public Bishop(Color color, Position position) {
        super(color, position, PieceType.BISHOP);
    }

    @Override
    public boolean isValidMove(Position oldPosition, Position newPosition) {
        return (Math.abs(newPosition.getX() - oldPosition.getX()) ==
                Math.abs(newPosition.getY() - oldPosition.getY()));
    }

    @Override
    public String toString() {
        if (this.getColor() == Color.WHITE)
            return "♗";
        return "♝";
    }
}

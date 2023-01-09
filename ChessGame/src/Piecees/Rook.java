package Piecees;

import Enums.Color;
import Enums.PieceType;

public class Rook extends Piece {
    public Rook(Color color, Position position) {
        super(color, position, PieceType.ROOK);
    }

    @Override
    public boolean isValidMove(Position oldPosition, Position newPosition) {
        return newPosition.getX() == oldPosition.getX() ||
                newPosition.getY() == oldPosition.getY();
    }

    @Override
    public String toString() {
        if (this.getColor() == Color.WHITE)
            return "♖";
        return "♜";
    }
}

package Piecees;

import Enums.Color;
import Enums.PieceType;

public abstract class Piece {
    private Position position;
    private final Color color;
    private final PieceType pieceType;
    private boolean kill;


    public Piece(Color color, Position position, PieceType pieceType) {
        this.color = color;
        this.position = position;
        this.pieceType = pieceType;
        this.kill = false;
    }

    public Color getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract boolean isValidMove(Position oldPosition, Position newPosition);

    public boolean isSameColor(Piece piece) {
        if (piece == null)
            return false;
        return (this.color == piece.getColor());
    }

    public void setKill(boolean kill) {
        this.kill = kill;
    }

    public boolean isKill() {
        return kill;
    }

}

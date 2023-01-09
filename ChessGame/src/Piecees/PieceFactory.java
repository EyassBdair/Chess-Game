package Piecees;

import Enums.Color;
import Enums.PieceType;

public class PieceFactory {

    public Piece createPiece(PieceType pieceType, Color colors, Position position) {
        if (pieceType == PieceType.BISHOP)
            return new Bishop(colors, position);
        else if (pieceType == PieceType.KING)
            return new King(colors, position);
        else if (pieceType == PieceType.KNIGHT)
            return new Knight(colors, position);
        else if (pieceType == PieceType.PAWN)
            return new Pawn(colors, position);
        else if (pieceType == PieceType.QUEEN)
            return new Queen(colors, position);
        else if (pieceType == PieceType.ROOK)
            return new Rook(colors, position);
        return null;
    }
}

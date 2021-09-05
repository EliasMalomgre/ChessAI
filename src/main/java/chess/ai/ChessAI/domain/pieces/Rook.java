package chess.ai.ChessAI.domain.pieces;

import chess.ai.ChessAI.domain.Tile;
import chess.ai.ChessAI.domain.pieces.Piece;

public final class Rook extends Piece {

    public String whiteRook;
    public String blackRook;

    public Rook(boolean isWhite, Tile tile) {
        super(isWhite, tile);
    }
}


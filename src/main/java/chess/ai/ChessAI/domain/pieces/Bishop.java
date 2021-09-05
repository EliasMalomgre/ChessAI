package chess.ai.ChessAI.domain.pieces;

import chess.ai.ChessAI.domain.Tile;

public final class Bishop extends Piece {
    
    public String whiteBishop;
    public String blackBishop;


    public Bishop(boolean isWhite, Tile tile) {
        super(isWhite, tile);
    }
}

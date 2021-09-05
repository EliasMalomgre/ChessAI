package chess.ai.ChessAI.domain.pieces;

import chess.ai.ChessAI.domain.Tile;

public final class Knight extends Piece {

    
    public String whiteKnight;
    public String blackKnight;


    public Knight(boolean isWhite, Tile tile) {
        super(isWhite, tile);
    }
}

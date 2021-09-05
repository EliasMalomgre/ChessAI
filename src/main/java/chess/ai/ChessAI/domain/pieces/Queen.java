package chess.ai.ChessAI.domain.pieces;

import chess.ai.ChessAI.domain.Tile;


public final class Queen extends Piece {

    
    public String whiteQueen;
    public String blackQueen;


    public Queen(boolean isWhite, Tile tile) {
        super(isWhite, tile);
    }
}

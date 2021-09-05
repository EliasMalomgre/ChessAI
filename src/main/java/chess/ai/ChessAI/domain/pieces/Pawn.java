package chess.ai.ChessAI.domain.pieces;


import chess.ai.ChessAI.domain.Tile;


public final class Pawn extends Piece {

    public String whitePawn;
    public String blackPawn;

    public Pawn(boolean isWhite, Tile tile) {
        super(isWhite, tile);
    }

}

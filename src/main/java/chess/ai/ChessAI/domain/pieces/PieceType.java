package chess.ai.ChessAI.domain.pieces;


import chess.ai.ChessAI.domain.Tile;


public enum PieceType {
    
    NoPiece {
        @Override
        public Piece createPiece(Tile tile) {
            return null;
        }   
    },
    WhitePawn {
        @Override
        public Piece createPiece(Tile tile) {
            return new Pawn(true, tile);
        }        
    },
    WhiteBishop {
        @Override
        public Piece createPiece(Tile tile) {
            return new Bishop(true, tile);
        }
        
    },
    WhiteKnight {
        @Override
        public Piece createPiece(Tile tile) {
            return new Knight(true, tile);
        }        
    },
    WhiteRook {
        @Override
        public Piece createPiece(Tile tile) {
            return new Rook(true, tile);
        }        
    },
    WhiteQueen {
        @Override
        public Piece createPiece(Tile tile) {
            return new Queen(true, tile);
        }     
    },
    WhiteKing {
        @Override
        public Piece createPiece(Tile tile) {
            return new King(true, tile);
        }
    },
    BlackPawn {
        @Override
        public Piece createPiece(Tile tile) {
            return new Pawn(false, tile);
        }
    },
    BlackBishop {
        @Override
        public Piece createPiece(Tile tile) {
            return new Bishop(false, tile);
        }
    },
    BlackKnight {
        @Override
        public Piece createPiece(Tile tile) {
            return new Knight(false, tile);
        }
    },
    BlackRook {
        @Override
        public Piece createPiece(Tile tile) {
            return new Rook(false, tile);
        }
    },
    BlackQueen {
        @Override
        public Piece createPiece(Tile tile) {
            return new Queen(false, tile);
        } 
    },
    BlackKing {
        @Override
        public Piece createPiece(Tile tile) {
            return new King(false, tile);
        }      
    };
    
    
    public abstract Piece createPiece(Tile tile);
    
}

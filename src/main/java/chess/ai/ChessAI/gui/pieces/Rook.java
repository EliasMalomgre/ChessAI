/*
 * Class for the Rook StackPane GUI and functionality
 * 7/3/20.
 */
package chess.ai.ChessAI.gui.pieces;

import chess.ai.ChessAI.gui.Game;
import chess.ai.ChessAI.gui.GameInfo;
import chess.ai.ChessAI.gui.Piece;
import chess.ai.ChessAI.gui.Tile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * @author Joseph
 */
public final class Rook extends Piece {

    /*
     * Attack pattern:
     * O    O    O    X    O    O    O    O
     * O    O    O    X    O    O    O    O
     * O    O    O    X    O    O    O    O
     * O    O    O    X    O    O    O    O
     * X    X    X    R    X    X    X    X
     * O    O    O    X    O    O    O    O
     * O    O    O    X    O    O    O    O
     * O    O    O    X    O    O    O    O
     */

    public String whiteRook;
    public String blackRook;

    public final void setPaths(String path) {
        whiteRook = "src/main/resources/" + path + "/whiterook.png";
        blackRook = "src/main//resources/" + path + "/blackrook.png";
    }

    /**
     * Constructs a Rook
     *
     * @param isWhite side of the piece
     * @param tile    tile piece belongs to
     * @param path    for image
     */
    public Rook(boolean isWhite, Tile tile, String path) {
        super(isWhite, tile);
        setPaths(path);
        Image image = null;
        try {
            if (isWhite) {
                image = new Image(Files.newInputStream(Paths.get(whiteRook)));


            } else {
                image = new Image(Files.newInputStream(Paths.get(blackRook)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(tileSize);
        imageView.setFitWidth(tileSize);
        this.getChildren().add(imageView);
    }

    @Override
    public void pieceAvailableMoves() {
        Game controller = getController();
        Tile[][] tiles = controller.getTiles();
        int row = getTile().getRow();
        int col = getTile().getCol();
        ArrayList<Tile> available = getAvailable();

        int[][] multipliers = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] multiplier : multipliers) {
            int i = 1;
            boolean canContinue = true;
            while (canContinue) {
                if (withinBounds(row + (i * multiplier[0]), col + (i * multiplier[1]))) {
                    Tile tile = tiles[row + (i * multiplier[0])][col + (i * multiplier[1])];
                    if ((!tile.hasPiece())) {
                        available.add((tile));
                    } else if ((tile.getPiece().isWhite() != isWhite())) {
                        available.add((tile));
                        canContinue = false;
                    } else {
                        canContinue = false;
                    }
                } else {
                    canContinue = false;
                }
                i++;
            }
        }
    }

    @Override
    public void pieceAvailableMoves(ArrayList<Tile> whiteList) {
        Game controller = getController();
        Tile[][] tiles = controller.getTiles();
        int row = getTile().getRow();
        int col = getTile().getCol();
        ArrayList<Tile> available = getAvailable();

        int[][] multipliers = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] multiplier : multipliers) {
            int i = 1;
            boolean canContinue = true;
            while (canContinue) {
                if (withinBounds(row + (i * multiplier[0]), col + (i * multiplier[1]))) {
                    Tile tile = tiles[row + (i * multiplier[0])][col + (i * multiplier[1])];
                    if ((!tile.hasPiece())) {
                        if (whiteListed(whiteList, tile)) {
                            available.add((tile));
                        }
                    } else if ((tile.getPiece().isWhite() != isWhite())) {
                        if (whiteListed(whiteList, tile)) {
                            available.add((tile));
                        }
                        canContinue = false;
                    } else {
                        canContinue = false;
                    }
                } else {
                    canContinue = false;
                }
                i++;
            }
        }
    }

    @Override
    public ArrayList<int[]> calcCommonPieceLocations(int[] location) {
        Game controller = getController();
        Tile[][] tiles = controller.getTiles();
        int row = location[0];
        int col = location[1];
        ArrayList<int[]> locations = new ArrayList<>();
        int[][] multipliers = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] multiplier : multipliers) {
            int i = 1;
            boolean canContinue = true;
            while (canContinue) {
                if (withinBounds(row + (i * multiplier[0]), col + (i * multiplier[1]))) {
                    Tile tile = tiles[row + (i * multiplier[0])][col + (i * multiplier[1])];
                    if (tile.hasPiece()) {
                        if ((tile.getPiece().isWhite() == isWhite())
                                && tile.getPiece().isRook() && tile.getPiece() != this) {
                            int[] loc = {row + (i * multiplier[0]), col + (i * multiplier[1])};
                            locations.add(loc);
                        }
                        canContinue = false;
                    }
                } else {
                    canContinue = false;
                }
                i++;
            }
        }
        return locations;
    }

    @Override
    public boolean isRook() {
        return true;
    }

    @Override
    public String getNotation() {
        return "R";
    }

    @Override
    public int getValue() {
        return 4;
    }

    @Override
    public byte getInfoCode() {
        return isWhite() ? GameInfo.WHITE_ROOK : GameInfo.BLACK_ROOK;
    }
}

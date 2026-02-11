package Model.AllPieces;

import Model.Board;

import java.awt.image.BufferedImage;
import java.io.Console;

public class Rook extends Piece{
    public Rook(Board board, int col, int row, boolean isWhite){
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;
        this.isWhite = isWhite;
        this.name = "Rook";
        this.sprite = sheet.getSubimage(4 * sheetScale, isWhite?0:sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
    }

    @Override
    public boolean isValidMovement(int col, int row) {
        return (this.col == col || this.row == row);
    }
    @Override
    public boolean moveCollidesWithPiece(int col, int row) {
        // Gauche
        if (this.col > col) {
            for (int i = this.col - 1; i > col; i--) {
                if (board.getPiece(i, this.row) != null) {
                    return true;
                }
            }
        }
       // Droite
        if (this.col < col) {
                for (int i = this.col + 1; i < col; i++) {
                    if (board.getPiece(i, this.row) != null) {
                        return true;
                    }
                }
            }
        // Haut
        if (this.row > row) {
                for (int i = this.row - 1; i > row; i--) {
                    if (board.getPiece(this.col, i) != null) {
                        return true;
                    }
                }
            }
        // Bas
        if (this.row < row) {
                for (int i = this.row + 1; i < row; i++) {
                    if (board.getPiece(this.col, i) != null) {
                        return true;
                    }
                }
            }
        return false;
    }

}

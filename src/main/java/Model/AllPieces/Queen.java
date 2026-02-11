package Model.AllPieces;

import Model.Board;

import java.awt.image.BufferedImage;

public class Queen extends Piece{
    public Queen(Board board, int col, int row, boolean isWhite){
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;
        this.isWhite = isWhite;
        this.name = "Queen";
        this.sprite = sheet.getSubimage(sheetScale, isWhite?0:sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
    }

    @Override
    public boolean isValidMovement(int col, int row) {
        return (this.col == col || this.row == row || Math.abs(this.col - col) == Math.abs(this.row - row));
    }
    @Override
    public boolean moveCollidesWithPiece(int col, int row) {
        if(this.col == col || this.row == row) {
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
        }else {
            // Haut-Gauche
            if (this.col > col && this.row > row) {
                for (int i = 1; i < Math.abs(this.col - col); i++) {
                    if (board.getPiece(this.col - i, this.row - i) != null) return true;
                }
            }

            // Haut-Droite
            if (this.col < col && this.row > row) {
                for (int i = 1; i < Math.abs(this.col - col); i++) {
                    if (board.getPiece(this.col + i, this.row - i) != null) return true;
                }
            }

            // Bas-Gauche
            if (this.col > col && this.row < row) {
                for (int i = 1; i < Math.abs(this.col - col); i++) {
                    if (board.getPiece(this.col - i, this.row + i) != null) return true;
                }
            }

            // Bas-Droite
            if (this.col < col && this.row < row) {
                for (int i = 1; i < Math.abs(this.col - col); i++) {
                    if (board.getPiece(this.col + i, this.row + i) != null) return true;
                }
            }
        }
        return false;
}
}

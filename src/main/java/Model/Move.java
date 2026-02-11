package Model;

import Model.AllPieces.Piece;

public class Move {
    private int oldCol;
    private int oldRow;

    public int getNewCol() {return newCol;}
    public int getNewRow() {return newRow;}

    private int newCol;
    private int newRow;
    Piece piece;
    Piece capture;

    public Move(Board board, Piece piece_, int newCol_, int newRow_){
        this.oldCol = piece_.getCol();
        this.oldRow = piece_.getRow();
        this.newCol = newCol_;
        this.newRow = newRow_;

        this.piece = piece_;
        this.capture = board.getPiece(newCol, newRow);
    }
}

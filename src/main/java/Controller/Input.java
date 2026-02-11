package Controller;

import Model.AllPieces.Piece;
import Model.Board;
import Model.Move;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Input extends MouseAdapter {

    Board board;
    public Input(Board board_){
        this.board = board_;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int col = e.getX()/board.tileSize;
        int row = e.getY()/board.tileSize;
        Piece pieceXY = board.getPiece(col, row);
        if(pieceXY != null){
            board.selectedPiece = pieceXY;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int col = e.getX()/board.tileSize;
        int row = e.getY()/board.tileSize;
        if(board.selectedPiece != null){
            Move move = new Move(board, board.selectedPiece, col, row);
            if(board.isValidMove(move)){
                board.makeMove(move);
            }else{
                board.selectedPiece.setxPos(board.selectedPiece.getCol() * board.tileSize);
                board.selectedPiece.setyPos(board.selectedPiece.getRow() * board.tileSize);
            }
            board.selectedPiece = null;
            board.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(board.selectedPiece != null){
            if(e.getX() <= board.tileSize * 8 && e.getY()< board.tileSize*8){
                board.selectedPiece.setxPos(e.getX() - board.tileSize/2);
                board.selectedPiece.setyPos(e.getY() - board.tileSize/2);
                board.repaint();
            }
        }
    }
}

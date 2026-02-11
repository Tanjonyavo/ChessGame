package Model;

import Model.AllPieces.Piece;

public class CheckScanneur {
    Board board;
    public CheckScanneur(Board board){
        this.board = board;
    }
    public boolean isKingChecked(Move move){
        Piece king = board.findKing(move.piece.isWhite);
        assert king!=null;
        int kingCol = king.getCol();
        int kingRow = king.getRow();
        if(board.selectedPiece != null && board.selectedPiece.name.equals("King")){
            kingCol = move.getNewCol();
            kingRow = move.getNewRow();
        }
        return  hitByRook(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, 0, 1) || //haut
                hitByRook(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, 1, 0) || //droite
                hitByRook(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, 0, -1) || //bas
                hitByRook(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, -1, 0) || //gauche

                hitByBishop(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, -1, -1)|| //haut gauche
                hitByBishop(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, 1, -1)|| //haut droite
                hitByBishop(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, 1, 1)||//bas droite
                hitByBishop(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, -1, 1)||//bas gauche

                hitByKnight(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow)||
                hitByPawn(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow)||
                hitByKing(king, kingCol, kingRow);


    }
    private boolean hitByRook(int col, int row, Piece king,int kingCol, int kingRow, int colVal, int rowVal){
        for(int i = 1; i < 8; i++){
            if(kingCol + (i*colVal) == col && kingRow + (i * rowVal) == row){
                break;
            }
            Piece piece = board.getPiece(kingCol + (i*colVal), kingRow + (i * rowVal));
            if(piece != null && piece != board.selectedPiece){
                if(!board.sameTeam(piece, king) && (piece.name.equals("Rook") || piece.name.equals("Queen"))){
                    return true;
                }
                break;
            }
        }
        return false;
    }
    private boolean hitByBishop(int col, int row, Piece king,int kingCol, int kingRow, int colVal, int rowVal){
        for(int i = 1; i < 8; i++){
            if(kingCol - (i*colVal) == col && kingRow - (i * rowVal) == row){
                break;
            }
            Piece piece = board.getPiece(kingCol - (i*colVal), kingRow - (i * rowVal));
            if(piece != null && piece != board.selectedPiece){
                if(!board.sameTeam(piece, king) && (piece.name.equals("Bishop") || piece.name.equals("Queen"))){
                    return true;
                }
                break;
            }
        }
        return false;
    }

    private boolean hitByKnight(int col, int row, Piece king, int kingCol, int kingRow){
        return checkKnight(board.getPiece(kingCol - 1, kingRow - 2), king, col, row) ||
                checkKnight(board.getPiece(kingCol + 1, kingRow - 2), king, col, row) ||
                checkKnight(board.getPiece(kingCol + 2, kingRow - 1), king, col, row) ||
                checkKnight(board.getPiece(kingCol + 2, kingRow + 1), king, col, row) ||
                checkKnight(board.getPiece(kingCol + 1, kingRow + 2), king, col, row) ||
                checkKnight(board.getPiece(kingCol - 1, kingRow + 2), king, col, row) ||
                checkKnight(board.getPiece(kingCol - 2, kingRow + 1), king, col, row) ||
                checkKnight(board.getPiece(kingCol - 2, kingRow - 1), king, col, row);
    }

    private boolean checkKnight(Piece p, Piece k, int col, int row){
        return p!=null && !board.sameTeam(p, k)&&p.name.equals("Knight") && !(p.getCol() == col && p.getRow() == row);
    }
    private boolean hitByKing(Piece king, int kingCol, int kingRow){
        return checkKing(board.getPiece(kingCol - 1, kingRow - 1), king)||
                checkKing(board.getPiece(kingCol + 1, kingRow - 1), king)||
                checkKing(board.getPiece(kingCol, kingRow - 1), king)||
                checkKing(board.getPiece(kingCol - 1, kingRow), king)||
                checkKing(board.getPiece(kingCol + 1, kingRow), king)||
                checkKing(board.getPiece(kingCol - 1, kingRow + 1), king)||
                checkKing(board.getPiece(kingCol + 1, kingRow + 1), king)||
                checkKing(board.getPiece(kingCol, kingRow + 1), king);

    }
    private boolean checkKing(Piece p, Piece k){
        return p!=null && !board.sameTeam(p, k) && p.name.equals("King");
    }

    private boolean hitByPawn(int col, int row, Piece king, int kingCol, int kingRow){
        int colorVal = king.isWhite ? -1:1;
        return checkPawn(board.getPiece(kingCol + 1, kingRow + colorVal), king, col, row) ||
                checkPawn(board.getPiece(kingCol - 1, kingRow + colorVal), king, col, row);
    }
    private boolean checkPawn(Piece p, Piece k, int col, int row){
        return p!=null && !board.sameTeam(p, k)&&p.name.equals("Pawn") && !(p.getCol() == col && p.getRow() == row);
    }

    public boolean isGameOver(Piece king){
        for(Piece piece : board.pieceList){
            if(board.sameTeam(piece, king)){
                board.selectedPiece = piece==king ? king : null;
                for(int row = 0; row < board.rows; row ++){
                    for(int col = 0; col < board.rows; col ++){
                        Move move = new Move(board, piece, col, row);
                        if(board.isValidMove(move)){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
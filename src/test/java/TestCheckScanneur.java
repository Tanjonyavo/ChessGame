import Model.Board;
import Model.CheckScanneur;
import Model.Move;
import Model.AllPieces.King;
import Model.AllPieces.Rook;
import Model.AllPieces.Pawn;

public class TestCheckScanneur {
    public static void main(String[] args) {
        testKingInCheckByRook();
        testKingNotInCheck();
        testKingInCheckByPawn();
    }

    private static void testKingInCheckByRook() {
        Board board = new Board();
        King whiteKing = new King(board, 4, 0, true);
        Rook blackRook = new Rook(board, 4, 7, false);

        board.addOnePiece(whiteKing);
        board.addOnePiece(blackRook);
        CheckScanneur scanneur = new CheckScanneur(board);
        Move dummyMove = new Move(board, whiteKing, 4, 0);

        boolean isChecked = scanneur.isKingChecked(dummyMove);
        System.out.println("Test 1 - King in check by Rook: " + (isChecked ? "Passed" : "Failed"));
        board.removOnePiece(whiteKing);
        board.removOnePiece(blackRook);
    }

    private static void testKingNotInCheck() {
        Board board = new Board();
        King whiteKing = new King(board, 4, 0, true);
        Pawn whitePawn = new Pawn(board, 4, 1, true);

        board.addOnePiece(whiteKing);
        board.addOnePiece(whitePawn);

        CheckScanneur scanneur = new CheckScanneur(board);
        Move dummyMove = new Move(board, whiteKing, 4, 0);

        boolean isChecked = scanneur.isKingChecked(dummyMove);
        System.out.println("Test 2 - King not in check: " + (!isChecked ? "Passed" : "Failed"));
        board.removOnePiece(whiteKing);
        board.removOnePiece(whitePawn);
    }

    private static void testKingInCheckByPawn() {
        Board board = new Board();
        King whiteKing = new King(board ,4, 4, true);
        Pawn blackPawn = new Pawn(board, 3, 3, false);

        board.addOnePiece(whiteKing);
        board.addOnePiece(blackPawn);

        CheckScanneur scanneur = new CheckScanneur(board);
        Move dummyMove = new Move(board, whiteKing, 4, 4);

        boolean isChecked = scanneur.isKingChecked(dummyMove);
        System.out.println("Test 3 - King in check by Pawn: " + (isChecked ? "Passed" : "Failed"));

        board.removOnePiece(whiteKing);
        board.removOnePiece(blackPawn);
    }
}

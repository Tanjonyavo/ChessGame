package Model;

import Model.AllPieces.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {

    @Test
    public void testMoveInitialization() {
        Board board = new Board();
        board.rows = 8;

        Pawn piece = new Pawn(board, 3, 3, true);

        board.addOnePiece(piece);

        int targetCol = 3;
        int targetRow = 4;

        Move move = new Move(board, piece, targetCol, targetRow);

        assertEquals(3, move.getNewCol());
        assertEquals(4, move.getNewRow());
    }

    @Test
    public void testMoveWithCapture() {
        Board board = new Board();
        board.rows = 8;

        Queen attacker = new Queen(board, 3, 3, true);
        Rook target = new Rook(board, 3, 5, false);

        board.addOnePiece(attacker);
        board.addOnePiece(target);

        Move move = new Move(board, attacker, 3, 5);

        assertNotNull(move.capture);
        assertEquals("Rook", move.capture.name);
    }

    @Test
    public void testMoveWithoutCapture() {
        Board board = new Board();
        board.rows = 8;

        Knight mover = new Knight(board, 1, 1, true);
        board.addOnePiece(mover);

        Move move = new Move(board, mover, 2, 3);

        assertNull(move.capture);
    }
}

import Model.AllPieces.Piece;
import Model.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    private Piece testPiece;

    static class TestPiece extends Piece {
        public TestPiece(Board board) {
            super(board);
            this.name = "TestPiece";
            this.isWhite = true;
        }

        @Override
        public boolean isValidMovement(int col, int row) {
            return true;
        }

        @Override
        public boolean moveCollidesWithPiece(int col, int row) {
            return false;
        }
    }

    @BeforeEach
    void setUp() {
        Board board = new Board();
        testPiece = new TestPiece(board);
    }

    @Test
    void testInitialPosition() {
        testPiece.setCol(3);
        testPiece.setRow(5);
        assertEquals(3, testPiece.getCol());
        assertEquals(5, testPiece.getRow());
    }

    @Test
    void testSetAndGetPosition() {
        testPiece.setxPos(100);
        testPiece.setyPos(200);
        assertEquals(100, testPiece.xPos);
        assertEquals(200, testPiece.yPos);
    }

    @Test
    void testIsFirstMoveDefault() {
        assertTrue(testPiece.isFirstMove);
    }

}

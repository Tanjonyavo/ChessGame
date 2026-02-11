
import Model.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testRepetitionDetection() {
        String fakePositionKey = "Pawn11WPawn22BPawn33W";

        board.resetPos();
        for (int i = 0; i < 3; i++) {
            board.makeFakePosition(fakePositionKey);
        }

        int count = board.getPositionCount(fakePositionKey);
        assertEquals(3, count, "La position devrait apparaître 3 fois");
    }
}

package View;

import Model.Board;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameClockTest {

    private GameClock clock;

    @BeforeEach
    public void setUp() {
        Board.isWhiteToMove = true;
        Board.isGameOver = false;
        clock = new GameClock(1, true); // 1 minute
    }

    @Test
    public void testInitialTimeIsCorrect() {
        assertEquals(60, clock.timeRemaining);
        assertEquals("01:00", clock.timerLabel.getText());
    }

    @Test
    public void testFormatTime() {
        clock.timeRemaining = 75;
        clock.timerLabel.setText(clock.timerLabel.getText());
        assertTrue(clock.timerLabel.getText().matches("\\d{2}:\\d{2}"));
    }

    @Test
    public void testUpdateClockReducesTime() {
        int before = clock.timeRemaining;
        clock.updateClock();
        int after = clock.timeRemaining;
        assertEquals(before - 1, after);
    }

    @Test
    public void testClockStopsAtZero() {
        clock.timeRemaining = 1;
        clock.updateClock();
        assertEquals(0, clock.timeRemaining);
        assertEquals("00:00", clock.timerLabel.getText());
    }

}

package View;

import App.Main;
import Model.Board;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;

public class GameOverWindowTest {

    private GameOverWindow gameOverWindow;

    @BeforeEach
    public void setUp() {
        Main.topClock = new GameClock(1, true);
        Main.bottomClock = new GameClock(1, false);
    }

    @Test
    public void testWindowTitle() {
        gameOverWindow = new GameOverWindow("Black");

        assertEquals("Game Over", gameOverWindow.getTitle());
    }

    @Test
    public void testWinnerMessage() {
        gameOverWindow = new GameOverWindow("White");

        JLabel message = (JLabel) gameOverWindow.getContentPane().getComponent(0);
        assertEquals("White Wins!", message.getText());
        assertEquals(SwingConstants.CENTER, message.getHorizontalAlignment());
    }


    @Test
    public void testGameOverWindowAppearance() {
        gameOverWindow = new GameOverWindow("Black");

        assertTrue(gameOverWindow.isVisible());
    }

    @AfterEach
    public void tearDown() {
        if (gameOverWindow != null) {
            gameOverWindow.dispose();
        }
    }
}

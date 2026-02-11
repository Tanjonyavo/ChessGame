package View;

import Model.Board;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class GameCadenceSelectionTest {

    private GameCadenceSelection selectionPanel;

    @BeforeEach
    public void setUp() {
        selectionPanel = new GameCadenceSelection();
    }

    @Test
    public void testBlitzButtonSetsCorrectCadence() {
        selectionPanel.blitzButton.doClick();
        assertEquals(5, Board.cadence);
    }

    @Test
    public void testRapidButtonSetsCorrectCadence() {
        selectionPanel.rapidButton.doClick();
        assertEquals(10, Board.cadence);
    }

    @Test
    public void testClassicalButtonSetsCorrectCadence() {
        selectionPanel.classicalButton.doClick();
        assertEquals(30, Board.cadence);
    }

    @Test
    public void testButtonsAreCorrectlyInitialized() {
        assertNotNull(selectionPanel.blitzButton);
        assertNotNull(selectionPanel.rapidButton);
        assertNotNull(selectionPanel.classicalButton);

        assertEquals("Blitz (5 min)", selectionPanel.blitzButton.getText());
        assertEquals("Rapide (10 min)", selectionPanel.rapidButton.getText());
        assertEquals("Classique (30 min)", selectionPanel.classicalButton.getText());
    }
}

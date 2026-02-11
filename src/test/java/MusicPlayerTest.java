import Controller.MusicPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MusicPlayerTest {

    private MusicPlayer musicPlayer;

    @BeforeEach
    public void setUp() {
        musicPlayer = new MusicPlayer();
    }

    @Test
    public void testPlayMusicDoesNotThrow() {
        assertDoesNotThrow(() -> musicPlayer.playMusic());
    }

}

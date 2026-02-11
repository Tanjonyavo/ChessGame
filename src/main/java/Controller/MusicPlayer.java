package Controller;

import javax.sound.sampled.*;
import java.io.InputStream;

public class MusicPlayer {
    private Clip clip;

    public void playMusic() {
        try {
            InputStream file = getClass().getClassLoader().getResourceAsStream("music.wav");
            assert file != null;
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.getMessage();
        }
    }


    public void resumeMusic() {
        if (clip != null) {
            clip.start();
        }
    }
}

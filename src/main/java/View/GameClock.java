package View;

import Model.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameClock extends JPanel {
    public int timeRemaining;
    public JLabel timerLabel;
    private Timer timer;
    public int initialTime;
    public GameClock(int time, boolean isWhite) {
        initialTime = time*60;
        timeRemaining = time * 60;
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));
        setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        timerLabel = new JLabel(formatTime(timeRemaining), JLabel.CENTER);
        timerLabel.setFont(new Font("Serif", Font.BOLD, 50));
        timerLabel.setForeground(new Color(255, 215, 0));
        add(timerLabel, BorderLayout.CENTER);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isWhite == Board.isWhiteToMove){
                    updateClock();
                }
            }
        });
        startClock();
    }

    public void startClock() {
        timer.start();
    }

    public void updateClock() {
        if (timeRemaining > 0) {
            if(!Board.isGameOver) {
                timeRemaining--;
            }
            timerLabel.setText(formatTime(timeRemaining));
        } else {
            timer.stop();
            timerLabel.setText("00:00");
        }
    }

    private String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}

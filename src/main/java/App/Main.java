package App;

import Model.Board;
import View.GameCadenceSelection;
import View.GameClock;
import View.Welcome;
import Controller.MusicPlayer;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static GameClock topClock;
    public static GameClock bottomClock;
    public static MusicPlayer player = new MusicPlayer();
    public static void main(String[] args) {
        player.playMusic();
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Chess Master");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(850, 720);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            Welcome welcomePanel = new Welcome();
            frame.setContentPane(welcomePanel);
            frame.setVisible(true);

            GameCadenceSelection gameCadence = new GameCadenceSelection();

            welcomePanel.startButton.addActionListener(e -> {
                frame.getContentPane().removeAll();

                JPanel boardPanel = new JPanel(new GridBagLayout());
                boardPanel.setBackground(Color.GRAY);
                boardPanel.add(gameCadence);

                frame.setContentPane(boardPanel);
                frame.revalidate();
                frame.repaint();
            });

            gameCadence.blitzButton.addActionListener(e -> startGame(frame, 5));
            gameCadence.rapidButton.addActionListener(e -> startGame(frame, 10));
            gameCadence.classicalButton.addActionListener(e -> startGame(frame, 30));
        });
    }

    private static void startGame(JFrame frame, int time) {
        frame.getContentPane().removeAll();
        Board board = Board.getBoard();
        JPanel clockPanel = new JPanel(new BorderLayout());
        clockPanel.setBackground(Color.DARK_GRAY);
        topClock = new GameClock(time, false);
        bottomClock = new GameClock(time, true);
        clockPanel.add(topClock, BorderLayout.NORTH);
        clockPanel.add(bottomClock, BorderLayout.SOUTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, board, clockPanel);
        splitPane.setDividerSize(0);
        splitPane.setEnabled(false);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(splitPane, BorderLayout.CENTER);

        frame.setContentPane(mainPanel);
        frame.revalidate();
        frame.repaint();
    }
}

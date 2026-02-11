package View;

import App.Main;
import Model.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverWindow extends JFrame {

    public GameOverWindow(String winner) {
        setTitle("Game Over");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        // Message de victoire
        JLabel message;
        if (winner.equalsIgnoreCase("Black") || winner.equalsIgnoreCase("White")) {
            message = new JLabel(winner + " Wins!", SwingConstants.CENTER);
        } else {
            message = new JLabel(winner, SwingConstants.CENTER);
        }
        message.setFont(new Font("Arial", Font.BOLD, 20));
        add(message, BorderLayout.CENTER);


        JButton rematch = new JButton("Rematch");
        rematch.setFont(new Font("Arial", Font.PLAIN, 14));
        rematch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Board board = Board.getBoard();
                board.pieceList.clear();
                board.addPieces();
                board.isWhiteToMove = true;
                board.enPassantTile = -1;
                board.isGameOver = false;
                Main.topClock.timeRemaining = Main.topClock.initialTime + 1;
                Main.bottomClock.timeRemaining = Main.bottomClock.initialTime + 1;
                Main.bottomClock.updateClock();Main.topClock.updateClock();
                board.resetPos();
                board.revalidate();
                board.repaint();
                dispose();
            }
        });

        add(rematch, BorderLayout.SOUTH);

        setVisible(true);
    }
}

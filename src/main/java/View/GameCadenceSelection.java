package View;

import Model.Board;

import javax.swing.*;
import java.awt.*;

public class GameCadenceSelection extends JPanel {
    public JButton blitzButton;
    public JButton rapidButton;
    public JButton classicalButton;

    public GameCadenceSelection() {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Choisissez la cadence", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 50));
        titleLabel.setForeground(new Color(255, 215, 0));
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setOpaque(false);

        blitzButton = createButton("Blitz (5 min)", new Color(255, 69, 0));
        rapidButton = createButton("Rapide (10 min)", new Color(60, 179, 113));
        classicalButton = createButton("Classique (30 min)", new Color(70, 130, 180));

        buttonPanel.add(blitzButton);
        buttonPanel.add(rapidButton);
        buttonPanel.add(classicalButton);

        add(buttonPanel, BorderLayout.CENTER);

        blitzButton.addActionListener(e -> selectCadence(5));
        rapidButton.addActionListener(e -> selectCadence(10));
        classicalButton.addActionListener(e -> selectCadence(30));
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setPreferredSize(new Dimension(250, 60));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void selectCadence(int cadence) {
        Board.cadence = cadence;
    }

}

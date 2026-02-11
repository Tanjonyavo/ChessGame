package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Welcome extends JPanel {

    public JButton startButton;
    private JButton exitButton;
    private BufferedImage backgroundImage;

    public Welcome() {
        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("fond.jpg")));
        } catch (IOException | NullPointerException e) {
            System.err.println("Erreur au niveau de l'image: " + e.getMessage());
        }

        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30)); // Fond sombre élégant
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("♟ Chess Master ♟", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 70));
        titleLabel.setForeground(new Color(255, 215, 0));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(0, 0, 0, 150));
        titleLabel.setBorder(BorderFactory.createLineBorder(new Color(255, 215, 0), 3));
        add(titleLabel, BorderLayout.NORTH);

        JLabel description = new JLabel("<html><div style='text-align: center;'>"
                + "<p style='font-size: 22px;'>"
                + "Bienvenue dans Chess Master! <br>"
                + "</p></div></html>");
        description.setFont(new Font("SansSerif", Font.PLAIN, 24));
        description.setForeground(Color.WHITE);
        description.setHorizontalAlignment(JLabel.CENTER);
        description.setBackground(new Color(0, 0, 0, 150));
        description.setOpaque(true);
        description.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(description, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout());

        startButton = new JButton("Démarrer le jeu");
        styleButton(startButton, new Color(50, 205, 50));

        exitButton = new JButton("Quitter");
        styleButton(exitButton, new Color(220, 20, 60));

        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        startButton.addActionListener(e -> System.out.println("Démarrer la partie..."));
        exitButton.addActionListener(e -> System.exit(0));

        addHoverEffect(startButton);
        addHoverEffect(exitButton);
    }

    private void styleButton(JButton button, Color color) {
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setPreferredSize(new Dimension(250, 60));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void addHoverEffect(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(button.getBackground().darker());
                button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(button.getBackground().brighter());
                button.setBorder(BorderFactory.createRaisedBevelBorder());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            System.err.println("L'image de fond est nulle.");
        }
    }
}

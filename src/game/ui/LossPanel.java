package game.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import game.utils.Utils;

/**
 * A {@link JPanel} that displays the "Game Over" screen.
 *
 * <p>
 * This panel is shown by the {@link game.engine.GameEngine} when
 * the player's health reaches zero. It displays a "GAME OVER" message
 * and an "Exit" button to close the game.
 * </p>
 */
public class LossPanel extends JPanel {

    /**
     * Creates the "Game Over" loss panel.
     *
     * <p>
     * This constructor sets up all the visual components, including
     * labels for the loss message and a button to exit the application.
     * </p>
     *
     * @param parentFrame - the main application window ({@link JFrame})
     */
    public LossPanel(JFrame parentFrame) {
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.BLACK);

        JLabel mainLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
        mainLabel.setFont(new Font("Noto Sans", Font.BOLD, 48));
        mainLabel.setForeground(new Color(220, 38, 38));
        mainLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(mainLabel);

        contentPanel.add(Box.createVerticalStrut(20));

        JLabel lowerLabel = new JLabel("You've been defeated by nasty linux operating systems.", SwingConstants.CENTER);
        lowerLabel.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        lowerLabel.setForeground(Color.WHITE);
        lowerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(lowerLabel);

        contentPanel.add(Box.createVerticalStrut(20));

        JLabel humoristicTextLabel = new JLabel(Utils.getRandomLossMessage());
        humoristicTextLabel.setFont(new Font("Noto Sans", Font.ITALIC, 16));
        humoristicTextLabel.setForeground(Color.YELLOW);
        humoristicTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(humoristicTextLabel);

        contentPanel.add(Box.createVerticalStrut(40));

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Noto Sans", Font.BOLD, 18));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(new Color(220, 38, 38));
        exitButton.setFocusPainted(false);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        contentPanel.add(exitButton);

        add(contentPanel, new GridBagConstraints());
    }

}

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

import game.engine.GameEngine;
import game.utils.Utils;

public class LossPanel extends JPanel {

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

        JButton restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Noto Sans", Font.BOLD, 18));
        restartButton.setForeground(Color.WHITE);
        restartButton.setBackground(new Color(220, 38, 38));
        restartButton.setFocusPainted(false);
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        restartButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        restartButton.addActionListener(e -> {
            parentFrame.getContentPane().removeAll();
            GameEngine newEngine = new GameEngine((GameFrame) parentFrame);
            newEngine.start();
            parentFrame.revalidate();
            parentFrame.repaint();
        });

        contentPanel.add(restartButton);

        add(contentPanel, new GridBagConstraints());
    }
    
}

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

public class WinPanel extends JPanel {

    public WinPanel(JFrame parentFrame) {
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.BLACK);

        JLabel mainLabel = new JLabel("CONGRATS! YOU WIN!", SwingConstants.CENTER);
        mainLabel.setFont(new Font("Noto Sans", Font.BOLD, 48));
        mainLabel.setForeground(new Color(34, 197, 94));
        mainLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(mainLabel);

        contentPanel.add(Box.createVerticalStrut(20));

        JLabel lowerLabel = new JLabel("You've reached 300 point. Maybe one more round?", SwingConstants.CENTER);
        lowerLabel.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        lowerLabel.setForeground(Color.WHITE);
        lowerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(lowerLabel);

        contentPanel.add(Box.createVerticalStrut(20));

        JLabel humoristicTextLabel = new JLabel(Utils.getRandomWinMessage());
        humoristicTextLabel.setFont(new Font("Noto Sans", Font.ITALIC, 16));
        humoristicTextLabel.setForeground(Color.YELLOW);
        humoristicTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(humoristicTextLabel);

        contentPanel.add(Box.createVerticalStrut(40));

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Noto Sans", Font.BOLD, 18));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(new Color(34, 197, 94));
        exitButton.setFocusPainted(false);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        contentPanel.add(exitButton);

        add(contentPanel, new GridBagConstraints());
        contentPanel.setBounds(0, 0, 900, 600);
    }

}

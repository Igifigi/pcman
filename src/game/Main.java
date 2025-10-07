package game;

import javax.swing.SwingUtilities;

import game.engine.GameEngine;
import game.ui.GameFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameFrame gameFrame = new GameFrame();
            GameEngine gameEngine = new GameEngine(gameFrame);
            gameEngine.start();
            
        });

        System.out.println("Test");
    }
}

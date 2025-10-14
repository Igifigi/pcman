package game.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.entities.Player;

public class KeyboardManager implements KeyListener {
    Player player = Player.getInstance();

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            player.setDesiredMovement(0, -1);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            player.setDesiredMovement(0, 1);
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            player.setDesiredMovement(-1, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            player.setDesiredMovement(1, 0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // do nothing
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

}

package game.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.entities.Player;

public class KeyboardManager implements KeyListener {
    Player player = Player.getInstance();

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            player.setDesiredMovement(Direction.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            player.setDesiredMovement(Direction.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            player.setDesiredMovement(Direction.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            player.setDesiredMovement(Direction.RIGHT);
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

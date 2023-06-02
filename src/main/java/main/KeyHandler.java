package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, rPressed, enterPressed, pPressed;

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		switch (code) {
		case (KeyEvent.VK_W):
			upPressed = true;

		case (KeyEvent.VK_S):
			downPressed = true;

		case (KeyEvent.VK_A):
			leftPressed = true;

		case (KeyEvent.VK_D):
			rightPressed = true;

		case (KeyEvent.VK_SPACE):
			spacePressed = true;

		case (KeyEvent.VK_R):
			rPressed = true;

		case (KeyEvent.VK_ENTER):
			enterPressed = true;

		case (KeyEvent.VK_P):
			pPressed = true;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		switch (code) {
		case (KeyEvent.VK_W):
			upPressed = false;

		case (KeyEvent.VK_S):
			downPressed = false;

		case (KeyEvent.VK_A):
			leftPressed = false;

		case (KeyEvent.VK_D):
			rightPressed = false;

		case (KeyEvent.VK_SPACE):
			spacePressed = false;

		case (KeyEvent.VK_R):
			rPressed = false;

		case (KeyEvent.VK_ENTER):
			enterPressed = false;

		case (KeyEvent.VK_P):
			pPressed = false;

		}
	}

}

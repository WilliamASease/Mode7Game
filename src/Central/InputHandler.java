package Central;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Macros.*;

public class InputHandler implements KeyListener {
	boolean[] KEYS = new boolean[KeyCodes.NUMBER_OF_MACROS];
	int[] keyCodes = setKeyCodes();
	Data db;
	public InputHandler(Data db) {
		this.db = db;
		this.db.mainFrame.addKeyListener(this); }
	
	public void update()
	{
		for (int i = 0; i < db.keys.length; i++)
			db.keys[i] = KEYS[i];
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		try { KEYS[keyCodes[e.getKeyCode()]] = true; } catch(Exception h) {}
	}

	public void keyReleased(KeyEvent e) {
		try { KEYS[keyCodes[e.getKeyCode()]] = false; } catch(Exception h) {}
	}

	int[] setKeyCodes() {
		int[] out = new int[1000];
		for (int i = 0; i < out.length; i++) out[i] = -1;
		//Change Key Bindings here!!!
		out[KeyEvent.VK_A] = KeyCodes.SHIFT_LEFT;
		out[KeyEvent.VK_D] = KeyCodes.SHIFT_RIGHT;
		out[KeyEvent.VK_W] = KeyCodes.SHIFT_UP;
		out[KeyEvent.VK_S] = KeyCodes.SHIFT_DOWN;
		out[KeyEvent.VK_LEFT] = KeyCodes.ROTATE_LEFT;
		out[KeyEvent.VK_UP] = KeyCodes.ROTATE_UP;
		out[KeyEvent.VK_DOWN] = KeyCodes.ROTATE_DOWN;
		out[KeyEvent.VK_RIGHT] = KeyCodes.ROTATE_RIGHT;
		out[KeyEvent.VK_ENTER] = KeyCodes.SUBMIT;
		out[KeyEvent.VK_SPACE] = KeyCodes.SUBMIT;
		
		out[KeyEvent.VK_NUMPAD1] = KeyCodes.DEV_FUNC_ONE;
		out[KeyEvent.VK_NUMPAD2] = KeyCodes.DEV_FUNC_TWO;
		out[KeyEvent.VK_NUMPAD3] = KeyCodes.DEV_FUNC_THREE;
		///
		return out;
	}
}

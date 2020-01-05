package Frames;

import java.awt.Color;

import Central.Data;
import Objects.ColorMap;

public class GameOverFrame extends FrameInstruction {

	public ColorMap mp = db.mcmb.grab("overworld");
	public ColorMap bg = db.mcmb.grab("background");

	public GameOverFrame(Data db) {
		super(db);
	}

	public void render() {
		if (!db.gameover.ti.done)
			for (int i = 0; i < 600; i++)
				for (int j = 0; j < 450; j++) {
					int[] coords = Mathematics.Mode7.rotateSimpleZoom(i, j + 300, db.overWorld.ti.px,
							db.overWorld.ti.py, db.gameover.ti.rot, db.gameover.ti.expand, db.sin, db.cos);
					Color c = mp.sample(coords[0], coords[1]);
					if (c == null)
						c = bg.sample(i, j);
					db.sprts.paintPixel(i, j, c);
				}
		else {
			db.sprts.rect(0, 0, 600, 450, Color.BLACK);
			db.sprts.fillTextBox(db.mcmb.grab("textbox"), new String[] { "", "Game Over.", "" });
		}
	}
}

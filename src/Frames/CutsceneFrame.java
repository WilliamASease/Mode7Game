package Frames;

import Central.Data;

public class CutsceneFrame extends FrameInstruction
{

	public CutsceneFrame(Data db) { super(db); }

	public void render() 
	{
		db.sprts.placeSpriteNaive(0, 0, db.mcmb.grab("battle"), false);
		if (db.cutscene.ti.naive) db.sprts.placeSpriteNaive(0, 0, db.cutscene.ti.activeSpr, false);
		else db.sprts.placeSpriteNaive(300, 250, db.cutscene.ti.activeSpr, true);
		db.sprts.fillTextBox(db.mcmb.grab("textbox"), db.cutscene.ti.txt);
	}

}

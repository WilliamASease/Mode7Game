package Frames;

//import java.awt.Color;

import Central.*;
import Entities.Party.*;
import Macros.SpellBook;
import Objects.ColorMap;

public class BattleFrame extends FrameInstruction
{
	ColorMap bg = db.mcmb.grab("battle");
	ColorMap txtbx = db.mcmb.grab("textbox");
	ColorMap prtybx = db.mcmb.grab("partybox");
	ColorMap splbx = db.mcmb.grab("spellbox");
	ColorMap arrow = db.mcmb.grab("arrow");
	ColorMap cross = db.mcmb.grab("cross");
	int[] penPos = {30};
	int[] pyPos = {40};
	int[] enPos = new int[] { 300, 250, 350, 200, 400, 150, 450, 100, 500, 50, 550};
	public BattleFrame(Data db) { super(db); }
	
	public void render()
	{
		db.sprts.placeSpriteNaive(0, 0, bg, false);
		for (int i = 0; i < db.battle.ti.opp.size(); i++) db.sprts.placeSprite(enPos[i], 250, db.battle.ti.opp.get(i).sprite, true);
		db.sprts.fillTextBox(txtbx, db.battle.ti.battleText);
		for (int i = 0; i < db.battle.ti.frnd.members.size(); i++) db.sprts.fillPartyBox(i, db.battle.ti.frnd.members.get(i), prtybx);
		if (db.battle.ti.battleState == 1 && db.battle.ti.active instanceof PartyMember) spellSelectRender();
		paintTarget(db.battle.ti.activeEntity, 50, arrow);
	}
	
	public void spellSelectRender()
	{
		db.sprts.placeSpriteNaive(0, 150, splbx, false);
		int y = 0;
		for (int i = db.battle.ti.spellSelect - 2; i <= db.battle.ti.spellSelect + 2; i++)
		{
			if (i < 0 || i >= db.battle.ti.active.availableSpells.length) { y++; continue; }
			db.sprts.stringOut(SpellBook.SPELLNAMES[db.battle.ti.active.availableSpells[i]], 5, 160 + 20 * y);
			y++;
		}
		paintTarget(db.battle.ti.targetSelect, 0, cross);
	}
	
	public void paintTarget(int what, int yOffset, ColorMap cm)
	{
		if (what < db.battle.ti.frnd.members.size()) db.sprts.placeSprite(penPos[what], pyPos[what] + yOffset, cm, true);
		else db.sprts.placeSprite(enPos[what - db.battle.ti.frnd.members.size()], 250 + yOffset, cm, true);
	}
}

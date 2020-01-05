package Ticks;

import java.util.ArrayList;

import Central.Data;
import Entities.Entity;
import Entities.Enemies.Enemy;
import Entities.Enemies.EnemyBag;
import Entities.Party.Party;
import Macros.KeyCodes;
//import Macros.SpellBook;

public class BattleTick extends TickInstruction
{
	public int battleState = 0; //0 = Selecting Move. 1 = Executing Move. 2 = Reporting Effects. 3 = Victory. 4 = Defeat.
	public int activeEntity = 0;
	public Entity active;
	public EnemyBag eBag = new EnemyBag(db);
	public ArrayList<Enemy> opp = new ArrayList<Enemy>();
	public Party frnd = new Party(db);
	public ArrayList<Entity> all = new ArrayList<Entity>();
	public static BattleTick bt;
	public static BattleTick getInstance() { return bt; }
	public BattleTick(Data data) { super(data); bt = this;}
	
	public int xpPayout = 0;
	public String[] battleText = new String[3];
	public int[] avSpells;
	public int spellSelect = 0;
	public int targetSelect = 0;
	public boolean latch = false;
	public int buffer = 0;

	public void tick() 
	{
		if (buffer > 0) {buffer--; return;}
		if (activeEntity >= all.size()) activeEntity = 0;
		active = all.get(activeEntity);
		processInput();
		if (latch) { return; }
		switch(battleState) {
			case 0: { spellSel(); break; }
			case 1: { executeMove(); break; }
			case 2: { reportEffects(); break; }
			case 3: { victory(); break; }
			case 4: { defeat(); break;}
			case 5: { backToWorld(); break; }
		}
	}
	
	public void begin(String data)
	{
		opp.clear();
		if (!(data == null)) opp.add(eBag.fetch(data));
		else opp.add(eBag.fetch(frnd.mc.lvl));
		for (int i = 0; i < opp.size(); i++) xpPayout += opp.get(i).xp;
		all.clear();
		all.addAll(frnd.members);
		all.addAll(opp);
		activeEntity = 0;
		battleState = 0;
		battleText[0] = "Enemy x" + opp.size() + "!!!";;
		battleText[1] = "";
		battleText[2] = "";
	}
	
	public void buffer(int frames) { buffer = frames; }
	public void latch() { buffer = 30; latch = true; }
	
	public void spellSel()
	{
		battleState++;
		if (active instanceof Enemy) { active.simulateTurn(active, all); return; }
		latch();
	}
	
	public void executeMove()
	{
		battleState++;
		if (!(active instanceof Enemy)) active.cast(all.get(targetSelect), active.availableSpells[spellSelect]);
		battleText[1] = active.action;
		latch();
	}
	
	public void reportEffects()
	{
		battleState = 0;
		latch();
		battleText[1] = active.result;
		activeEntity++;
		if(opp.size() < 1) battleState = 3;
		if(!frnd.members.contains(frnd.mc)) battleState = 4;
	}
	
	public void victory()
	{
		latch();
		battleState = 5;
		battleText[0] = "This battle is Won!";
		battleText[1] = "Gained " + xpPayout + " XP! ";
		for (int i = 0; i < frnd.members.size(); i++) if (frnd.members.get(i).payXP(xpPayout)) battleText[2] = battleText[2] + " " + frnd.members.get(i).name + " level up!";
	}
	
	public void defeat()
	{
		db.transition.ti.transitionTo(db.gameover, null, db.transition.ti.SLOW);
	}
	
	public void backToWorld() { db.transition.ti.transitionTo(db.overWorld, null, db.transition.ti.FAST); } 
	
	public void processInput()
	{
		if (db.keys[KeyCodes.SHIFT_LEFT] || db.keys[KeyCodes.ROTATE_LEFT])  {targetSelect--; buffer(7);}
		if (db.keys[KeyCodes.SHIFT_RIGHT] || db.keys[KeyCodes.ROTATE_RIGHT]) {targetSelect++; buffer(7);}
		if (db.keys[KeyCodes.SHIFT_UP] || db.keys[KeyCodes.ROTATE_UP]) {spellSelect--; buffer(7);}
		if (db.keys[KeyCodes.SHIFT_DOWN] || db.keys[KeyCodes.ROTATE_DOWN]) {spellSelect++; buffer(7);}
		if (targetSelect < 0 || targetSelect >= all.size()) targetSelect = 0;
		if (spellSelect < 0 || spellSelect >= active.availableSpells.length) spellSelect = 0;
		if (db.keys[KeyCodes.SUBMIT]) latch = false;
		//if (battleState == 0 && !(active instanceof Enemy)) battleText[2] = SpellBook.SPELLDESCS[spellSelect];
	}
	
	public void kill(Entity tgt)
	{
		if (all.contains(tgt)) all.remove(tgt);
		if (opp.contains(tgt)) opp.remove(tgt);
		if (frnd.members.contains(tgt)) frnd.members.remove(tgt);
	}
}

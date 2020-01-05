package Ticks;

import Central.Data;
import DevTools.DataReader;
import Macros.KeyCodes;
import Objects.ColorMap;

public class CutsceneTick extends TickInstruction
{

	public CutsceneTick(Data db) {super(db);}
	public ColorMap activeSpr;
	public boolean naive;
	public String[] inst;
	public String[] txt = new String[3];
	int pointer;
	boolean latch;
	int buffer;

	public void tick() 
	{
		if (buffer > 0) { buffer--; return; }
		if (db.keys[KeyCodes.SUBMIT]) latch = false;
		while(!latch) { process(inst[pointer]); pointer++; }
	}
	
	public void process(String in)
	{
		String[] spl = in.split(" ");
		switch(spl[0])
		{
		case "LDSPR": loadSpr(spl[1], spl[2]); break;
		case "TEXT": text(); break;
		case "LATCH": latch(); break;
		case "MAP": map(); break;
		case "BOSS": boss(spl[1]); break;
		}
	}
	
	public void loadSpr(String in, String val) { activeSpr = db.mcmb.grab(in); if (val.equals("TRUE")) naive = true; else naive = false;}
	
	public void text()
	{
		txt[0] =inst[pointer + 1];
		txt[1] =inst[pointer + 2];
		txt[2] =inst[pointer + 3];
	}
	
	public void latch() {latch = true; buffer = 30;}
	
	public void map()
	{
		pointer = 0;
		latch = true;
		db.transition.ti.transitionTo(db.overWorld, "", db.transition.ti.MED);
	}
	
	public void boss(String in)
	{
		pointer = 0;
		latch = true;
		db.transition.ti.transitionTo(db.battle, in, db.transition.ti.SLOW);
	}
	
	public void begin(String scene)
	{
		inst = DataReader.readRawText("scenes/"+scene);
		pointer = 0;
		txt = new String[3];
		latch = false;
		buffer = 0;
	}
}

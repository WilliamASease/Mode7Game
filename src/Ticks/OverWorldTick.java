package Ticks;

import Central.Data;
import Macros.KeyCodes;

public class OverWorldTick extends TickInstruction
{
	public int steps;
	public double tpxs;
	public double tpys;
	public double pxs = 100.0; // x subpixel.
	public double pys = 100.0; // y subpixel.
	public int px;
	public int py;
	public int rot = 90001;
	public int scalar = 1;
	public boolean[][] walls;
	
	public OverWorldTick(Data db) 
	{
		super(db);
	}
	
	public void tick()
	{
		if (walls == null) walls = db.overWorld.fi.mp.getReferences(0,3);
		tpxs = pxs;
		tpys = pys;
		if(db.keys[KeyCodes.SHIFT_DOWN]) transformDown();
		if(db.keys[KeyCodes.SHIFT_UP]) transformUp();
		if(db.keys[KeyCodes.SHIFT_LEFT]) shiftLeft();
		if(db.keys[KeyCodes.SHIFT_RIGHT]) shiftRight();
		if(db.keys[KeyCodes.ROTATE_LEFT]) rotateLeft();
		if(db.keys[KeyCodes.ROTATE_RIGHT]) rotateRight();
		if(db.keys[KeyCodes.ROTATE_UP]) rotateUp();
		if(db.keys[KeyCodes.ROTATE_DOWN]) rotateDown();
		repairCollision();
		px = (int)pxs;
		py = (int)pys;
	}
	
	public void transformUp()
	{
		pxs -= 3 * db.sin[rot];
		pys -= 3 * db.cos[rot];
		steps++;
		rollForBattle();
	}	
	public void transformDown()
	{
		int rotT = rot;
		rot = (rot + 180000) % 360000;
		transformUp();
		rot = rotT;
	}	
	public void shiftLeft()
	{
		int rotT = rot;
		rot = (rot + 90000) % 360000;
		transformUp();
		rot = rotT;
	}
	public void shiftRight()
	{
		int rotT = rot;
		rot = (rot + 270000) % 360000;
		transformUp();
		rot = rotT;
	}
	public void rotateLeft()
	{
		rot = rot + 700;
		if (rot >= db.sin.length - 1) rot = 0;
	}	
	public void rotateRight()
	{
		rot = rot - 700;
		if (rot <= 1) rot = db.sin.length - 2;
	}
	public void rotateUp()
	{
		scalar = scalar + 1;
		if (scalar >= 300) scalar = 300;
	}
	public void rotateDown()
	{
		scalar = scalar - 1;
		if (scalar < 1) scalar = 1;
	}

	public void repairCollision()
	{
		if (pys > db.overWorld.fi.mp.getHeight() || pxs > db.overWorld.fi.mp.getLength() 
				|| pys < 0 || pxs < 0 || walls[(int)pxs][(int)pys])
		{
			pxs = tpxs;
			pys = tpys;
		}
		
	}
	
	public void rollForBattle()
	{
		if (db.dice.nextInt(900 - steps) == 0)
		{
			steps = 0;
			db.transition.ti.transitionTo(db.battle, null, 1);
		}
	}
	
	public void begin(String in) {}
}

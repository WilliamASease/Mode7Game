package Frames;

import java.awt.Color;
import java.util.LinkedList;

import Central.*;
import Macros.OVData;
import Objects.*;

public class OverWorldFrame extends FrameInstruction
{
	public ColorMap mp = db.mcmb.grab("overworld");
	public ColorMap bg = db.mcmb.grab("background");
	public ColorMap protag = db.mcmb.grab("protag");
	boolean[][] spriteExists = new boolean[mp.getLength()][mp.getHeight()];
	LinkedList<QueuedSprite> spriteQ = new LinkedList<QueuedSprite>();
	
	public OverWorldFrame(Data db) { super(db); }

	public void render() 
	{
		for (int i = 0; i < 600; i++)
			for (int j = 0; j < 450; j++)
			{
				int[] coords = Mathematics.Mode7.rotateSimple(i, j + 100, db.overWorld.ti.px, db.overWorld.ti.py, db.overWorld.ti.rot, db.overWorld.ti.scalar, db.sin, db.cos);
				Color c = mp.sample(coords[0], coords[1]);
				if (c == null) c = bg.sample(i, j);
				db.sprts.paintPixel(i, j, c);
				if (coords[0] == db.overWorld.ti.px && coords[1] == db.overWorld.ti.py) queueSprite(i, j, protag);
			}
		for (BoardEntity b : OVData.spriteMaster)
		{
			int[] revCoords = Mathematics.Mode7.rotateInverse(b.x, b.y, db.overWorld.ti.px, db.overWorld.ti.py, db.overWorld.ti.rot, db.overWorld.ti.scalar, db.sin, db.cos);
			revCoords[1] -= 100;
			if (revCoords[0] >= 0 && revCoords[0] < 600 && revCoords[1] >= 0 && revCoords[1] < 450) queueSprite(revCoords[0], revCoords[1], db.mcmb.grab(b.txt));
		}
		printAllSprites();
	}
	
	public void queueSprite(int x, int y, ColorMap spr) {spriteQ.addFirst(new QueuedSprite(x, y, spr));}
	
	public void printAllSprites()
	{
		for (QueuedSprite e : spriteQ)
			db.sprts.placeSprite(e.x, e.y, e.spr, true);
		spriteQ.clear();
	}
}
